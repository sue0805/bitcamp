package com.sue.open.member;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Member {

	private int idx;
	private String id;
	private String password;
	private String name;
	private String photo;
	private String regDate;
	private String authCode;
	private int status;
	
}
