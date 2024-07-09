package com.fleetmanagement.api_rest.dto;

import com.fleetmanagement.api_rest.model.UserModel;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {
   private Integer id;
   private String name;
   private String email;

}
