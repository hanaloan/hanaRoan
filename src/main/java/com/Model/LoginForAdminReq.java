package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class LoginForAdminReq{
    private String username;
    private String password;
    private String userType;


}