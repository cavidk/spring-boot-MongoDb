package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	//How to use java beans inside after creating interface
	@Bean
   CommandLineRunner runner(
			StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address(
					"England",
					"London",
					"NE9"
			);

			String email = "jahmed@gmail.com";
			Student student = new Student(
					"Jamila",
					"Ahmed",
					email,
					Gender.FEMALE,
					address,
					List.of("Computer Science","Math"),
					BigDecimal.TEN,
					LocalDateTime.now()

			);
    //Added mongodb query where is email showed like that::

			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
             repository.findStudentsByEmail(email)
					 .ifPresentOrElse(s -> {
						 System.out.println(s + " already exists.");
					 },()-> {System.out.println("Inserting student " + student);
						 repository.insert(student);
					 });
		};
   }

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("jahmed@gmail.com"));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1){
			throw new IllegalStateException("" +
					"Founded many students within this email " + email);
		}
		if (students.isEmpty()){
			System.out.println("Inserting student " + student);
			repository.insert(student);
		}
		else {
			System.out.println(student + " already exists.");
		}
	}
}
