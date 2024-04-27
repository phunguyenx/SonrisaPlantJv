package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.User.UserDto;
import com.example.sonrisaplantjv.domain.model.User.User;

public class UserMapper implements Mapper<UserDto, User>{
    @Override
    public User Map(UserDto userDto) {
        User User = new User();
        User.Id = userDto.Id;
        User.Name = userDto.Name;
        User.Email = userDto.Email;
        User.PasswordHash = userDto.PasswordHash;
        User.DateOfBirth = userDto.DateOfBirth;
        User.Address = userDto.Address;
        User.PIN = userDto.PIN;
        User.Avatar = userDto.Avatar;
        return User;
    }
}
