package com.codeurfou.jidoublevete.controller;


import com.codeurfou.jidoublevete.daforuser.AuthRequest;
import com.codeurfou.jidoublevete.daforuser.AuthResponse;
import com.codeurfou.jidoublevete.entity.User;
import com.codeurfou.jidoublevete.jwtutils.JwtTokenUtil;
import com.codeurfou.jidoublevete.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired AuthenticationManager authManager;
   @Autowired JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepo userRepo;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            String accesstoken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accesstoken);
            return ResponseEntity.ok(response);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try {
            PasswordEncoder  passwordEncoder =new BCryptPasswordEncoder();
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           userRepo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
