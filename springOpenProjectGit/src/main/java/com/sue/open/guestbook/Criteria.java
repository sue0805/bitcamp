package com.sue.open.guestbook;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;
	private int amount;
	private int startNum;
	private int endNum;
	
	public Criteria() {
		this(1, 3);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public void calcStartNum() {
		startNum = (pageNum -1) * amount;
	}
}
