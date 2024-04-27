package com.example.sonrisaplantjv.data.mapper;

import com.example.sonrisaplantjv.data.remote.dto.Role.RoleDto;
import com.example.sonrisaplantjv.domain.model.Role.Role;

public class RoleMapper implements Mapper<RoleDto, Role>{
    @Override
    public Role Map(RoleDto roleDto) {
        Role role = new Role();
        role.Id = roleDto.Id;
        role.Name = roleDto.Name;
        return role;
    }
}
