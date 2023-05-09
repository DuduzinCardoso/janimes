package com.janime.service;

import com.janime.domain.User;
import com.janime.dto.auth.AuthenticationRequestDto;
import com.janime.dto.auth.AuthenticationResponseDto;
import com.janime.dto.auth.RegisterRequestDto;
import com.janime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto registerRquest) {
        var user = User.builder()
                .firstName(registerRquest.getFirstName())
                .lastName(registerRquest.getLastName())
                .email(registerRquest.getEmail())
                .password(passwordEncoder.encode(registerRquest.getPassword()))
                .role(registerRquest.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()
                )
        );
        var user = userRepository.findByEmail(authenticationRequestDto.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
