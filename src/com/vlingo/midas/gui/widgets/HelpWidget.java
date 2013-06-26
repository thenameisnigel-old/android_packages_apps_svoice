package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.util.List;

public class HelpWidget extends Widget<List<Object>>
  implements AdapterView.OnItemClickListener
{
  private Context mContext;
  PackageInfoProvider mPackageInfo;

  public HelpWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mPackageInfo = new PackageInfoProvider(this.mContext);
  }

  private void checkIcon(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2130837846:
    }
    while (true)
    {
      return;
      this.mPackageInfo.setMemoIcon();
    }
  }

  private Drawable getDrawable(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2130837846:
    }
    for (Drawable localDrawable = null; ; localDrawable = this.mPackageInfo.getMemoIcon())
      return localDrawable;
  }

  private void setTextFromHtml(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    TextView localTextView = (TextView)findViewById(paramInt2);
    if ((localTextView != null) && (paramInt2 != 0))
    {
      if (paramBoolean)
        break label40;
      localTextView.setText(Html.fromHtml(this.mContext.getString(paramInt1)));
    }
    while (true)
    {
      return;
      label40: String str1 = "";
      for (String str2 : getResources().getStringArray(paramInt1))
        str1 = str1 + str2;
      localTextView.setText(Html.fromHtml(str1));
    }
  }

  public void initialize(List<Object> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void setAddView(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("EXTRA_SUBTITLE_ICON", 0);
    int j = paramIntent.getIntExtra("EXTRA_TITLE_BAR", 0);
    int k = paramIntent.getIntExtra("EXTRA_EXAMPLE_LIST", 0);
    ImageView localImageView;
    if (i != 0)
    {
      localImageView = (ImageView)findViewById(2131558483);
      checkIcon(i);
      Drawable localDrawable = getDrawable(i);
      if (localDrawable == null)
        break label82;
      localImageView.setImageDrawable(localDrawable);
    }
    while (true)
    {
      setTextFromHtml(j, 2131559035, false);
      setTextFromHtml(k, 2131558486, true);
      return;
      label82: localImageView.setImageResource(i);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.HelpWidget
 * JD-Core Version:    0.6.0
 */