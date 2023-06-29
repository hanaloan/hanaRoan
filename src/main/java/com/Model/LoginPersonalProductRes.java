package com.Model;


import lombok.*;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class LoginPersonalProductRes {
    private ArrayList<LoginLendProduct> subscribedProducts;
}
