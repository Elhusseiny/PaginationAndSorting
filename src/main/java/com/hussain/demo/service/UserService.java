package com.hussain.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hussain.demo.dto.UserResponseDTO;
import com.hussain.demo.model.ERole;
import com.hussain.demo.model.User;
import com.hussain.demo.repository.UserRepository;
import com.hussain.demo.utils.Mapper;
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

    private final Mapper mapper ;

    @Transactional
    public Page<User> getAllUsers() {
        int page = 0;
        int size = 3;
        Pageable pageable = PageRequest.of(page, size, Sort.by("email"));
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage;
    }

    @Transactional
    public Page<User> getUsers() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("roles_name"));
        String username = "huss";
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("username", username);
        searchParams.put("role" , ERole.ROLE_USER );
        Specification<User> userSpecification = getUserSpecification(searchParams);
        Page<User> usersList = userRepository.findAll(userSpecification , pageable);
        usersList.forEach(System.out::println);
        return usersList;
    }

    @Transactional
    public Page<UserResponseDTO> getUsersDTOs() {
        Pageable pageable = PageRequest.of(0, 4, Sort.by("roles_name").descending());
        //Pageable pageable = PageRequest.of(0, 4, Sort.by("id").descending());
        String username = "huss";
        Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("username", username);
        Specification<User> userSpecification = getUserSpecification(searchParams);
        Page<UserResponseDTO> usersDtoList = userRepository.findAll(userSpecification , pageable).map(mapper::toUserResponse);

        return usersDtoList;
    }

    private Specification<User> getUserSpecification(Map<String, Object> searchParams) {
        return new UserSpecification(searchParams);
    }


}
