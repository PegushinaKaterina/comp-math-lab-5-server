package com.example.compmathlab5server.service;

import com.example.compmathlab5server.dto.LagrangeResponse;
import com.example.compmathlab5server.dto.InterpolationRequestDto;
import com.example.compmathlab5server.dto.NewtonResponse;

public interface InterpolationService {
    LagrangeResponse interpolateByLagrange(InterpolationRequestDto interpolationRequestDto);
    NewtonResponse interpolateByNewton(InterpolationRequestDto interpolationRequestDto);
}
