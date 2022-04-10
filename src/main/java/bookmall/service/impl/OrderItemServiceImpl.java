package bookmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bookmall.pojo.OrderItem;
import bookmall.service.OrderItemService;
import bookmall.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_order_item】的数据库操作Service实现
* @createDate 2022-04-09 20:27:34
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




