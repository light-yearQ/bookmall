package bookmall.controller.manager;

import bookmall.pojo.OrderBean;
import bookmall.pojo.OrderItem;
import bookmall.pojo.User;
import bookmall.service.BookService;
import bookmall.service.OrderBeanService;
import bookmall.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderManagerController {
	@Autowired
	private OrderBeanService orderBeanService;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private BookService bookService;

	@GetMapping("/orderManagerList")
	public String showOrderManagerList(HttpSession session, Integer pageNo, String operate){
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

		QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("id");


		orderBeanService.page(page, queryWrapper);

		session.setAttribute("orderManagerPage", page);

		return "redirect:/orderManager";
	}

	/**
	 * 获取订单详情列表
	 *
	 * @return
	 */
	@GetMapping(value="/orderMessageManagerList")
	public String getOrderMessageList(Integer orderId, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_bean", orderId);
		List<OrderItem> orderItemList = orderItemService.listByMap(map);

		for (OrderItem orderItem : orderItemList) {
			orderItem.setRealBook(bookService.getById(orderItem.getBook()));
		}

		session.setAttribute("orderItemManagerList", orderItemList);
		System.out.println(orderItemList);

		return "redirect:/orderManagerMessage";
	}

	/**
	 * 修改订单状态
	 */
	@GetMapping(value="/updateOrderManagerCondition")
	public String updateOrderCondition(Integer orderStatus, Integer id) {
		if(orderStatus == 0){
			QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("id",id);
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderStatus(1);
			orderBeanService.update(orderBean,queryWrapper);
		}

		if(orderStatus == 1){
			QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("id",id);
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderStatus(0);
			orderBeanService.update(orderBean,queryWrapper);
		}

		if(orderStatus == 3){
			QueryWrapper<OrderBean> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("id",id);
			OrderBean orderBean = new OrderBean();
			orderBean.setOrderStatus(0);
			orderBeanService.update(orderBean,queryWrapper);
		}

		return "redirect:/orderManagerList";
	}
}
