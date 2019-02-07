package com.sue.open.member;

import java.sql.Date;

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
	private Date regDate;
	
}
