package bookmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bookmall.pojo.User;
import bookmall.service.UserService;
import bookmall.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-04-09 20:07:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




