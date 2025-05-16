/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class JSONReader {
    
     public List<Carrera> readJSONFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Carrera> carreraList = new ArrayList<>();

        carreraList = objectMapper.readValue(new File(filePath),
                                              objectMapper.getTypeFactory().constructCollectionType(List.class, Carrera.class));

        return carreraList;
    }
}
