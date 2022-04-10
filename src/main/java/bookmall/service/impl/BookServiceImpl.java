package bookmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import bookmall.pojo.Book;
import bookmall.service.BookService;
import bookmall.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author xlf044215
* @description 针对表【t_book】的数据库操作Service实现
* @createDate 2022-04-09 20:14:16
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




