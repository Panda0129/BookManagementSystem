package com.library.web;

import com.library.domain.Admin;
import com.library.domain.BookInfo;
import com.library.domain.ReaderCard;
import com.library.service.AdminService;
import com.library.service.BookInfoService;
import com.library.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class LoginController {
    private ReaderCardService readerCardService;
    private BookInfoService bookInfoService;
    private AdminService adminService;

    @RequestMapping(value = "/index.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidAdmin = adminService.getMatchAccount(loginCommand.getId(),loginCommand.getPwd());
        boolean isValidReader = readerCardService.hasMatchUser(loginCommand.getId(), loginCommand.getPwd());

        if(!isValidReader && !isValidAdmin) {
            return new ModelAndView("login_error");
        } else if (isValidAdmin){
            Admin admin = adminService.findAdminById(loginCommand.getId());
            request.getSession().setAttribute("admin", admin);
            ArrayList<BookInfo> bookInfos = bookInfoService.showAllBookInfo();
            ModelAndView modelAndView = new ModelAndView("admin_main");
            modelAndView.addObject("bookInfos", bookInfos);
            return modelAndView;
        } else  {
            ReaderCard readerCard = readerCardService.findReaderById(loginCommand.getId());
//            System.out.println(readerCard.getPwd());
            request.getSession().setAttribute("readerCard", readerCard);
            ArrayList<BookInfo> bookInfos = bookInfoService.showAllBookInfo();
            ModelAndView modelAndView = new ModelAndView("reader_main");
            modelAndView.addObject("bookInfos", bookInfos);
            return modelAndView;
        }
    }

    @Autowired
    public void setReaderCardService (ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    @Autowired
    public void setBookInfoService(BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @Autowired
    public void setAdminService (AdminService adminService) {
        this.adminService = adminService;
    }
}
