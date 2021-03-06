package tech.criasystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.criasystem.authentication.JwtTokenUtil;
import tech.criasystem.authentication.UserService;
import tech.criasystem.dto.res.UserResDTO;
import tech.criasystem.model.UserLogin;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private UserService usuarioService;
	
	@GetMapping
	public Boolean validToken() {
		return true;
	}
	
	@PostMapping
    public ResponseEntity<UserResDTO> post(@Valid @RequestBody UserLogin user){
		try{
			if(usuarioService.autentication(user)){
				String jwtToken = new JwtTokenUtil().generateToken(user);
                return new ResponseEntity<UserResDTO>(new UserResDTO(new UserLogin("","",jwtToken)),HttpStatus.OK);
	            }
	            else
	                return new ResponseEntity<UserResDTO>(HttpStatus.UNAUTHORIZED);
	        }
	        catch(Exception ex)
	        {
	        	return new ResponseEntity<UserResDTO>(HttpStatus.BAD_REQUEST);
	        } 
	   }
}
