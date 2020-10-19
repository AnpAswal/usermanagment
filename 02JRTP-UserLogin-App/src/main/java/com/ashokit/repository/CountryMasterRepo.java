package com.ashokit.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ashokit.Entity.CountryMasterEntity;

public interface CountryMasterRepo extends JpaRepository <CountryMasterEntity, Serializable> {

	

}
