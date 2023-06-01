package com.example.demo.Controller;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Mode.Card;
import com.example.demo.Mode.Gym;
import com.example.demo.Service.GymService;

@Controller
public class GymController {

	@Autowired
	private GymService gymService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/ViewPlans")
	public String viewPlans() {
		return "ViewPlans";
	}

	@GetMapping("/ExploreGym")
	public String explore() {
		return "ExploreGym";
	}

	@GetMapping("/AboutUs")
	public String about() {
		return "AboutUs";
	}

	@GetMapping("/CardPayment")
	public String cardPayment(Model model) {
		Card newCard = new Card();
		model.addAttribute("newCard", newCard);
		return "CardPayment";
	}

	@PostMapping("/cardPayment")
	public String addCard(@ModelAttribute("newCard") Card newCard) {
		gymService.addCard(newCard);
		return "redirect:/gym";
	}

	@GetMapping("/register")
	public String register(Model model) {
		Gym newUsers = new Gym();
		model.addAttribute("newUsers", newUsers);
		return "register.html";
	}

	@PostMapping("/register")
	public String addNewCustomerNow(@ModelAttribute("newUsers") Gym newUsers) {
		gymService.addCustomer(newUsers);
		return "redirect:/gym";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") Integer customerId) {
		gymService.deleteCustomer(customerId);
		return "redirect:/gym";
	}

	@GetMapping("/update/{customerId}")
	public String updateCustomer(@PathVariable("customerId") Integer customerId, Model model) {
		Gym foundCustomer = gymService.getCustomerById(customerId);
		model.addAttribute("foundCustomer", foundCustomer);
		return "Update";
	}

	@PostMapping("/update/{customerId}")
	public String updateCustomer(@PathVariable("customerId") Integer customerId,
			@ModelAttribute("foundCustomer") Gym updateCustomer) {
		gymService.updateCustomer(customerId, updateCustomer);
		return "redirect:/gym";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("newUserList", gymService.getAllCustoemr());
		model.addAttribute("newCardList", gymService.getAllCustoemrCardInfo());
		return"Admin";
	}
}


	
	
	
	


