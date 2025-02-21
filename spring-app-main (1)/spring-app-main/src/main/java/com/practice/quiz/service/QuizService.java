package com.practice.quiz.service;

import com.practice.quiz.model.*;
import com.practice.quiz.repository.QuestionRepo;
import com.practice.quiz.repository.QuizRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;


    public List<Quiz> getAll() {
        return quizRepo.findAll();
    }

    public Quiz getQuiz(long quizId) {
        return quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz With quizId : "+quizId+" Not Found"));
    }

    public void addQuiz(Quiz quiz) {
        // Récupère les questions existantes ou sauvegarde les nouvelles questions
        List<Question> questions = quiz.getQuestions().stream()
                .map(question -> {
                    // Cherche la question dans la base de données par ID (si fourni) ou par texte (pour éviter les doublons)
                    if (question.getQuestionId() != null && questionRepo.existsById(question.getQuestionId())) {
                        return questionRepo.findById(question.getQuestionId()).orElse(question);
                    } else {
                        // Sauvegarde une nouvelle question si elle n'existe pas
                        return questionRepo.save(question);
                    }
                })
                .collect(Collectors.toList());

        // Associe les questions récupérées ou sauvegardées au quiz
        quiz.setQuestions(questions);

        // Sauvegarde le quiz
        quizRepo.save(quiz);
    }

    public long createQuiz(String quizName, int noOfQuestions, String category) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(Category.valueOf(category.toUpperCase()),noOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizName);
        quiz.setQuestions(questions);
        //Saving Quiz and returning Quiz ID
        return quizRepo.save(quiz).getQuizId();
    }

    public List<QuestionWrapper> getQuestionOfQuiz(long quizId) {
        //Checking Quiz is Present with quizId
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz With QuizId : "+quizId+" Not Found"));
        List<QuestionWrapper> questionsForUser =  new ArrayList<>();

        //Mapping Questions to QuestionWrapper
        for(Question q : quiz.getQuestions()) {
            questionsForUser.add(new QuestionWrapper(q.getQuestionId(),q.getQuestionText(),q.getOptions()));
        }
        return questionsForUser;
    }

    public int getMarks(long quizId, List<QuizResponse> response) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz With QuizId : "+quizId+" Not Found"));

        int marks = 0;
        int i=0;

        //Calculating Marks
        for(Question q : quiz.getQuestions()) {
            //Checking Selected Option is Corrected or Not
            if(q.getCorrectAnswer().equals(response.get(i).getSelectedOption())) {
                marks++;
            }
            i++;
        }
        return marks;
    }
}
