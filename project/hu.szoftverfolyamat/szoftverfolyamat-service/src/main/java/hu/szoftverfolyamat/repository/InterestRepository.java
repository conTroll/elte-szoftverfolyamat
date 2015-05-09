package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    @Query("SELECT i FROM InterestEntity i WHERE :user IN i.users")
    public List<InterestEntity> findByUser(final @Param("user") long userId);

    @Query("SELECT i FROM InterestEntity i WHERE :channel IN i.channels")
    public List<InterestEntity> findByChannel(final @Param("channel") long channel);

    @Query("SELECT i FROM InterestEntity i")
    public List<InterestEntity> findMostUsed();
}
