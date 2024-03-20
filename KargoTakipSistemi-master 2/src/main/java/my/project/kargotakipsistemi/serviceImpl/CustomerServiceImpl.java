package my.project.kargotakipsistemi.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.dto.saveReqDtos.CustomerSaveReqDto;
import my.project.kargotakipsistemi.mapper.CustomerMapper;
import my.project.kargotakipsistemi.repositories.AuthorityRepository;
import my.project.kargotakipsistemi.repositories.CustomerRepository;
import my.project.kargotakipsistemi.repositories.UserRepository;
import my.project.kargotakipsistemi.services.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final CustomerMapper customerMapper;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveCustomer(CustomerSaveReqDto customerSaveReqDto) {
        var user = userRepository.findByName(customerSaveReqDto.getName());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        var customer = customerMapper.saveCustomerReqDtoToCustomer(customerSaveReqDto);
        var customerAuthority = authorityRepository.findById(1);
        customerAuthority.get().getUsers().add(customer);
        customer.getAuthorities().add(customerAuthority.get());
        customer.setPassword(passwordEncoder.encode(customerSaveReqDto.getPassword()));

        authorityRepository.save(customerAuthority.get());
        customerRepository.save(customer);
    }
}
