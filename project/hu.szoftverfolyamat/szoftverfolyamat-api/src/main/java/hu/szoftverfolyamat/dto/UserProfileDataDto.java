package hu.szoftverfolyamat.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserProfileDataDto {

	private Long credentialId;
	private String email;
	private String fullName;
	private String shortName;
	private boolean publicHabitat;
	private String habitat;
	private boolean publicJobAndWorkplace;
	private String job;
	private String workplace;
	private boolean publicBirthday;
	private String birthday;
	private Long friendNumber;
	private boolean friend;
	private Long avatarId;
}
