import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import loanmain.*;
import mockit.Tested;

public class LoanTest {

    @Tested
    private LoanControler loanControler;

    @Injectable
    private EventBusManager bus;

    @Test
    public void testMensHorsAss(){
        LoanItem item = new LoanItem();
        loanControler.setCurrentItem(item);
        new Expectations(){{

        }};
        loanControler.updateEntry(100.0f,10.0f,0.0f,10.0f);

        Assert.assertEquals((Float)1.32f, item.getMensualite());
    }
}
