package javau9.securingjpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/public")
	public String getPublicData() {
				
		return "This is public data";
	}
	
	@GetMapping("/home")
	public String getPrivateData() {
		return "This is private data";
	}
	
	
	
	
	
	
}
