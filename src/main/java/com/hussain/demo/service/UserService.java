package com.hussain.demo.service;

import com.hussain.demo.model.User;
import com.hussain.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;

    @Transactional
    public Page<User> getAllUsers()
    {
        int page = 0 ;
        int size = 3 ;
        Pageable pageable = PageRequest.of(page, size , Sort.by("id"));
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage ;
    }


}
