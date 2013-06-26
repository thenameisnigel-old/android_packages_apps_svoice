package com.vlingo.midas.gui.widgets;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.help.WCISData;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class HelpChoiceWidget extends Widget<List<Object>>
  implements View.OnClickListener
{
  public static final String EXTRA_LIST_HEIGHT = "EXTRA_LIST_HEIGHT";
  public static final String LIST_MOVE_ACTION = "LIST_MOVE_ACTION";
  private final int ANIMATE_TRANSLATE_UP_DURATION = 866;
  private View expandedView = null;
  private boolean firstHelpView = true;
  private LinearLayout lv;
  private final Context mContext;
  private Locale mLocale;
  PackageInfoProvider mPackageInfo;
  private WCISData wcisData = new WCISData();

  public HelpChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mPackageInfo = new PackageInfoProvider(this.mContext);
  }

  private Rect addRects(Rect paramRect1, Rect paramRect2)
  {
    Rect localRect = new Rect();
    paramRect1.bottom += paramRect2.bottom;
    paramRect1.top += paramRect2.top;
    localRect.left = (paramRect1.top + paramRect2.top);
    localRect.right = (paramRect1.top + paramRect2.top);
    return localRect;
  }

  private void checkIcon(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2130837846:
    case 2130837843:
    case 2130837803:
    }
    while (true)
    {
      return;
      this.mPackageInfo.setMemoIcon();
      continue;
      this.mPackageInfo.setMessageIcon();
      continue;
      this.mPackageInfo.setNavigationIcon();
    }
  }

  private Drawable getDrawable(int paramInt)
  {
    Drawable localDrawable;
    switch (paramInt)
    {
    default:
      localDrawable = null;
    case 2130837846:
    case 2130837843:
    case 2130837803:
    }
    while (true)
    {
      return localDrawable;
      localDrawable = this.mPackageInfo.getMemoIcon();
      continue;
      localDrawable = this.mPackageInfo.getMessageIcon();
      continue;
      localDrawable = this.mPackageInfo.getNavigationIcon();
    }
  }

  private Rect getRelativeRect(View paramView1, View paramView2)
  {
    if ((paramView1.getParent() == paramView1.getRootView()) || (paramView1 == paramView2));
    for (Rect localRect = getViewRect(paramView1); ; localRect = addRects(getViewRect(paramView1), getViewRect((View)paramView1.getParent())))
      return localRect;
  }

  private ScrollView getScrollView()
  {
    for (Object localObject = this; (localObject != null) && (localObject != ((View)localObject).getRootView()) && (!(localObject instanceof ScrollView)); localObject = (View)((View)localObject).getParent());
    if ((localObject instanceof ScrollView));
    for (ScrollView localScrollView = (ScrollView)localObject; ; localScrollView = null)
      return localScrollView;
  }

  private Rect getViewRect(View paramView)
  {
    Rect localRect = new Rect();
    localRect.bottom = paramView.getBottom();
    localRect.top = paramView.getTop();
    localRect.left = paramView.getLeft();
    localRect.right = paramView.getRight();
    return localRect;
  }

  protected boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    if ((!((AccessibilityManager)this.mContext.getSystemService("accessibility")).isEnabled()) && ((paramMotionEvent.getAction() == 7) || (paramMotionEvent.getAction() == 9)));
    for (boolean bool = true; ; bool = super.dispatchHoverEvent(paramMotionEvent))
      return bool;
  }

  public int getLocaleLanguage()
  {
    this.mLocale = MidasSettings.getCurrentLocale();
    String str = this.mLocale.toString();
    int i;
    if (str.equals("en_GB"))
      i = 0;
    while (true)
    {
      return i;
      if (str.equals("en_US"))
      {
        i = 1;
        continue;
      }
      if (str.equals("ko_KR"))
      {
        i = 2;
        continue;
      }
      if (str.equals("de_DE"))
      {
        i = 3;
        continue;
      }
      if (str.equals("fr_FR"))
      {
        i = 4;
        continue;
      }
      if (str.equals("it_IT"))
      {
        i = 5;
        continue;
      }
      i = 6;
    }
  }

  public void initialize(List<Object> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.firstHelpView = true;
    setAddView();
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onClick(View paramView)
  {
    LinearLayout localLinearLayout1 = (LinearLayout)paramView.findViewById(2131558855);
    View localView1 = paramView.findViewById(2131558857);
    boolean bool;
    if (!paramView.isSelected())
    {
      bool = true;
      paramView.setSelected(bool);
      if (localLinearLayout1.getVisibility() != 8)
        break label162;
      localLinearLayout1.setVisibility(0);
      localView1.setVisibility(0);
      if (this.expandedView != null)
      {
        this.expandedView.setSelected(false);
        LinearLayout localLinearLayout2 = (LinearLayout)this.expandedView.findViewById(2131558855);
        View localView2 = this.expandedView.findViewById(2131558857);
        localLinearLayout2.setVisibility(8);
        localView2.setVisibility(8);
        this.expandedView.setSelected(false);
      }
    }
    for (this.expandedView = paramView; ; this.expandedView = null)
    {
      ScrollView localScrollView = getScrollView();
      Rect localRect = getRelativeRect(paramView, (View)getParent());
      localScrollView.requestChildRectangleOnScreen((View)getParent(), localRect, true);
      return;
      bool = false;
      break;
      label162: localLinearLayout1.setVisibility(8);
      localView1.setVisibility(8);
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.lv = ((LinearLayout)findViewById(2131558850));
    this.wcisData.addItems(this.mContext);
    setAddView();
    checkIcon(2130837846);
    checkIcon(2130837843);
    checkIcon(2130837803);
    this.firstHelpView = false;
  }

  public void setAddView()
  {
    Iterator localIterator = this.wcisData.getItems().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      HashMap localHashMap = (HashMap)localIterator.next();
      View localView = View.inflate(this.mContext, 2130903136, null);
      ViewHolder localViewHolder = new ViewHolder();
      localViewHolder.image = ((ImageView)localView.findViewById(2131558851));
      localViewHolder.text1 = ((TextView)localView.findViewById(2131558852));
      localViewHolder.text2 = ((TextView)localView.findViewById(2131558853));
      localViewHolder.text1_examples = ((TextView)localView.findViewById(2131558856));
      localView.setTag(localViewHolder);
      Drawable localDrawable = getDrawable(((Integer)localHashMap.get("EXTRA_LIST_ICON")).intValue());
      if (localDrawable != null)
        localViewHolder.image.setImageDrawable(localDrawable);
      while (true)
      {
        localViewHolder.text1.setText((String)localHashMap.get("EXTRA_LIST_TITLE"));
        localViewHolder.text2.setText((String)localHashMap.get("EXTRA_LIST_EXAMPLE"));
        setDetailText(((Integer)localHashMap.get("EXTRA_EXAMPLE_LIST")).intValue(), localViewHolder.text1_examples);
        if ((((Integer)localHashMap.get("EXTRA_EXAMPLE_LIST")).intValue() == 2131165205) && (PackageInfoProvider.hasRadio() != true))
          setDetailText(2131165206, localViewHolder.text1_examples);
        if (!localIterator.hasNext())
          localView.findViewById(2131558854).setVisibility(8);
        if (i != 0)
        {
          localView.setBackgroundResource(2130838208);
          i = 0;
        }
        localView.setOnClickListener(this);
        this.lv.addView(localView);
        break;
        localViewHolder.image.setImageResource(((Integer)localHashMap.get("EXTRA_LIST_ICON")).intValue());
      }
    }
  }

  public void setDetailText(int paramInt, TextView paramTextView)
  {
    if (paramTextView != null)
    {
      Object localObject = "";
      try
      {
        for (String str1 : getResources().getStringArray(paramInt))
        {
          String str2 = (String)localObject + str1;
          localObject = str2;
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        paramTextView.setText(Html.fromHtml((String)localObject));
      }
    }
  }

  public void startAnimationTranslate(View paramView)
  {
    if (this.firstHelpView)
    {
      float[] arrayOfFloat2 = new float[2];
      arrayOfFloat2[0] = paramView.getRootView().getHeight();
      arrayOfFloat2[1] = 0.0F;
      ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(paramView, "translationY", arrayOfFloat2);
      localObjectAnimator2.setDuration(866L);
      localObjectAnimator2.start();
    }
    while (true)
    {
      return;
      float[] arrayOfFloat1 = new float[2];
      arrayOfFloat1[0] = paramView.getRootView().getHeight();
      arrayOfFloat1[1] = 0.0F;
      ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(paramView, "translationY", arrayOfFloat1);
      localObjectAnimator1.end();
      localObjectAnimator1.cancel();
    }
  }

  class ViewHolder
  {
    ImageView image;
    TextView text1;
    TextView text1_examples;
    TextView text2;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.HelpChoiceWidget
 * JD-Core Version:    0.6.0
 */