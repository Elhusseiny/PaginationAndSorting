package com.hussain.demo.utils;

import com.hussain.demo.dto.UserResponseDTO;
import com.hussain.demo.model.Role;
import com.hussain.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Mapper {

    public UserResponseDTO toUserResponse(User user )
    {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles())
        {
            roles.add(role.getName().toString());
        }
        userResponseDTO.setRoles(roles);
        return userResponseDTO ;
    }
}
