package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
}
