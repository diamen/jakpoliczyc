package pl.jakpoliczyc.web.dto;

import java.io.Serializable;

public class JwtAuthenticationResponseDto implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
