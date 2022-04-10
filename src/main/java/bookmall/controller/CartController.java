package bookmall.controller;

import bookmall.pojo.*;
import bookmall.service.BookService;
import bookmall.service.CartItemService;
import bookmall.service.OrderBeanService;
import bookmall.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CartController {
	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderBeanService orderBeanservice;

	@Autowired
	private OrderItemService orderItemservice;

	@RequestMapping(value = "/addToCart")
	public String addToCart(Integer bookId, HttpSession session) {
		//将指定的图书添加到购物车
		//如果当前有这本书，那么将这本书的数量加一，否则新增一本书
		User user = (User) session.getAttribute("user");
		QueryWrapper<CartItem> qw = new QueryWrapper<>();
		qw.eq("book", bookId).eq("user_bean", user.getId());
		CartItem one = cartItemService.getOne(qw);

		if (one != null) {
			one.setBuyCount(one.getBuyCount() + 1);
			cartItemService.updateById(one);
		} else {
			one = new CartItem(null, bookId, null, user.getId(), null, null, null);
			cartItemService.save(one);
		}


		return "redirect:/bookList";
	}

	//获取当前用户的购物车消息
	@GetMapping("/getCart")
	public String getUserCart(HttpSession session) {
		User user = (User) session.getAttribute("user");
		QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_bean", user.getId());
		List<CartItem> cartItemList = cartItemService.list(queryWrapper);
		for (CartItem cartItem : cartItemList) {
			cartItem.setRealBook(bookService.getById(cartItem.getBook()));
		}

		user.setCart(new Cart(cartItemList));

		session.setAttribute("user", user);

		System.out.println(new Cart(cartItemList));

		return "redirect:/cart";
	}

	/**
	 * 付款后
	 *
	 * @return
	 */
	@GetMapping(value = "/pullCartList", params = {"operate=pull"})
	public String pullCartList(HttpSession session) {
		//向订单表添加一条信息
		User user = (User) session.getAttribute("user");
		if (user.getCart().getTotalCount() == 0) {
			session.setAttribute("order_no", "您的订单中没有书本");
			return "redirect:/checkout";
		}

		String s = UUID.randomUUID().toString();
		OrderBean orderBean = new OrderBean(null, s, Calendar.getInstance().getTime(), user.getCart().getTotalMoney(), null, user.getId(), user.getCart().getTotalBookCount(), null, null, null);
		orderBeanservice.save(orderBean);

		//向订单详情表添加信息
		Map<String, Object> map = new HashMap<>();
		map.put("order_no", s);
		List<OrderBean> orderBeans = orderBeanservice.listByMap(map);
		Integer id = orderBeans.get(0).getId();
		for (CartItem cartItem : user.getCart().getCartItemList()) {
			OrderItem orderItem = new OrderItem(null, cartItem.getBook(), cartItem.getBuyCount(), id, null, null, null);
			orderItemservice.save(orderItem);
		}

		//删除购物车信息
		QueryWrapper<CartItem> query = new QueryWrapper<>();
		query.eq("user_bean", user.getId());
		cartItemService.remove(query);

		//减少书的存量,增加书的销量
		for (CartItem cartItem : user.getCart().getCartItemList()) {
			QueryWrapper<Book> qw = new QueryWrapper<>();
			qw.eq("id", cartItem.getBook());
			Book book = bookService.getById(cartItem.getBook());
			book.setSaleCount(book.getSaleCount() + 1);
			book.setBookCount(book.getBookCount() - 1);
			bookService.saveOrUpdate(book, qw);
		}


		session.setAttribute("order_no", s);
		return "redirect:/checkout";
	}

	@GetMapping("/deleteCartItemBook")
	public String deleteCartItemBook(Integer bookId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("book", bookId).eq("user_bean", user.getId());
		cartItemService.remove(queryWrapper);

		return "redirect:/getCart";
	}

	@RequestMapping(value = "/cart", params = {"operate=editCart"})
	public String editCart(Integer cartItemId, Integer buyCount) {
		QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id", cartItemId);

		CartItem cartItem = new CartItem();
		cartItem.setBuyCount(buyCount);

		cartItemService.update(cartItem, queryWrapper);

		return "redirect:/getCart";
	}

	/**
	 * 清空购物车
	 */
	@GetMapping("/clearCart")
	public String clearCart(Integer userId) {
		QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_bean", userId);

		cartItemService.remove(queryWrapper);

		return "redirect:/getCart";
	}
}
