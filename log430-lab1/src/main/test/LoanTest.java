import com.google.common.eventbus.EventBus;
import mockit.*;
import org.junit.Test;
import loanmain.*;

public class LoanTest {

    @Tested
    private LoanControler loanControler;

    @Mocked
    private EventBus bus;

    @Mocked
    private CalcLoanItem calcLoanItem;

    private LoanItem item = new LoanItem();

    @Test
    public void testMensualité(){
        loanControler.setCurrentItem(item);
        loanControler.updateEntry(10.0f,10.0f,0.0f,10.0f);

        new Verifications(){
            {
                CalcLoanItem.computeMensHorsAss(item);
                times = 1;
                CalcLoanItem.computeRate(item);
                times = 0;
                CalcLoanItem.computeDuration(item);
                times = 0;
                CalcLoanItem.computeAmount(item);
                times = 0;
                bus.post(withInstanceOf(LoanChangeEvent.class));
                times = 1;
            }
        };
    }

    @Test
    public void testTaux(){
        loanControler.setCurrentItem(item);

        loanControler.updateEntry(10.0f,0.0f,10.0f,10.0f);

        new Verifications(){
            {
                CalcLoanItem.computeMensHorsAss(item);
                times = 0;
                CalcLoanItem.computeRate(item);
                times = 1;
                CalcLoanItem.computeDuration(item);
                times = 0;
                CalcLoanItem.computeAmount(item);
                times = 0;
                bus.post(withInstanceOf(LoanChangeEvent.class));
                times = 1;
            }
        };
    }

    @Test
    public void testMontant(){
        loanControler.setCurrentItem(item);
        loanControler.updateEntry(0.0f,10.0f,10.0f,10.0f);

        new Verifications(){
            {
                CalcLoanItem.computeMensHorsAss(item);
                times = 0;
                CalcLoanItem.computeRate(item);
                times = 0;
                CalcLoanItem.computeDuration(item);
                times = 0;
                CalcLoanItem.computeAmount(item);
                times = 1;
                bus.post(withInstanceOf(LoanChangeEvent.class));
                times = 1;
            }
        };
    }

    @Test
    public void testDuree(){
        loanControler.setCurrentItem(item);
        loanControler.updateEntry(10.0f,10.0f,10.0f,0.0f);

        new Verifications(){
            {
                CalcLoanItem.computeMensHorsAss(item);
                times = 0;
                CalcLoanItem.computeRate(item);
                times = 0;
                CalcLoanItem.computeDuration(item);
                times = 1;
                CalcLoanItem.computeAmount(item);
                times = 0;
                bus.post(withInstanceOf(LoanChangeEvent.class));
                times = 1;
            }
        };
    }
}
