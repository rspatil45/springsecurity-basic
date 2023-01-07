package springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@GetMapping("/greet/{yourName}")
	public String greet(@PathVariable("yourName") String name)
	{
		return "Hello "+name;
	}
}
