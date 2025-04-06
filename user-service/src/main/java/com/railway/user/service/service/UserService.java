package com.railway.user.service.service;


import com.railway.user.service.dto.AuthRequest;
import com.railway.user.service.dto.AuthResponse;
import com.railway.user.service.dto.UserDto;
import com.railway.user.service.entity.User;
import com.railway.user.service.enums.Role;
import com.railway.user.service.repository.UserRepository;
import com.railway.user.service.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String register(User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User registered successfully";
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtils.generateToken(user);
        return new AuthResponse(token);
    }
    public UserDto getUserByEmail(String email){
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto dto = new UserDto(user.getId(), user.getName());

        return dto;
    }
}
