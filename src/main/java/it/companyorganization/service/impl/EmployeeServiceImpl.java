package it.companyorganization.service.impl;

import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.exception.ResourceConflictException;
import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.mappers.EmployeeMapper;
import it.companyorganization.model.*;
import it.companyorganization.repository.*;
import it.companyorganization.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    /*
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
     */

    private final PasswordEncoder passwordEncoder;

    /*
    private EmployeeDetailsDTO convertEntityToDTO(Employee employee) {

        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setEmployeeId(employee.getId());
        employeeDetailsDTO.setUsername(employee.getUsername());
        employeeDetailsDTO.setEmail(employee.getEmail());
        employeeDetailsDTO.setCompanyName(employee.getCompany().getCompanyName());
        return employeeDetailsDTO;
    }

    private List<EmployeeDetailsDTO> convertListOfEntityToDTO(List<Employee> employeeList) {

        List<EmployeeDetailsDTO> edtoList = new ArrayList<EmployeeDetailsDTO>();

        for (Employee e: employeeList) {
            EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
            employeeDetailsDTO.setEmployeeId(e.getId());
            employeeDetailsDTO.setUsername(e.getUsername());
            employeeDetailsDTO.setEmail(e.getEmail());
            employeeDetailsDTO.setCompanyName(e.getCompany().getCompanyName());

            edtoList.add(employeeDetailsDTO);
        }

        return edtoList;
    }
    */

    @Override
    public Employee saveEmployee(@Valid Employee employee) {

        log.info("Saving employee {} to the database", employee.getUsername());

        /*
         * Se il codice fiscale è già esistente allora il salvataggio non deve andare
         * a buon fine.
         */
        Optional<Employee> existingEmployee = employeeRepository.findByCf(employee.getCf());

        if(existingEmployee.isPresent()) {
            throw new ResourceConflictException("Employee", "Fiscal Code");
        }

        /*
         * In questo modo funziona sia se inserisco solo l'ID, sia se inserisco solo il CompanyName.
         */
        Company existingCompany = companyRepository.findById(employee.getCompany().getId())
                .orElse(companyRepository.findByCompanyName(employee.getCompany().getCompanyName()));

        if(existingCompany == null) {
            throw new ResourceNotFoundException("Company", "Id", employee.getCompany().getId());
        }

        Salary existingSalary = null;

        if(employee.getSalary() != null) {
            existingSalary = salaryRepository.findById(employee.getSalary().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Salary", "ID", employee.getSalary().getId()));
        }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setCompany(existingCompany);
        employee.setSalary(existingSalary);

        return employeeRepository.save(employee);
    }

    /*
     * Salvare più di un Employee, in modo che mentre sta salvando stoppo il client
     * MySQL per vedere il tipo di eccezione e provare la proprietà
     * rollBackFor sull'annotation @Transactional.
     */

    @Override
    public Employee addRoleToEmployee(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Employee employee = employeeRepository.findByUsername(username);
        RoleEntity roleEntity = roleRepository.findByName(roleName);
        employee.getRoles().add(roleEntity);

        return employee;
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(long id) {
        /*Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }*/

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        if(existingEmployee.getSalary() != null) {
            Salary salary = existingEmployee.getSalary();
            salary.setTotalHour(salary.calculateTotalHour());
            salary.setTotalReward(salary.calculateTotalReward());
        }

        //existingEmployee.setSalary(salary);

        return existingEmployee;
    }

    @Override
    public List<EmployeeDetailsDTO> getEmployeeByCompanyId(long companyId) {

        List<Employee> listEmployee = employeeRepository.findByCompanyId(companyId);
        for(Employee employee : listEmployee) {
            if(employee.getSalary() != null) {
                Salary salary = employee.getSalary();
                salary.setTotalHour(salary.calculateTotalHour());
                salary.setTotalReward(salary.calculateTotalReward());
            }
        }

        return employeeMapper.toEmployeeDetailsDTOs(listEmployee);
    }

    @Override
    public EmployeeDetailsDTO getDetailedEmployee(long id) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        if(existingEmployee.getSalary() != null) {
            Salary salary = existingEmployee.getSalary();
            salary.setTotalHour(salary.calculateTotalHour());
            salary.setTotalReward(salary.calculateTotalReward());
        }

        return employeeMapper.toEmployeeDetailsDTO(existingEmployee);
    }

    @Override
    public Employee addPhotoToEmployee(Image image, long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        Image existingImage = imageRepository.findById(image.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Image", "ID", image.getId())
        );

        existingEmployee.setPhoto(existingImage);

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public Optional<Image> getPhotoFromEmployee(long id) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        Optional<Image> image = null;

        if(existingEmployee.getPhoto() != null) {
            image = imageRepository.findById(existingEmployee.getPhoto().getId());
        }
        else {
            throw new ResourceNotFoundException("Image", "Employee ID", id);
        }

        return image;
    }

    @Override
    public Optional<List<Employee>> getEmployeeFromDataRange(Date dataRange) {
        Optional<List<Employee>> employeeList = employeeRepository.findEmployeeFromDataRange(dataRange);
        for(Employee e : employeeList.get()) {
            Salary salary = e.getSalary();
            salary.setTotalHour(salary.calculateTotalHour());
            salary.setTotalReward(salary.calculateTotalReward());
        }
        return employeeRepository.findEmployeeFromDataRange(dataRange);
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //Check if the employee with ID 'id' exist in the db or not.
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        Optional<Employee> existingCfEmployee = employeeRepository.findByCfAndIdIsNot(employee.getCf(), id);
        if(existingCfEmployee.isPresent()) {
            throw new ResourceConflictException("Employee", "Fiscal Code");
        }

        if(employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        if(employee.getCf() != null) {
            existingEmployee.setCf(employee.getCf());
        }
        if(employee.getUsername() != null) {
            existingEmployee.setUsername(employee.getUsername());
        }
        if(employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        if(employee.getCompany() != null) {
            existingEmployee.setCompany(employee.getCompany());
        }

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        //Check if the employee with ID 'id' exist in the db or not.
        employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getRelationEmployeeCompany(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "ID", id)
        );

        return employee.getCompany().getEmployees();
    }

    private static final String USER_NOT_FOUND_MESSAGE = "User with username %s not found!";
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if(employee == null) {
            String message = String.format(USER_NOT_FOUND_MESSAGE, username);
            log.error(message);
            throw new UsernameNotFoundException(message);
        }
        else {
            log.debug("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            employee.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            return new User(
                    employee.getUsername(),
                    employee.getPassword(),
                    authorities
            );
        }
    }
}
