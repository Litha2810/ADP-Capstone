package za.ac.cput.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpRequest,
                                   HttpServletResponse httpResponse){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(),request.password())
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            //trying to make the security context persist through the session
            new HttpSessionSecurityContextRepository().saveContext(context, httpRequest,httpResponse);

            return ResponseEntity.ok(Map.of(
                    "success",true,
                    "username", authentication.getName(),
                    "roles", authentication.getAuthorities()
            ));
        } catch(BadCredentialsException e){
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "Invalid Creds"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?>logout(HttpServletRequest request){
        SecurityContextHolder.clearContext();

        if(request.getSession(false)!=null){
            request.getSession(false).invalidate();
        }

        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/whoami")
    public ResponseEntity<?> whoami(Authentication authentication) {
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                "anonymousUser".equals(authentication.getPrincipal())) {

            return ResponseEntity.status(401).body(Map.of("authenticated", false));
        }

        return ResponseEntity.ok(Map.of(
                "authenticated ", true,
                "username", authentication.getName(),
                "roles", authentication.getAuthorities()

        ));
    }


    public record LoginRequest(String username, String password) {}


}
