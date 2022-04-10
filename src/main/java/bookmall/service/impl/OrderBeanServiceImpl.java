package bookmall.service.impl;

import bookmall.pojo.OrderBean;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import bookmall.service.OrderBeanService;
import bookmall.mapper.OrderBeanMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2022-04-10 16:33:42
*/
@Service
public class OrderBeanServiceImpl extends ServiceImpl<OrderBeanMapper, OrderBean>
    implements OrderBeanService{

}




