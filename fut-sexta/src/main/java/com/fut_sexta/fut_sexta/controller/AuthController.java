package com.fut_sexta.fut_sexta.controller;


import com.fut_sexta.fut_sexta.DTO.input.AuthInputDTO;
import com.fut_sexta.fut_sexta.DTO.output.AuthOutputDTO;
import com.fut_sexta.fut_sexta.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;


    @PostMapping("/login")
    public ResponseEntity<AuthOutputDTO> login(@RequestBody @Valid AuthInputDTO in){
        var out = new AuthOutputDTO(service.login(in.username(), in.password()));
        return ResponseEntity.ok(out);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthOutputDTO> register(@RequestBody @Valid AuthInputDTO in){
        var out = new AuthOutputDTO(service.register(in.username(), in.password()));
        return ResponseEntity.ok(out);
    }

}
