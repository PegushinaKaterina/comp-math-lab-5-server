package com.example.compmathlab5server.service;

import com.example.compmathlab5server.dto.LagrangeResponse;
import com.example.compmathlab5server.Interpolator;
import com.example.compmathlab5server.dto.NewtonResponse;
import com.example.compmathlab5server.entity.Point;
import com.example.compmathlab5server.dto.InterpolationRequestDto;
import org.springframework.stereotype.Service;

@Service
public class InterpolationServiceImpl implements InterpolationService {
    @Override
    public LagrangeResponse interpolateByLagrange(InterpolationRequestDto interpolationRequestDto) {
        Point[] points = new Point[interpolationRequestDto.getPoints().length];
        for (int i = 0; i < interpolationRequestDto.getPoints().length; i++) {
            double[] point = interpolationRequestDto.getPoints()[i];
            points[i] = new Point(point[0], point[1]);
        }
        return Interpolator.getInterpolationByLagrange(points, interpolationRequestDto.getX());
    }

    @Override
    public NewtonResponse interpolateByNewton(InterpolationRequestDto interpolationRequestDto) {
        Point[] points = new Point[interpolationRequestDto.getPoints().length];
        for (int i = 0; i < interpolationRequestDto.getPoints().length; i++) {
            double[] point = interpolationRequestDto.getPoints()[i];
            points[i] = new Point(point[0], point[1]);
        }
        return Interpolator.getInterpolationByNewton(points, interpolationRequestDto.getX());
    }
}
