package com.scma.anilg.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Getter
@Setter
@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "name shuld not be blank")
	@Size(min = 3, max = 20, message = "name must be bitween 2 to 20 characters")
	private String name;
	
	@Column(unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid email address")
	private String email;
	
	@Size(min = 6, message = "password must be minimum 6 character")
	private String password;
	
	private String imageUrl;
	
	@Column(length = 500)
	
	@NotBlank(message = "about field must not be blank")
	private String about;
	
	private String role;
	
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private List<Employee> contacts = new ArrayList<>();


}
