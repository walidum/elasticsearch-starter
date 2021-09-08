package com.meylium.elsch.batch.steps;

import com.meylium.elsch.model.User;
import com.meylium.elsch.util.dtos.UserDto;
import org.springframework.batch.item.ItemProcessor;

public class UsersDtosProcessor implements ItemProcessor<UserDto, User> {
    @Override
    public User process(UserDto userDto) throws Exception {
        return UserDto.toUser(userDto);
    }
}
