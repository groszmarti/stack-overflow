package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.codecool.stackoverflowtw.dao.model.Question;
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
        this.tables = Map.of("question", TableStatements.QUESTION);
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
        String query = "SELECT * FROM question\n" +
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
                QuestionDTO question = new QuestionDTO(id, title, description, dateAndTime);
                questions.add(question);
            }
            for (QuestionDTO question : questions) {
                System.out.println(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QuestionDTO getQuestionById(int questionId) {
        String query = "SELECT * FROM question WHERE id = ?";
        try (Connection connection = database.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            QuestionDTO question = null;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                LocalDateTime dateAndTime = resultSet.getTimestamp("date").toLocalDateTime();
                question = new QuestionDTO(id, title, description, dateAndTime);
            }

            return question;
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
