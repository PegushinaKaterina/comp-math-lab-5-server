package com.example.compmathlab5server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewtonResponse {
    private String f;
    private double n;
    private double[][] finiteDifferences;
}
