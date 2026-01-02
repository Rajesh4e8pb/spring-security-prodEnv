
package com.security.demo.user;

import com.security.demo.role.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Entity
@Table(name="users")
@Data
public class User {
 @Id @GeneratedValue public Long id;
 public String username;
 public String password;
 @ManyToMany(fetch=FetchType.EAGER) public Set<Role> roles;

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public Set<Role> getRoles() {
  return roles;
 }

 public void setRoles(Set<Role> roles) {
  this.roles = roles;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }
}
