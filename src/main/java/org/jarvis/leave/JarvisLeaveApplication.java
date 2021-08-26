package org.jarvis.leave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JarvisLeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(JarvisLeaveApplication.class, args);
	}

}
