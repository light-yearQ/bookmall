package bookmall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Cart {
	/**
	 * 购物车中的属性
	 */
	private List<CartItem> cartItemList;

	/**
	 * 购物车的总金额
	 */
	private Double totalMoney;

	/**
	 * 购物车中购物项的数量
	 */
	private Integer totalCount;

	/**
	 * 书本总量
	 */
	private Integer totalBookCount;

	public Cart() {

	}

	public Cart(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}

	public Double getTotalMoney() {
		BigDecimal sum = new BigDecimal("0.0");
		BigDecimal multiResult;
		if (cartItemList != null && cartItemList.size() > 0) {
			for (CartItem cartItem : cartItemList) {
				multiResult = new BigDecimal(cartItem.getRealBook().getPrice() + "").multiply(new BigDecimal(cartItem.getBuyCount() + ""));
				sum = sum.add(multiResult);
			}
		}

		return sum.doubleValue();
	}

	public Integer getTotalCount() {
		totalCount = 0;
		if (cartItemList != null && cartItemList.size() > 0) {
			totalCount = cartItemList.size();
		}

		return totalCount;
	}

	public Integer getTotalBookCount() {
		totalBookCount = 0;
		if (cartItemList != null && cartItemList.size() > 0) {
			for (CartItem cartItem : cartItemList) {
				totalBookCount += cartItem.getBuyCount();
			}
		}

		return totalBookCount;
	}
}
