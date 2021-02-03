package org.mattrr78.mysqltx;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
public class AccountService {

    private final EntityManager entityManager;

    public AccountService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Account createAndSaveAccount(String prefix, int nextSequence, String name)  {
        Account account = createAccount(prefix, nextSequence, name);
        entityManager.persist(account);
        return account;
    }

    public Account createAccount(String prefix, int nextSequence, String name)  {
        Account account = new Account();
        account.id = UUID.randomUUID().toString();
        account.badge = prefix + "-" + nextSequence;
        account.name = name;
        return account;
    }

}
