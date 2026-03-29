package com.wiwu.bankaapi.controller;

import com.wiwu.bankaapi.dto.LoginRequestDTO;
import com.wiwu.bankaapi.rolesenum.Role;
import com.wiwu.bankaapi.security.JwtUtil;
import com.wiwu.bankaapi.model.User;
import com.wiwu.bankaapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/admin")
    public String adminAcess(){
        return "acesso permitido ao admin";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
                return jwtUtil.generateToken(request.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequestDTO request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        repository.save(user);
        return "usario created";
    }

}
