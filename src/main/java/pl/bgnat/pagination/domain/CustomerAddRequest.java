package pl.bgnat.pagination.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CustomerAddRequest(
		@NotNull
		@NotBlank(message = "First name is mandatory")
		String firstName,
		@NotBlank(message = "Last name is mandatory")
		@NotNull String lastName,

		@NotNull
		@NotBlank(message = "Username is mandatory")
		@Length(min = 3, max = 255)
		@Pattern(regexp = "^[a-zA-Z0-9_]+$",
				message = "Username can only contain letters, numbers, and underscores.")
		String username,

		@NotNull String gender,
		@NotNull LocalDate dateOfBirth,

		@NotNull
		@NotBlank(message = "Email is mandatory")
		@Email
		String email,

		@NotNull String address,
		@NotNull String phone,
		@NotNull String imageUrl
) {
}
