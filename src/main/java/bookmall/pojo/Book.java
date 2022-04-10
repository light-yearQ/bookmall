package bookmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 书本
 * @TableName t_book
 */
@TableName(value ="t_book")
@Data
public class Book implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String bookName;

    /**
     * 
     */
    private String author;

    /**
     * 
     */
    private Double price;

    /**
     * 销量
     */
    private Integer saleCount;

    /**
     * 库存
     */
    private Integer bookCount;

    /**
     * 封面
     */
    private String bookImg;

    /**
     * 书本状态
     */
    private Integer bookStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}