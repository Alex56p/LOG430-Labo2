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
    private CalcLoanItem calcLoanItem;

    @Mocked
    private LoanItem item;

    @Test
    public void testMensHorsAss(){
        item = new LoanItem();
        new Expectations(){{
            loanControler.updateEntry(100.0f,10.0f,0.0f,10.0f);
            times = 1;
        }};

        Assert.assertEquals((Float)1.32f, item.getMensualite());
    }
}
