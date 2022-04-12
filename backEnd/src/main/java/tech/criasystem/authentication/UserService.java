package tech.criasystem.authentication;

import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import tech.criasystem.model.UserLogin;

@Service
public class UserService {
	
	public Boolean userExists(UserLogin user) {
		if(user.getUsername().equals("user")) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean autentication(UserLogin user) throws NoSuchAlgorithmException {
		if(user.getUsername().equals("user") && user.getPassword().equals("123")) {
			return true;
		} else {
			return false;
		}
	}
}
