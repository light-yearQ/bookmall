package bookmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bookmall.pojo.Admin;
import bookmall.service.AdminService;
import bookmall.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_admin】的数据库操作Service实现
* @createDate 2022-04-10 20:05:49
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




