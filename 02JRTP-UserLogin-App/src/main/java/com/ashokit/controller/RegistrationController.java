package com.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashokit.Service.UserService;
import com.ashokit.domain.UserAccountsDomain;

@Controller
public class RegistrationController {
	@Autowired
	UserService service; 
	
	@ModelAttribute
	public void loadFormData(Model model) {
		UserAccountsDomain user=new UserAccountsDomain();
		Map<Integer, String> countriesentity = service.loadCountries();
		model.addAttribute("cntry",countriesentity);
		model.addAttribute("userobj",user);
	}

	@GetMapping("/register")
	public String loadRegForm(Model model) {
		return "registrationpage";
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam("email") String email) {
		   
		   return service.isUniqueEmail(email)==true ?"Unique Email": "Email already registerd";
		
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) { 
		return service.loadStatesByCountryId(countryId) ;
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam("stateId") Integer stateId) {
		return service.loadCitiesByStateId(stateId);
	}
	
	@PostMapping("/userRegistration")
	public String handleRegisterBtn(UserAccountsDomain userAcc, Model model) {
		
		if(service.saveUserAccount(userAcc)) 
			model.addAttribute("succ","Registerd With Us!!");
			else 
				model.addAttribute("err","Something Went worong!!");
	
		return "registrationpage";
	}

}