package com.vlingo.midas.dialogmanager.actions;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.android.email.backgroundsender.IEmailBackgroundSender;
import com.android.email.backgroundsender.IEmailBackgroundSender.Stub;
import com.android.email.backgroundsender.IEmailRemoteService;
import com.android.email.backgroundsender.IEmailRemoteService.Stub;
import com.android.email.backgroundsender.IEmailRemoteServiceCallback;
import com.android.email.backgroundsender.IEmailRemoteServiceCallback.Stub;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.contacts.ContactType;
import com.vlingo.core.internal.contacts.mru.MRU;
import com.vlingo.core.internal.dialogmanager.DMAction;
import com.vlingo.core.internal.dialogmanager.DMAction.Listener;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface;
import com.vlingo.core.internal.util.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SamsungSendEmailAction extends DMAction
  implements SendEmailInterface
{
  private static final int RECEIVE_MSG = 100;
  public static final int REQUEST_CODE_ATTACH_MEDIA = 10;
  private static final int SENDING_FAIL = 0;
  private static final int SENDING_SUCCESS = 1;
  private static final String TAG = "BackgroundSender.EmailBackgroundSenderService";
  private static int jobIDByGallery;
  private static int jobIDByQuickPannel = 1000;
  private static int jobIDByViceQuick;
  private List<ContactData> contacts = new ArrayList();
  private IEmailRemoteService mCallbackBinder = null;
  private ServiceConnection mCallbackConn = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      SamsungSendEmailAction.access$902(SamsungSendEmailAction.this, IEmailRemoteService.Stub.asInterface(paramIBinder));
      SamsungSendEmailAction.access$1002(SamsungSendEmailAction.this, true);
      try
      {
        SamsungSendEmailAction.this.mCallbackBinder.registerCallback(SamsungSendEmailAction.this.mEmailSendCallback);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          localRemoteException.printStackTrace();
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      try
      {
        SamsungSendEmailAction.this.mCallbackBinder.unregisterCallback(SamsungSendEmailAction.this.mEmailSendCallback);
        label19: SamsungSendEmailAction.access$902(SamsungSendEmailAction.this, null);
        SamsungSendEmailAction.access$1002(SamsungSendEmailAction.this, false);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        break label19;
      }
    }
  };
  private boolean mCallbackConnected = false;
  private IEmailRemoteServiceCallback mEmailSendCallback = new IEmailRemoteServiceCallback.Stub()
  {
    public void onResponse(int paramInt1, int paramInt2)
    {
      SamsungSendEmailAction.this.mHandler.sendMessage(SamsungSendEmailAction.this.mHandler.obtainMessage(100, paramInt1, paramInt2));
    }
  };
  private SamsungSendEmailActionHandler mHandler = new SamsungSendEmailActionHandler(this);
  private int mJobID = 1000;
  private IEmailBackgroundSender mServiceBinder = null;
  ServiceConnection mServiceConn = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      SamsungSendEmailAction.access$502(SamsungSendEmailAction.this, IEmailBackgroundSender.Stub.asInterface(paramIBinder));
      SamsungSendEmailAction.access$602(SamsungSendEmailAction.this, true);
      try
      {
        if (SamsungSendEmailAction.this.mServiceBinder != null)
          SamsungSendEmailAction.this.mServiceBinder.startListening();
        SamsungSendEmailAction.this.sendMessage();
        label50: return;
      }
      catch (RemoteException localRemoteException)
      {
        break label50;
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      try
      {
        SamsungSendEmailAction.this.mServiceBinder.stopListening();
        SamsungSendEmailAction.access$502(SamsungSendEmailAction.this, null);
        SamsungSendEmailAction.access$602(SamsungSendEmailAction.this, false);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          localRemoteException.printStackTrace();
      }
    }
  };
  private boolean mSvcConnected = false;
  private String message;
  private int retriesLeft = 100;
  private String subject;

  static
  {
    jobIDByGallery = 2000;
    jobIDByViceQuick = 1000;
  }

  private List<ContactData> contacts()
  {
    Object localObject;
    if (this.contacts == null)
    {
      localObject = new LinkedList();
      this.contacts = ((List)localObject);
    }
    while (true)
    {
      return localObject;
      localObject = this.contacts;
    }
  }

  private String[] extractAddresses()
  {
    String[] arrayOfString = new String[contacts().size()];
    for (int i = 0; i < contacts().size(); i++)
      arrayOfString[i] = ((ContactData)contacts().get(i)).address;
    return arrayOfString;
  }

  private void sendMessage()
  {
    try
    {
      if (!this.mServiceBinder.hasAccount())
      {
        Log.e("BackgroundSender.EmailBackgroundSenderService", "no account! can't send a email");
      }
      else
      {
        Intent localIntent = new Intent("com.android.email.backgroundsender.SEND_BACKGROUND");
        localIntent.putExtra("to", extractAddresses());
        if (!StringUtils.isNullOrWhiteSpace(this.subject))
          localIntent.putExtra("subject", this.subject);
        localIntent.putExtra("text", this.message);
        this.mServiceBinder.sendMessage(this.mJobID, localIntent);
        getListener().actionSuccess();
      }
    }
    catch (RemoteException localRemoteException)
    {
    }
  }

  private void updateMRU()
  {
    Iterator localIterator = contacts().iterator();
    while (localIterator.hasNext())
    {
      ContactData localContactData = (ContactData)localIterator.next();
      MRU.getMRU().incrementCount(ContactType.EMAIL.toString(), (int)localContactData.contact.primaryContactID, localContactData.address);
    }
  }

  public SamsungSendEmailAction contact(ContactData paramContactData)
  {
    this.contacts.add(paramContactData);
    return this;
  }

  public SamsungSendEmailAction contacts(List<ContactData> paramList)
  {
    this.contacts = paramList;
    return this;
  }

  protected void execute()
  {
  }

  public SamsungSendEmailAction message(String paramString)
  {
    this.message = paramString;
    return this;
  }

  public SamsungSendEmailAction subject(String paramString)
  {
    this.subject = paramString;
    return this;
  }

  private static class SamsungSendEmailActionHandler extends Handler
  {
    private final WeakReference<SamsungSendEmailAction> outer;

    SamsungSendEmailActionHandler(SamsungSendEmailAction paramSamsungSendEmailAction)
    {
      this.outer = new WeakReference(paramSamsungSendEmailAction);
    }

    public void handleMessage(Message paramMessage)
    {
      SamsungSendEmailAction localSamsungSendEmailAction = (SamsungSendEmailAction)this.outer.get();
      if (localSamsungSendEmailAction != null)
        switch (paramMessage.what)
        {
        default:
          super.handleMessage(paramMessage);
        case 100:
        }
      while (true)
      {
        return;
        if ((paramMessage.arg1 == SamsungSendEmailAction.jobIDByQuickPannel) || (paramMessage.arg1 == SamsungSendEmailAction.jobIDByGallery) || (paramMessage.arg1 == SamsungSendEmailAction.jobIDByViceQuick))
        {
          if (paramMessage.arg2 == 1)
          {
            Toast.makeText(localSamsungSendEmailAction.getContext(), 2131362657, 1).show();
            continue;
          }
          if (paramMessage.arg2 != 0)
            continue;
          Toast.makeText(localSamsungSendEmailAction.getContext(), 2131362658, 1).show();
          continue;
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.actions.SamsungSendEmailAction
 * JD-Core Version:    0.6.0
 */