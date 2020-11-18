package com.example.module.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClientRequestDTO {
  private String name;

  private String lastName;

  private String email;

  private Date birthDate;
}
