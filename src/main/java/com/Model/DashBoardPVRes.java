package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashBoardPVRes {
    private Map<String, Integer> oneWeekTotalPV;
    private int maxPVValue;
    private int minPVValue;
    private Map<String, Integer> oneWeekUV;
    private int maxUVValue;
    private int minUVValue;
}