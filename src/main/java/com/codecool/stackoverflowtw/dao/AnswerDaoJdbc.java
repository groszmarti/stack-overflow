package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.database.Database;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnswerDaoJdbc implements AnswerDAO{
    private Database database;
    private Map<String, String> tables;

    public AnswerDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<AnswerDTO> getAllAnswersForQuestion(int questionId) {
        String query = "SELECT answer.id as answer_id, answer.question_id as question_id, answer.answer as answer, answer.date as answer_date, answer.time as answer_time  FROM answer\n" +
                "RIGHT JOIN question ON answer.question_id = question.id\n" +
                "WHERE question.id = ?";
        try (Connection connection = database.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            List<AnswerDTO> answers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("answer_id");
                int question_id = resultSet.getInt("question_id");
                String answerText = resultSet.getString("answer");
                LocalDate answerDate = null;
                LocalTime answerTime = null;
                Timestamp nonFormattedAnswerDate = resultSet.getTimestamp("answer_date");
                Timestamp nonFormattedAnswerTime = resultSet.getTimestamp("answer_time");
                if (nonFormattedAnswerDate != null) {
                    answerDate = nonFormattedAnswerDate.toLocalDateTime().toLocalDate();
                }
                if (nonFormattedAnswerTime != null) {
                    answerTime = nonFormattedAnswerTime.toLocalDateTime().toLocalTime();
                }

                AnswerDTO answer = new AnswerDTO(id, question_id, answerText, answerDate, answerTime);
                answers.add(answer);
            }

            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addNewAnswer(int questionId, NewAnswerDTO answer) {
        String query = "INSERT INTO answer (question_id, answer, date, time)" +
                "VALUES (?, ?, ?, ?)";
        try {
            Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            prepareAnswer(questionId, answer.answer(), statement);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    private void prepareAnswer(int questionId, String answer, PreparedStatement statement) throws SQLException {
        statement.setInt(1, questionId);
        statement.setString(2, answer);
        statement.setDate(3, Date.valueOf(LocalDateTime.now().toLocalDate()));
        statement.setTime(4, Time.valueOf(LocalDateTime.now().toLocalTime()));
    }

    @Override
    public Boolean deleteAnswer(int answerId) {
        String query = "DELETE FROM answer WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, answerId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
