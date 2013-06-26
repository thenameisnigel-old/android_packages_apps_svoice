package com.vlingo.midas.util;

import android.content.Context;
import com.vlingo.sdk.recognition.VLRecognitionErrors;

public class ErrorCodeUtils
{
  public static String getLocalizedMessageForErrorCode(VLRecognitionErrors paramVLRecognitionErrors, Context paramContext)
  {
    String str = paramContext.getString(2131362328);
    switch (1.$SwitchMap$com$vlingo$sdk$recognition$VLRecognitionErrors[paramVLRecognitionErrors.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    }
    while (true)
    {
      return str;
      str = paramContext.getString(2131362329);
      continue;
      str = paramContext.getString(2131362331);
      continue;
      str = paramContext.getString(2131362332);
      continue;
      str = paramContext.getString(2131362333);
      continue;
      str = paramContext.getString(2131362334);
      continue;
      str = paramContext.getString(2131362335);
      continue;
      str = paramContext.getString(2131362336);
      continue;
      str = paramContext.getString(2131362337);
      continue;
      str = paramContext.getString(2131362330);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.util.ErrorCodeUtils
 * JD-Core Version:    0.6.0
 */