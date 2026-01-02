
package com.security.demo.role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
 @Id @GeneratedValue public Long id;
 @Column(unique=true) public String roleName;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getRoleName() {
  return roleName;
 }

 public void setRoleName(String roleName) {
  this.roleName = roleName;
 }
}
