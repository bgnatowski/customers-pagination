package pl.bgnat.pagination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bgnat.pagination.domain.*;
import pl.bgnat.pagination.service.CustomerService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;

	@GetMapping(value = "/customers")
	public ResponseEntity<HttpResponse> getCustomers(@RequestParam Optional<String> name,
									   @RequestParam Optional<Integer> page,
									   @RequestParam Optional<Integer> size){
		Page<Customer> customers = customerService.getCustomers(
				name.orElse(""),
				page.orElse(0),
				size.orElse(10));
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.data(Map.of("page", customers))
						.message("Customers Retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
		);
	}
	@GetMapping("/customer/{id}")
	public ResponseEntity<HttpResponse> getCustomerById(@PathVariable("id") Long id) {
		Customer customer = customerService.getCustomer(id);
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.data(Map.of("customer", customer))
						.message("Customer Retrieved")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
		);
	}


	@PutMapping("/customer/{id}")
	public ResponseEntity<HttpResponse> updateCustomer(
			@PathVariable("id") Long id,
			@RequestBody CustomerUpdateRequest customer) {
		Customer updatedCustomer = customerService.updateCustomer(id, customer);
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.data(Map.of("customer", updatedCustomer))
						.message("Customer updated")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
		);
	}

	@PatchMapping("/customer/{id}")
	public ResponseEntity<HttpResponse> updateCustomerImage(
			@PathVariable("id") Long id,
			@RequestBody UpdateImageRequest updateImageRequest) {
		Customer updatedCustomer = customerService.updateCustomerImage(id, updateImageRequest);
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.data(Map.of("customer", updatedCustomer))
						.message("Customer image updated")
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build()
		);
	}

	@PostMapping("customers/add")
	ResponseEntity<HttpResponse> addCustomer(@RequestBody @Valid CustomerAddRequest customerAddRequest){
		Customer customer = customerService.addCustomer(customerAddRequest);
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.data(Map.of("customer", customer))
						.message("Customer created")
						.status(HttpStatus.CREATED)
						.statusCode(HttpStatus.CREATED.value())
						.build());
	}

	@DeleteMapping("customers/delete/{id}")
	ResponseEntity<HttpResponse> deleteCustomerById(@PathVariable("id") Long id) {
		String deleteMessage = customerService.deleteCustomerById(id);
		return ResponseEntity.ok().body(
				HttpResponse.builder()
						.timeStamp(LocalDateTime.now().toString())
						.message(deleteMessage)
						.status(HttpStatus.OK)
						.statusCode(HttpStatus.OK.value())
						.build());
	}
}
