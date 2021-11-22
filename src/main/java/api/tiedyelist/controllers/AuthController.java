package api.tiedyelist.controllers;

import api.tiedyelist.dtos.LoginRequestDTO;
import api.tiedyelist.dtos.AuthResponseDTO;
import api.tiedyelist.dtos.RefreshTokenRequestDTO;
import api.tiedyelist.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public AuthResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("refreshToken")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public AuthResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
}
