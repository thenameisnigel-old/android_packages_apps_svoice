package com.tencent.tauth.http;

import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;

class AsyncHttpRequestRunner$1 extends Thread
{
  public void run()
  {
    try
    {
      String str = ClientHttpRequest.openUrl(this.val$url, this.val$httpMethod, this.val$parameters, 0);
      this.val$listener.onComplete(str, this.val$state);
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      while (true)
        this.val$listener.onFileNotFoundException(localFileNotFoundException, this.val$state);
    }
    catch (IOException localIOException)
    {
      while (true)
        this.val$listener.onIOException(localIOException, this.val$state);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.http.AsyncHttpRequestRunner.1
 * JD-Core Version:    0.6.0
 */