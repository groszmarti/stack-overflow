package com.codecool.stackoverflowtw.initialize_tables;

public interface TableStatements {
    String QUESTION = "CREATE TABLE question (id SERIAL PRIMARY KEY, title character varying, description character varying, date date, time time);";

    String ANSWER = "CREATE TABLE answer (id SERIAL PRIMARY KEY, question_id integer, answer character varying, date date, time time);";
}
