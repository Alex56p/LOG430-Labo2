package loanmain;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class AppInjector extends AbstractModule{
     @Override
    protected void configure() {
        bind(LoanControler.class).to(LoanControler.class);
    }
}
