package org.jarvis.leave;

import org.jarvis.leave.service.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class JarvisLeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(JarvisLeaveApplication.class, args);
	}

	@Bean
	AuditorAwareImpl auditorProvider() {
		return new AuditorAwareImpl();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
