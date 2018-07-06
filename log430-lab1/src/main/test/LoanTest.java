import com.google.common.eventbus.EventBus;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import loanmain.*;
import mockit.Tested;

public class LoanTest {

    @Tested
    private LoanControler loanControler;

    @Mocked
    private EventBus bus;

    @Mocked
    private CalcLoanItem calc;

    private LoanItem item;

    @Test
    public void testMensualité(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.MENSUALITE);
        item.setMensualite(0.13215074f);
        loanControler.setCurrentItem(item);
        new Expectations(calc){{
        bus.post(withInstanceOf(LoanChangeEvent.class));
        times = 1;
        }};
        loanControler.updateEntry(10.0f,10.0f,0.0f,10.0f);

        Assert.assertEquals((Float)0.13215074f, item.getMensualite());
    }

    @Test
    public void testTaux(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.TAUX);
        loanControler.setCurrentItem(item);
        new Expectations(){{

        }};
        loanControler.updateEntry(100.0f,0.0f,100.0f,100.0f);

        Assert.assertEquals((Float)1000.0f, item.getTaux());
    }

    @Test
    public void testMontant(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.MONTANT);
        loanControler.setCurrentItem(item);
        new Expectations(){{

        }};
        loanControler.updateEntry(0.0f,100.0f,100.0f,100.0f);

        Assert.assertEquals((Float)1200.0f, item.getAmount());
    }

    @Test
    public void testDuree(){
        item = new LoanItem();
        item.setLoanType(LoanItem.LoanType.DUREE);
        loanControler.setCurrentItem(item);
        new Expectations(){{

        }};
        loanControler.updateEntry(100.0f,100.0f,100.0f,0.0f);

        Assert.assertEquals((Float)0.09058849f, item.getDuree());
    }
}
