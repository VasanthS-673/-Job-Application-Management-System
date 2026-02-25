package com.vasanth.jobapplication.auth;

import com.vasanth.jobapplication.user.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}