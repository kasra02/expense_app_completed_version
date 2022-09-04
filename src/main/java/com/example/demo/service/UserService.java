package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void save(UserDTO userDTO)
    {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = mapToEntity(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    private User mapToEntity(UserDTO userDTO)
    {
        User map = modelMapper.map(userDTO, User.class);
        return map;
    }

    public User getLoggedInUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> userByEmail = userRepository.findUserByEmail(email);
        return userByEmail.orElseThrow(()->new UsernameNotFoundException("no user with emial"+email));
    }

}
