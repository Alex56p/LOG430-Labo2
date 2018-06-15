package loanmain;

public class OpDuree implements Operation {

    public void operate(LoanItem item) {

        if (item.getMensualite().equals(0F) || item.getTaux().equals(0F) || item.getAmount().equals(0F)) {
            return;
        }

        double lMens = item.getMensualite() - (item.getAmount() * item.getInsurance() / 1200D);
        double lTaux = item.getTaux() / 1200D;
        Double newValue = -Math.log(1D - item.getAmount() * lTaux / lMens) / Math.log(1D + lTaux) / 12D;

        item.setDuree(newValue.floatValue());
    }
}
