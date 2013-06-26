package com.vlingo.core.internal.settings.util;

import com.vlingo.core.internal.http.URL;

public abstract class URLSetting extends StringSetting
{
  URL url = null;

  protected URLSetting(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }

  private void updateDetails()
  {
    if ((getValue() != null) && (getValue().length() > 0))
      this.url = new URL(getValue());
  }

  public URL getURL()
  {
    if (this.url == null)
      updateDetails();
    return this.url;
  }

  public void setValue(URL paramURL)
  {
    this.url = paramURL;
    super.setValueInternal(paramURL.toString());
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.settings.util.URLSetting
 * JD-Core Version:    0.6.0
 */