
package com.security.demo.tag;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@Service
public class TagService {
 private final TagRepository r;
 public TagService(TagRepository r){this.r=r;}
 public Tag create(Tag t, Authentication a){
   boolean admin=a.getAuthorities().stream()
     .anyMatch(x->x.getAuthority().equals("ROLE_ADMIN"));
   if(t.tagType==TagType.GLOBAL && !admin)
     throw new RuntimeException("Only ADMIN can create GLOBAL tags");
   return r.save(t);
 }
}
