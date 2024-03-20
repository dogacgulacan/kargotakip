package my.project.kargotakipsistemi.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.project.kargotakipsistemi.dto.saveReqDtos.EmployeeSaveReqDto;
import my.project.kargotakipsistemi.mapper.EmployeeMapper;
import my.project.kargotakipsistemi.repositories.AuthorityRepository;
import my.project.kargotakipsistemi.repositories.EmployeeRepository;
import my.project.kargotakipsistemi.repositories.UserRepository;
import my.project.kargotakipsistemi.services.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveEmployee(EmployeeSaveReqDto employeeSaveReqDto) {
        var user = userRepository.findByName(employeeSaveReqDto.getName());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        var employee = employeeMapper.saveEmployeeReqDtoToEmployee(employeeSaveReqDto);
        var empAuthority = authorityRepository.findById(2);
        empAuthority.get().getUsers().add(employee);
        employee.getAuthorities().add(empAuthority.get());
        employee.setPassword(passwordEncoder.encode(employeeSaveReqDto.getPassword()));

        authorityRepository.save(empAuthority.get());
        employeeRepository.save(employee);
    }
}
