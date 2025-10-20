package com.ttc.app.dto.user;

import java.util.List;

public record SearchUserResponse(
    List<UserDto> data
) {}
