package user;

import java.sql.Date;

public class User {
	private String id;
	private String password;
	private String name;
	private String photo;
	private int idx;
	private Date regDate;
	
	public User() {}
	
	
	public User(String id, String password, String name, String photo, int idx) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.photo = photo;
		this.idx = idx;
	}
	
	public User(String id, String password, String name, String photo, int idx, Date regDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.photo = photo;
		this.idx = idx;
		this.regDate = regDate;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Date getRegDate() {
		return regDate;
	}


	@Override
	public String toString() {
		return "[id=" + id + ", password=" + password + ", name=" + name + ", photo=" + photo + ", idx=" + idx
				+ ", regDate=" + regDate + "]";
	}

	
}
