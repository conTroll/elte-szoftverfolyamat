package hu.szoftverfolyamat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserProfileDataDto {

	private Long credentialId;
	
	@Size(min=4, max=20) @NotNull @Email
	private String email;
	
	@NotNull @Size(min=4, max=128)
	private String fullName;
	
	@NotNull @Size(min=4, max=32)
	private String shortName;
	
	@NotNull
	private boolean publicHabitat;
	
	@NotNull @Size(min=4, max=128)
	private String habitat;
	
	@NotNull 
	private boolean publicJobAndWorkplace;
	
	@NotNull @Size(min=1, max=128)
	private String job;
	
	@Size(min=1, max=128)
	private String workplace;
	
	@NotNull
	private boolean publicBirthday;
	
	@NotNull @DateTimeFormat(pattern="YYYY.MM.DD.")
	private String birthday;
	
	private Long friendNumber;
	private boolean friend;
	private Long avatarId;
}
