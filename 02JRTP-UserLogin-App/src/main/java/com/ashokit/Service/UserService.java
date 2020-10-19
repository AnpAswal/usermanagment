package com.ashokit.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashokit.domain.UserAccountsDomain;

@Service
public interface UserService {
//1.Login Screen	
		public String loginCheck(String email, String pwd);
	//validating user with email and pass a) present in DB or not or b) user is locked or unlocked
		
//2.Registration Screen

	//----------------2.1 dependent dropdowns------------------
		
		
		public Map<Integer, String> loadCountries();
	//will load all countries present in DB	with their id and name present in MAP

		public Map<Integer, String> loadStatesByCountryId(Integer countryId);
	//will load all states present in DB based on ID of country with their id and name present in MAP

		public Map<Integer, String> loadCitiesByStateId(Integer stateId);
	//will load all cities present in DB based on ID of of state with their id and name present in MAP
		
		//---------------2.2 mail validation-----------------	
		
		public boolean isUniqueEmail(String email);
	// to check if mail-id entered  is unquiue or not if unique then proceed
		
	    public boolean saveUserAccount(UserAccountsDomain userAccount);
	// save the user credentials in DB with its state locked and password as temperory
	    
		public String generateTempPwd();
	// to generate temp password and save to DB with user email
		
		public String getRegSuccMailBody(UserAccountsDomain userAccount);
    //generate succes mail structure ,when user is validated
		
		public boolean sendRegSuccEmail(String to, String subject, String body);
	//generate succes mail to user  ,when user is validated
		
	//---------------2.3 Unlock Screen -----------------
		public boolean isTempPwdValid(String email, String tempPwd);
	// to check if temp password is valid or not	
		
		public boolean unlockAccount(String email, String password);
	// to unlock accound if temp pass and user email are validated
		
		public String recoverPassword(String email);
	// to check weather to start recovery pass if forget pass 
		
	 public String getRecoverPwdEmailBody(UserAccountsDomain userAccount);
		 //generate success recovery  mail structure ,when user is validated
		
		public String sendPwdToEmail(String email, String subject, String body);
		 //generate success recovery  mail to user ,when user is validated


	}
