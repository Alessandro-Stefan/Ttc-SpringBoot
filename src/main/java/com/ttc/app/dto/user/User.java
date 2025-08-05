package com.ttc.app.dto.user;

import java.util.Date;

public record User(
    Long id,
    String username,
    String email,
    String password,
    Date createdAt,
    Date updatedAt
) {}
