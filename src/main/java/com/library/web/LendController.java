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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class LendController {

    private LendService lendService;
    private BookInfoService bookInfoService;
    private ReaderInfoService readerInfoService;
    private ReaderCardService readerCardService;

    @Autowired
    public void setReaderCardService (ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    @Autowired
    public void setReaderInfoService (ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    @Autowired
    public void setBookInfoService (BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @Autowired
    public void setLendService (LendService lendService) {
        this.lendService = lendService;
    }

    @RequestMapping("reader_my_lendList.html")
    public ModelAndView myLendList (HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ModelAndView modelAndView = new ModelAndView("reader_myLendList");
        modelAndView.addObject("reader_lend_list", lendService.myLendList(readerCard.getReader_id(), readerCard.getState()));
        return modelAndView;
    }

//    @RequestMapping("/reader_lend_book.html")
//    public ModelAndView lendBook() {
//        return new ModelAndView("reader_lendBook");
//    }

    @RequestMapping("/reader_lend_book_do.html")
    public String lendBookDo(HttpServletRequest request, String book_id) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        BookInfo bookInfo = bookInfoService.showBookInfo(book_id);
        if(bookInfo.getState() == 1) {
            if (lendService.lendBook(book_id, readerCard.getReader_id(), readerCard.getState())) {
                return "redirect:/reader_my_lendList.html";
            } else {
                return "redirect:/reader_over_lend.html";
            }
        } else {
            return "redirect:/lend_book_out.html";
        }
    }

    @RequestMapping("reader_over_lend.html")
    public ModelAndView lendBookOver() {
        return new ModelAndView("reader_over_lend");
    }

    @RequestMapping("/lend_book_out.html")
    public  ModelAndView lendBookOut() {
        return new ModelAndView("reader_lend_book_out");
    }

    @RequestMapping("/return_book.html")
    public String returnBook(String book_id) {
        System.out.println(book_id);
        BookInfo bookInfo = bookInfoService.showBookInfo(book_id);
        System.out.println(bookInfo.getState());
        if(bookInfo.getState() != -1) {
            if (bookInfo.getState() == 0) {
                if (lendService.returnBook(book_id)) {
                    return "redirect:/admin_return_book_success.html";
                } else {
                    return "redirect:/admin_return_book_error.html";
                }
            } else {
                return "redirect:/admin_repeat_return.html";
            }
        } else {
            return "redirect:/admin_return_loss_book.html";
        }
    }

    @RequestMapping("/admin_return_loss_book.html")
    public ModelAndView returnLossBook() {
        return new ModelAndView("admin_returnLossBook");
    }

    @RequestMapping("/admin_return_book_success.html")
    public ModelAndView returnBookSuccess() {
        return new ModelAndView("admin_return_book_success");
    }

    @RequestMapping("/admin_return_book_error.html")
    public ModelAndView returnBookFail() {
        return new ModelAndView("admin_return_book_error");
    }

    @RequestMapping("/admin_repeat_return.html")
    public ModelAndView returnBookRepeat() {
        return new ModelAndView("admin_return_book_repeat");
    }

    @RequestMapping("/admin_lend_list.html")
    public ModelAndView adminLendList(HttpServletRequest request) {
        ArrayList<LendList> lendLists = lendService.adminLendList();
        ArrayList<ReaderInfo> readerInfos = new ArrayList<ReaderInfo>();
        ArrayList<BookInfo> bookInfos = new ArrayList<BookInfo>();
        for(int i = 0; i < lendLists.size(); i++) {
            System.out.println(lendLists.get(i).getSernum());
            ReaderInfo readerInfo = readerInfoService.showReaderInfo(lendLists.get(i).getReader_id());
            BookInfo bookInfo = bookInfoService.showBookInfo(lendLists.get(i).getBook_id());
            ReaderCard readerCard = readerCardService.findReaderById(lendLists.get(i).getReader_id());
            lendService.refreshDeadline(lendLists.get(i).getLend_date(), lendLists.get(i).getSernum(), readerCard.getState());
            readerInfos.add(readerInfo);
            bookInfos.add(bookInfo);
        }
        ModelAndView modelAndView = new ModelAndView("admin_lendList");
        modelAndView.addObject("bookInfos", bookInfos);
        modelAndView.addObject("readerInfos", readerInfos);
        modelAndView.addObject("lendLists",lendLists);
        return modelAndView;
    }
}
