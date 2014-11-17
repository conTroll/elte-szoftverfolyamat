package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserProfileData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDataRepository extends JpaRepository<UserProfileData, Long> {

}
