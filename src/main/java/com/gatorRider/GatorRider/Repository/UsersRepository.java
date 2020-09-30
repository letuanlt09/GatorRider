package com.gatorRider.GatorRider.Repository;

import com.gatorRider.GatorRider.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Override
    @Query("Select users from Users users")
    List<Users> findAll();
}
