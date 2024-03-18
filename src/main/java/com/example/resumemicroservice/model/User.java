package com.example.resumemicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Table(name="users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long userId;
    private String email;

    private String password;
    private String firstName;

    private String middleName;
    private String lastName;

    private String mobileNumber;
    private String city;

    private String profession;

    private Boolean isAccountNonExpired;

    private Boolean isAccountNonLocked;

    private Boolean isCredentialsNonExpired;

    private Boolean isEnabled;


    @OneToMany(mappedBy = "roleId",fetch = FetchType.EAGER)
    private List<Role> roleList;

    @OneToMany(mappedBy = "educationId",fetch = FetchType.EAGER)
    private List<Education> educationList;

    @OneToMany(mappedBy = "experienceId",fetch = FetchType.EAGER)
    private List<Experience> experienceList;
    @OneToMany(mappedBy = "socialId",fetch = FetchType.EAGER)
    private List<Social> socialList;

    @OneToMany(mappedBy = "skillId",fetch = FetchType.EAGER)
    private List<Skill> skillList;

    @OneToMany(mappedBy = "resumeId",fetch = FetchType.EAGER)
    private List<Resume> resumeList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream()
                .map(role -> new SimpleGrantedAuthority(String.valueOf(role.getRoleType()) ))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
