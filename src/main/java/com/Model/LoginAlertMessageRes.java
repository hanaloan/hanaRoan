package com.Model;


import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;

@NoArgsConstructor
@Getter
@Setter
public class LoginAlertMessageRes {
    private HashMap<String, ArrayList<String>> alertMessageRes;

    public void addAlertMessages(String type, ArrayList<String> messages){
        if(alertMessageRes == null){
            alertMessageRes = new HashMap<>();
        }
        alertMessageRes.put(type, messages);
    }
}
