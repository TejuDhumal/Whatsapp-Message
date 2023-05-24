package com.example.WhatsappMessage.controller;

import com.example.WhatsappMessage.model.Data;
import com.example.WhatsappMessage.model.Result;
import com.example.WhatsappMessage.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/send-questions")
    public ResponseEntity<String> sendQuestionsToEmployees() {
        questionService.sendQuestionsToEmployees();
        return ResponseEntity.ok("Questions sent successfully.");
    }

    @GetMapping("/get")
    public ResponseEntity getQuestions() {
        List<Data> questions = questionService.generateQuestionPool();
      return ResponseEntity.ok(questions);

    }
}
