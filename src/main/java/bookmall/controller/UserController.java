package bookmall.controller;

import bookmall.pojo.Admin;
import bookmall.pojo.Book;
import bookmall.pojo.CartItem;
import bookmall.pojo.User;
import bookmall.service.AdminService;
import bookmall.service.BookService;
import bookmall.service.CartItemService;
import bookmall.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private CartItemService cartItemService;

	@PostMapping(value = "/login", params = "operate=login")
	public String login(String uname, String pwd, HttpSession session, String userType) {
		if ("user".equals(userType)) {
			LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
			queryWrapper.eq(User::getUname, uname).eq(User::getPwd, pwd);
			User user = userService.getOne(queryWrapper, false);
			if (user != null) {
				session.setAttribute("user", user);
				return "redirect:/bookList";
			} else {
				return "redirect:/login";
			}
		} else {
			LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
			queryWrapper.eq(Admin::getAdminName, uname).eq(Admin::getAdminPwd, pwd);
			Admin admin = adminService.getOne(queryWrapper, false);
			if (admin != null) {
				session.setAttribute("admin", admin);
				return "redirect:/manager";
			} else {
				return "redirect:/login";
			}
		}


	}

	/**
	 * 注销用户
	 */
	@GetMapping(value = "/deleteUser", params = {"operate=deleteUser"})
	public String deleteUser(HttpSession session) {
		session.setAttribute("user", null);
		return "redirect:/login";
	}

	/**
	 * 注册用户
	 */
	@PostMapping("/addUser")
	public String addUser(String uname, String pwd, String ok, String email, Model model) {
		User user = new User();
		user.setUname(uname);
		user.setPwd(pwd);
		user.setEmail(email);

		System.out.println(user + "    " + ok);

		if (!pwd.equals(ok)) {
			return "redirect:/regist";
		}


		userService.save(user);

		model.addAttribute("addUser", user);

		return "redirect:/login";
	}

}
