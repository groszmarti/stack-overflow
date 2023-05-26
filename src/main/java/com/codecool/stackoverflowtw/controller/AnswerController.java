package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{questionId}/answers")
    public List<AnswerDTO> getAllAnswersForQuestion(@PathVariable int questionId) {
        return answerService.getAllAnswersForQuestion(questionId);
    }


    @PostMapping("/{questionId}")
    public int addAnswerToQuestion(@PathVariable int questionId, @RequestBody NewAnswerDTO answer) {
        answerService.addNewAnswer(questionId, answer);
        return 0;
    }
    @PatchMapping("/{questionId}/{answerIs}")
    public int editAnswer(@PathVariable int answerId, @RequestBody NewAnswerDTO answer) {
        answerService.editAnswer(answerId, answer);
        return 1;
    }

    @DeleteMapping("/{questionId}/{answerId}")
    public boolean deleteAnswer(@PathVariable int answerId) {
        answerService.deleteAnswer(answerId);
        return true;
    }
}