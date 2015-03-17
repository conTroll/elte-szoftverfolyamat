package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

}
