package it.companyorganization.dto;

import it.companyorganization.model.Company;
import lombok.Data;

@Data
public class EmployeeDetailsDTO {
    private long employeeId;
    private String username;
    private String email;
    private String companyName;
}
