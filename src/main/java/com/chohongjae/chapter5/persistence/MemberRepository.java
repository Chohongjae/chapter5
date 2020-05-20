package com.chohongjae.chapter5.persistence;

import com.chohongjae.chapter5.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, String> {
}
