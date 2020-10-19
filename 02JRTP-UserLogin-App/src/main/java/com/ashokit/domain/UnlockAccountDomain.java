package com.ashokit.domain;

import lombok.Data;

@Data
public class UnlockAccountDomain {
	
	private String email;
	private String tempPwd;
	private String newPwd;
	private String cnfrmPwd;
}
