package com.ashokit.Entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Entity
@Table(name="User_Entity")
public class UserEntity {
	
	@Id
	@Column(name="User_Id")
	@GeneratedValue
	private Integer userId;
	
	@Column(name="First_Name")
	private String fname;
	
	@Column(name="Last_Name")
	private String lname;
	
	@Column(unique = true)
	private String email;
	
	@Column(name="Password")
	private String pwd;
	
	@Column(name="MobileNumber")
	private Long phno;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DateofBirth")
	private Date dob;
	
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="City")
	private Integer cityId;
	
	@Column(name="State")
	private Integer stateId;
	
	@Column(name="Country")
	private String cntryId;
		
	@Column(name="Updated_Date")
	@UpdateTimestamp
	private Date updateddate;
  
	@CreationTimestamp
	@Column(name="Created_Date")
	private Date createddate;
	
	@Column(name="Account_Status")
	private String accstatus;
	


}
