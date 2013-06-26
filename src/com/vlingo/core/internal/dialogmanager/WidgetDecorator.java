package com.vlingo.core.internal.dialogmanager;

public class WidgetDecorator
{
  private WidgetDecorator next = null;
  private final DecoratorType type;

  private WidgetDecorator()
  {
    this.type = DecoratorType.NoOp;
  }

  private WidgetDecorator(DecoratorType paramDecoratorType)
  {
    this.type = paramDecoratorType;
  }

  public static WidgetDecorator makeCancelButton()
  {
    return new WidgetDecorator(DecoratorType.CancelButton);
  }

  public static WidgetDecorator makeConfirmButton()
  {
    return new WidgetDecorator(DecoratorType.ConfirmButton);
  }

  public static WidgetDecorator makeContactShowAddress()
  {
    return new WidgetDecorator(DecoratorType.ContactShowAddress);
  }

  public static WidgetDecorator makeContactShowBirthday()
  {
    return new WidgetDecorator(DecoratorType.ContactShowBirthday);
  }

  public static WidgetDecorator makeContactShowEmail()
  {
    return new WidgetDecorator(DecoratorType.ContactShowEmail);
  }

  public static WidgetDecorator makeContactShowPhone()
  {
    return new WidgetDecorator(DecoratorType.ContactShowPhone);
  }

  public static WidgetDecorator makeReadOnly()
  {
    return new WidgetDecorator(DecoratorType.ReadOnly);
  }

  public static WidgetDecorator makeSendButton()
  {
    return new WidgetDecorator(DecoratorType.SendButton);
  }

  public static WidgetDecorator makeShowMessageBody()
  {
    return new WidgetDecorator(DecoratorType.ShowMessageBody);
  }

  public static WidgetDecorator makeTimerCancelCmd()
  {
    return new WidgetDecorator(DecoratorType.TimerCancelCmd);
  }

  public static WidgetDecorator makeTimerResetCmd()
  {
    return new WidgetDecorator(DecoratorType.TimerResetCmd);
  }

  public static WidgetDecorator makeTimerRestartCmd()
  {
    return new WidgetDecorator(DecoratorType.TimerRestartCmd);
  }

  public static WidgetDecorator makeTimerStartCmd()
  {
    return new WidgetDecorator(DecoratorType.TimerStartCmd);
  }

  public static WidgetDecorator makeTimerStopCmd()
  {
    return new WidgetDecorator(DecoratorType.TimerStopCmd);
  }

  public static WidgetDecorator makeWebSearchButton()
  {
    return new WidgetDecorator(DecoratorType.WebSearchButton);
  }

  public static WidgetDecorator noOp()
  {
    return new WidgetDecorator();
  }

  private WidgetDecorator prepend(WidgetDecorator paramWidgetDecorator)
  {
    paramWidgetDecorator.next = this;
    return paramWidgetDecorator;
  }

  public WidgetDecorator cancelButton()
  {
    return prepend(new WidgetDecorator(DecoratorType.CancelButton));
  }

  public WidgetDecorator confirmButton()
  {
    return prepend(new WidgetDecorator(DecoratorType.ConfirmButton));
  }

  public WidgetDecorator contactShowAddress()
  {
    return prepend(new WidgetDecorator(DecoratorType.ContactShowAddress));
  }

  public WidgetDecorator contactShowBirthday()
  {
    return prepend(new WidgetDecorator(DecoratorType.ContactShowBirthday));
  }

  public WidgetDecorator contactShowEmail()
  {
    return prepend(new WidgetDecorator(DecoratorType.ContactShowEmail));
  }

  public WidgetDecorator contactShowPhone()
  {
    return prepend(new WidgetDecorator(DecoratorType.ContactShowPhone));
  }

  public boolean has(DecoratorType paramDecoratorType)
  {
    boolean bool;
    if (this.type == paramDecoratorType)
      bool = true;
    while (true)
    {
      return bool;
      if (this.next != null)
      {
        bool = this.next.has(paramDecoratorType);
        continue;
      }
      bool = false;
    }
  }

  public WidgetDecorator readOnly()
  {
    return prepend(new WidgetDecorator(DecoratorType.ReadOnly));
  }

  public WidgetDecorator sendButton()
  {
    return prepend(new WidgetDecorator(DecoratorType.SendButton));
  }

  public WidgetDecorator showMessageBody()
  {
    return prepend(new WidgetDecorator(DecoratorType.ShowMessageBody));
  }

  public WidgetDecorator timerResetCmd()
  {
    return prepend(new WidgetDecorator(DecoratorType.TimerResetCmd));
  }

  public WidgetDecorator timerRestartCmd()
  {
    return prepend(new WidgetDecorator(DecoratorType.TimerRestartCmd));
  }

  public WidgetDecorator timerStartCmd()
  {
    return prepend(new WidgetDecorator(DecoratorType.TimerStartCmd));
  }

  public WidgetDecorator timerStopCmd()
  {
    return prepend(new WidgetDecorator(DecoratorType.TimerStopCmd));
  }

  public WidgetDecorator webSearchButton()
  {
    return prepend(new WidgetDecorator(DecoratorType.WebSearchButton));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.WidgetDecorator
 * JD-Core Version:    0.6.0
 */