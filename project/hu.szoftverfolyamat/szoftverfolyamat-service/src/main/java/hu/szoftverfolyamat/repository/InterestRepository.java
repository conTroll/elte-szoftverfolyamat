package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    @Query("SELECT i FROM InterestEntity i")
    public List<InterestEntity> findMostUsed();
}
