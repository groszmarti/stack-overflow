package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AnswerDTO(int id, int questionId, String answer, LocalDate createdDate, LocalTime createdTime) {
}
