
package com.security.demo.role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {
 private final RoleService s;
 public RoleController(RoleService s){this.s=s;}
 @PostMapping public Role create(@RequestBody Role r){return s.create(r);}
 @GetMapping public List<Role> all(){return s.all();}
 @GetMapping("/{id}") public Role get(@PathVariable Long id){return s.get(id);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){s.delete(id);}
}
