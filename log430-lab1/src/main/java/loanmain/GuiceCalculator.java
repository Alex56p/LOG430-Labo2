package loanmain;

import javax.inject.Inject;

public class GuiceCalculator {
    private Operation operation;

    @Inject
    public GuiceCalculator(Operation operation) {
        this.operation = operation;
    }

    public void execute(LoanItem item) {
        operation.operate(item);
    }
}
