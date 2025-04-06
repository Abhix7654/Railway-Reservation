package com.railway.user.service.controller;


import com.railway.user.service.dto.AuthRequest;
import com.railway.user.service.dto.AuthResponse;
import com.railway.user.service.dto.UserDto;
import com.railway.user.service.entity.User;
import com.railway.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public String profile() {
        return "Hello, Abhishek!";
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {

        return ResponseEntity.ok(userService.getUserByEmail(email));
    }


}
