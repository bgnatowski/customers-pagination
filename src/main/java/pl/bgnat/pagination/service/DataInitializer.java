package pl.bgnat.pagination.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bgnat.pagination.domain.Customer;
import pl.bgnat.pagination.repository.CustomerRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {
	private final CustomerRepository customerRepository;
	@Value("${data.file.path}") // lub "${data.file.path.production}" dla profilu "production"
	private String dataFilePath;
	@Transactional
	@PostConstruct
	public void initializeData() {
		try {
			if(!customerRepository.existsById(1L)){
				String data = new String(Files.readAllBytes(Paths.get(dataFilePath)));
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				Customer[] employees = objectMapper.readValue(data, Customer[].class);
				customerRepository.saveAll(Arrays.asList(employees));
			}
		} catch (IOException e) {
			log.error("Could not insert data to db");
			e.printStackTrace();
		}
	}
}