package org.edupro.webapi.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_USER")
public class UserEntity implements UserDetails {
	private static final long serialVersionUID = -7513004010560492767L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID", nullable = false)
    private Integer id;

    @Column(name = "FIRST_NAME", length = 64)
    private String firstname;

    @Column(name = "LAST_NAME", length = 64)
    private String lastname;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "PASSWORD", length = 64)
    private String password;

    @Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "UID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "RID")
    )
    private List<RoleEntity> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = this.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
