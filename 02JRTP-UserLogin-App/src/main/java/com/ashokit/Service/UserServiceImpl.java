package com.ashokit.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.Entity.CityMasterEntity;
import com.ashokit.Entity.CountryMasterEntity;
import com.ashokit.Entity.StateMasterEntity;
import com.ashokit.Entity.UserEntity;
import com.ashokit.domain.UserAccountsDomain;
import com.ashokit.repository.CityMasterRepo;
import com.ashokit.repository.CountryMasterRepo;
import com.ashokit.repository.StateMasterRepo;
import com.ashokit.repository.UserRepo;
import com.ashokit.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	CityMasterRepo repo1;

	@Autowired
	StateMasterRepo repo2;

	@Autowired
	CountryMasterRepo repo3;

	@Autowired
	UserRepo repo4;
	
	@Autowired
	EmailUtils emailutil;

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countrymap = new HashMap<>();
		List<CountryMasterEntity> all = repo3.findAll();

		all.forEach(e -> {
			countrymap.put(e.getCountryId(), e.getCountryName());
		});

		return countrymap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer, String> statemap = new HashMap<>();
		List<StateMasterEntity> all = repo2.findBycountryId(countryId);

		all.forEach(e -> {
			statemap.put(e.getStateId(), e.getStateName());
		});

		return statemap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer, String> citymap = new HashMap<>();
		List<CityMasterEntity> all = repo1.findBystateId(stateId);

		all.forEach(e -> {
			citymap.put(e.getCityId(), e.getCityName());
		});

		return citymap;
	}

	@Override
	public boolean isUniqueEmail(String email) {

		UserEntity entity = repo4.findByEmail(email);

		return !(entity!=null &&(entity.getUserId()!=null));
	}

	@Override
	public boolean saveUserAccount(UserAccountsDomain userAccount) {
		userAccount.setAccstatus("LOCKED");
		userAccount.setPwd(generateTempPwd());
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userAccount,entity);
	    UserEntity savedEntity = repo4.save(entity);
	    //send email if succesfully inserted
	    if(savedEntity!=null &&(savedEntity.getUserId()!=null))
	    {
	    	String to= userAccount.getEmail();
	        String subject="--------Confirm Your Mail------";
	        String body=  getRegSuccMailBody(userAccount);
	    	return emailutil.sendEmail(to, subject, body) ? true : false ;
	    }
	    return false;
	}

	@Override
	public String generateTempPwd() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
	            +"!@#$%^&*?"+ "0123456789" + "abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString 
		StringBuilder temppwd = new StringBuilder(6);

		for (int i = 0; i < 6; i++) {

// generate a random number between 
// 0 to AlphaNumericString variable length 
			int index = (int) (AlphaNumericString.length() * Math.random());

// add Character one by one in end of sb 
			temppwd.append(AlphaNumericString.charAt(index));
		}

		return temppwd.toString();
	}

	@Override
	public String getRegSuccMailBody(UserAccountsDomain user) {
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			 Stream<String> lines = Files.lines(path);

			 replacedLines = lines.map(line -> line.replace("{FNAME}", user.getFname())
					 .replace("{LNAME}", user.getLname())
					 .replace("{TEMP-PWD}", user.getPwd())
					 .replace("{EMAIL}", user.getEmail()))
					 .collect(Collectors.toList());

			mailBody = String.join("", replacedLines);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public boolean sendRegSuccEmail(String to, String subject, String body) {
	
		return emailutil.sendEmail(to, subject, body);
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		
		UserEntity entity = repo4.findByEmailAndPwd(email,tempPwd);

		return (entity!=null &&(entity.getUserId()!=null));
		
	}

	@Override
	public boolean unlockAccount(String email, String password) {

		UserEntity entity = repo4.findByEmail(email);

		if(entity!=null &&(entity.getUserId()!=null))
		{
			entity.setPwd(password);
			entity.setAccstatus("UNLOCKED");
			UserEntity savedentity = repo4.save(entity);
			return (savedentity!=null &&(savedentity.getUserId()!=null));
			
		}
		return false;
	}

	@Override
	public String recoverPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccountsDomain userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendPwdToEmail(String email, String subject, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginCheck(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

}
