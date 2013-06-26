package com.vlingo.core.internal.dialogmanager.types;

import com.vlingo.core.internal.social.api.SocialNetworkType;

public class SocialType
{
  private String message;
  private SocialNetworkType socialNetwork;

  public SocialType(SocialNetworkType paramSocialNetworkType, String paramString)
  {
    this.socialNetwork = paramSocialNetworkType;
    this.message = paramString;
  }

  public String getMessage()
  {
    return this.message;
  }

  public SocialNetworkType getSocialNetwork()
  {
    return this.socialNetwork;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setSocialNetwork(SocialNetworkType paramSocialNetworkType)
  {
    this.socialNetwork = paramSocialNetworkType;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.types.SocialType
 * JD-Core Version:    0.6.0
 */