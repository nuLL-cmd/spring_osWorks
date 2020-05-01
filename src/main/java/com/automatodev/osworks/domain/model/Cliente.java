package com.automatodev.osworks.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "tb_cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private long id;
	
	@Size(max = 60)
	@NotBlank
	@Column(name = "nm_cliente")
	private String name;
	
	@Size(max = 60)
	@Email
	private String email;
	
	@Size(max = 13)
	@Column(name = "cell_phone")
	private String phone;
	
	public Cliente() {
		
	}

	public Cliente(long id, String name, String email,String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone(){
		return phone;
	}
	

	
	
}
