package pl.bgnat.pagination.domain;

import java.time.LocalDate;

public record CustomerUpdateRequest(
		String firstName,
		String lastName,
		String username,
		String gender,
		LocalDate dateOfBirth,
		String email,
		String status,
		String address,
		String phone,
		String imageUrl) {
}
