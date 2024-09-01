package com.DogMate.profilePage;

import org.springframework.boot.SpringApplication;

public class TestProfilePageApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProfilePageApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
