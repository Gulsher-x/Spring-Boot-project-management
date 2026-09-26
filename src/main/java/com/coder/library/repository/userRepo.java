package com.coder.library.repository;

import com.coder.library.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<users,Long> {

}
