package com.ashokit.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CITY_MASTER")
@Data
public class CityMasterEntity {
	
		@Id
		@Column(name="CityId")
		private Integer cityId;
		
		@Column(name="City_Name")
		private String cityName;
		
		@Column(name="State_Id")
		private int stateId;
	}

