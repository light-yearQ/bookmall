package bookmall.mapper;

import bookmall.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xlf044215
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-04-09 20:07:58
* @Entity bookmall.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




