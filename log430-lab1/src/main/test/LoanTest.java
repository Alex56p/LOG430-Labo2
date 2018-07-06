import com.google.common.eventbus.EventBus;
import mockit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import loanmain.*;

public class LoanTest {

    @Tested
    private LoanControler loanControler;

    //@Mocked
    //private EventBus bus;

    @Mocked
    private CalcLoanItem calc;


    private LoanItem item;

    @Test
    public void testMensualité(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.MENSUALITE);
        loanControler.setCurrentItem(item);
        new MockUp<CalcLoanItem>(){
            @Mock
            Double computeMensHorsAss(LoanItem item){
                return 10D;
            }
        };
        loanControler.updateEntry(10.0f,10.0f,0.0f,10.0f);

        Assert.assertEquals(10D, item.getMensualite(),0);
    }

    @Test
    public void testTaux(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.TAUX);
        loanControler.setCurrentItem(item);
        new MockUp<CalcLoanItem>(){
            @Mock
            Double computeRate(LoanItem item){
                return 10D;
            }
        };
        loanControler.updateEntry(10.0f,0.0f,10.0f,10.0f);

        Assert.assertEquals(10D, item.getTaux(),0);
    }

    @Test
    public void testMontant(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.MONTANT);
        loanControler.setCurrentItem(item);
        new MockUp<CalcLoanItem>(){
            @Mock
            Double computeAmount(LoanItem item){
                return 10D;
            }
        };
        loanControler.updateEntry(0.0f,10.0f,10.0f,10.0f);

        Assert.assertEquals(10D, item.getAmount(),0);
    }

    @Test
    public void testDuree(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.DUREE);
        loanControler.setCurrentItem(item);
        new MockUp<CalcLoanItem>(){
            @Mock
            Double computeDuration(LoanItem item){
                return 10D;
            }
        };
        loanControler.updateEntry(10.0f,10.0f,10.0f,0.0f);

        Assert.assertEquals(10D, item.getDuree(),0);
    }
}
