package hu.szoftverfolyamat.repository;

import hu.szoftverfolyamat.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
