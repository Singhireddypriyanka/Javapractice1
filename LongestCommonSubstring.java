package com.handsonspringsinghireddy;
import org.springframework.stereotype.Component;

@Component
public class LongestCommonSubstring {
    public String getLongestCommon(String[] strings) {
        if (strings == null || strings.length == 0) {
            return ""; // Return an empty string if no input strings are provided
        }

        String longestCommon = strings[0]; // Assume the first string is the longest common substring

        for (int i = 1; i < strings.length; i++) {
            longestCommon = findLongestCommon(longestCommon, strings[i]); // Find the longest common substring between current string and previous longest common
        }
        System.out.println("Result in getCommon method:" + longestCommon); // debugging to see if thus method is returning correct value
        return longestCommon;
    }

    private String findLongestCommon(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i - 1;
                    }
                }
            }
        }

        return str1.substring(endIndex - maxLength + 1, endIndex + 1);
    }
}
