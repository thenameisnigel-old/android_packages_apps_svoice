package com.vlingo.core.internal.dialogmanager;

public enum DecoratorType
{
  static
  {
    CancelButton = new DecoratorType("CancelButton", 3);
    ConfirmButton = new DecoratorType("ConfirmButton", 4);
    WebSearchButton = new DecoratorType("WebSearchButton", 5);
    TimerStartCmd = new DecoratorType("TimerStartCmd", 6);
    TimerStopCmd = new DecoratorType("TimerStopCmd", 7);
    TimerResetCmd = new DecoratorType("TimerResetCmd", 8);
    TimerRestartCmd = new DecoratorType("TimerRestartCmd", 9);
    TimerCancelCmd = new DecoratorType("TimerCancelCmd", 10);
    ContactShowEmail = new DecoratorType("ContactShowEmail", 11);
    ContactShowBirthday = new DecoratorType("ContactShowBirthday", 12);
    ContactShowAddress = new DecoratorType("ContactShowAddress", 13);
    ContactShowPhone = new DecoratorType("ContactShowPhone", 14);
    ShowMessageBody = new DecoratorType("ShowMessageBody", 15);
    DecoratorType[] arrayOfDecoratorType = new DecoratorType[16];
    arrayOfDecoratorType[0] = NoOp;
    arrayOfDecoratorType[1] = ReadOnly;
    arrayOfDecoratorType[2] = SendButton;
    arrayOfDecoratorType[3] = CancelButton;
    arrayOfDecoratorType[4] = ConfirmButton;
    arrayOfDecoratorType[5] = WebSearchButton;
    arrayOfDecoratorType[6] = TimerStartCmd;
    arrayOfDecoratorType[7] = TimerStopCmd;
    arrayOfDecoratorType[8] = TimerResetCmd;
    arrayOfDecoratorType[9] = TimerRestartCmd;
    arrayOfDecoratorType[10] = TimerCancelCmd;
    arrayOfDecoratorType[11] = ContactShowEmail;
    arrayOfDecoratorType[12] = ContactShowBirthday;
    arrayOfDecoratorType[13] = ContactShowAddress;
    arrayOfDecoratorType[14] = ContactShowPhone;
    arrayOfDecoratorType[15] = ShowMessageBody;
    $VALUES = arrayOfDecoratorType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.DecoratorType
 * JD-Core Version:    0.6.0
 */