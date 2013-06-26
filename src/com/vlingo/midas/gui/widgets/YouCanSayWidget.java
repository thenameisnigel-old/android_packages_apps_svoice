package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.midas.help.WCISData;
import com.vlingo.midas.ui.PackageInfoProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class YouCanSayWidget extends RelativeLayout
{
  public static final String EXTRA_LIST_HEIGHT = "EXTRA_LIST_HEIGHT";
  public static final String LIST_MOVE_ACTION = "LIST_MOVE_ACTION";
  public static int NUMBER_OF_ITEMS = 3;
  private final int ANIMATE_TRANSLATE_UP_DURATION = 866;
  ArrayList<HashMap<String, Object>> arrayList;
  private boolean firstHelpView = true;
  private LinearLayout lv;
  private final Context mContext;
  PackageInfoProvider mPackageInfo;
  private WCISData wcisData = new WCISData();

  public YouCanSayWidget(Context paramContext, AttributeSet paramAttributeSet)
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

  private int[] getRandomNumber()
  {
    Random localRandom = new Random();
    int[] arrayOfInt = new int[NUMBER_OF_ITEMS];
    for (int i = 0; i < arrayOfInt.length; i++)
    {
      arrayOfInt[i] = localRandom.nextInt(this.wcisData.getItems().size());
      int j = 1;
      while (j != 0)
      {
        j = 0;
        for (int k = 0; k < i; k++)
        {
          if (arrayOfInt[i] != arrayOfInt[k])
            continue;
          arrayOfInt[i] = localRandom.nextInt(this.wcisData.getItems().size());
          j = 1;
        }
      }
    }
    Arrays.sort(arrayOfInt);
    return arrayOfInt;
  }

  private void setThreeItems()
  {
    this.wcisData.addItems(this.mContext);
    this.arrayList = new ArrayList();
    int[] arrayOfInt = getRandomNumber();
    this.arrayList.clear();
    for (int i = 0; i < this.wcisData.getItems().size(); i++)
    {
      if ((i != arrayOfInt[0]) && (i != arrayOfInt[1]) && (i != arrayOfInt[2]))
        continue;
      this.arrayList.add(this.wcisData.getItems().get(i));
    }
  }

  protected boolean dispatchHoverEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 7) || (paramMotionEvent.getAction() == 9));
    for (boolean bool = true; ; bool = super.dispatchHoverEvent(paramMotionEvent))
      return bool;
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.lv = ((LinearLayout)findViewById(2131559168));
    setThreeItems();
    checkIcon(2130837846);
    checkIcon(2130837843);
    checkIcon(2130837803);
    this.firstHelpView = false;
  }

  public void setAddView()
  {
    Iterator localIterator = this.arrayList.iterator();
    if (localIterator.hasNext())
    {
      HashMap localHashMap = (HashMap)localIterator.next();
      View localView = LayoutInflater.from(this.mContext).inflate(2130903101, null);
      ViewHolder localViewHolder = new ViewHolder();
      localViewHolder.image = ((ImageView)localView.findViewById(2131558779));
      localViewHolder.title = ((TextView)localView.findViewById(2131558780));
      localViewHolder.command = ((TextView)localView.findViewById(2131558781));
      localViewHolder.examples = ((TextView)localView.findViewById(2131558783));
      localView.setTag(localViewHolder);
      Drawable localDrawable = getDrawable(((Integer)localHashMap.get("EXTRA_LIST_ICON")).intValue());
      if (localDrawable != null)
        localViewHolder.image.setImageDrawable(localDrawable);
      while (true)
      {
        String str = (String)localHashMap.get("EXTRA_LIST_TITLE");
        localViewHolder.title.setText(str);
        localViewHolder.command.setText((String)localHashMap.get("EXTRA_LIST_EXAMPLE"));
        setDetailText(((Integer)localHashMap.get("EXTRA_EXAMPLE_LIST")).intValue(), localViewHolder.examples);
        if ((((Integer)localHashMap.get("EXTRA_EXAMPLE_LIST")).intValue() == 2131165205) && (PackageInfoProvider.hasRadio() != true))
          setDetailText(2131165206, localViewHolder.examples);
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

  class ViewHolder
  {
    TextView command;
    TextView examples;
    ImageView image;
    TextView title;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.YouCanSayWidget
 * JD-Core Version:    0.6.0
 */