package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record  QuestionDetailsDTO(int id, String title, String description, LocalDateTime created, int answerId, String answer, LocalDateTime answerDate) {}