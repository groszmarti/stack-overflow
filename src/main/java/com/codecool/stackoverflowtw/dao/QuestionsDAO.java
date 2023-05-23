package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    void initializeTables();

    void sayHi();
    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestionById(int id);
    int addNewQuestion();
    Boolean deleteQuestion(int id);
}
