package com.example.module.model.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDTO {
  private List<ClientDTO> clients;
}
