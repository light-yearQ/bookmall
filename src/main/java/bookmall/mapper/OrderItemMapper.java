package bookmall.mapper;

import bookmall.pojo.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xlf044215
* @description 针对表【t_order_item】的数据库操作Mapper
* @createDate 2022-04-09 20:27:34
* @Entity bookmall.pojo.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}




