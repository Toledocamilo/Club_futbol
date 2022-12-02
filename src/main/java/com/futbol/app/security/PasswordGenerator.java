package com.futbol.app.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) { 
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String clave = "12345";
	String encodedClave = encoder.encode(clave);
	
	System.out.println(encodedClave);
	
	}
}
