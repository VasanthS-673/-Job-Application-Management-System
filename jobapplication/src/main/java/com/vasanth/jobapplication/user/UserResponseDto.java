package com.vasanth.jobapplication.user;

import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
}