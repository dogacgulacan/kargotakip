package my.project.kargotakipsistemi.mapper;

import my.project.kargotakipsistemi.domain.Customer;
import my.project.kargotakipsistemi.dto.saveReqDtos.CustomerSaveReqDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CustomerMapper {

    Customer saveCustomerReqDtoToCustomer(CustomerSaveReqDto customerSaveReqDto);

}
