package net.guides.springboot2.springboot2jpacrudexample.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OW_SYS_PRJ_USER")
public class Employee {


    private String  first_name;
    private String last_name;
    private BigDecimal something2;
 
    public Employee() {
  
    }
 
    public Employee(String first_name, String last_name, BigDecimal something2 ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.something2 = something2;
    }
 
    
    @Id
    @Column(name = "project_name", nullable = false)
       public String getFirstName() {
           return first_name;
    }
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
 
    @Column(name = "user_id", nullable = false)
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "SECURITY_LEVEL")
    public BigDecimal getLastsec() {
        return something2;
    }
    public void setLastsec(BigDecimal something2) {
        this.something2 = something2;
    }


}
