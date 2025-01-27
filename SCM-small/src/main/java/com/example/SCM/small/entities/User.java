package com.example.SCM.small.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed")
    private String firstname;

    @NotBlank(message = "Second name is required")
    @Size(min = 2, max = 20, message = "Min 2 and max 20 characters are allowed")
    private String secondname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "Password must be at least 8 characters long and contain at least one capital letter")
    private String password;

    @NotBlank(message = "Repeat Password is required")
    @Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "Password must be at least 8 characters long and contain at least one capital letter")
    @Transient 
    private String repeatPassword;

    private String role;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotBlank(message = "College is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "College must contain only characters")
    private String college;

    @NotBlank(message = "College Year is required")
    private String collegeYear;

    @NotBlank(message = "Best Skill is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Best Skill must contain only characters")
    private String bestSkill;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must contain only characters")
    private String city;

    @NotBlank(message = "Experience is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Experience must contain only characters")
    private String experience;

    private boolean enabled;
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<contact> contact = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCollegeYear() {
		return collegeYear;
	}

	public void setCollegeYear(String collegeYear) {
		this.collegeYear = collegeYear;
	}

	public String getBestSkill() {
		return bestSkill;
	}

	public void setBestSkill(String bestSkill) {
		this.bestSkill = bestSkill;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<contact> getContact() {
		return contact;
	}

	public void setContact(List<contact> contact) {
		this.contact = contact;
	}

	public String getRepeatPassword() {
	    return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
	    this.repeatPassword = repeatPassword;
	}
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", secondname=" + secondname + ", email=" + email
				+ ", password=" + password + ", repeatPassword=" + repeatPassword + ", role=" + role + ", age=" + age
				+ ", college=" + college + ", collegeYear=" + collegeYear + ", bestSkill=" + bestSkill + ", city="
				+ city + ", experience=" + experience + ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", contact="
				+ contact + "]";
	}

 
}
