package org.mattrr78.mysqltx;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequencer")
public class Sequencer {

    @Id
    @Column
    String id;

    @Column
    String prefix;

    @Column(name = "next_sequence")
    int nextSequence;

}
