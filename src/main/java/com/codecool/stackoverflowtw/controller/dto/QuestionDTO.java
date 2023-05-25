package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record  QuestionDTO(int id, String title, String description, LocalDate createdDate, LocalTime createdTime, int answerCount) {}
