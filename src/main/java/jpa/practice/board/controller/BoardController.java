package jpa.practice.board.controller;

import jpa.practice.board.entity.Board;
import jpa.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 메인게시판 목록 호출
     * @param model
     * @return
     *
     * @GetMapping에서는
     * @RequestParam으로 받아지는데
     *
     * @PostMapping에서는
     * @RequestParam으로 받아지지 않고
     * @RequestBody으로 받아지는 이유
     *
     * -> @RequestParam은 url상에서
     * '?variable=value' 형식으로 들어온 parameter를 받는 annotation이고
     *
     * POST 방식으로 보내진 데이터는 url상에 나타나지 않고
     * http requestbody안에 담겨져서 오기 때문에
     * @RequsetBody를 통해 값을 가져와야 한다.
     */
    @GetMapping("/main")
    public String MainBoard(Model model
            , @RequestParam(value = "page",defaultValue = "0" ,required = false) int page){
        Page<Board> boardList = boardService.getBoardList(page);
        model.addAttribute("BoardList", boardList);
        return "/board/mainBoard";
    }


}
