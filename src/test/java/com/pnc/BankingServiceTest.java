package com.pnc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pnc.transaction.Repository.AccountRepository;
import com.pnc.transaction.Service.BankingService;

class BankingServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BankingService bankingService;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testTransferMoney_InvalidFromAcoountId() {
        Long fromAccountId = 999L;
        Long toAccountId = 2L;
        Double amount = 100.0;

        when(accountRepository.findById(fromAccountId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bankingService.trancferMoney(fromAccountId, toAccountId, amount);
        });

        assertEquals("Account not found" + fromAccountId, exception.getMessage());
    }

}
