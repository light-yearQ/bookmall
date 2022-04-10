package bookmall.controller;

import bookmall.pojo.Book;
import bookmall.pojo.CartItem;
import bookmall.pojo.User;
import bookmall.service.BookService;
import bookmall.service.CartItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	@Autowired
	private CartItemService cartItemService;

	@RequestMapping("/bookList")
	public String showBookList(HttpSession session, Integer pageNo, String operate) {
		if (pageNo == null) {
			pageNo = 1;
		}

		//分页显示条数
		int size = 10;

		Page<Book> page = null;

		//判断是上一页还是下一页
		if (operate == null) {
			page = new Page<>(pageNo, size);
		} else if ("pageUp".equals(operate)) {
			page = new Page<>(pageNo - 1, size);
		} else if ("pageDown".equals(operate)) {
			page = new Page<>(pageNo + 1, size);
		}

		QueryWrapper<Book> query = new QueryWrapper<>();
		query.eq("book_status", 0);
		bookService.page(page, query);

		session.setAttribute("page", page);
		System.out.println(page.getPages());
		System.out.println(page.getCurrent());

		/**
		 * 获取购物车中书的数量
		 */
		User user = (User) session.getAttribute("user");
		QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_bean", user.getId());
		List<CartItem> cartItemList = cartItemService.list(queryWrapper);
		session.setAttribute("cartCount", cartItemList.size());


		return "redirect:/index";
	}

}
