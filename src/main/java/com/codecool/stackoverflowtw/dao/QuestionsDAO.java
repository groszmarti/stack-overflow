package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.database.Database;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    List<QuestionDTO> getAllQuestions();
    QuestionDTO getQuestionById(int id);
    int addNewQuestion(NewQuestionDTO question);
    Boolean deleteQuestion(int id);
}
