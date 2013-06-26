package com.vlingo.core.internal.dialogmanager.vvs;

import com.vlingo.core.internal.dialogmanager.Controller;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import java.security.InvalidParameterException;

public abstract class VVSActionHandler extends VVSActionBase
{
  protected <T extends Controller> T getController(Class<T> paramClass)
  {
    Controller localController = (Controller)getListener().getState(DialogDataType.ACTIVE_CONTROLLER);
    if (!paramClass.isInstance(localController));
    try
    {
      localController = (Controller)paramClass.newInstance();
      localController.setListener(getListener());
      localController.setTurn(this.turn);
      getListener().storeState(DialogDataType.ACTIVE_CONTROLLER, localController);
      label61: return (Controller)paramClass.cast(localController);
    }
    catch (Exception localException)
    {
      break label61;
    }
  }

  protected void throwUnknownActionException(String paramString)
    throws InvalidParameterException
  {
    throw new InvalidParameterException("Unrecognized action " + paramString + " for handler " + getClass().getName());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler
 * JD-Core Version:    0.6.0
 */