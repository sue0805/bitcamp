package com.sue.open.guestbook;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Guestbook {
	private int no;
	private String gname;
	private String gpassword;
	private String gcontent;
}
