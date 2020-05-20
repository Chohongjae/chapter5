package com.chohongjae.chapter5.persistence;

import com.chohongjae.chapter5.domain.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

//    @Before
//    public void dataPrepare(){
//        for(int i =0; i<10; i++){
//            Board board = new Board();
//            board.setWriter("조홍제");
//            board.setCreateDate(new Date());
//            board.setContent("내용");
//            board.setCnt(4L);
//            board.setTitle("내용" + i);
//            boardRepository.save(board);
//        }
//    }

//    @Test
//    public void testRepository() {
//        Board board = new Board();
//        board.setCnt(1L);
//        board.setContent("hi");
//        board.setCreateDate(new Date());
//        board.setTitle("ji");
//        board.setWriter("zzz");
//
//        boardRepository.save(board);
//    }

    @Test
    public void testGetBoard() {
        Board board = boardRepository.findById(1L).get();
        System.out.println(board.toString());
    }

//    @Test
//    public void testUpdateBoard() {
//        Board board = boardRepository.findById(1L).get();
//        board.setWriter("zlzlzlzlzlzlzlzlz");
//        Board board1 = boardRepository.save(board);
//        System.out.println(board1.toString());
//    }

    @Test
    public void testDeleteBoard() {
        Board board = boardRepository.findById(1L).get();
        boardRepository.delete(board);
    }

    @Test
    public void testCount() {
        Long count = boardRepository.count();
        System.out.println(count);
    }

    @Test
    public void testFindByTitle(){
        List<Board> boardList = boardRepository.findByTitle("내용");
        System.out.println(boardList);
    }

    @Test
    public void testByContentContaining(){
        List<Board> boardList = boardRepository.findByContentContaining("내용");
        System.out.println(boardList);
    }

    @Test
    public void testByOrContaining(){
        List<Board> boardList = boardRepository.findByTitleContainingOrContentContainingOrderBySeqDesc("내용", "내용");
        System.out.println(boardList);
    }

    @Test
    public void testFindByTitleContaining(){
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC, "seq");
        List<Board> boards = boardRepository.findByTitleContaining("내용", pageable);

        System.out.println(boards);
    }

    @Test
    public void testFindPageByTitleContaining(){
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC, "seq");
        Page<Board> pageInfo = boardRepository.findPageByTitleContaining("내용", pageable);

        System.out.println(pageInfo.getSize());
        System.out.println(pageInfo.getTotalPages());
        System.out.println(pageInfo.getTotalElements());
        System.out.println(pageInfo.nextPageable());

        List<Board> boards = pageInfo.getContent();
        System.out.println(boards);
    }

}