
package com.curd.model;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

//Employee ID
//FirstName
//LastName
//Email
//PhoneNumber(May have multiple phone numbers)
//DOJ
//Salary(per month)
@Data
@Entity
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer eId;
@Pattern(regexp="^[A-Za-z]*$",message = "firstname should be enter in characters only")
@Size(min = 2,max = 30, message = " firstname should be atleast characters and maxmimum 30 characters")
private String firstName;
@NotEmpty(message = " lastname should be enter")
@Pattern(regexp="^[A-Za-z]*$",message = "lastname should be enter in characters only")
@Size(min = 2,max = 30, message = " lastname should be atleast characters and maxmimum 30 characters")
private String lastName;
@NotEmpty(message = "email should be enter")
@Email(message = "email should be valid",regexp = "^[a-zA-z0-9_\\-\\.]+[@][a-z]+[\\.][a-z]{2,5}$")
private String email;
@NotEmpty(message = "mobile should be enter")
@Pattern(regexp = "^[6-9][0-9]{9}$")
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "emp_id")
private List<MultiplePhoneNumber> phoneNumbers;
private Double eSal;
private Double taxAmount;
private Double cessAmount;
@NotNull(message = "date of joining should be enter")
private Date dateOfJoining;

}
