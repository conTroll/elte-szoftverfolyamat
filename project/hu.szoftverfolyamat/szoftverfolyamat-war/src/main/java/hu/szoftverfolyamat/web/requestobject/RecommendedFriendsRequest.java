package hu.szoftverfolyamat.web.requestobject;

import lombok.Data;

@Data
public class RecommendedFriendsRequest {

	private String recommendByHabitat;
	private String recommendByJobAndWorkplace;
	private String recommendByBirthday;
}
