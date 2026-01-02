
package com.security.demo.tag;
import jakarta.persistence.*;
@Entity
public class Tag {
 @Id @GeneratedValue public Long id;
 public String tagName;
 public String tagDescription;
 @Enumerated(EnumType.STRING) public TagType tagType;
}
