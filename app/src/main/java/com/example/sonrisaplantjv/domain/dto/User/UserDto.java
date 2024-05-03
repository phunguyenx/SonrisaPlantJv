// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.sonrisaplantjv.domain.dto.User;
import com.example.sonrisaplantjv.common.ActionResponse;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

public class UserDto extends ActionResponse<UserDto> {
    public String PhoneNumber;
    public String Address;
    public String PIN;
    public String Name;
    public LocalDateTime DateOfBirth;
    public UUID Id;
    public String Avatar;
    public List<String> RoleNames;
    public String Email;
    public String PasswordHash;

}
