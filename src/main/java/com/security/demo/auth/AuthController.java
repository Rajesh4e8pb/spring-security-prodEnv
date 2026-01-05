
package com.security.demo.auth;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {
 private final AuthenticationManager am;
 private final JwtUtil jwt;
 public AuthController(AuthenticationManager am,JwtUtil jwt){
     this.am=am;
     this.jwt=jwt;
 }
 @PostMapping("/login")
 public String login(@RequestBody AuthRequest r){
   Authentication a=am.authenticate(
     new UsernamePasswordAuthenticationToken(r.username,r.password));
   return jwt.generateToken((UserDetails)a.getPrincipal());
 }
}
