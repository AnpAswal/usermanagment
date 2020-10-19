package com.ashokit.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="COUNTRY_MASTER")
@Data
public class CountryMasterEntity {
	
		@Id
		@Column(name="CountryId")
		private int countryId;
		
		@Column(name="Country_Code")
		private String countryCode;
		
		@Column(name="Country_Name")
		private String countryName;
}
