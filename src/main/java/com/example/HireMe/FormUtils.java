package com.example.HireMe;
import com.example.HireMe.Model.Organisation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class FormUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Organisation convertFormToOrganisation(MultiValueMap<String, String> formData) throws Exception {
        return objectMapper.convertValue(formData.toSingleValueMap(), Organisation.class);
    }
}
