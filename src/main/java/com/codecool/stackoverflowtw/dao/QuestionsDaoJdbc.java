package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialize_tables.TableInitializer;
import com.codecool.stackoverflowtw.initialize_tables.TableStatements;

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
        return null;
    }

    @Override
    public QuestionDTO getQuestionById(int id) {
        return null;
    }

    @Override
    public int addNewQuestion() {
        return 0;
    }

    @Override
    public Boolean deleteQuestion(int id) {
        return null;
    }
}
