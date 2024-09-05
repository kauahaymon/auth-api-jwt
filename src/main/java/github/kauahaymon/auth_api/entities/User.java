package github.kauahaymon.auth_api.entities;

import github.kauahaymon.auth_api.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.ConstructorParameters;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String login;
    private String password;
    private RoleEnum role;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == RoleEnum.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
