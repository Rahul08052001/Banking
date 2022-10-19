package com.LoginRegister.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TempRegister")
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String lastName;
	@Column(unique = true) 	
	private String emailID;
	private String address;
	@Column(unique = true)           
	private String govtId;
	@Column(unique = true)
	private long phoneNumber;
	private String dob;
	private String password;
    @Column(columnDefinition = "boolean default false")
	private boolean status;
	


}

