package com.example.module.service;

import com.example.module.model.Client;
import com.example.module.model.dto.KpiResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ClientService {
  Client createPlayer(String name, String lastName, String email, Date birthDate);

  List<Client> findAll();

  Page<Client> findAllPaginated(String searchTerm, Pageable pageable);

  void deleteAll();

  KpiResponseDTO getClientsKpis();
}
