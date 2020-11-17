package com.example.module.service.impl;

import com.example.module.model.Client;
import com.example.module.repository.ClientRepository;
import com.example.module.service.ClientService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

  private ClientRepository repository;
  private Map<UUID, Object> createdPlayers = new HashMap<>();

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
      client = Client.builder()
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

    createdPlayers.put(uuid, client);

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
      return repository.findAllByNameLikeOrLastNameLikeOrEmailLike(searchTerm, searchTerm, searchTerm, pageable);
    }
  }

  public void deleteAll() {
    repository.deleteAll();
  }
}
