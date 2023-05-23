package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.database.Database;

import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialize_tables.TableInitializer;
import com.codecool.stackoverflowtw.initialize_tables.TableStatements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private Database dataBase;

    public QuestionsDaoJdbc(Database dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        String query = "SELECT question.id as id, question.title as question FROM question\n" +
                "ORDER BY question.date DESC";
        try (Connection connection = dataBase.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<QuestionDTO> questions = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(question.id);
                String title = resultSet.getString(question.title);
                String description = resultSet.getString(question.description);
                LocalDateTime dateAndTime = resultSet.getDate(question.created);
                QuestionDTO question = new QuestionDTO(id, title, description,dateAndTime);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public QuestionDTO getQuestionById(int id) {
        return null;
    }

    @Override
    public int addNewQuestion(NewQuestionDTO question) {
        return 0;
    }

    @Override
    public Boolean deleteQuestion(int id) {
        return null;
    }
}
