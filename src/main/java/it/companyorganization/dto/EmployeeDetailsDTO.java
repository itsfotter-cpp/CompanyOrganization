package it.companyorganization.dto;

import lombok.Data;

@Data
public class EmployeeDetailsDTO {
    private long employeeId;
    private String username;
    private String email;
    private String companyName;
    private Double hour;
    private Double reward;

}
