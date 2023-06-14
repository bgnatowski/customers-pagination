package pl.bgnat.pagination.domain;

import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record CustomerAddRequest(
		@NonNull String firstName,
		@NonNull String lastName,
		@NonNull String username,
		@NonNull String gender,
		@NonNull LocalDate dateOfBirth,
		@NonNull String email,
		@NonNull String address,
		@NonNull String phone,
		@NonNull String imageUrl
) {
}
