package pl.bgnat.pagination.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@Table(name = "Customer")
@Entity(name = "customer")
public class Customer {
	@Id
	@SequenceGenerator(name = "customer_id_generator", sequenceName = "customer_id_generator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_generator")
	@Column(name = "id")
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "username")
	private String username;
	@Column(name = "gender")
	private String gender;
	@Column(name = "date_of_birth", columnDefinition = "date")
	private LocalDate dateOfBirth;
	@Column(name = "email")
	private String email;
	@Column(name = "status")
	private String status;
	@Column(name = "address")
	private String address;
	@Column(name = "phone")
	private String phone;
	@Column(name = "image_url")
	private String imageUrl;


}
