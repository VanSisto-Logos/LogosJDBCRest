package com.vansisto.model;

import com.vansisto.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
}
