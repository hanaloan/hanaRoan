package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class LoginForAdminRes{
    private String name;
    private Integer admin_idx;
    private boolean authenticated;


}