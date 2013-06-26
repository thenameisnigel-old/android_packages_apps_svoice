package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrivingPhoneTypeSelectWidget extends BargeInWidget<Map<String, ContactData>>
  implements DrivingWidgetInterface
{
  private ListView contactList;
  private LinearLayout contactListContainer;
  private final Context context;

  public DrivingPhoneTypeSelectWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public int getDecreasedHeight()
  {
    int i = 799;
    if (getResources().getConfiguration().orientation == 1)
      if (this.contactList.getAdapter().getCount() != 2);
    for (i = 550; ; i = getResources().getDimensionPixelSize(2131427352))
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(Map<String, ContactData> paramMap, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    if (MidasSettings.isNightMode())
      this.contactListContainer.setBackgroundResource(2130837874);
    while (true)
    {
      if (paramMap != null)
      {
        ArrayList localArrayList1 = new ArrayList(paramMap.keySet());
        ArrayList localArrayList2 = new ArrayList(paramMap.values());
        ContactAdapter localContactAdapter = new ContactAdapter(getContext(), localArrayList1, localArrayList2);
        this.contactList.setAdapter(localContactAdapter);
        this.contactList.setDivider(null);
        localContactAdapter.notifyDataSetChanged();
        paramWidgetActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.DataTransfered"), Integer.valueOf(BargeInWidget.getDisplayCount(localArrayList1.size())));
      }
      return;
      this.contactListContainer.setBackgroundResource(2130837590);
    }
  }

  public boolean isDecreasedSize()
  {
    return false;
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
    this.contactListContainer = ((LinearLayout)findViewById(2131558916));
    this.contactList = ((ListView)findViewById(2131558893));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }

  public class ContactAdapter extends BaseAdapter
  {
    private final List<ContactData> allContactInfo;
    private final List<String> phoneTypeInfo;

    public ContactAdapter(List<String> paramList, List<ContactData> arg3)
    {
      Object localObject2;
      this.allContactInfo = localObject2;
      Object localObject1;
      this.phoneTypeInfo = localObject1;
    }

    public int getCount()
    {
      int i = this.allContactInfo.size();
      return DrivingPhoneTypeSelectWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.allContactInfo.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public String getSelectedPhoneType(String paramString)
    {
      String str;
      if (paramString.equals(DrivingPhoneTypeSelectWidget.this.getResources().getString(2131362216)))
        str = "1";
      while (true)
      {
        return str;
        if (paramString.equals(DrivingPhoneTypeSelectWidget.this.getResources().getString(2131362217)))
        {
          str = "2";
          continue;
        }
        if (paramString.equals(DrivingPhoneTypeSelectWidget.this.getResources().getString(2131362219)))
        {
          str = "3";
          continue;
        }
        str = "7";
      }
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ContactData localContactData = (ContactData)this.allContactInfo.get(paramInt);
      String str = (String)this.phoneTypeInfo.get(paramInt);
      DrivingPhoneTypeSelectWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(DrivingPhoneTypeSelectWidget.this.getContext(), 2130903088, null);
        localViewHolder = new DrivingPhoneTypeSelectWidget.ViewHolder(DrivingPhoneTypeSelectWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558689));
        localViewHolder.number = ((TextView)paramView.findViewById(2131558705));
        localViewHolder.type = ((TextView)paramView.findViewById(2131558704));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        if (MidasSettings.isNightMode())
        {
          localViewHolder.type.setTextColor(-2039584);
          localViewHolder.divider.setBackgroundColor(-16777216);
        }
        if ((DrivingPhoneTypeSelectWidget.this.getResources().getConfiguration().orientation != 1) || (!MidasSettings.isMultiwindowedMode()))
          break label250;
        LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams)localViewHolder.choice_container.getLayoutParams();
        localLayoutParams2.height = 249;
        localViewHolder.choice_container.setLayoutParams(localLayoutParams2);
        label200: localViewHolder.number.setText(localContactData.address);
        localViewHolder.type.setText(str);
        if (paramInt != 0)
          break label284;
        localViewHolder.divider.setVisibility(0);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (DrivingPhoneTypeSelectWidget.ViewHolder)paramView.getTag();
        break;
        label250: LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)localViewHolder.choice_container.getLayoutParams();
        localLayoutParams1.height = 312;
        localViewHolder.choice_container.setLayoutParams(localLayoutParams1);
        break label200;
        label284: if ((MidasSettings.isMultiwindowedMode()) && (((DrivingPhoneTypeSelectWidget.this.getResources().getConfiguration().orientation == 1) && (paramInt == -1 + this.allContactInfo.size())) || ((DrivingPhoneTypeSelectWidget.this.getResources().getConfiguration().orientation == 2) && (paramInt == 2))))
        {
          localViewHolder.divider.setVisibility(8);
          continue;
        }
        localViewHolder.divider.setVisibility(0);
      }
    }
  }

  class ViewHolder
  {
    LinearLayout choice_container;
    View divider;
    TextView number;
    TextView type;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingPhoneTypeSelectWidget
 * JD-Core Version:    0.6.0
 */