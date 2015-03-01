package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserProfileData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomUserProfileDataRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<UserProfileData> searchUserProfileData(String emailAddress,
			String fullName, String place, String job) {
		String query;
		boolean firstElement;

		firstElement = true;

		query = "select upd from UserProfileData upd";
		if ((emailAddress != null) && !emailAddress.isEmpty()) {
			query += " where upd.email like '%" + emailAddress + "%'";
			firstElement = false;
		}
		if ((fullName != null) && !fullName.isEmpty()) {
			query += (firstElement ? " where upd.fullName like '%"
					: " and upd.fullName like '%") + fullName + "%'";
			firstElement = false;
		}
		if ((place != null) && !place.isEmpty()) {
			query += (firstElement ? " where upd.place like '%"
					: " and upd.place like '%") + place + "%'";
			firstElement = false;
		}
		if ((job != null) && !job.isEmpty()) {
			query += (firstElement ? " where upd.job like '%"
					: " and upd.job like '%") + job + "%'";
			firstElement = false;
		}
		
		return this.entityManager.createQuery(query, UserProfileData.class).getResultList();
	}
}
