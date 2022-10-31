package com.hussain.demo.service;

import com.hussain.demo.model.ERole;
import com.hussain.demo.model.User;
import com.hussain.demo.repository.UserRepository;
import com.hussain.demo.utils.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Page<User> getAllUsers() {
        Map<String, String> searchParams = new HashMap<>();
        int page = 0;
        int size = 3;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage;
    }

    @Transactional
    public List<User> getUsers() {
        String username = "hussein1";
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("username", username);
        searchParams.put("role" , ERole.ROLE_USER );
        Specification<User> userSpecification = getUserSpecification(searchParams);
        List<User> usersList = userRepository.findAll(userSpecification);
        usersList.forEach(System.out::println);
        return usersList;
    }

    private Specification<User> getUserSpecification(Map<String, Object> searchParams) {
        return new UserSpecification(searchParams);
    }


}
