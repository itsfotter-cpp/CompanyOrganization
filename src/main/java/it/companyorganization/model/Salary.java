package it.companyorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "salary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private long id;
    @NotNull
    @Column(name = "laborHours")
    private double laborHours;
    @NotNull
    @Column(name = "overTime")
    private double overTime;
    @Column(name = "rewardPerHour")
    private double rewardPerHour;
    @Transient
    private Double totalHour;
    @Transient
    private Double totalReward;

    @JsonIgnore
    @OneToMany(mappedBy = "salary", fetch = FetchType.EAGER)
    private List<Employee> employee;

    public Double calculateTotalHour() {
        return this.laborHours + this.overTime;
    }
    public Double calculateTotalReward() {
        return (this.laborHours + this.overTime)*this.rewardPerHour;
    }

}
