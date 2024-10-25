package com.alonsofritz.simplified_authenticator_spring.adapters.dtos;

import java.util.Set;
import java.util.UUID;

public record SignUpResponseDto(UUID userId, String email, Set<String> permission) {
}
