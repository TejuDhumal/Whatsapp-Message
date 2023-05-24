package com.example.WhatsappMessage.service;

import com.example.WhatsappMessage.model.*;
import com.example.WhatsappMessage.model.message.EventMessage;
import com.example.WhatsappMessage.model.message.Row;
import com.sun.net.httpserver.Headers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class QuestionService {
    private List<Data> questionPool = generateQuestionPool();
    private int questionsPerDay = 4;
    private List<DataEmployee> employees = generateEmployees();
//    private int totalDays = questionPool.size() / questionsPerDay;

    //    @Autowired
//    private RestTemplateBuilder restTemplateBuilder;


    public QuestionService() {
    }

//    public QuestionService(List<Question> questionPool, int questionsPerDay, List<Employee> employees, int totalDays, Set<String> useIds) {
//        this.questionPool = questionPool;
//        this.questionsPerDay = questionsPerDay;
//        this.employees = employees;
//        this.totalDays = totalDays;
//        this.useIds = useIds;
//    }

    Random random = new Random();
//    RestTemplate restTemplate = restTemplateBuilder.build();

    Set<String> useIds = new HashSet<>(); // Track used indices

    // Generate the question pool
    public List<Data> generateQuestionPool() {
        RestTemplate restTemplate = new RestTemplate();
        List<Data> questionPool = new ArrayList<>();
        // get all questions from gessa get all questions api
        HttpEntity<Event> entity = new HttpEntity<>(getHeaders());

        ResponseEntity<Event> response = restTemplate.exchange(
                "https://gessa.io/cms/questions/?page=0&size=10",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Event>(){
                },
                Event.class
        );
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        return response.getBody().getResult().getData();
//        return questionPool;
    }


    //
    public HttpHeaders getHeaders() {
        String authHeader = "Bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ3elRvX1hzYXQycW9vMFBxbUxVRkZnR2VsRjcwY251bVkzT0ltU2daTWhZIn0.eyJleHAiOjE2ODQ5MzkzNjUsImlhdCI6MTY4NDkzMjE2OCwiYXV0aF90aW1lIjoxNjg0OTI5NDIxLCJqdGkiOiI3NDNlMzIyOS1hMjY0LTQwNjAtYTc1NC02YWJkYzE1Y2NhYWIiLCJpc3MiOiJodHRwczovL2F1dGgtZGV2Lmdlc3NhLmlvL3JlYWxtcy9nZXNzYS1zdHVkaW8iLCJhdWQiOlsiYXV0aG9yaXphdGlvbiIsImFjY291bnQiXSwic3ViIjoiZGI4ZTUwNGYtZjkxYy00ZDI3LThiOTUtZWEwMGY2NTUxMGNmIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoibWFzdGVyQ2xpZW50Iiwibm9uY2UiOiI2NmJiODkyNy1mMzdhLTRkYWMtOTAwYi1iZDNkYTY0Njc4NjEiLCJzZXNzaW9uX3N0YXRlIjoiNGY4ZDA4OTItNzBiZS00NDRmLWJkYmItMTAwZDZhYzA1ODhkIiwiYWNyIjoiMCIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLWdlc3NhLXN0dWRpbyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhdXRob3JpemF0aW9uIjp7InJvbGVzIjpbImFkbWluIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwic2lkIjoiNGY4ZDA4OTItNzBiZS00NDRmLWJkYmItMTAwZDZhYzA1ODhkIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoidGVqYXN3aW5pIGRodW1hbCIsInRlbmFudElkIjoiZ2Vzc2Etc3R1ZGlvIiwibW9iaWxlIjoiOTg3NjU0MzIxMCIsInByZWZlcnJlZF91c2VybmFtZSI6InRlamFzdmluaWRodW1hbDQ5QGdtYWlsLmNvbSIsImdpdmVuX25hbWUiOiJ0ZWphc3dpbmkiLCJ1c2VySWQiOiI2NDY1Y2VmZjBmYmVlMmJiZmQ0NmZlMjAiLCJmYW1pbHlfbmFtZSI6ImRodW1hbCIsImVtYWlsIjoidGVqYXN2aW5pZGh1bWFsNDlAZ21haWwuY29tIn0.h22hTlOQOXe3aA14TEN8d8aPM0inwc75eZoHqQNZIjk-1GnEh00WjUOe9ODdCXrezFnu_qcEdqLPWD-llJtlfSIPp0pIzFVlE-f8Cq1mAJErARH-IzlT5D-z20nsvzcz0ANpSmuqkPBZOSCD_iMvqshWSDv29fbogP64NKLezD4g334x1K13uKXpXu1QBd_wYa35SrzfTYRALwBVWGId6IpCfredss3NXiDT4IJreMIZ3se5YPyGxcPGMHT_U6I96x_DmlGUs2WgknPiB9oM1igNE-YpUUJSjDIIUrla_EbzefjV1D58MzE4jZ4A9DVU0SU5xAJ3iq6KaD8e-BbwQA";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-tenant-id", "646e0759c4d077fad4e24592");
        System.out.println(headers);
        return headers;
    }



    // Generate the list of employees

    private List<DataEmployee> generateEmployees() {
        RestTemplate restTemplate = new RestTemplate();
//        List<Employee> employees = new ArrayList<>();
//        get all employees by gessa get all employee api
        HttpEntity<EventEmployee> entity = new HttpEntity<>(getHeaders());
        ResponseEntity<EventEmployee> response = restTemplate.exchange(
                "https://gessa.io/cms/employees/?page=0&size=2",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<EventEmployee>() {},
                EventEmployee.class);
        System.out.println(response.getBody());
        return response.getBody().getResult().getData();

//        return employees;

    }

    // Method to send questions to employees

    public void sendQuestionsToEmployees(EventMessage eventMessage)  {
//        for (int day = 0; day < totalDays; day++) {
//        while (true) {
        for (DataEmployee employee : employees) {
                List<Data> selectedQuestions = selectQuestionsForOneDay();

                // Send the questions to the employee
                sendQuestionsToEmployee(employee, selectedQuestions, eventMessage);
            }
    }



    // Method to select questions for a day

    private List<Data> selectQuestionsForDay() {
        List<String> questionIds = new ArrayList<>();
        System.out.println(questionIds);
        for (int i = 0; i < questionPool.size(); i++) {
            questionIds.add(questionPool.get(i).getQuestion_id());
        }

        // Shuffle the questionIds list
        Collections.shuffle(questionIds);

        List<Data> selectedQuestions = new ArrayList<>();

        for (int i = 0; i < questionsPerDay; i++) {
            String id = questionIds.get(i);

            // Check if the id has already been used
            while (useIds.contains(id)) {
                // If so, generate a new random id
                int index = random.nextInt(questionIds.size());
                id =  questionIds.get(index);
            }
            // Add the question to the selectedQuestions list

            String finalId = id;
            selectedQuestions.add(questionPool.stream()
                    .filter(question -> question.getQuestion_id().equals(finalId))
                    .findFirst()
                    .orElse(null));
            // Add the index to the usedIndices set
            useIds.add(id);
        }
        System.out.println(selectedQuestions);
        System.out.println(useIds);

        return selectedQuestions;
    }

    // Method to send questions to an employee
    private void sendQuestionsToEmployee(DataEmployee employee, List<Data> questions, EventMessage eventMessage) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("EAANFSPQeQyQBABzWE6CQZBVTJ8OoEYzzoJfNCsoJwLXcTzxxcqXYYUxjQlHPxuhobR14Swa4Lz2XPPxnXz0fcGphrZBPnaRLL76LiSthNeSnBwQCJCIFcBgq4zB13HYTTlu2OlgZAV6M5qYNgjapbEoWjHZA4kzlYDfSw5ZBj3Q5JIDCr054owVib2GPEGPtxI1KITZBQPgKOZCEpnPd1YU");
        eventMessage.getInteractive().getAction().getSections().get(0).setTitle("question");
        String no = employee.getMobile_no();
        eventMessage.setTo(employee.getMobile_no());
        List<Row> rows = new ArrayList<>();
        System.out.println(eventMessage.getTo());
        for (int i = 0; i < questions.size(); i++) {
            Data question = questions.get(i);
            Row row = new Row();
            row.setId(question.getQuestion_id());
            row.setTitle(question.getQuestion());
            rows.add(row);
        }
        eventMessage.getInteractive().getAction().getSections().get(0).setRows(rows);
       HttpEntity<EventMessage> entity = new HttpEntity<>(eventMessage,headers);
        ResponseEntity<EventMessage> response = restTemplate.exchange(
                "https://graph.facebook.com/v16.0/123419960748882/messages",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<EventMessage>() {},
                EventEmployee.class);
        System.out.println(response.getBody());

    }




// using questions names
Set<String> usedQuestions = new HashSet<>(); // Track used question names
    public void sendQuestionsToEmployee() {

        while (true) {
            List<Data> selectedQuestions = selectQuestionsForOneDay();

            for (DataEmployee employee : employees) {
//                sendQuestionsToEmployee(employee, selectedQuestions);
            }

            // Check if all questions have been used
            if (usedQuestions.size() == questionPool.size()) {
                break; // Exit the loop if all questions have been used
            }

        }
    }

    private List<Data> selectQuestionsForOneDay() {
        List<String> questionnames = new ArrayList<>();
        for (int i = 0; i < questionPool.size(); i++) {
            questionnames.add(questionPool.get(i).getQuestion());
        }


        // Shuffle the questionnames list
        Collections.shuffle(questionnames);

        List<Data> selectedQuestions = new ArrayList<>();

        for (int i = 0; i < questionsPerDay; i++) {
            String questionname = questionnames.get(i);

            // Check if the name has already been used
            while (usedQuestions.contains(questionname)) {
                // If so, generate a new random name
                int index = random.nextInt(questionnames.size());
                questionname =  questionnames.get(index);
            }
            // Add the question to the selectedQuestions list

            String finalQuestionname = questionname;
            selectedQuestions.add(questionPool.stream()
                    .filter(question -> question.getQuestion().equals(finalQuestionname))
                    .findFirst()
                    .orElse(null));
            // Add the index to the usedIndices set
            usedQuestions.add(questionname);
        }

        return selectedQuestions;
    }


}
