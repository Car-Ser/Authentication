package com.carsservice.CarsTeam.Repositories.Role;

import com.carsservice.CarsTeam.Model.Role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
    Optional<RoleEntity> findByName(String name);

}
