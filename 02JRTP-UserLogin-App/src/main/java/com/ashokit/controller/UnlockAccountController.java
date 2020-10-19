package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.Service.UserService;
import com.ashokit.domain.UnlockAccountDomain;

@Controller
public class UnlockAccountController {
	@Autowired
	UserService service;

	/**
	 * This method is used load unlock-account form
	 */
	@GetMapping("/unlockAcc")
	public String loadUnlockAccForm(@RequestParam("email") String email, Model model) {
		UnlockAccountDomain unlck = new UnlockAccountDomain();
		unlck.setEmail(email);
		model.addAttribute("unlockobj", unlck);

		return "unlockpage";
	}

	/**
	 * This method is used to handle unlock-account form submission
	 * 
	 * @param unlockAcc
	 * @param model
	 * @return String
	 */
	@PostMapping("/unlockAcc")
	public String handleSubmitBtn(@ModelAttribute("unlockobj") UnlockAccountDomain unlockAcc, Model model) {

		
		
		if (service.isTempPwdValid(unlockAcc.getEmail(), unlockAcc.getTempPwd())) {

			if (service.unlockAccount(unlockAcc.getEmail(), unlockAcc.getCnfrmPwd())) {
				model.addAttribute("succ", "Account Unlocked go to <a href=\"\">Login Here</a>");
			}

			else {
				model.addAttribute("err", "Somehing went worng !!!");
			}

		} else {
			model.addAttribute("err", "Enter Valid Temp Password");
		}

		return "unlockpage";
	}

}
