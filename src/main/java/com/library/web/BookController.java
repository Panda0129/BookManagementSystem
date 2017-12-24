package com.library.web;

import com.library.domain.BookInfo;
import com.library.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BookController {

    private BookInfoService bookInfoService;

    @RequestMapping("/allBooks.html")
    public ModelAndView showAllBookInfo() {
        ArrayList<BookInfo> bookInfos = bookInfoService.showAllBookInfo();
        ModelAndView modelAndView = new ModelAndView("reader_main");
        modelAndView.addObject("bookInfos", bookInfos);
        return modelAndView;
    }

    @RequestMapping("/bookInfo.html")
    public ModelAndView showBookInfo(HttpServletRequest request) {
        BookInfo bookInfo = bookInfoService.showBookInfo(request.getParameter("id"));
        System.out.println(bookInfo.getName());
        ModelAndView modelAndView = new ModelAndView("reader_bookInfo");
        modelAndView.addObject("bookInfo", bookInfo);
        return modelAndView;
    }

    @RequestMapping("/searchBook.html")
    public ModelAndView searchBook () {
        return new ModelAndView("reader_search");
    }

    @RequestMapping("/searchBookDo.html")
    public ModelAndView searchBookDo (String input) {
        boolean isExist = bookInfoService.hasMatchBook(input);
        if(isExist) {
            ArrayList<BookInfo> bookInfos = bookInfoService.queryBook(input);
            ModelAndView modelAndView = new ModelAndView("reader_search_info");
            modelAndView.addObject("bookInfos", bookInfos);
            return modelAndView;
        } else {
            return new ModelAndView("reader_search_error");
        }
    }


    @Autowired
    public void setBookInfoService(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }
}
