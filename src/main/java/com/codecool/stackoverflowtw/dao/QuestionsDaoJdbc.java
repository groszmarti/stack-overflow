package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialize_tables.TableInitializer;
import com.codecool.stackoverflowtw.initialize_tables.TableStatements;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuestionsDaoJdbc implements QuestionsDAO {

    private Database database;
    private Map<String, String> tables;

    public QuestionsDaoJdbc(Database database) {
        this.database = database;
        this.tables = Map.of(
                "question", TableStatements.QUESTION,
                "answer", TableStatements.ANSWER
        );
    }

    @Override
    public void initializeTables() {
        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();
    }


    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        String query = "SELECT question.id, question.title, question.description, question.date, question.time, COUNT(answer.id) as answer_count FROM question\n" +
                "LEFT JOIN answer ON answer.question_id = question.id\n" +
                "GROUP BY question.id\n" +
                "ORDER BY question.date DESC";
        try (Connection connection = database.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<QuestionDTO> questions = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate questionDate = resultSet.getTimestamp("date").toLocalDateTime().toLocalDate();
                LocalTime questionTime = resultSet.getTimestamp("time").toLocalDateTime().toLocalTime();
                int answerCount = resultSet.getInt("answer_count");
                QuestionDTO question = new QuestionDTO(id, title, description, questionDate, questionTime, answerCount);
                questions.add(question);
            }
            for (QuestionDTO question : questions) {
                System.out.println(question);
            }
            System.out.println(questions);
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QuestionDTO getQuestionById(int questionId) {
        String query = "SELECT question.*, COUNT(answer.id) as answer_count FROM question\n" +
                "LEFT JOIN answer ON answer.question_id = question.id\n" +
                "WHERE question.id = ?\n" +
                "GROUP BY question.id";
        try (Connection connection = database.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDate questionDate = resultSet.getTimestamp("date").toLocalDateTime().toLocalDate();
                LocalTime questionTime = resultSet.getTimestamp("time").toLocalDateTime().toLocalTime();
                int answerCount = resultSet.getInt("answer_count");

                QuestionDTO question = new QuestionDTO(id, title, description, questionDate, questionTime, answerCount);
                return question;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int addNewQuestion(NewQuestionDTO question) {
        String query = "INSERT INTO question (title, description, date, time)" +
                "VALUES (?, ?, ?, ?)";
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            prepareQuestion(question.title(), question.description(), statement);
            statement.executeUpdate();
        }
        catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return 1;
    }

    private void prepareQuestion(String title, String description, PreparedStatement statement) throws SQLException {
        statement.setString(1, title);
        statement.setString(2, description);
        statement.setDate(3, Date.valueOf(LocalDateTime.now().toLocalDate()));
        statement.setTime(4, Time.valueOf(LocalDateTime.now().toLocalTime()));
    }

    @Override
    public Boolean deleteQuestion(int questionId) {
        String query = "DELETE FROM question WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, questionId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void editQuestion(int questionId, NewQuestionDTO question) {
        String query = "UPDATE question\n" +
                "SET title = ?, description = ?\n" +
                "WHERE question.id = ?";
        try (Connection connection = database.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, question.title());
            statement.setString(2, question.description());
            statement.setInt(3, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
