package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.*;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.database.Database;

import java.util.List;

public interface AnswerDAO {

    List<AnswerDTO> getAllAnswersForQuestion(int questionId);

    int addNewAnswer(int questionId, NewAnswerDTO answer);

    Boolean deleteAnswer(int answerId);

    int editAnswer(int answerId, NewAnswerDTO answer);
}
