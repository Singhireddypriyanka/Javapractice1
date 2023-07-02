package com.handsonspringsinghireddy;
import com.fasterxml.jackson.databind.ObjectMapper; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MyController {
    private final LongestCommonSubstring longestCommonSubstring;
    private final ObjectMapper objectMapper;

    public MyController(LongestCommonSubstring longestCommonSubstring, ObjectMapper objectMapper) {
        this.longestCommonSubstring = longestCommonSubstring;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/lcs")
    public ResponseEntity<String> returnLcs(@RequestBody RequestBodyData requestBodyData) {
        List<StringValue> setOfStrings = requestBodyData.getSetOfStrings();

        // Extract the string values from the setOfStrings
        StringBuilder stringBuilder = new StringBuilder();
        for (StringValue stringValue : setOfStrings) {
            stringBuilder.append(stringValue.getValue()).append(",");
        }
        String[] strings = stringBuilder.toString().split(",");

        // Call the LongestCommonSubstring class method to get the longest common substring
        String result = longestCommonSubstring.getLongestCommon(strings);
        System.out.println("Result: " + result); // Print to debug getting null value in JSON response body

        // Serialize the result to JSON string
        try {
            String jsonResult = objectMapper.writeValueAsString(result);
            return ResponseEntity.ok(jsonResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during serialization");
        }
    }

    public static class RequestBodyData {
        private List<StringValue> setOfStrings;

        public List<StringValue> getSetOfStrings() {
            return setOfStrings;
        }

        public void setSetOfStrings(List<StringValue> setOfStrings) {
            this.setOfStrings = setOfStrings;
        }
    }

    public static class StringValue {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
