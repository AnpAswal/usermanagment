package com.ashokit.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class UserAccountsDomain {
	
	private Integer userId;
	
	private String fname;

	private String lname;	

	private String email;
		
	private String pwd;	

	private Long phno;	

	private Date dob;	
	
	private String gender;
	
	private Integer cityId;

	private Integer stateId;
	
	private String cntryId;
		
	private Date updateddate;

	private Date createddate;
	
	private String accstatus;

}
