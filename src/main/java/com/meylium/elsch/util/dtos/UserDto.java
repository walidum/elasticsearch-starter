package com.meylium.elsch.util.dtos;

import com.meylium.elsch.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String id;

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        return user;
    }
}
