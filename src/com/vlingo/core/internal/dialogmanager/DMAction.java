package com.vlingo.core.internal.dialogmanager;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.ActionInterface;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;

public abstract class DMAction
  implements ActionInterface
{
  protected VVSActionHandlerListener actionHandlerListener;
  private Context context;
  private Listener listener;

  public void abort()
  {
    this.listener.actionAbort();
  }

  public DMAction actionHandlerListener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    this.actionHandlerListener = paramVVSActionHandlerListener;
    return this;
  }

  public DMAction context(Context paramContext)
  {
    this.context = paramContext;
    return this;
  }

  protected abstract void execute();

  protected Context getContext()
  {
    return this.context;
  }

  protected Listener getListener()
  {
    return this.listener;
  }

  protected void init(Context paramContext, Listener paramListener)
  {
    setContext(paramContext);
    setListener(paramListener);
  }

  public DMAction listener(Listener paramListener)
  {
    this.listener = paramListener;
    return this;
  }

  public void queue()
  {
    if (this.actionHandlerListener != null)
      this.actionHandlerListener.execute(this);
  }

  public void readyToExec()
  {
    execute();
  }

  public DMAction setActionHandlerListener(VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    return actionHandlerListener(paramVVSActionHandlerListener);
  }

  public DMAction setContext(Context paramContext)
  {
    return context(paramContext);
  }

  public DMAction setListener(Listener paramListener)
  {
    return listener(paramListener);
  }

  public static abstract interface Listener
  {
    public abstract void actionAbort();

    public abstract void actionFail(String paramString);

    public abstract void actionSuccess();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DMAction
 * JD-Core Version:    0.6.0
 */