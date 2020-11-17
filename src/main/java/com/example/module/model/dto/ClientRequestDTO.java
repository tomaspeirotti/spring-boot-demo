package com.example.module.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequestDTO {
  private String name;

  private String lastName;

  private String email;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date birthDate;
}
