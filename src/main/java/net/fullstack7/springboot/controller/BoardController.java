package net.fullstack7.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.springboot.dto.BoardDTO;
import net.fullstack7.springboot.dto.ConditionRequestDTO;
import net.fullstack7.springboot.dto.ConditionResponseDTO;
import net.fullstack7.springboot.repository.search.BoardSearch;
import net.fullstack7.springboot.service.BoardServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceIf boardService;

    @GetMapping("/list")
    public String list(ConditionRequestDTO requestDTO, Model model){
        ConditionResponseDTO<BoardDTO> responseDTO = boardService.searchAndSort(requestDTO);
        log.info("queryString : {}",responseDTO.getQueryString());
        
        model.addAttribute("requestDTO", requestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "board/list";
    }

    @GetMapping("/view")
    public String view(@RequestParam(required = false, defaultValue = "-1")int idx, @RequestParam(required = false, defaultValue = "") String queryString, Model model) {
        BoardDTO boardDTO = boardService.view(idx);
        if(idx==-1){
            return "redirect:/board/list?"+queryString;
        }
        model.addAttribute("boardDTO", boardDTO);
        model.addAttribute("queryString", queryString);
        return "board/view";
    }
    @GetMapping("/regist")
    public String regist(@RequestParam(required = false, defaultValue="")String queryString, Model model ) {
        model.addAttribute("queryString", queryString);
        return "board/regist";
    }
    @PostMapping("/regist")
    public String regist(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
        int result = boardService.regist(boardDTO);
        return "redirect:/board/view?idx="+result;
    }
    @GetMapping("/modify")
    public String modify(@RequestParam(required = false, defaultValue = "-1")int idx, Model model) {
        BoardDTO boardDTO = boardService.view(idx);
        model.addAttribute("boardDTO", boardDTO);
        return "board/modify";
    }
    @PostMapping("/modify")
    public String modify(@RequestParam(required = false, defaultValue = "-1")int idx, BoardDTO boardDTO) {
        BoardDTO prevDTO = boardService.view(idx);
        if(prevDTO==null){
            return "redirect:/board/modify?idx="+idx;
        }
        int result = boardService.modify(boardDTO);
        return "redirect:/board/view?idx="+idx;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "-1")int idx, Model model) {
        boardService.delete(idx);
        return "redirect:/board/list";
    }
}
