package com.vlingo.midas.gui.widgets;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.OpenAppUtil.AppInfo;
import java.util.List;

public class ApplicationChoiceWidget extends BargeInWidget<List<OpenAppUtil.AppInfo>>
  implements AdapterView.OnItemClickListener
{
  private WidgetActionListener mActionListener;
  private Context mContext;
  private ListView mListView;
  private int screenDensity;

  public ApplicationChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private Drawable getIconFromActivityName(String paramString1, String paramString2)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    Object localObject = null;
    try
    {
      Drawable localDrawable = localPackageManager.getActivityInfo(new ComponentName(paramString2, paramString1), 0).loadIcon(localPackageManager);
      localObject = localDrawable;
      label35: return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label35;
    }
  }

  public void initialize(List<OpenAppUtil.AppInfo> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenDensity = (localDisplayMetrics.densityDpi / 160);
    this.mActionListener = paramWidgetActionListener;
    this.mListView.setAdapter(new ApplicationsAdapter(this.mContext, paramList));
    Intent localIntent = new Intent("com.vlingo.core.internal.dialogmanager.DataTransfered");
    this.mActionListener.handleIntent(localIntent, Integer.valueOf(BargeInWidget.getDisplayCount(paramList.size())));
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
    this.mListView = ((ListView)findViewById(2131558893));
    this.mListView.setDividerHeight(1);
    this.mListView.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    retire();
    Intent localIntent = new Intent();
    OpenAppUtil.AppInfo localAppInfo = (OpenAppUtil.AppInfo)paramAdapterView.getAdapter().getItem(paramInt);
    this.mActionListener.handleIntent(localIntent, localAppInfo);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mListView);
    super.onMeasure(paramInt1, paramInt2);
  }

  private class ApplicationsAdapter extends ArrayAdapter<OpenAppUtil.AppInfo>
  {
    private List<OpenAppUtil.AppInfo> applist;
    private Rect mOldBounds = new Rect();

    public ApplicationsAdapter(List<OpenAppUtil.AppInfo> arg2)
    {
      super(0, localList);
      this.applist = localList;
    }

    public int getCount()
    {
      int i = this.applist.size();
      return ApplicationChoiceWidget.this.getLimitedCount(i);
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      OpenAppUtil.AppInfo localAppInfo = (OpenAppUtil.AppInfo)this.applist.get(paramInt);
      ApplicationChoiceWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        localViewHolder = new ApplicationChoiceWidget.ViewHolder(ApplicationChoiceWidget.this);
        paramView = LayoutInflater.from(ApplicationChoiceWidget.this.mContext).inflate(2130903082, paramViewGroup, false);
        localViewHolder.textView = ((TextView)paramView.findViewById(2131558688));
        localViewHolder.imageView = ((ImageView)paramView.findViewById(2131558687));
        paramView.setTag(localViewHolder);
        Drawable localDrawable = ApplicationChoiceWidget.this.getIconFromActivityName(localAppInfo.getActivityName(), localAppInfo.getPackageName());
        if (localDrawable != null)
          localViewHolder.imageView.setBackground(localDrawable);
        localViewHolder.textView.setText(localAppInfo.toString());
        if (paramInt != 0)
          break label155;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (ApplicationChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label155: if (paramInt == -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class ViewHolder
  {
    ImageView imageView;
    TextView textView;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ApplicationChoiceWidget
 * JD-Core Version:    0.6.0
 */