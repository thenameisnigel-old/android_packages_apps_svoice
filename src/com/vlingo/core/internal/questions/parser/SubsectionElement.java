package com.vlingo.core.internal.questions.parser;

import com.vlingo.core.internal.questions.Answer.Subsection;
import com.vlingo.core.internal.questions.DownloadableImage;
import java.util.ArrayList;

public class SubsectionElement extends ResponseElement
  implements Answer.Subsection
{
  private DownloadableImage mImage;
  private ArrayList<ImageElement> mImages = new ArrayList();

  public SubsectionElement(String paramString)
  {
    super(paramString);
  }

  public void add(ImageElement paramImageElement)
  {
    this.mImages.add(paramImageElement);
  }

  public int getHeight()
  {
    try
    {
      int j = Integer.parseInt(getAttribute("Height"));
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = 0;
    }
  }

  public DownloadableImage getImage()
  {
    return this.mImage;
  }

  public String getImageUrl()
  {
    return getAttribute("Src");
  }

  public String getText()
  {
    return getAttribute("Text");
  }

  public int getWidth()
  {
    try
    {
      int j = Integer.parseInt(getAttribute("Width"));
      i = j;
      return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = 0;
    }
  }

  public boolean hasImage()
  {
    if (this.mImage != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public void setImage(DownloadableImage paramDownloadableImage)
  {
    this.mImage = paramDownloadableImage;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.SubsectionElement
 * JD-Core Version:    0.6.0
 */