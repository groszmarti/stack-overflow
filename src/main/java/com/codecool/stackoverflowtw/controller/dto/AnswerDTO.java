package com.codecool.stackoverflowtw.controller.dto;

import java.time.LocalDateTime;

public record AnswerDTO(int id, int questionId, String answer, LocalDateTime created) {
}
