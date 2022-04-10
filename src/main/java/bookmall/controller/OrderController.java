package bookmall.controller;

import bookmall.pojo.Book;
import bookmall.pojo.OrderBean;
import bookmall.pojo.OrderItem;
import bookmall.pojo.User;
import bookmall.service.BookService;
import bookmall.service.OrderBeanService;
import bookmall.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
	@Autowired
	private OrderBeanService orderBeanService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/orderList")
	public String getOrderList(HttpSession session, Integer pageNo, String operate) {
		if (pageNo == null) {
			pageNo = 1;
		}

		//分页显示条数
		int size = 10;

		Page<OrderBean> page = null;

		//判断是上一页还是下一页
		if (operate == null) {
			page = new Page<>(pageNo, size);
		} else if ("pageUp".equals(operate)) {
			page = new Page<>(pageNo - 1, size);
		} else if ("pageDown".equals(operate)) {
			page = new Page<>(pageNo + 1, size);
		}

		User user = (User) session.getAttribute("user");
		QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("order_user", user.getId()).orderByDesc("id");

		orderBeanService.page(page, queryWrapper);
		System.out.println(page.getPages());
		System.out.println(page.getCurrent());

		session.setAttribute("orderPage", page);

		return "redirect:/order";
	}

	/**
	 * 获取订单详情列表
	 *
	 * @return
	 */
	@GetMapping("/orderMessageList")
	public String getOrderMessageList(Integer orderId, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_bean", orderId);
		List<OrderItem> orderItemList = orderItemService.listByMap(map);

		for (OrderItem orderItem : orderItemList) {
			orderItem.setRealBook(bookService.getById(orderItem.getBook()));
		}

		session.setAttribute("orderItemList", orderItemList);

		return "redirect:/orderMessage";
	}

	/**
	 * 修改订单状态
	 */
	@GetMapping("/updateOrderCondition")
	public String updateOrderCondition(Integer orderStatus, Integer id) {
		if(orderStatus == 1){
			QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("id",id);
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderStatus(2);
			orderBeanService.update(orderBean,queryWrapper);
		}

		return "redirect:/orderList";
	}
}
