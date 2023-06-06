package com.example.compmathlab5server.controller;

import com.example.compmathlab5server.dto.InterpolationResponseDto;
import com.example.compmathlab5server.dto.LagrangeResponse;
import com.example.compmathlab5server.dto.InterpolationRequestDto;
import com.example.compmathlab5server.dto.NewtonResponse;
import com.example.compmathlab5server.service.InterpolationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class InterpolationController {
    private final InterpolationServiceImpl interpolationService;

    @Autowired
    public InterpolationController(InterpolationServiceImpl interpolationService) {
        this.interpolationService = interpolationService;
    }

    @PostMapping("/interpolate")
    public ResponseEntity<InterpolationResponseDto> interpolateByLagrange(@RequestBody InterpolationRequestDto interpolationRequestDto) {
        LagrangeResponse lagrangeResponse = interpolationService.interpolateByLagrange(interpolationRequestDto);
        NewtonResponse newtonResponse = interpolationService.interpolateByNewton(interpolationRequestDto);
        return ResponseEntity.ok(new InterpolationResponseDto(lagrangeResponse, newtonResponse));

    }
}