package com.example.sonrisaplantjv.data.remote.dto.User;

import com.example.sonrisaplantjv.common.ActionResponse;

public class LoginResponse extends ActionResponse<ValidateToken>{}
class ValidateToken{
    public String accessToken;
    public String refreshToken;

}
