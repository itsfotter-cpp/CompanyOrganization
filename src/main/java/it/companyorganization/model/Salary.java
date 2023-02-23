package it.companyorganization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    @Column(name = "labor_hours")
    private double laborHours;
    @NotNull
    @Column(name = "over_time")
    private double overTime;
    @Column(name = "reward_per_hour")
    private double rewardPerHour;
    @Column(name = "bonus")
    private double bonus;
    @Column(name = "data_insert")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataInsert;
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
        return ((this.laborHours + this.overTime)*this.rewardPerHour)+this.bonus;
    }

}
