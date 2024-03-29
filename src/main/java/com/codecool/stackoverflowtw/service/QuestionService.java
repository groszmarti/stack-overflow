package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        return  questionsDAO.getAllQuestions();
    }

    public QuestionDTO getQuestionById(int id) {
        // TODO
        return questionsDAO.getQuestionById(id);
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestion(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        questionsDAO.addNewQuestion(question);
        return createdId;
    }


    public int editQuestion(int questionId, NewQuestionDTO question) {
        questionsDAO.editQuestion(questionId, question);
        return 1;
    }
}
