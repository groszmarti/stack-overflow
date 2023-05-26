package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.dao.AnswerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    public AnswerService(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    public List<AnswerDTO> getAllAnswersForQuestion(int questionId) {
        return answerDAO.getAllAnswersForQuestion(questionId);
    }


    public int addNewAnswer(int questionId, NewAnswerDTO answer) {
        answerDAO.addNewAnswer(questionId, answer);
        return 1;
    }

    public boolean deleteAnswer(int answerId) {
        return answerDAO.deleteAnswer(answerId);
    }

    public int editAnswer(int answerId, NewAnswerDTO answer) {
        answerDAO.editAnswer(answerId, answer);
        return 1;
    }
}
