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
        return solveTaux(pItem.getMensualite() / pItem.getAmount() - pItem.getInsurance() / 1200D, pItem);
    }

    /**
     * Solve the function rate for a given amount
     *
     * @param pC the amount to retrieve
     * @param pItem the current loan item
     * @return the root of the function for this amount
     */
    private Double solveTaux(final Double pC, final LoanItem pItem) {
        final double lD = pItem.getDuree();
        final double lPuis = 1D + 1D / 12D / lD;
        class lFunc implements OneParamFuncItf<Double> {

            @Override
            public Double f(Double pX) {
                return Math.pow(pX, lPuis) - (pC + 1D) * pX + pC;
            }
        }
        double lZ0 = Math.pow((pC + 1D) / lPuis, 12D * lD);
        SolverItf<Double> lSolver = new Solver();
        try {
            Double lRoot = lSolver.solve(new lFunc(), (lZ0 + 1D) / 2D, lZ0 + 1D, 0.00001D);
            return 1200D * (Math.pow(lRoot, 1D / 12D / lD) - 1D);
        } catch (ArithmeticException lEx) {
            return 1000D;
        }
    }
}
