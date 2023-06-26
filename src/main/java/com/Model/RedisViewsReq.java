package com.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedisViewsReq {
    private Map<Integer, Integer> pageViewsMap;

    public void addPageView(Integer customer_Idx, Integer currentPageViews) {
        if (pageViewsMap == null) {
            pageViewsMap = new HashMap<>();
        }
        pageViewsMap.put(customer_Idx, currentPageViews);
    }
}




