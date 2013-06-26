package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class AsyncFacebookRunner
{
  Facebook fb;

  public AsyncFacebookRunner(Facebook paramFacebook)
  {
    this.fb = paramFacebook;
  }

  public void logout(Context paramContext, RequestListener paramRequestListener)
  {
    new Thread(paramContext, paramRequestListener)
    {
      public void run()
      {
        try
        {
          String str = AsyncFacebookRunner.this.fb.logout(this.val$context);
          if ((str.length() == 0) || (str.equals("false")))
            this.val$listener.onFacebookError(new FacebookError("auth.expireSession failed"));
          else
            this.val$listener.onComplete(str);
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          this.val$listener.onFileNotFoundException(localFileNotFoundException);
        }
        catch (MalformedURLException localMalformedURLException)
        {
          this.val$listener.onMalformedURLException(localMalformedURLException);
        }
        catch (IOException localIOException)
        {
          this.val$listener.onIOException(localIOException);
        }
      }
    }
    .start();
  }

  public void request(Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(null, paramBundle, "GET", paramRequestListener);
  }

  public void request(String paramString, Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(paramString, paramBundle, "GET", paramRequestListener);
  }

  public void request(String paramString1, Bundle paramBundle, String paramString2, RequestListener paramRequestListener)
  {
    new Thread(paramString1, paramBundle, paramString2, paramRequestListener)
    {
      public void run()
      {
        try
        {
          String str = AsyncFacebookRunner.this.fb.request(this.val$graphPath, this.val$parameters, this.val$httpMethod);
          this.val$listener.onComplete(str);
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          while (true)
            this.val$listener.onFileNotFoundException(localFileNotFoundException);
        }
        catch (MalformedURLException localMalformedURLException)
        {
          while (true)
            this.val$listener.onMalformedURLException(localMalformedURLException);
        }
        catch (IOException localIOException)
        {
          while (true)
            this.val$listener.onIOException(localIOException);
        }
      }
    }
    .start();
  }

  public void request(String paramString, RequestListener paramRequestListener)
  {
    request(paramString, new Bundle(), "GET", paramRequestListener);
  }

  public static abstract interface RequestListener
  {
    public abstract void onComplete(String paramString);

    public abstract void onFacebookError(FacebookError paramFacebookError);

    public abstract void onFileNotFoundException(FileNotFoundException paramFileNotFoundException);

    public abstract void onIOException(IOException paramIOException);

    public abstract void onMalformedURLException(MalformedURLException paramMalformedURLException);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.facebook.android.AsyncFacebookRunner
 * JD-Core Version:    0.6.0
 */