package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserProfileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProfileDataRepository extends JpaRepository<UserProfileData, Long> {

    @Query("SELECT upd FROM UserProfileData upd WHERE :interestId IN upd.interests")
    List<UserProfileData> findByInterest(final @Param("interestId") long interest);
}
