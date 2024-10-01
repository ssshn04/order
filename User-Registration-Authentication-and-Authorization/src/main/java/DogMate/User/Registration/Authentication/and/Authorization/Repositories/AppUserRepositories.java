package DogMate.User.Registration.Authentication.and.Authorization.Repositories;

import DogMate.User.Registration.Authentication.and.Authorization.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepositories extends JpaRepository<AppUser, Integer> {

    public AppUser findByUsername(String username);
}
