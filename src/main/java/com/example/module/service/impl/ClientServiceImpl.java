package com.example.module.service.impl;

import com.example.module.model.Client;
import com.example.module.model.dto.KpiResponseDTO;
import com.example.module.repository.ClientRepository;
import com.example.module.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

  private ClientRepository repository;

  public ClientServiceImpl(ClientRepository repository) {
    this.repository = repository;
  }

  public Client createPlayer(String name, String lastName, String email, Date birthDate) {
    log.info("Request to create a Client being executed");
    Client client;
    UUID uuid;
    try {
      if (birthDate == null) {
        birthDate = new Date();
      }
      uuid = UUID.randomUUID();
      client =
          Client.builder()
              .uuid(uuid)
              .name(name)
              .birthDate(birthDate)
              .lastName(lastName)
              .email(email)
              .build();
    } catch (Exception e) {
      log.error("Error creating Client", e);
      throw new IllegalArgumentException();
    }

    try {
      return repository.save(client);
    } catch (Exception e) {
      log.error("Error persisting entity in the database", e);
      throw new PersistenceException(e);
    }
  }

  @Override
  public List<Client> findAll() {
    return repository.findAll();
  }

  @Override
  public Page<Client> findAllPaginated(String searchTerm, Pageable pageable) {
    if (StringUtils.isEmpty(searchTerm)) {
      return repository.findAll(pageable);
    } else {
      return repository.findAllByNameLikeOrLastNameLikeOrEmailLike(
          searchTerm, searchTerm, searchTerm, pageable);
    }
  }

  public void deleteAll() {
    repository.deleteAll();
  }

  @Override
  public KpiResponseDTO getClientsKpis() {
    List<Client> clients = repository.findAll();
    int length = clients.size();
    double avg = 0;
    double std = 0;
    if (length > 0) {
      int sum = clients.stream().mapToInt(Client::getAge).sum();
      avg = (double) (sum / length);
      double standardDeviation = 0;
      for (double num : clients.stream().map(Client::getAge).collect(Collectors.toSet())) {
        standardDeviation += Math.pow(num - avg, 2);
      }
      std = Math.sqrt(standardDeviation / length);
    }
    return KpiResponseDTO.builder().avg(avg).stdDeviation(std).build();
  }
}
