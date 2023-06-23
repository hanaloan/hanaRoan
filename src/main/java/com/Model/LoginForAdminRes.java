package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class LoginForAdminRes{
    private String name;
    private int adminIdx;
    private String authorityType;
    private boolean authenticated;
}