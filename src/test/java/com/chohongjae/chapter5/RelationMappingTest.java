package com.chohongjae.chapter5;

import com.chohongjae.chapter5.domain.Board;
import com.chohongjae.chapter5.domain.Member;
import com.chohongjae.chapter5.persistence.BoardRepository;
import com.chohongjae.chapter5.persistence.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelationMappingTest {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testManytoOneInsert(){
        Member member = new Member();
        member.setId("1");
        member.setName("조홍제");
        member.setPassword("123");
        member.setRole("user1");
//        memberRepository.save(member);

        Member member2 = new Member();
        member2.setId("2");
        member2.setName("김철수");
        member2.setPassword("123");
        member2.setRole("user2");
//        memberRepository.save(member2);

        for(int i=0; i<10; i++){
            Board board = new Board();
            board.setMember(member);
            board.setTitle("hihi");
            board.setCnt(1L);
            board.setContent("test");
            board.setCreateDate(new Date());
//            boardRepository.save(board);
        }
        memberRepository.save(member);


        for(int i=0; i<10; i++){
            Board board = new Board();
            board.setMember(member2);
            board.setTitle("hihi2");
            board.setCnt(2L);
            board.setContent("test2");
            board.setCreateDate(new Date());
//            boardRepository.save(board);
        }
        memberRepository.save(member2);
        // cascadetype.all 로 지정했기때문에 자식도 같이 영속화된다.
    }

    @Test
    public void testManytoOneGet(){
        Board board = boardRepository.findById(22L).get();
        System.out.println(board.toString());
    }

    @Test
    public void testTwoWayMapping(){
        Member member = memberRepository.findById("2").get();
        System.out.println(member.getName());
        List<Board> boardList = member.getBoardList();
        System.out.println(boardList);
    }

    @Test
    public void testDeleteTwoWayMapping(){
        memberRepository.deleteById("2");
    }
}
