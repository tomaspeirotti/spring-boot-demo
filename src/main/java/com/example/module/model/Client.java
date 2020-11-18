package com.example.module.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

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
      Calendar today = new GregorianCalendar();
      today.setTime(new Date());
      Calendar birth = new GregorianCalendar();
      birth.setTime(birthDate);
      return today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
    } else {
      return 0;
    }
  }
}
