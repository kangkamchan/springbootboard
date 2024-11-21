package net.fullstack7.springboot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/ex1")
public class Ex1Controller {
    @GetMapping("/ex1")
    public String ex1(
            HttpServletRequest req,
            HttpServletResponse resp,
            Model model) {
        String[] arr = new String[]{"AAA","BBB","CCC"};
        String msg = req.getParameter("msg") != null ? req.getParameter("msg") : "Hello world";
        int age = Integer.parseInt(req.getParameter("age")!=null?req.getParameter("age"):"0");

        model.addAttribute("list", Arrays.asList(arr));
        model.addAttribute("arr", arr);
        model.addAttribute("msg", msg);
        model.addAttribute("age", age);
        return "ex1/ex1";
    }
    @GetMapping("/ex2")
    public String ex2(
            HttpServletRequest req,
            HttpServletResponse resp,
            Model model
    ){
        String[] arr = new String[]{"AAA","BBB","CCC"};
        model.addAttribute("list", Arrays.asList(arr));
        return "ex1/ex2";
    }
}
