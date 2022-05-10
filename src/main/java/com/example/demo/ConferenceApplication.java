package com.example.demo;

import com.example.demo.model.Conference;
import com.example.demo.model.Topic;
import com.example.demo.repository.ConferenceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ConferenceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConferenceApplication.class, args);

	}

}
