import org.junit.jupiter.api.Test;

import com.example.money.ATM;
import com.example.money.BanknoteFifty;
import com.example.money.BanknoteFive;
import com.example.money.BanknoteHundred;

import org.junit.jupiter.api.Assertions;

public class MoneyTests {

    private static final int VALID_AMOUNT = 185;
    private static final int EXACT_AMOUNT_HUNDRED = 100;
    private static final int EXACT_AMOUNT_FIFTY = 50;
    private static final int EXACT_AMOUNT_FIVE = 5;
    private static final int INVALID_AMOUNT = 3;
    private static final int ZERO_AMOUNT = 0;
    private static final int BANKNOTE_HIERARCHY_VALID = 155;
    private static final int BANKNOTE_HIERARCHY_INVALID = 2;

    @Test
    void testATMProcessValidAmount() {
        ATM atm = new ATM();
        Assertions.assertDoesNotThrow(() -> atm.process(VALID_AMOUNT));
    }

    @Test
    void testATMProcessExactAmount() {
        ATM atm = new ATM();
        Assertions.assertDoesNotThrow(() -> atm.process(EXACT_AMOUNT_HUNDRED));
        Assertions.assertDoesNotThrow(() -> atm.process(EXACT_AMOUNT_FIFTY));
        Assertions.assertDoesNotThrow(() -> atm.process(EXACT_AMOUNT_FIVE));
    }

    @Test
    void testATMProcessInvalidAmount() {
        ATM atm = new ATM();
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> atm.process(INVALID_AMOUNT));
    }

    @Test
    void testATMProcessZeroAmount() {
        ATM atm = new ATM();
        Assertions.assertDoesNotThrow(() -> atm.process(ZERO_AMOUNT));
    }

    @Test
    void testBanknoteHierarchy() {
        BanknoteHundred hundred = new BanknoteHundred();
        BanknoteFifty fifty = new BanknoteFifty();
        BanknoteFive five = new BanknoteFive();

        hundred.setNextItem(fifty).setNextItem(five);

        Assertions.assertDoesNotThrow(() -> hundred
        .process(BANKNOTE_HIERARCHY_VALID));
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> hundred.process(BANKNOTE_HIERARCHY_INVALID));
    }

    @Test
    void testBanknoteNextItem() {
        BanknoteHundred hundred = new BanknoteHundred();
        BanknoteFifty fifty = new BanknoteFifty();
        hundred.setNextItem(fifty);

        Assertions.assertEquals(fifty, hundred.getNextItem());
    }
}
