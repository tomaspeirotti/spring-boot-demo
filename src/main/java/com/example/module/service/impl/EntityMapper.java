package com.example.module.service.impl;

import com.example.module.model.Client;
import com.example.module.model.dto.ClientDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {

  public ClientDTO toDto(Client client) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Date birthDate = client.getBirthDate() == null ? new Date() : client.getBirthDate();
    return ClientDTO.builder()
        .name(client.getName())
        .birthDate(sdf.format(birthDate))
        .uid(client.getUuid())
        .build();
  }

}
