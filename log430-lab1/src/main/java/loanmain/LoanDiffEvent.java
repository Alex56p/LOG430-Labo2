package loanmain;

import java.util.EventObject;

public class LoanDiffEvent extends EventObject
{
    public LoanDiffEvent(Object changedItem)
    {
        super(changedItem);
    }

    public LoanItem GetChangedItem()
    {
        return (LoanItem)getSource();
    }
}
