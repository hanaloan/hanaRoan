package com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRes {
    private int cusIdx;
    private String name;
    private String cusId;
    private String password;
    private String contactInfo;
    private int creditScore;
    private Long income;
    private String jobType;

    public String getFormattedIncome() {
        // Format income with commas as thousands separators
        if (income != null) {
            return String.format("%,d", income);
        }
        return null;
    }

    public String getFormattedContactInfo() {
        // Format the contact info based on the pattern
        // e.g., "01012345678" -> "010-1234-5678"
        if (contactInfo != null && !contactInfo.isEmpty()) {
            if (contactInfo.matches("\\d{3}-?\\d{3,4}-?\\d{4}")) {
                return contactInfo.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            }
        }
        return contactInfo;
    }
}

