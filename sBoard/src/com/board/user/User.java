package com.board.user;

import java.sql.SQLException;

public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	
	// 생성자 만들기 : 4개의 정보가 다 있어야 User 객체를 만들어야 한다.
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	// password가 동일한지 확인하는 메서드
	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}
	
	// 로그인이 성공했는 확인하는 메서드
	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchExpcetion {
		UserDAO userDAO=new UserDAO();
		User user=null;
		try {
			user = userDAO.findByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user==null) {
			throw new UserNotFoundException(); // userId가 존재하지 않을 경우, 예외 발생
		}
		if(!user.matchPassword(password)) {	// 로그인 실패한 경우
			throw new PasswordMismatchExpcetion();
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}
