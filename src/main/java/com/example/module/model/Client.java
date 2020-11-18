package com.example.module.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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

  public int getAge() {
    if (birthDate != null) {
      Period period = Period.between(LocalDate.from(birthDate.toInstant()), LocalDate.from(new Date().toInstant()));
      return period.getYears();
    } else {
      return -1;
    }
  }
}
