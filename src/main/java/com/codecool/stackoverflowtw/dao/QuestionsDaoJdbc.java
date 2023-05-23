package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;

import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
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
