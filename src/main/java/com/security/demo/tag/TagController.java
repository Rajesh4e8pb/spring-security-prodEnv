
package com.security.demo.tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/tags")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class TagController {
 private final TagService s;
 public TagController(TagService s){this.s=s;}
 @PostMapping public Tag create(@RequestBody Tag t, Authentication a){
   return s.create(t,a);
 }
}
