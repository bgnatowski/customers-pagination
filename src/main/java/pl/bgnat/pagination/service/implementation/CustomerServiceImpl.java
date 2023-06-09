package pl.bgnat.pagination.service.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.bgnat.pagination.controller.UpdateImageRequest;
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
	public Customer updateCustomerImage(Long id, UpdateImageRequest updateImageRequest) {
		Customer customer = customerRepository.findById(id).get();
		if(updateImageRequest!=null && !updateImageRequest.imageUrl().isEmpty())
			customer.setImageUrl(updateImageRequest.imageUrl());
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer update) {
		log.info("Updating customer: {}", update);
		Customer customer = customerRepository.findById(id).get();
		if(update.getFirstName()!=null)
			customer.setFirstName(update.getFirstName());
		if(update.getLastName()!=null)
			customer.setLastName(update.getLastName());
		if(update.getUsername()!=null)
			customer.setUsername(update.getUsername());
		if(update.getEmail()!=null)
			customer.setEmail(update.getEmail());
		if(update.getGender()!=null)
			customer.setGender(update.getGender());
		if(update.getDateOfBirth()!=null)
			customer.setDateOfBirth(customer.getDateOfBirth());
		if(update.getAddress()!=null)
			customer.setAddress(update.getAddress());
		if(update.getStatus()!=null)
			customer.setStatus(update.getStatus());
		if(update.getPhone()!=null)
			customer.setPhone(update.getPhone());
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		log.info("Fetching customer by id {}", id);
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException());
	}


}
