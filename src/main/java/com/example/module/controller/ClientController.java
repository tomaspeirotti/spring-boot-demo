package com.example.module.controller;

import com.example.module.model.Client;
import com.example.module.model.dto.ClientDTO;
import com.example.module.model.dto.ClientRequestDTO;
import com.example.module.model.dto.ClientResponseDTO;
import com.example.module.service.impl.EntityMapper;
import com.example.module.service.ClientService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/client")
public class ClientController {

  private ClientService clientService;
  private EntityMapper entityMapper;

  public ClientController(ClientService clientService,
                          EntityMapper entityMapper) {
    this.clientService = clientService;
    this.entityMapper = entityMapper;
  }

  @PostMapping(value = "/create")
  public ResponseEntity<ClientDTO> create(@RequestBody ClientRequestDTO dto) {
    Client client = this.clientService.createPlayer(dto.getName(), dto.getLastName(), dto.getEmail(), dto.getBirthDate());
    return ResponseEntity.status(HttpStatus.OK).body(entityMapper.toDto(client));
  }

  @GetMapping(value = "/list")
  public ResponseEntity<ClientResponseDTO> findAll() {
    List<Client> clients = this.clientService.findAll();
    return ResponseEntity.ok(ClientResponseDTO.builder()
        .players(clients.stream()
            .map(entityMapper::toDto)
            .collect(Collectors.toList()))
        .build());
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<ClientDTO>> findPaginated(@RequestParam(name = "searchTerm") String searchTerm, Pageable pageable) {
    Page<Client> playersPage = this.clientService.findAllPaginated(searchTerm, pageable);
    return ResponseEntity.ok(playersPage.map(entityMapper::toDto));
  }

  @DeleteMapping(value = "/deleteAll")
  public ResponseEntity deleteAll() {
    this.clientService.deleteAll();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
