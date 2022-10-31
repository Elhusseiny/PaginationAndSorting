package com.hussain.demo.utils;

import com.hussain.demo.model.Role;
import com.hussain.demo.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserSpecification implements Specification <User> {

    Map<String , Object > searchParams ;
    public UserSpecification(Map<String , Object > searchParams) {
        super();
        this.searchParams = searchParams;
    }

    @Override
    public Specification<User> and(Specification<User> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<User> or(Specification<User> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicateList = new ArrayList<>();

        Join<User , Role> userWithRolesJoin =  root.join(("roles")) ;

        if (searchParams.get("username") != null)
            predicateList.add(cb.like(root.get("username"), "%" + searchParams.get("username").toString() + "%"));
        if (searchParams.get("email") != null)
            predicateList.add(cb.equal(root.get("email"), searchParams.get("email")));
        if (searchParams.get("role") != null)
            predicateList.add(cb.equal(userWithRolesJoin.get("name") , searchParams.get("role")));

        return cb.and(predicateList.toArray(new Predicate[0]));
    }
}
