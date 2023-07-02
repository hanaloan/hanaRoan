package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter
public class DisplayProductListRes {
    private ArrayList<DisplayProduct> productList;
}
