package com.universityapp.university;

import com.universityapp.university.model.Question;
import com.universityapp.university.model.Quiz;
import com.universityapp.university.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UniversityApplication {
	// swagger link: http://localhost:8080/swagger-ui/index.html
	public static void main(String[] args) {
		//SpringApplication.run(UniversityApplication.class, args);

		//Testing Quiz app OOP
		Quiz quiz = new Quiz();

		// Add some sample questions
		quiz.addQuestion(new Question("Where is Sumerge located?",
				new String[]{"Maadi", "New Cairo", "Mokattam", "Nasr City"}, "Maadi"));
		quiz.addQuestion(new Question("What is the capital of Egypt?",
				new String[]{"Cairo", "Giza", "Alexandria", "Aswan"}, "Cairo"));
		quiz.addQuestion(new Question("What is 2 + 2?",
				new String[]{"3", "4", "5", "6"}, "4"));

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();

		User user = new User(name, quiz);
		user.takeQuiz();

	}

}
