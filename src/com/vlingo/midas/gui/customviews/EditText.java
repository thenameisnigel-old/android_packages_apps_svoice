package com.vlingo.midas.gui.customviews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.midas.gui.OnEditListener;
import java.lang.ref.WeakReference;

public class EditText extends android.widget.EditText
{
  private static final String IS_VISIBLE_WINDOW = "AxT9IME.isVisibleWindow";
  private static final int MSG_EDIT_CANCELED = 2;
  private static final int MSG_LOSS_WINDOW_FOCUS = 1;
  private static final String RESPONSE_AXT9INFO = "ResponseAxT9Info";
  private static boolean msEditDone = false;
  private boolean firstTouch = true;
  private final Context mContext;
  private OnEditListener mEditListener;
  private EditTextHandler mHandler = new EditTextHandler(this);
  private boolean mIsKeypadShow;
  private boolean mIsReceiverRegistered;
  private final BroadcastReceiver mMainReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ("ResponseAxT9Info".equals(paramIntent.getAction()))
      {
        EditText.access$002(EditText.this, paramIntent.getBooleanExtra("AxT9IME.isVisibleWindow", false));
        if (EditText.this.isPermitted())
        {
          if (!EditText.this.mIsKeypadShow)
            break label90;
          EditText.this.mHandler.removeMessages(2);
          EditText.this.setCursorVisible(true);
          if (!EditText.this.isFocused())
            EditText.this.requestFocus();
          EditText.access$602(false);
        }
      }
      while (true)
      {
        return;
        label90: EditText.access$702(EditText.this, true);
        if (EditText.msEditDone)
          continue;
        EditText.this.mHandler.sendEmptyMessageDelayed(2, 500L);
      }
    }
  };
  private boolean mPermitted = false;
  private final ResultReceiver mSoftkeyReceiver = new ResultReceiver(this.mHandler)
  {
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      switch (paramInt)
      {
      default:
      case 1:
      case 3:
      case 0:
      case 2:
      }
      while (true)
      {
        return;
        EditText.access$002(EditText.this, false);
        continue;
        EditText.access$002(EditText.this, true);
      }
    }
  };

  public EditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setEnabled(false);
    this.mContext = paramContext;
  }

  private void editCanceled()
  {
    super.setEnabled(false);
    hideKeypad();
    if (this.mEditListener != null)
      this.mEditListener.onEditCanceled(this);
  }

  private boolean isPermitted()
  {
    return this.mPermitted;
  }

  private void setEditUI(boolean paramBoolean)
  {
    int i = getPaddingTop();
    int j = getPaddingBottom();
    int k = getPaddingRight();
    int m = getPaddingLeft();
    super.setEnabled(paramBoolean);
    if (paramBoolean)
    {
      setBackgroundResource(2130838147);
      setTextColor(Color.rgb(220, 220, 220));
      setCursorVisible(true);
      if (!isFocused())
        requestFocus();
      showKeyapd();
    }
    while (true)
    {
      setPadding(m, i, k, j);
      return;
      setBackgroundResource(2130838146);
      setTextColor(-16777216);
      setCursorVisible(false);
    }
  }

  public void hideKeypad()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    if (localInputMethodManager.isActive(this))
      localInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0, this.mSoftkeyReceiver);
  }

  public boolean isReadyToTouch()
  {
    return this.firstTouch;
  }

  public InputConnection onCreateInputConnection(EditorInfo paramEditorInfo)
  {
    InputConnection localInputConnection = super.onCreateInputConnection(paramEditorInfo);
    int i = 0xFF & paramEditorInfo.imeOptions;
    if ((i & 0x6) != 0)
    {
      paramEditorInfo.imeOptions = (i ^ paramEditorInfo.imeOptions);
      paramEditorInfo.imeOptions = (0x6 | paramEditorInfo.imeOptions);
    }
    if ((0x40000000 & paramEditorInfo.imeOptions) != 0)
      paramEditorInfo.imeOptions = (0xBFFFFFFF & paramEditorInfo.imeOptions);
    return localInputConnection;
  }

  public void onEditorAction(int paramInt)
  {
    super.onEditorAction(paramInt);
    if (paramInt == 6)
    {
      this.firstTouch = true;
      msEditDone = true;
      hideKeypad();
      setEditUI(false);
      if (this.mEditListener != null)
        this.mEditListener.onEditFinished(this);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onTouchEvent(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    if (i == 0)
      this.mHandler.removeMessages(2);
    while (true)
    {
      return bool;
      if ((i != 1) || (!isPermitted()) || (!this.firstTouch))
        continue;
      this.firstTouch = false;
      if (this.mEditListener != null)
        this.mEditListener.onEditStarted(this);
      setEditUI(true);
    }
  }

  public boolean performLongClick()
  {
    setEditUI(true);
    if (this.mEditListener != null)
      this.mEditListener.onEditStarted(this);
    return super.performLongClick();
  }

  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.mPermitted = paramBoolean;
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    if (paramBoolean)
      if (!this.mIsReceiverRegistered)
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("ResponseAxT9Info");
        localContext.registerReceiver(this.mMainReceiver, localIntentFilter);
        this.mIsReceiverRegistered = true;
      }
    while (true)
    {
      return;
      if (this.mIsReceiverRegistered)
      {
        localContext.unregisterReceiver(this.mMainReceiver);
        this.mIsReceiverRegistered = false;
        continue;
      }
    }
  }

  public void setOnEditListener(OnEditListener paramOnEditListener)
  {
    this.mEditListener = paramOnEditListener;
  }

  public void setReadyToTouch()
  {
    setEditUI(false);
    hideKeypad();
  }

  public void showKeyapd()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this, 0, this.mSoftkeyReceiver);
  }

  private static class EditTextHandler extends Handler
  {
    private final WeakReference<EditText> outer;

    EditTextHandler(EditText paramEditText)
    {
      this.outer = new WeakReference(paramEditText);
    }

    public void handleMessage(Message paramMessage)
    {
      EditText localEditText = (EditText)this.outer.get();
      if (localEditText != null)
        switch (paramMessage.what)
        {
        default:
        case 1:
        case 2:
        }
      while (true)
      {
        return;
        if (localEditText.mIsKeypadShow)
          continue;
        localEditText.setEditUI(false);
        localEditText.editCanceled();
        continue;
        if (localEditText.mEditListener == null)
          continue;
        localEditText.setEditUI(false);
        localEditText.editCanceled();
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.EditText
 * JD-Core Version:    0.6.0
 */