package bookmall.controller.manager;

import bookmall.pojo.Book;
import bookmall.service.BookService;
import bookmall.service.DownAndUpService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class BookManagerController {
	@Autowired
	private BookService bookService;

	@Autowired
	private DownAndUpService downAndUpService;

	@GetMapping("/bookManagerList")
	public String getBookManagerList(HttpSession session, Integer pageNo, String operate) {
		if (pageNo == null) {
			pageNo = 1;
		}

		//分页显示条数
		int size = 10;

		Page<Book> page = null;

		//判断是上一页还是下一页
		if (operate == null) {
			page = new Page<>(pageNo, size);
		} else if ("pageUp".equals(operate)) {
			page = new Page<>(pageNo - 1, size);
		} else if ("pageDown".equals(operate)) {
			page = new Page<>(pageNo + 1, size);
		}

		QueryWrapper<Book> query = new QueryWrapper<>();
		query.eq("book_status", 0);
		bookService.page(page, query);

		session.setAttribute("pageManager", page);

		return "redirect:/bookManager";
	}

	/**
	 * 添加图书
	 */
	@PostMapping("/addBook")
	public String addBook(String bookName, MultipartFile bookImg, Double price, String author, Integer saleCount, Integer bookCount) {
		Book book = new Book();
		book.setBookName(bookName);
		book.setPrice(price);
		book.setAuthor(author);
		book.setSaleCount(saleCount);
		book.setBookCount(bookCount);
		System.out.println(bookImg);

		try {
			String s = downAndUpService.upFile(bookImg, "D:\\BookMallResources\\downLoadImgs");
			book.setBookImg(s);

			bookService.save(book);
		} catch (IOException e) {
			e.printStackTrace();
		}


		return "redirect:/bookManagerList";
	}

	/**
	 * 修改书本
	 */
	@PostMapping("/updateManagerBook")
	public String updateBook(Integer id, String bookName, Double price, String author, Integer saleCount,Integer bookCount) {
		Book book = new Book();
		book.setId(id);
		book.setBookName(bookName);
		book.setPrice(price);
		book.setAuthor(author);
		book.setSaleCount(saleCount);
		book.setBookCount(bookCount);

		System.out.println("******************************************");
		System.out.println(book);
		System.out.println("******************************************");

		QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("id",id);

		bookService.update(book,queryWrapper);

		return "redirect:/bookManagerList";
	}

	/**
	 * 删除书本
	 */
	@GetMapping("/deleteBook")
	public String deleteBook(Integer bookId) {
		bookService.removeById(bookId);

		return "redirect:/bookManagerList";
	}

}
