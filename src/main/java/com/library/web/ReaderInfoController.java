package com.library.web;

import com.library.domain.ReaderCard;
import com.library.domain.ReaderInfo;
import com.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReaderInfoController {
    private ReaderInfoService readerInfoService;

    @Autowired
    public void setReaderInfoService(ReaderInfoService readerInfoService) {
        this.readerInfoService = readerInfoService;
    }

    @RequestMapping("/readerInfo.html")
    public ModelAndView showReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ModelAndView modelAndView = new ModelAndView("reader_ReaderInfo");
        System.out.println(readerCard.getName());
        ReaderInfo readerInfo = readerInfoService.showReaderInfo(readerCard.getReader_id());
        modelAndView = modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("/reader_modifyReaderInfo.html")
    public ModelAndView modifyIndividualInfo() {
        return  new ModelAndView("reader_modifyInfo");
    }

    @RequestMapping("/reader_modifyReaderInfoDo.html")
    public String modifyIndividualInfo (HttpServletRequest request, String sex, String birth, String address, String telcode) throws ParseException {
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");

        Date date = fmt.parse(birth);

        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.setId(readerCard.getReader_id());
        readerInfo.setName(readerCard.getName());
        readerInfo.setBirth(date);
        readerInfo.setAddress(address);
        readerInfo.setTelcode(telcode);
        readerInfo.setSex(sex);

        readerInfoService.modifyIndividualInfo(readerInfo);

        return "redirect:/readerInfo.html";

    }

}
