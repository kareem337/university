package com.universityapp.university.model;

public class Question {
    private String questionText;
    private String[] possibleAnswers;
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Question(String questionText, String[] possibleAnswers, String correctAnswer) {
        this.questionText = questionText;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
