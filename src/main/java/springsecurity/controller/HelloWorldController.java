package springsecurity.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

	@GetMapping("/")
	public String helloworld(Authentication auth, Principal principal, Model model)
	{
		//authentication extends princiipal so using auth reference we can access methods of principal like getName()
		String username = auth.getName();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		model.addAttribute("username", username);
		model.addAttribute("roles", roles);
		return "home-page";
	}
	
	@ResponseBody
	@GetMapping("/hello")
	public String hello()
	{
		return "hello from rahul patil";
	}
	
	@GetMapping("/bye")
	@ResponseBody
	public String bye()
	{
		return "good night by anjali";
	}
	
	@GetMapping("trainer")
	public String showTrainerDashboard()
	{
		return "trainer-dashboard";
	}
	
	@GetMapping("coder")
	public String showCoderDashboard()
	{
		return "coder-dashboard";
	}
	
	@GetMapping("accessDenied")
	public String error403()
	{
		return "access-denied";
	}
}
