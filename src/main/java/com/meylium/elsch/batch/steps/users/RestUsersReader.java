package com.meylium.elsch.batch.steps.users;

import com.meylium.elsch.util.dtos.UserDto;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestUsersReader implements ItemReader<UserDto> {
    private final String restUri;
    private final RestTemplate restTemplate;

    private List<UserDto> usersData;
    private int nextUserIndex;

    public RestUsersReader(String restUri, RestTemplate restTemplate) {
        this.restUri = restUri;
        this.restTemplate = restTemplate;
        this.nextUserIndex = 0;
    }

    @Override
    public UserDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (usersDataIsNotInitialized()) {
            this.usersData = fetchUserDataFromAPI();
        }
        UserDto nextUserDto = null;
        if (this.nextUserIndex < usersData.size()) {
            nextUserDto = this.usersData.get(this.nextUserIndex);
            this.nextUserIndex++;
        } else {
            this.nextUserIndex = 0;
            usersData = null;

        }
        return nextUserDto;
    }

    private List<UserDto> fetchUserDataFromAPI() {
        ResponseEntity<UserDto[]> response = restTemplate.getForEntity(this.restUri, UserDto[].class);
        UserDto[] studentData = response.getBody();
        return Arrays.asList(studentData);
    }

    private boolean usersDataIsNotInitialized() {
        return this.usersData == null;
    }
}
