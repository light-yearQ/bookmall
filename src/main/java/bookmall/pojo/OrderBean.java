package bookmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单
 *
 * @TableName t_order
 */
@TableName(value = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBean implements Serializable {
	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 *
	 */
	private String orderNo;

	/**
	 *
	 */
	private Date orderDate;

	/**
	 *
	 */
	private Double orderMoney;

	/**
	 * 订单状态
	 * 0:未发货
	 * 1:已发货
	 */
	private Integer orderStatus;

	/**
	 *
	 */
	private Integer orderUser;

	/**
	 *
	 */
	private Integer totalBookCount;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	private User User;

	@TableField(exist = false)
	private List<OrderItem> orderItemList;

	@TableField(exist = false)
	private String orderCondition;

	public String getOrderCondition() {
		if (orderStatus == 0) {
			return "未发货";
		} else if (orderStatus == 1) {
			return "已发货";
		} else if (orderStatus == 2) {
			return "已签收";
		} else {
			return "订单异常";
		}
	}

}