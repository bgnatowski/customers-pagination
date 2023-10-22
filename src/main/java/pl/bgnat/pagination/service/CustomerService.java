package pl.bgnat.pagination.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.bgnat.pagination.domain.*;
import pl.bgnat.pagination.exception.DuplicateResourceException;
import pl.bgnat.pagination.exception.RequestValidationException;
import pl.bgnat.pagination.exception.customer.CustomerNotFoundException;
import pl.bgnat.pagination.repository.CustomerRepository;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
	private final CustomerRepository customerRepository;
	public Page<Customer> getCustomers(String name, int page, int size) {
		log.info("Fetching users for page {} of size {}", page, size);
		Page<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCaseOrderById(name, PageRequest.of(page, size));
		return customers;
	}

	public Customer updateCustomerImage(Long id, UpdateImageRequest updateImageRequest) {
		Customer customer = customerRepository.findById(id).get();
		if(updateImageRequest!=null && !updateImageRequest.imageUrl().isEmpty())
			customer.setImageUrl(updateImageRequest.imageUrl());
		return customerRepository.save(customer);
	}

	public Customer updateCustomer(Long id, CustomerUpdateRequest updateRequest) {
		log.info("Updating customer: {}", updateRequest);
		// TODO: for JPA use .getReferenceById(customerId) as it does does not bring object into memory and instead a reference
//		Customer customer = customerRepository.findById(id)
//				.orElseThrow(() -> new CustomerNotFoundException(id));
		Customer customer = customerRepository.getReferenceById(id);
		boolean changes = false;

		if (updateRequest.firstName() != null && !updateRequest.firstName().equals(customer.getFirstName())) {
			customer.setFirstName(updateRequest.firstName());
			changes = true;
		}
		if (updateRequest.lastName() != null && !updateRequest.lastName().equals(customer.getLastName())) {
			customer.setLastName(updateRequest.lastName());
			changes = true;
		}
		if (updateRequest.username() != null && !updateRequest.username().equals(customer.getUsername())) {
			checkIfUsernameIsNotTaken(updateRequest.username());
			customer.setUsername(updateRequest.username());
			changes = true;
		}
		if (updateRequest.gender() != null && !updateRequest.gender().equals(customer.getGender())) {
			customer.setGender(updateRequest.gender());
			changes = true;
		}
		if (updateRequest.dateOfBirth() != null && !updateRequest.dateOfBirth().equals(customer.getDateOfBirth())) {
			customer.setDateOfBirth(updateRequest.dateOfBirth());
			changes = true;
		}
		if (updateRequest.address() != null && !updateRequest.address().equals(customer.getAddress())) {
			customer.setAddress(updateRequest.address());
			changes = true;
		}
		if (updateRequest.phone() != null && !updateRequest.phone().equals(customer.getPhone())) {
			customer.setPhone(updateRequest.phone());
			changes = true;
		}
		if (updateRequest.status() != null && !updateRequest.status().equals(customer.getStatus())) {
			customer.setStatus(updateRequest.status());
			changes = true;
		}
		if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
			checkIfEmailIsNotTaken(updateRequest.email());
			customer.setEmail(updateRequest.email());
			changes = true;
		}
		if (!changes)
			throw new RequestValidationException("no data changes found");

		return customerRepository.save(customer);
	}


	public Customer getCustomer(Long id) {
		log.info("Fetching customer by id {}", id);
		checkIfCustomerWithIdExists(id);
		return customerRepository.findById(id).orElseThrow(
				() -> new CustomerNotFoundException(id));
	}

	public Customer addCustomer(CustomerAddRequest customerAddRequest) {
		checkIfUsernameIsNotTaken(customerAddRequest.username());
		checkIfEmailIsNotTaken(customerAddRequest.email());
		Customer customer = createCustomer(customerAddRequest);
		return customerRepository.save(customer);
	}

	public String deleteCustomerById(Long id) {
		checkIfCustomerWithIdExists(id);
		customerRepository.deleteById(id);
		return String.format("Employee with id=%s deleted!", id);
	}

	private void checkIfCustomerWithIdExists(Long id) {
		if (!isExistById(id)) {
			throw new CustomerNotFoundException(id);
		}
	}

	private boolean isExistById(Long id) {
		return customerRepository.existsById(id);
	}

	private void checkIfEmailIsNotTaken(String email) {
		if (customerRepository.existsCustomerByEmail(email)) {
			throw new DuplicateResourceException(String.format("email: %s is already taken", email));
		}
	}
	private void checkIfUsernameIsNotTaken(String username) {
		if (customerRepository.existsCustomerByUsername(username)) {
			throw new DuplicateResourceException(String.format("username: %s is already taken", username));
		}
	}

	private static Customer createCustomer(CustomerAddRequest customerAddRequest) {
		return Customer.builder()
				.firstName(customerAddRequest.firstName())
				.lastName(customerAddRequest.lastName())
				.username(customerAddRequest.username())
				.gender(customerAddRequest.gender())
				.dateOfBirth(customerAddRequest.dateOfBirth())
				.email(customerAddRequest.email())
				.address(customerAddRequest.address())
				.phone(customerAddRequest.phone())
				.imageUrl(customerAddRequest.imageUrl())
				.status(Status.ACTIVE.name())
				.build();
	}

}
