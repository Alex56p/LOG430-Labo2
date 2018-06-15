package loanmain;

public class OpMontant implements Operation {

    public void operate(LoanItem item) {
        if (item.getMensualite().equals(0F) || item.getTaux().equals(0F) || item.getDuree().equals(0F)) {
            return;
        }

        Double newValue = item.getMensualite() / (calcF(item) + item.getInsurance() / 1200D);

        item.setAmount(newValue.floatValue());
    }

    /**
     * Compute the f(Te, D). We need :
     * <ol>
     * <li>A duration (D)
     * <li>A loan rate (Te)
     * </ol>
     *
     * @param pItem the current loan item
     * @return the function f (see on top of this page)
     */
    private Double calcF(final LoanItem pItem) {
        double lTaux = pItem.getTaux();
        double lDuration = pItem.getDuree();
        double lX = lTaux / 1200D;
        return lX / (1D - 1D / Math.pow(1D + lX, lDuration * 12D));
    }
}
