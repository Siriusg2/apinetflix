package com.consulti.templatespringboot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "profiles")
@SequenceGenerator(
  name = "profiles_sequence",
  sequenceName = "profiles_sequence",
  allocationSize = 1
)
public class ProfilesModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "profiles_sequence"
  )
  @Column(name = "profile_id", unique = true, updatable = false)
  private Long id;

  private String name;

  @Column(name = "date_of_birth", nullable = false)
  private String dateOfBirth;

  @Column(name = "is_active")
  private Boolean isActive = true;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "created_date")
  private Date createdDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Column(name = "modified_date")
  private Date modifiedDate;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "modified_by")
  private String modifiedBy;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private UsersModel user;
}
