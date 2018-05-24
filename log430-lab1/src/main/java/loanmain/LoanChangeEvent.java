package loanmain;

import java.util.EventObject;

public class LoanChangeEvent extends EventObject
{
    public LoanChangeEvent(Object changedItem)
    {
        super(changedItem);
    }

    public Object GetChangedItem()
    {
        return getSource();
    }
}
