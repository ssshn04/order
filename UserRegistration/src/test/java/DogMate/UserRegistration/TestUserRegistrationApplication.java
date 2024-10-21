package DogMate.UserRegistration;

import org.springframework.boot.SpringApplication;

public class TestUserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserRegistrationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
