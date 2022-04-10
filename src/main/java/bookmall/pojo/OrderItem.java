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
 * 订单详情
 * @TableName t_order_item
 */
@TableName(value ="t_order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
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
    private Integer orderBean;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Book realBook;

    @TableField(exist = false)
    private OrderBean realOrderBean;

    @TableField(exist = false)
    private Double simpleBookPrice;

    public Double getSimpleBookPrice(){
        return new BigDecimal(this.buyCount + "").multiply(new BigDecimal(this.realBook.getPrice() + "")).doubleValue();
    }
}