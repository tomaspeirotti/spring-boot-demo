package com.example.module.model.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {
  private UUID uid;
  private String name;
  private String birthDate;
}
