package org.mattrr78.mysqltx;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Map;

@Repository
public interface SequencerRepo extends PagingAndSortingRepository<Sequencer, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT sequencer FROM Sequencer sequencer WHERE sequencer.id = ?1")
    Sequencer findByIdAndLockPessimisticWrite(String id);

    @Query(value = "SELECT prefix, next_sequence FROM sequencer WHERE id = ?1 FOR UPDATE", nativeQuery = true)
    Map<String, Object> findByIdAndLockForUpdate(String id);

    @Modifying
    @Query(value = "UPDATE sequencer SET next_sequence = next_sequence + 1 WHERE id = ?1", nativeQuery = true)
    int incrementById(String id);

}
