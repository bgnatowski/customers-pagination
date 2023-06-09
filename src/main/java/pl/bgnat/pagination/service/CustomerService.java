package pl.bgnat.pagination.service;

import org.springframework.data.domain.Page;
import pl.bgnat.pagination.controller.UpdateImageRequest;
import pl.bgnat.pagination.domain.Customer;

import java.util.List;

public interface CustomerService {
	Page<Customer> getCustomers(String name, int page, int size);

	Customer updateCustomer(Long id, Customer customer);

	Customer getCustomer(Long id);
	List<Customer> getCustomers();
	Customer updateCustomerImage(Long id, UpdateImageRequest updateImageRequest);
}
