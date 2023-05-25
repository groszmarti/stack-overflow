package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswerDAO;
import com.codecool.stackoverflowtw.dao.AnswerDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        QuestionsDAO questionDaoJdbc = new QuestionsDaoJdbc(createDataBase());
        questionDaoJdbc.initializeTables();
        return questionDaoJdbc;
    }

    @Bean
    public AnswerDAO answerDAO() {
        return new AnswerDaoJdbc(createDataBase());
    }

    public Database createDataBase() {
        return new Database(
                "jdbc:postgresql://localhost:5432/stackoverflow",
                "postgres",
                "postgres");
    }
}
