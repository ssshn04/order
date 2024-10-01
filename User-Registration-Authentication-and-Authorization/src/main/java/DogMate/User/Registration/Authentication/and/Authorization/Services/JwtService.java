package DogMate.User.Registration.Authentication.and.Authorization.Services;

import DogMate.User.Registration.Authentication.and.Authorization.Models.AppUser;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService  {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time-ms}")
    private long expirationTimeMs;

    @Value("${security.jwt.issuer}")
    private String issuer;

}
