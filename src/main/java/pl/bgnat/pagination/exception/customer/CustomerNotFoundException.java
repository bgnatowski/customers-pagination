package pl.bgnat.pagination.exception.customer;


import pl.bgnat.pagination.exception.ResourceNotFoundException;

public class CustomerNotFoundException extends ResourceNotFoundException {
	private static final String EMPLOYEE_WITH_ID_S_DOES_NOT_EXIST = "Employee with id=%s does not exist!";
	public CustomerNotFoundException(Long id) {
		super(String.format(EMPLOYEE_WITH_ID_S_DOES_NOT_EXIST, id));
	}
}
