
package com.security.demo.user;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
 private final UserService s;
 public UserController(UserService s){this.s=s;}
 @PostMapping public User create(@RequestBody User u){return s.create(u);}
 @GetMapping public List<User> all(){return s.all();}
 @GetMapping("/{id}") public User get(@PathVariable Long id){return s.get(id);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){s.delete(id);}
}
