package com.example.module.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Client implements Serializable {

  @Type(type = "uuid-char")
  @Column(nullable = false, length = 36)
  @Id
  private UUID uuid;

  private String name;

  private String lastName;

  private String email;

  private Date birthDate;
}
