package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.security.JwtTokenUtils;
import pl.jakpoliczyc.security.JwtUser;
import pl.jakpoliczyc.web.dto.JwtAuthenticationRequestDto;
import pl.jakpoliczyc.web.dto.JwtAuthenticationResponseDto;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequestDto authenticationRequest, HttpServletRequest request) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails == null) {
            throw new AuthenticationCredentialsNotFoundException(
                    String.format("Credentials for username %s with given password not found", authenticationRequest.getUsername()));
        }

        return ResponseEntity.ok(new JwtAuthenticationResponseDto(jwtTokenUtils.generateToken(userDetails, DeviceUtils.getCurrentDevice(request))));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        final String token = jwtTokenUtils.getTokenFromRequest(request);
        final String username = jwtTokenUtils.getUsernameFromToken(token);
        final JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            final String refreshedToken = jwtTokenUtils.refreshToken(token);
            return new ResponseEntity<>(new JwtAuthenticationResponseDto(refreshedToken), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
