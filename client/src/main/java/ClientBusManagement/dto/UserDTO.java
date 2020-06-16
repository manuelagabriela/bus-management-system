package ClientBusManagement.dto;

import ClientBusManagement.enumeration.UserRole;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserDTO{

    private Long id;

    @NotBlank
    private String username;

    @Length(min = 6)
    private String password;
    private UserRole role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
