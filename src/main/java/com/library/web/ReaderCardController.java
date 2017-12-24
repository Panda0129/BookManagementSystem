package com.library.web;

import com.library.domain.ReaderCard;
import com.library.service.ReaderCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReaderCardController {

    private ReaderCardService readerCardService;

    @Autowired
    public void setReaderCardService (ReaderCardService readerCardService) {
        this.readerCardService = readerCardService;
    }

    @RequestMapping("/reader_rePwd.html")
    public ModelAndView readerRePwd () {
        return  new ModelAndView("reader_rePwd");
    }

    @RequestMapping("/reader_rePwd_do.html")
    public String readerRePwdDo (HttpServletRequest request, String old_pwd_in_change,
                                 String new_pwd_in_change, String confirm_new_pwd_in_change) {
        ReaderCard readerCard = (ReaderCard)request.getSession().getAttribute("readerCard");
//        System.out.println(readerCard.getName());
        String reader_id = readerCard.getReader_id();
//        System.out.println(readerCard.getPwd());

        if(new_pwd_in_change.equals(confirm_new_pwd_in_change)) {
            if (old_pwd_in_change.equals(readerCard.getPwd())) {
                boolean isSuccess = readerCardService.rePassword(reader_id, new_pwd_in_change);

                if (isSuccess) {
                    ReaderCard newReaderCard = readerCardService.findReaderById(reader_id);
                    request.getSession().setAttribute("readerCard", newReaderCard);
                    return "redirect:/index.html";
                } else {
                    return "redirect:/reader_rePwd.html";
                }
            } else {
                return "redirect:/reader_rePwd_old_error.html";
            }
        } else {
            return "redirect:/reader_rePwd_new_error.html";
        }
    }

    @RequestMapping("/reader_rePwd_old_error.html")
    public ModelAndView readerRePwdError1() {
        return new ModelAndView("reader_rePwd_old_pwd_error");
    }

    @RequestMapping("/reader_rePwd_new_error.html")
    public ModelAndView readerRePwdError2() {
        return new ModelAndView("reader_rePwd_new_pwd_error");
    }

    @RequestMapping("reader_addReaderCard.html")
    public ModelAndView addReaderCard() {
        return new ModelAndView("reader_signUp");
    }

    @RequestMapping("/reader_addReaderCard_do.html")
    public String addReaderCardDo(String reader_id, String name, String pwd, String confirm_pwd) {
        ReaderCard readerCard = new ReaderCard();
        readerCard.setReader_id(reader_id);
        readerCard.setPwd(pwd);
        readerCard.setName(name);
        boolean isRepeat = readerCardService.hasMatchAccount(reader_id);

        if(isRepeat) {
            if (pwd.equals(confirm_pwd)) {
                boolean isSuccess = readerCardService.addReaderCard(readerCard);
                if (isSuccess) {
                    return "redirect:/index.html";
                } else {
                    return "redirect:/reader_addReaderCard.html";
                }
            } else {
                return "redirect:/add_fail_error.html";
            }
        } else {
            return "redirect:/account_exist.html";
        }
    }

    @RequestMapping("/add_fail_error.html")
    public ModelAndView addReaderCardError1(){
        return new ModelAndView("reader_signUp_error");
    }

    @RequestMapping("/account_exist.html")
    public ModelAndView existAccount() {
        return new ModelAndView("reader_signUp_repeatAccount");
    }
}
