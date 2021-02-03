package org.mattrr78.mysqltx;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleRestController {

    private final SequencerService sequencerService;

    private final AccountService accountService;

    private final PlatformTransactionManager transactionManager;

    public SampleRestController(SequencerService sequencerService, AccountService accountService,
                                PlatformTransactionManager transactionManager)  {
        this.sequencerService = sequencerService;
        this.accountService = accountService;
        this.transactionManager = transactionManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> persistAccount(@RequestParam String sequencerId, @RequestParam String accountName)  {
        TransactionStatus status = TransactionAspectSupport.currentTransactionStatus();

        Sequencer sequencer = sequencerService.findSequencer(sequencerId);

        Account account = accountService.createAndSaveAccount(sequencer.prefix, sequencer.nextSequence, accountName);

        sleep();

        sequencerService.updateSequencer(sequencer);

        return ResponseEntity.ok(account.id);
    }

    private void sleep()  {
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e)  {}
    }

}
