package org.mattrr78.mysqltx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column
    String id;

    @Column
    String badge;

    @Column
    String name;

}
