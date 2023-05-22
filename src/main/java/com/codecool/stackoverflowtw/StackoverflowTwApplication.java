package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialize_tables.TableInitializer;
import com.codecool.stackoverflowtw.initialize_tables.TableStatements;
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
        return new QuestionsDaoJdbc();
    }

    @Bean
    public void loadDataBase() {
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/stackoverflow",
                "postgres",
                "postgres");
        Map<String, String> tables = Map.of(
                "question", TableStatements.QUESTION
        );
        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();
    }
}
