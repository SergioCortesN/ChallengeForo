package com.alurachallenges.forohub.controller;

import com.alurachallenges.forohub.domain.usuario.AuthRequest;
import com.alurachallenges.forohub.security.JwtResponse;
import com.alurachallenges.forohub.security.TokenService;
import com.alurachallenges.forohub.domain.usuario.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authenticate and obtain JWT tokens")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

        @PostMapping("/login")
        @Operation(summary = "Authenticate user and return a JWT token")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
        })
        public ResponseEntity authenticate(@RequestBody @Valid AuthRequest datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generateToken((User) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new JwtResponse(JWTtoken));
    }
}
