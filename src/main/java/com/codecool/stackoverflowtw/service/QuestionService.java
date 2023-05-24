package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.QuestionDetailsDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<QuestionDetailsDTO> getQuestionById(int id) {
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


}
