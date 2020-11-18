package com.example.module.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KpiResponseDTO {
    private double avg;
    private double stdDeviation;
}
