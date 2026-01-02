
package com.security.demo.role;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleService {
 private final RoleRepository repo;
 public RoleService(RoleRepository r){this.repo=r;}
 public Role create(Role r){return repo.save(r);}
 public List<Role> all(){return repo.findAll();}
 public Role get(Long id){return repo.findById(id).orElseThrow();}
 public void delete(Long id){repo.deleteById(id);}
}
