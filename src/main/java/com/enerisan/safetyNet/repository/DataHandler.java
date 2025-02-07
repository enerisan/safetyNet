package com.enerisan.safetyNet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.enerisan.safetyNet.model.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;

@Component
public class DataHandler {
private final Data data;
    public DataHandler() throws IOException {
//Load file json from package resources. Resource Class is native ;
            Resource resource = new ClassPathResource("data.json");
            File file = resource.getFile();

            ObjectMapper objectMapper =  new ObjectMapper();

            this.data = objectMapper.readValue(file, Data.class);


    }

    public Data getData() {
        return data;
    }
}
