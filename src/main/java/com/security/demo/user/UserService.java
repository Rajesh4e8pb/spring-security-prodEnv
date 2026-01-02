
package com.security.demo.user;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
 private final UserRepository r; private final PasswordEncoder e;
 public UserService(UserRepository r,PasswordEncoder e){this.r=r;this.e=e;}
 public User create(User u){u.password=e.encode(u.password);return r.save(u);}
 public List<User> all(){return r.findAll();}
 public User get(Long id){return r.findById(id).orElseThrow();}
 public void delete(Long id){r.deleteById(id);}
}
