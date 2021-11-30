package com.tistory.workshop6349.LearnSpring.repository;

import com.tistory.workshop6349.LearnSpring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager manager;

    public JpaMemberRepository(EntityManager em) {
        this.manager = em;
    }

    public Member save(Member member) {
        manager.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        Member member = manager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        return manager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = manager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}