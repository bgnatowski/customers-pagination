package pl.bgnat.pagination.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bgnat.pagination.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Page<Customer> findByFirstNameContainingIgnoreCaseOrderById(String name, Pageable pageable);
}
