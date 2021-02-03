package org.mattrr78.mysqltx;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SequencerService {

    private final SequencerRepo sequencerRepo;

    public SequencerService(SequencerRepo sequencerRepo) {
        this.sequencerRepo = sequencerRepo;
    }

    protected Sequencer findSequencer(String id)  {
        //return findSequencerNativeQueryForUpdate(id);
        return sequencerRepo.findByIdAndLockPessimisticWrite(id);
    }

    private Sequencer findSequencerNativeQueryForUpdate(String id)  {
        Map<String, Object> map = sequencerRepo.findByIdAndLockForUpdate(id);

        Sequencer sequencer = new Sequencer();
        sequencer.id = id;
        sequencer.prefix = (String)map.get("prefix");
        sequencer.nextSequence = (int)map.get("next_sequence");

        return sequencer;
    }

    public void updateSequencer(Sequencer sequencer)  {
        //sequencerRepo.incrementById(sequencer.id);
        sequencer.nextSequence++;
    }

}
