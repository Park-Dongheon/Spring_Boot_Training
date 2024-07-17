package com.rubypaper.persistence;

import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{

}
