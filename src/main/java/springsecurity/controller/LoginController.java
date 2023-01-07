package springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import springsecurity.dao.SignupDAO;
import springsecurity.dto.SignupDTO;

@Controller
public class LoginController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SignupDAO signupDAO;
	
	@GetMapping("/customlogin")
	public String Login()
	{
		return "login";		
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO)
	{
		return "signup";
	}
	
	@PostMapping("/process-signup")
	public String processSignup(SignupDTO signupDTO)
	{
		System.out.println("before encoding "+signupDTO.getPassword());
		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);
		System.out.println("after encoding "+signupDTO.getPassword());
		signupDAO.saveUser(signupDTO);
		return "redirect:/customlogin";
	}	
}
