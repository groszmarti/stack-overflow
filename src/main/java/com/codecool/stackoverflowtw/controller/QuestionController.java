package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDetailsDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{questionId}")
    public List<QuestionDetailsDTO> getQuestionById(@PathVariable int questionId) {
        return questionService.getQuestionById(questionId);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        questionService.addNewQuestion(question);
        return 0;
    }

    @PatchMapping("/{questionId}")
    public int editQuestion(@PathVariable int questionId, @RequestBody NewQuestionDTO question) {
        questionService.editQuestion(questionId, question);
        return 1;
    }


    @DeleteMapping("/{questionId}")
    public boolean deleteQuestionById(@PathVariable int questionId) {
        questionService.deleteQuestionById(questionId);
        return true;
    }
}
