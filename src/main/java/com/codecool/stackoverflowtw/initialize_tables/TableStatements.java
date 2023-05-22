package com.codecool.stackoverflowtw.initialize_tables;

public interface TableStatements {
    String QUESTION = "CREATE TABLE question (id SERIAL PRIMARY KEY, title character varying, description character varying, date date);";
}
