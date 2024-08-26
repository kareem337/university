package com.universityapp.university.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int score;

    public List<Question> getQuestions() {
        return questions;
    }

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void displayQuestions() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String[] answers = question.getPossibleAnswers();
            for (int i = 0; i < answers.length; i++) {
                System.out.println((i + 1) + ". " + answers[i]);
            }
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            if (question.isCorrectAnswer(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + question.getCorrectAnswer());
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }
}

