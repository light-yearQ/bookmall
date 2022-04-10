package bookmall.service.impl;

import bookmall.pojo.CartItem;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bookmall.service.CartItemService;
import bookmall.mapper.CartItemMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_cart_item】的数据库操作Service实现
* @createDate 2022-04-10 00:16:38
*/
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem>
    implements CartItemService{

}




