package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.memo.IMemoUtil;
import com.vlingo.core.internal.memo.Memo;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.memo.MemoManager;
import com.vlingo.midas.memo.SMemo2Util;
import com.vlingo.midas.util.LPActionInterface;

public class MemoWidget extends BargeInWidget<Memo>
  implements LPActionInterface, View.OnClickListener
{
  private boolean isClicked = false;
  private WidgetActionListener listener;
  private final Context mContext;
  private Intent mIntent;
  private View memoContainer;
  protected TextView msgBody;

  public MemoWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private void launchMemo()
  {
    try
    {
      retire();
      if (this.mIntent != null)
        this.mContext.startActivity(this.mIntent);
      else
        this.listener.handleIntent(new Intent("memo.findmostrecent").putExtra("memo.body", this.msgBody.getText().toString()), this);
    }
    catch (Exception localException)
    {
    }
  }

  public boolean handleLPAction(String paramString)
  {
    return false;
  }

  public void initialize(Memo paramMemo, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    String str1 = paramMemo.getText();
    String str2 = Settings.getLanguageApplication();
    if ((!StringUtils.isNullOrWhiteSpace(str2)) && (str2.equals("en-US")))
      this.msgBody.setTypeface(Typeface.DEFAULT, 0);
    if (paramMemo.getId() == -1)
    {
      this.msgBody.setText(str1);
      this.mIntent = new Intent(MemoManager.getMemoUtil().getCreateMemoAction());
      String str3 = this.msgBody.getText().toString();
      this.mIntent.putExtra("voicetalk_string", str3);
    }
    while (true)
    {
      invalidate();
      requestLayout();
      return;
      if (paramMemo.getId() == -2)
      {
        this.msgBody.setText(str1);
        this.mIntent = null;
        continue;
      }
      if (StringUtils.isNullOrWhiteSpace(str1));
      for (str1 = getContext().getString(2131362571); ; str1 = str1.substring(0, -4 + str1.length()))
        do
        {
          this.msgBody.setText(str1);
          this.mIntent = new Intent(MemoManager.getMemoUtil().getViewMemoAction());
          if (!SMemo2Util.isInstalled())
            break label247;
          this.mIntent.putExtra("id", paramMemo.getId());
          break;
        }
        while ((str1 == null) || (!str1.endsWith(".snb")));
      label247: this.mIntent.putExtra("memoID", paramMemo.getId());
    }
  }

  public boolean isTranslated()
  {
    if (!this.isClicked)
      this.isClicked = true;
    for (boolean bool = false; ; bool = this.isClicked)
      return bool;
  }

  public boolean isWidgetReplaceable()
  {
    return true;
  }

  public boolean mustBeShown()
  {
    return false;
  }

  public void onClick(View paramView)
  {
    if (paramView == this.memoContainer)
      launchMemo();
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.msgBody = ((TextView)findViewById(2131558900));
    this.memoContainer = findViewById(2131558924);
    if (this.memoContainer != null)
      this.memoContainer.setOnClickListener(this);
  }

  public void retire()
  {
    super.retire();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.MemoWidget
 * JD-Core Version:    0.6.0
 */