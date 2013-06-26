package com.vlingo.core.internal.messages;

import android.content.Context;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.VlingoAndroidCore;
import com.vlingo.core.internal.safereader.SafeReaderAlert;
import com.vlingo.sdk.internal.util.StringUtils;

public class SMSMMSAlert extends SafeReaderAlert
{
  private String body = "";
  private String subject = "";

  public SMSMMSAlert(long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramLong1, paramString1, paramString2, paramLong2, paramString5);
    this.body = paramString3;
    this.subject = paramString4;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getDisplayableMessageText(Context paramContext)
  {
    String str = getMessageText();
    if (StringUtils.isNullOrWhiteSpace(str))
      if (!isMMS())
        break label37;
    label37: for (str = paramContext.getString(VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_car_sms_message_empty_tts)); ; str = paramContext.getString(VlingoAndroidCore.getResourceProvider().getResourceId(ResourceIdProvider.string.core_car_sms_message_empty_tts)))
      return str;
  }

  public String getMessageText()
  {
    String str = this.body;
    if (this.body == null)
      str = this.subject;
    if (StringUtils.isNullOrWhiteSpace(str))
      str = "";
    return str;
  }

  public String getSenderName()
  {
    String str = getSenderDisplayName();
    if (StringUtils.isNullOrWhiteSpace(str))
      str = getAddress();
    return str;
  }

  public String getSubject()
  {
    return this.subject;
  }

  public boolean isMMS()
  {
    return "MMS".equals(this.type);
  }

  public boolean isSMS()
  {
    return "SMS".equals(this.type);
  }

  public void setBody(String paramString)
  {
    this.body = paramString;
  }

  public void setSubject(String paramString)
  {
    this.subject = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.messages.SMSMMSAlert
 * JD-Core Version:    0.6.0
 */