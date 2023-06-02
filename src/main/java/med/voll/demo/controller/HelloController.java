package med.voll.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/hello")
public class HelloController {
	
	@GetMapping
	public String helloword() {
		return "hello word dasdsad";
	}
	
}
