package com.chohongjae.chapter5.persistence;

import com.chohongjae.chapter5.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findByTitle(String searchKeyword);
    List<Board> findByContentContaining(String searchKeyword);
    List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String title, String content);
    List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
    Page<Board> findPageByTitleContaining(String searchKeyword, Pageable paging);

    @Query("SELECT b FROM Board b WHERE b.title like %?1% order by b.seq DESC")
        // 위치기반
    List<Board> queryAnnotationTest1(String searchKeyword);

    @Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% order by b.seq DESC")
    // 이름기반
    List<Board> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);

//    @Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %:searchKeyword% order by b.seq DESC")
//    List<Object []> queryAnnotationTest3(@Param("searchKeyword") String searchKeyword);

//    @Query(value="select seq, title, writer from board where title like '%'||?1||'%' order by seq desc", nativeQuery=true)
//    List<Object []> queryAnnotationTest4(String searchKeyword);

    @Query("SELECT b FROM Board b order by b.seq DESC")
    List<Board> queryAnnotationTest5(Pageable paging);



}
