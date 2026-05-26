package com.fut_sexta.fut_sexta.service;


import com.fut_sexta.fut_sexta.model.User;
import com.fut_sexta.fut_sexta.model.UserDetailImplements;
import com.fut_sexta.fut_sexta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {


    private final UserRepository repository;

    public User getCurrentUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        UserDetailImplements userDetails =
                (UserDetailImplements) authentication.getPrincipal();

        return repository.findById(userDetails.getId())
                .orElseThrow();
    }
}
