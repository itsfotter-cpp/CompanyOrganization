package it.companyorganization.mappers;

import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "employeeId", source = "id")
    @Mapping(target = "companyName", source = "company.companyName")
    @Mapping(target = "hour", source = "salary.totalHour")
    @Mapping(target = "reward", source = "salary.totalReward")
    EmployeeDetailsDTO toEmployeeDetailsDTO(Employee employee);

    List<EmployeeDetailsDTO> toEmployeeDetailsDTOs(List<Employee> employees);

}
