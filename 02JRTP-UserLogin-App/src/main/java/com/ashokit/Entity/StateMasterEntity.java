package com.ashokit.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="STATE_MASTER")
@Data
public class StateMasterEntity {
		
	    @Id
		@Column(name="StateId")
		private int stateId;
		
		@Column(name="Country_ID")
		private int countryId;
		
		@Column(name="State_Name")
		private String stateName;
		
		
}
