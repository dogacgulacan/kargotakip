package my.project.kargotakipsistemi.mapper;

import my.project.kargotakipsistemi.domain.Customer;
import my.project.kargotakipsistemi.domain.Employee;
import my.project.kargotakipsistemi.dto.saveReqDtos.CustomerSaveReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.EmployeeSaveReqDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeMapper {

    Employee saveEmployeeReqDtoToEmployee(EmployeeSaveReqDto employeeSaveReqDto);

}
