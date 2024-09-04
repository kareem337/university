package com.universityapp.university.model;

public class User {
    private String name;
    private Quiz quiz;

    public User(String name, Quiz quiz) {
        this.name = name;
        this.quiz = quiz;
    }

    public String getName() {
        return name;
    }

    public void takeQuiz() {
        System.out.println(name + ", it's time to take the quiz!");
        quiz.displayQuestions();
        System.out.println("Your score: " + quiz.getScore() + "/" + quiz.getQuestions().size());
    }
}
