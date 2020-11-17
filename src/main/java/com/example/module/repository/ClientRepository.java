package com.example.module.repository;

import com.example.module.model.Client;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
  Page<Client> findAllByNameLikeOrLastNameLikeOrEmailLike(String name, String lastName, String email, Pageable pageable);
  Page<Client> findAll(Specification specification, Pageable pageable);
}
