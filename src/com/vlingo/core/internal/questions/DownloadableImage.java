package com.vlingo.core.internal.questions;

import android.graphics.drawable.Drawable;
import java.util.HashSet;
import java.util.Iterator;

public class DownloadableImage
{
  private Drawable image;
  private HashSet<Listener> listeners = new HashSet();
  private boolean timedout;
  private String url;

  public DownloadableImage(String paramString)
    throws NullPointerException
  {
    if (paramString == null)
      throw new NullPointerException("you MUST provide a 'url'");
    this.url = paramString;
  }

  private void notifyListeners(Notification paramNotification)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      paramNotification.notify((Listener)localIterator.next());
  }

  public DownloadableImage addListener(Listener paramListener)
  {
    this.listeners.add(paramListener);
    return this;
  }

  public Drawable getDrawable()
  {
    return this.image;
  }

  public String getURL()
  {
    return this.url;
  }

  public boolean isDownloaded()
  {
    if (this.image != null);
    for (int i = 1; ; i = 0)
      return i;
  }

  public DownloadableImage removeListener(Listener paramListener)
  {
    this.listeners.remove(paramListener);
    return this;
  }

  public DownloadableImage setDrawable(Drawable paramDrawable)
  {
    this.image = paramDrawable;
    notifyListeners(new Notification()
    {
      public void notify(DownloadableImage.Listener paramListener)
      {
        paramListener.onDownloaded(DownloadableImage.this);
      }
    });
    return this;
  }

  public void timedOut(boolean paramBoolean)
  {
    this.timedout = paramBoolean;
    if (!isDownloaded())
      notifyListeners(new Notification()
      {
        public void notify(DownloadableImage.Listener paramListener)
        {
          paramListener.onTimeout(DownloadableImage.this);
        }
      });
  }

  public boolean timedOut()
  {
    return this.timedout;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (isDownloaded());
    for (String str = "[DONE]"; ; str = "[....]")
      return str + " " + this.url;
  }

  public static abstract interface Listener
  {
    public abstract void onDownloaded(DownloadableImage paramDownloadableImage);

    public abstract void onTimeout(DownloadableImage paramDownloadableImage);
  }

  private static abstract interface Notification
  {
    public abstract void notify(DownloadableImage.Listener paramListener);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.DownloadableImage
 * JD-Core Version:    0.6.0
 */