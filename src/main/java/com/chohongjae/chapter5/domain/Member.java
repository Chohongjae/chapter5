package com.chohongjae.chapter5.domain;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "boardList")
@Entity
public class Member {
    @Id
    @Column(name="MEMBER_ID")
    private String id;
    private String password;
    private String name;
    private String role;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // CascadeType.ALL 을 지정하면 회원객체가 영속화되거나 수정, 삭제될때 회원과 관련된 게시판도 같이 변경된다.
    // 회원에서 게시물로의 객체적 접근을 위해, 디비는 원래 가능, 즉 내가 쓴 게시물들 알아올려고
    private List<Board> boardList = new ArrayList<>();


}
