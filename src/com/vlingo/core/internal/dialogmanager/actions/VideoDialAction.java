package com.vlingo.core.internal.dialogmanager.actions;

import android.content.Context;
import android.content.Intent;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.util.DialUtil;

public class VideoDialAction extends DMAction
{
  private String address;

  public VideoDialAction address(String paramString)
  {
    this.address = paramString;
    return this;
  }

  protected void execute()
  {
    if (this.address != null)
    {
      Intent localIntent = DialUtil.getVideoDialIntent(this.address);
      getContext().startActivity(localIntent);
      getListener().actionSuccess();
    }
    while (true)
    {
      return;
      getListener().actionSuccess();
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.actions.VideoDialAction
 * JD-Core Version:    0.6.0
 */