package bookmall.controller;

import bookmall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
	@Autowired
	private BookService bookService;

	@GetMapping(value = {"/login", "/"})
	public String showLogin() {
		return "user/login";
	}

	@RequestMapping("/index")
	public String showIndex(){
		return "index";
	}

	@GetMapping("/cart")
	public String showCart(){
		return "cart/cart";
	}

	@GetMapping("/checkout")
	public String showCheckout(){
		return "/cart/checkout";
	}

	@GetMapping("/order")
	public String showOrder(){
		return "/order/order";
	}

	@GetMapping("/regist")
	public String showRegist(){
		return "user/regist";
	}

	@GetMapping("/orderMessage")
	public String showOrderMessage(){
		return "order/order_message";
	}

	@GetMapping("/manager")
	public String showManager(){
		return "manager/manager";
	}

	@GetMapping("/bookManager")
	public String showBookManager(){
		return "manager/book_manager";
	}

	@GetMapping("/orderManager")
	public String showOrderManager(){
		return "manager/order_manager";
	}

	@GetMapping("/bookAdd")
	public String showBookEdit(){
		return "manager/book_add";
	}

	@GetMapping("/bookUpdate")
	public String showBookUpdate(Integer bookId, Model model){
		model.addAttribute("book", bookService.getById(bookId));
		return "manager/book_update";
	}

	@GetMapping("/orderManagerMessage")
	public String showOrderManagerMessage(){
		return "manager/order_manager_message";
	}
}
