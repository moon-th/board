package jpa.practice.board.controller;

import jpa.practice.board.dto.BoardForm;
import jpa.practice.board.entity.Board;
import jpa.practice.board.dto.BoardSearch;
import jpa.practice.board.entity.Member;
import jpa.practice.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     *
     */
    @GetMapping("/main")
    public String MainBoard(Model model
            , @RequestParam(value = "page",defaultValue = "0" ,required = false) int page){
        Page<Board> boardList = boardService.getBoardList(page);
        BoardSearch boardSearch = new BoardSearch("createdDate","DESC");
        model.addAttribute("BoardList", boardList);
        model.addAttribute("boardSearch", boardSearch);
        return "/board/mainBoard";
    }

    /**
     * querydsl 사용하여 페이징 및 검색
     * @param model
     * @param page
     * @param boardSearch
     * @return
     */
    @PostMapping("/main")
    public String MainBoardSearch(Model model
            , @RequestParam(value = "page",defaultValue = "0" ,required = false) int page
            , @ModelAttribute("boardSearch") BoardSearch boardSearch){
        Page<Board> boardList = boardService.getBoardSearchList(page,boardSearch);
        model.addAttribute("BoardList", boardList);
        model.addAttribute("boardSearch", boardSearch);
        return "/board/mainBoard";
    }

    /**
     * 게시판 글쓰기 화면으로 이동
     * @param model
     * @return
     */
    @GetMapping("/regist")
    public String ResistBoard(Model model){
        model.addAttribute("boardForm", new BoardForm());
        return "/board/regist";
    }

    /**
     * 게시물 저장
     * @param boardForm
     * @param request
     * @return
     */
    @PostMapping("/regist")
    public String insertBoard(@ModelAttribute("boardForm") BoardForm boardForm, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("Member");

        Board board = Board.builder()
                .title(boardForm.getTitle())
                .content(boardForm.getContent())
                .member(member)
                .build();
        boardService.insertBoard(board);
        return "redirect:/Board/main";
    }

    @GetMapping("/detail")
    public String BoardDetail(@RequestParam("id") Long id,Model model){
        Board findBoard = boardService.getBoardDetail(id);
        BoardForm boardForm = new BoardForm(findBoard.getTitle(), findBoard.getContent(), findBoard.getMember());
        model.addAttribute("boardForm", boardForm);
        return "/board/detail";
    }
}
