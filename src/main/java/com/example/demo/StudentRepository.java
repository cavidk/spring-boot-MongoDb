package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends MongoRepository<Student, String> {

Optional<Student> findStudentsByEmail(String email);
}
