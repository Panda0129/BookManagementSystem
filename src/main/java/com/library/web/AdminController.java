package com.library.web;

import com.library.domain.BookInfo;
import com.library.domain.LendList;
import com.library.domain.ReaderCard;
import com.library.domain.ReaderInfo;
import com.library.service.BookInfoService;
import com.library.service.LendService;
import com.library.service.ReaderCardService;
import com.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Controller
public class AdminController {

    private BookInfoService bookInfoService;
    private ReaderCardService readerCardService;
    private ReaderInfoService readerInfoService;
    private LendService lendService;

    @Autowired
    public void setReaderInfoService (ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    @Autowired
    public void setReaderCardService (ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    @Autowired
    public void setBookInfoService (BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @Autowired
    public void setLendService (LendService lendService) {
        this.lendService = lendService;
    }

    @RequestMapping(value = "/admin_allBooks.html", method = RequestMethod.POST)
    public ModelAndView showAllBookInfoAdmin() {
        ArrayList<BookInfo> bookInfos = bookInfoService.showAllBookInfo();
        ModelAndView modelAndView = new ModelAndView("admin_main");
        modelAndView.addObject("bookInfos", bookInfos);
        return modelAndView;
    }

    @RequestMapping("admin_bookInfo.html")
    public ModelAndView showBookInfoAdmin(HttpServletRequest request) {
        BookInfo bookInfo = bookInfoService.showBookInfo(request.getParameter("id"));
        System.out.println(bookInfo.getName());
        ModelAndView modelAndView = new ModelAndView("admin_bookInfo");
        modelAndView.addObject("bookInfo", bookInfo);
        return modelAndView;
    }

    @RequestMapping("admin_searchBook.html")
    public ModelAndView searchBookAdmin () {
        return new ModelAndView("admin_search");
    }

    @RequestMapping("admin_searchBookDo.html")
    public ModelAndView searchBookDoAdmin(String input) {
        boolean isExist = bookInfoService.hasMatchBook(input);
        if(isExist) {
            ArrayList<BookInfo> bookInfos = bookInfoService.queryBook(input);
            ModelAndView modelAndView = new ModelAndView("admin_search_info");
            modelAndView.addObject("bookInfos", bookInfos);
            return modelAndView;
        } else {
            return new ModelAndView("admin_search_error");
        }
    }

    @RequestMapping("admin_addBook.html")
    public ModelAndView addBookAdmin() {
        HashMap<Integer, String> bookClass = bookInfoService.getBookClass();
        ModelAndView modelAndView = new ModelAndView("admin_addBook");
        modelAndView.addObject("bookClass", bookClass);
        return modelAndView;
    }

    @RequestMapping("admin_addBookDo.html")
    public ModelAndView addBookDoAdmin (AddBookCommand addBookCommand) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(addBookCommand.getPubdate());
        BookInfo bookInfo = new BookInfo();
        bookInfo.setName(addBookCommand.getName());
        bookInfo.setBook_id(addBookCommand.getBook_id());
        bookInfo.setState(addBookCommand.getState());
        bookInfo.setPubdate(date);
        bookInfo.setLink(addBookCommand.getLink());
        bookInfo.setPublish(addBookCommand.getPublish());
        bookInfo.setPrice(addBookCommand.getPrice());
        bookInfo.setClass_id(addBookCommand.getClass_id());
        bookInfo.setPressmark(addBookCommand.getPressmark());
        bookInfo.setLanguage(addBookCommand.getLanguage());
        bookInfo.setISBN(addBookCommand.getISBN());
        bookInfo.setAuthor(addBookCommand.getAuthor());
        int count = bookInfoService.hasRepeatBook(bookInfo.getBook_id());

        if(count <= 0) {
            if (bookInfoService.addNewBook(bookInfo)) {
                return new ModelAndView("admin_addBookSuccess");
            } else {
                return new ModelAndView("admin_addBookFail");
            }
        } else {
            return new ModelAndView("admin_addBookRepeat");
        }
    }

    @RequestMapping("admin_searchReader.html")
    public ModelAndView searchReader() {
        return new ModelAndView("admin_searchReader");
    }

    @RequestMapping("admin_searchReaderDo.html")
    public ModelAndView searchReaderDo (String input) {
        if(readerInfoService.matchReader(input)){
            ModelAndView modelAndView = new ModelAndView("admin_searchReaderInfo");
            ArrayList<ReaderInfo> readerInfos = readerInfoService.queryReader(input);
            ArrayList<ReaderCard> readerCards = new ArrayList<ReaderCard>();
            for(int i = 0; i < readerInfos.size(); i++) {
                ReaderCard readerCard = readerCardService.findReaderById(readerInfos.get(i).getId());
                readerCard.setState(readerCard.getState());
                readerCards.add(readerCard);
            }
            modelAndView.addObject("readerInfos",readerInfoService.queryReader(input));
            modelAndView.addObject("readerCards", readerCards);
            return modelAndView;
        } else {
            return new ModelAndView("admin_searchReaderError");
        }
    }

    @RequestMapping("admin_addReader.html")
    public ModelAndView addReaderAdmin() {
        return new ModelAndView("admin_addReader");
    }

    @RequestMapping("admin_addReaderDo.html")
    public ModelAndView addReaderAdminDo(String reader_id, String name, String pwd, int state) {
        ReaderCard readerCard = new ReaderCard();
        readerCard.setReader_id(reader_id);
        readerCard.setName(name);
        readerCard.setPwd(pwd);
        readerCard.setState(state);
        if(readerCardService.findReaderById(reader_id).getReader_id() == null) {
            if (readerCardService.addReaderCard(readerCard)) {
                return new ModelAndView("admin_addReaderSuccess");
            } else {
                return new ModelAndView("admin_addReaderFail");
            }
        } else {
            return new ModelAndView("admin_addReaderRepeat");
        }

    }

    @RequestMapping("admin_deleteReader.html")
    public String deleteReaderAdmin(String reader_id) {
        readerCardService.deleteReaderCard(reader_id);
        return "redirect:/admin_readerList.html";
    }

    @RequestMapping("admin_deleteBook.html")
    public String deleteBookAdmin (String book_id) {
        bookInfoService.deleteBook(book_id);
        return "redirect:/admin_allBooks.html";
    }

    @RequestMapping("admin_reportBookLoss.html")
    public String reportBookLoss(String book_id) {
        BookInfo bookInfo = bookInfoService.showBookInfo(book_id);
        if(bookInfo.getState() == 0) {
            bookInfoService.reportBookLoss(book_id);
            return "redirect:/admin_reportLossDone.html";
        } else {
            return "redirect:/admin_illegal_report.html";
        }
    }

    @RequestMapping("admin_reportLossDone.html")
    public ModelAndView reportBookLossDone() {
        return new ModelAndView("admin_reportLossDone");
    }

    @RequestMapping("admin_illegal_report.html")
    public ModelAndView illegalReport() {
        return new ModelAndView("admin_illegal_report");
    }

    @RequestMapping("admin_readerList.html")
    public ModelAndView readerListAdmin() {
        ArrayList<ReaderInfo> readerInfos = readerInfoService.showAllReaderInfo();
        ArrayList<ReaderCard> readerCards = readerCardService.showAllReaderCard();
        for(int i = 0; i < readerCards.size(); i++) {
            readerCards.get(i).setLendCount(lendService.lendCount(readerCards.get(i).getReader_id()));
        }
        ModelAndView modelAndView = new ModelAndView("admin_readerList");
        modelAndView.addObject("readerInfos", readerInfos);
        modelAndView.addObject("readerCards", readerCards);
        return modelAndView;
    }
}
