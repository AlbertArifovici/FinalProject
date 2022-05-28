package ro.siit.pms.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String name;
    private String password;
    private String role;

    protected User() {}

    public User(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public void setUserRole(String role) {
        this.role = role;
    }
}

