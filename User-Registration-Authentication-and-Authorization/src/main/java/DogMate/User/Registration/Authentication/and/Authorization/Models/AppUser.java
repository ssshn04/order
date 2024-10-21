package DogMate.User.Registration.Authentication.and.Authorization.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private int age;

    @Column(unique = true, nullable = false)
    private String nickname;
    private boolean sex;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
