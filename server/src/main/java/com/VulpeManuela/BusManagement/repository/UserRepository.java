package com.VulpeManuela.BusManagement.repository;

import com.VulpeManuela.BusManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByUsernameIgnoreCase(String username);
}
