package hu.szoftverfolyamat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegistrationFormDto {

	private String username;
	private String password;
	private String email;
	private String fullName;
	private String shortName;
	private Boolean publicHabitat;
	private String habitat;
	private Boolean publicJobAndWorkplace;
	private String job;
	private String workplace;
	private Boolean publicBirthday;
	private Date birthday;
}
