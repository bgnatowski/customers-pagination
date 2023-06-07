package pl.bgnat.pagination.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.bgnat.pagination.domain.Customer;
import pl.bgnat.pagination.repository.CustomerRepository;
import pl.bgnat.pagination.service.CustomerService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	@Override
	public Page<Customer> getCustomers(String name, int page, int size) {
		log.info("Fetching users for page {} of size {}", page, size);
		Page<Customer> customers = customerRepository.findByFirstNameContainingIgnoreCaseOrderById(name, PageRequest.of(page, size));
		return customers;
	}

	@Override
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer update) {
		log.info("Updating customer: {}", update);
		Customer customer = customerRepository.findById(update.getId()).get();
		customer.setFirstName(update.getFirstName());
		customer.setLastName(update.getLastName());
		customer.setUsername(update.getUsername());
		customer.setEmail(update.getEmail());
		customer.setGender(update.getGender());
		customer.setDateOfBirth(customer.getDateOfBirth());
		customer.setAddress(update.getAddress());
		customer.setImageUrl(update.getImageUrl());
		customer.setStatus(update.getStatus());
		customer.setPhone(update.getPhone());
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		log.info("Fetching customer by id {}", id);
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}


}
