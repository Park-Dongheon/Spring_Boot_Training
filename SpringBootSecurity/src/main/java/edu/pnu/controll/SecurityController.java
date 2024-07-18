package edu.pnu.controll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	// controller return 은 view name
public class SecurityController {
	
	 @GetMapping({"/", "/index"})
	 public String index() {	// index.html
		 System.out.println("index 요청입니다.");
		 return"index";
	 }
	 
	 @GetMapping("/member")
	 public void member() {		// member.html
		 System.out.println("Member 요청입니다.");
	 }
	 
	 @GetMapping("/manager")
	 public void manager() {	// manager.html
		 System.out.println("Manager 요청입니다.");
	 }
	 
	 @GetMapping("/admin")
	 public void admin() {		// admin.html
		 System.out.println("Admin 요청입니다.");
	 }
	 
}
