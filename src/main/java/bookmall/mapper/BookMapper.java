package bookmall.mapper;

import bookmall.pojo.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xlf044215
* @description 针对表【t_book】的数据库操作Mapper
* @createDate 2022-04-09 20:14:16
* @Entity bookmall.pojo.Book
*/
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}




