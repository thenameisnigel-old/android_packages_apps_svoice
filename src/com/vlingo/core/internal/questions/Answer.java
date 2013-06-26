package com.vlingo.core.internal.questions;

public abstract interface Answer
{
  public abstract Section[] getInformationalSections();

  public abstract Section getSection(String paramString);

  public abstract Section[] getSections();

  public abstract String getSimpleResponse();

  public abstract boolean hasAnswer();

  public abstract boolean hasMoreInformation();

  public abstract boolean hasSection(String paramString);

  public static abstract interface Section
  {
    public abstract String getName();

    public abstract Answer.Subsection[] getSubsections();
  }

  public static abstract interface Subsection
  {
    public abstract int getHeight();

    public abstract DownloadableImage getImage();

    public abstract String getImageUrl();

    public abstract String getText();

    public abstract int getWidth();

    public abstract boolean hasImage();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.Answer
 * JD-Core Version:    0.6.0
 */