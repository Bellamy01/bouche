package com.bella.bouche.services;

import com.bella.bouche.models.Account;
import com.bella.bouche.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public  Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    public Optional<Account> getAccountByUserName(String userName) {
        return accountRepository.findAccountByUserName(userName);
    }

    public Account saveAccount(Account account) {
        if (account.getId() != null) {
            account.setCreatedAt(LocalDateTime.now());
        }
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
