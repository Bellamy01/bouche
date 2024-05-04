package com.bella.bouche.repositories;

import com.bella.bouche.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findAccountByUserName(String userName);
}
