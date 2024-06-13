package com.SpringBootProject.GestiondeStock.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
//on fait cet entity s il ya une chose d'utiliser dans tous le projet
public class AbstractEntity implements Serializable{
	
@Id
@GeneratedValue
private Integer id;

@CreatedDate
@Column(name = "creationDate" , nullable = false, updatable = false)

private Instant creationDate;
@LastModifiedDate
@Column(name="lastModifiedDate")

private Instant lastModifiedDate;


}
