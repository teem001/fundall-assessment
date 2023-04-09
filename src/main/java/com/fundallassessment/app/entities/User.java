package com.fundallassessment.app.entities;


import com.fundallassessment.app.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name ="user_table")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User extends Base {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    private String imageUrl;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;







}
