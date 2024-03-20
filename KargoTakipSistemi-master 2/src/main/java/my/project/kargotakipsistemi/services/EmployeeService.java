package my.project.kargotakipsistemi.services;

import my.project.kargotakipsistemi.dto.saveReqDtos.CustomerSaveReqDto;
import my.project.kargotakipsistemi.dto.saveReqDtos.EmployeeSaveReqDto;

public interface EmployeeService {

    void saveEmployee(EmployeeSaveReqDto employeeSaveReqDto);

}
