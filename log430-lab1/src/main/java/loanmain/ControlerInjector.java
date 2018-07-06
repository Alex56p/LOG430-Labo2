package loanmain;

import com.google.inject.AbstractModule;

public class ControlerInjector extends AbstractModule{
     @Override
    protected void configure() {
        bind(Controler.class).to(LoanControler.class);
    }
}
