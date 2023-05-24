package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.codecool.stackoverflowtw.controller.dto.QuestionDetailsDTO;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialize_tables.TableInitializer;
import com.codecool.stackoverflowtw.initialize_tables.TableStatements;

import java.sql.*;
import java.time.LocalDateTime;
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
        String query = "SELECT question.id, question.title, question.description, question.date, COUNT(answer.id) as answer_count FROM question\n" +
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
                LocalDateTime dateAndTime = resultSet.getTimestamp("date").toLocalDateTime();
                int answerCount = resultSet.getInt("answer_count");
                QuestionDTO question = new QuestionDTO(id, title, description, dateAndTime, answerCount);
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
    public List<QuestionDetailsDTO> getQuestionById(int questionId) {
        String query = "SELECT question.id, question.title, question.description, question.date, answer.answer as answer, answer.date as answer_date FROM question\n" +
                "LEFT JOIN answer ON answer.question_id = question.id\n" +
                "WHERE question.id =" + questionId;
        try (Connection connection = database.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<QuestionDetailsDTO> questions = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime dateAndTime = resultSet.getTimestamp("date").toLocalDateTime();
                String answer = resultSet.getString("answer");
                LocalDateTime answerDate = resultSet.getTimestamp("answer_date").toLocalDateTime();
                QuestionDetailsDTO question = new QuestionDetailsDTO(id, title, description, dateAndTime, answer, answerDate);
                questions.add(question);
            }

            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addNewQuestion(NewQuestionDTO question) {
        String query = "INSERT INTO question (title, description, date)" +
                "VALUES (?, ?, ?)";
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            prepare(question.title(),statement);
            statement.executeUpdate();
        }
        catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return 0;
    }

    private void prepare(String title, PreparedStatement statement) throws SQLException {
        statement.setString(1, title);
        statement.setString(2, null);
        statement.setDate(3, Date.valueOf(LocalDateTime.now().toLocalDate()));
    }

    @Override
    public Boolean deleteQuestion(int questionId) {
        String query = "DELETE FROM question WHERE id =" + questionId;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
