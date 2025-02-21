package com.practice.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    private String questionText;
    @ElementCollection
    private List<String> options;
    private String correctAnswer;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String level;

    // Getters and setters
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
