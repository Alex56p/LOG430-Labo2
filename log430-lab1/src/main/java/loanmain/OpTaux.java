package loanmain;

import loansolver.OneParamFuncItf;
import loansolver.Solver;
import loansolver.SolverItf;

public class OpTaux implements Operation {

    public void operate(LoanItem item) {

        if (item.getMensualite().equals(0F) || item.getAmount().equals(0F) || item.getDuree().equals(0F)) {
            return;
        }

        Double newValue = calcTauxBis(item);
        item.setTaux(newValue.floatValue());
    }

    /**
     * Compute the loan rate. We need :
     * <ol>
     * <li>A duration (D)
     * <li>An amount (A)
     * <li>A monthly fee (Mt)
     * <li>An insurance rate (Ti)
     * </ol>
     *
     * @param pItem the current loan item
     * @return the loan rate in %
     */
    private Double calcTauxBis(final LoanItem pItem) {
        return CalcLoanItem.solveTaux(pItem.getMensualite() / pItem.getAmount() - pItem.getInsurance() / 1200D, pItem);
    }
}
