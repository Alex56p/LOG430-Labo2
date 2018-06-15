package loanmain;

public class OpMensualite implements Operation {

    public void operate(LoanItem item) {

        if (item.getAmount().equals(0F) || item.getTaux().equals(0F) || item.getDuree().equals(0F)) {
            return;
        }

        Double newValue = item.getAmount() * calcF(item);
        Double mensAss = computeMensAss(item);
        Double mens = newValue + (mensAss == null ? 0D : mensAss);

        item.setMensualite(mens.floatValue());
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

    /**
     * Compute the monthly fee of the insurance only<BR>
     * We need :
     * <ol>
     * <li>An amount (A)
     * <li>A insurance rate (Ti)
     * </ol>
     *
     * @param pItem the current loan item
     * @return the monthly fee of the insurance only (Mi)
     */
    private Double computeMensAss(final LoanItem pItem) {
        if (pItem.getAmount().equals(0F) || pItem.getInsurance().equals(0F)) {
            return null;
        }
        return pItem.getAmount() * pItem.getInsurance() / 1200D;
    }
}
