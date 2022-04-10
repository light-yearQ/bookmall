package bookmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我们应该还需要一个Cart实体代表购物车
 * 购物车
 *
 * @TableName t_cart_item
 */
@TableName(value = "t_cart_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {
	/**
	 *
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

	/**
	 *
	 */
	private Integer book;

	/**
	 *
	 */
	private Integer buyCount;

	/**
	 *
	 */
	private Integer userBean;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	private User user;

	@TableField(exist = false)
	private Book realBook;

	/**
	 * 单种书的价格
	 */
	@TableField(exist = false)
	private Double simpleBookPrice;

	public Double getSimpleBookPrice() {

		return new BigDecimal(this.buyCount + "").multiply(new BigDecimal(this.realBook.getPrice() + "")).doubleValue();
	}


}