package com.ttc.app.dto.user;

import java.util.Date;

public record UserDto(
    Long id,
    String username,
    String email,
    String password
) {}
