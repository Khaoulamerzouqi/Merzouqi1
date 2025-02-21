package com.practice.quiz.controller;

import com.practice.quiz.model.QuestionWrapper;
import com.practice.quiz.model.Quiz;
import com.practice.quiz.model.QuizResponse;
import com.practice.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    /* GET List QUIZ*/
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAll() {
        return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
    }
    /* Get QUIZ*/
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable long quizId) {
        return new ResponseEntity<>(service.getQuiz(quizId),HttpStatus.OK);
    }
    /* NEW QUIZ*/
     @PostMapping("/new")
    public ResponseEntity<String> addQuiz(@RequestBody Quiz quiz) {
        service.addQuiz(quiz);
        return new ResponseEntity<>("Quiz Added", HttpStatus.CREATED);
    }

    /* Get Questions Of Quiz By Id*/
    @GetMapping("/questionofquiz/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionOfQuiz(@PathVariable long quizId) {
        return new ResponseEntity<>(service.getQuestionOfQuiz(quizId), HttpStatus.OK);
    }

    @PostMapping("/{quizName}/{category}/{noOfQuestions}")
    public ResponseEntity<String> createQuiz(@PathVariable String quizName,@PathVariable String category,@PathVariable int noOfQuestions) {
        long quizId = service.createQuiz(quizName,noOfQuestions,category);
        return new ResponseEntity<>("Quiz : "+quizName+" Created with Quiz Id : "+quizId, HttpStatus.CREATED);
    }

    @PostMapping("/submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable long quizId, @RequestBody List<QuizResponse> response) {
        int marks = service.getMarks(quizId,response);
        return new ResponseEntity<>("Total Marks for the Quiz are : "+marks,HttpStatus.OK);
    }
}
