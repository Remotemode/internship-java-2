package com.remotemode.internshipjava2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long userId;

    @NotNull
    @Column(name = "login", columnDefinition = "VARCHAR(50)")
    private String login;

    @NotNull
    @Column(name = "password", columnDefinition = "VARCHAR(50)")
    private String password;

    @NotNull
    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @NotNull
    @Column(name = "first_name", columnDefinition = "VARCHAR(50)")
    private String firstName;

    @NotNull
    @Column(name = "last_name", columnDefinition = "VARCHAR(50)")
    private String lastName;

    @NotNull
    @Column(name = "role", columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserEntity(@NotNull String login,
                      @NotNull String password,
                      @NotNull String email,
                      @NotNull String firstName,
                      @NotNull String lastName,
                      @NotNull UserRole role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}