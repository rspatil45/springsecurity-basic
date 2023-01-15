package springsecurity.controller;

import java.security.Principal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springsecurity.dao.SignupDAO;
import springsecurity.dto.ChangePasswordDTO;
import springsecurity.dto.SignupDTO;

@Controller
public class LoginController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	SignupDAO signupDAO;

	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;

	@GetMapping("/customlogin")
	public String Login() {
		return "login";
	}

	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		return "signup";
	}

	@PostMapping("/process-signup")
	public String processSignup(SignupDTO signupDTO) {
		System.out.println("before encoding " + signupDTO.getPassword());
		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);
		System.out.println("after encoding " + signupDTO.getPassword());
//		signupDAO.saveUser(signupDTO);

//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//		UserDetails usDetails = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword()).roles("Coder").build(); //here role method add ROLE prefix before role (ie. ROLE_Coder)
		UserDetails usDetails = User.withUsername(signupDTO.getUsername()).password(signupDTO.getPassword())
				.authorities("Coder", "Tester").build();
		jdbcUserDetailsManager.createUser(usDetails);

		return "redirect:/customlogin";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.deleteUser(username);
		return "redirect:/customlogin";
	}

	@GetMapping("/changePassword")
	public String getChangePassword(Model model) {
		ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();
		model.addAttribute("changePasswordDTO", changePasswordDTO);
		return "change-password";
	}

	@PostMapping("/changePassword")
	public String submitChangePassword(Principal principal, ChangePasswordDTO changePasswordDTO, Model model) {

		System.out.println(changePasswordDTO);

		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(principal.getName());
		if (changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
				String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
				jdbcUserDetailsManager.changePassword(changePasswordDTO.getOldPassword(), encodedPassword);
				System.out.println("Password changed ....");
				return "redirect:/?passwordChanged";
			}
			return "redirect:/changePassword?invalidPassword";
		}
		return "redirect:/changePassword?invalidPassword";
	}
}
