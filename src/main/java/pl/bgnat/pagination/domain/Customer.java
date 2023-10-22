package pl.bgnat.pagination.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties


@Entity(name = "Customer")
@Table(name = "customer",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "customer_username_constraint",
						columnNames = "username"
				),
				@UniqueConstraint(
						name = "customer_email_constraint",
						columnNames = "email"
				)
		})
public class Customer {
	@Id
	@SequenceGenerator(name = "customer_id_generator", sequenceName = "customer_id_generator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_generator")
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "first_name", columnDefinition = "TEXT", nullable = false)
	private String firstName;

	@Column(name = "last_name", columnDefinition = "TEXT", nullable = false)
	private String lastName;

	@Column(name = "username", columnDefinition = "TEXT", unique = true, nullable = false)
	private String username;

	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth", columnDefinition = "date", nullable = false)
	private LocalDate dateOfBirth;

	@Column(name = "email", columnDefinition = "TEXT", nullable = false, unique = true)
	private String email;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "image_url")
	private String imageUrl;
}
