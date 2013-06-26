package com.vlingo.sdk.recognition.dialog;

public final class VLDialogEventFieldGroup extends VLDialogEvent
{
  private VLDialogEventFieldGroup(Builder paramBuilder)
  {
    super(paramBuilder);
  }

  public static final class Builder extends VLDialogEvent.Builder
  {
    public Builder(String paramString)
    {
      super();
    }

    public VLDialogEventFieldGroup build()
    {
      return new VLDialogEventFieldGroup(this, null);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.dialog.VLDialogEventFieldGroup
 * JD-Core Version:    0.6.0
 */