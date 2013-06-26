package com.vlingo.midas.gui.widgets;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrivingContactChoiceWidget extends BargeInWidget<List<ContactMatch>>
  implements DrivingWidgetInterface
{
  private ListView contactList;
  private LinearLayout contactListContainer;
  private Context mContext;

  public DrivingContactChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
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

  public void initialize(List<ContactMatch> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    ArrayList localArrayList;
    Iterator localIterator;
    if (MidasSettings.isNightMode())
    {
      this.contactListContainer.setBackgroundResource(2130837874);
      if (paramList != null)
      {
        localArrayList = new ArrayList();
        new ArrayList();
        localIterator = paramList.iterator();
      }
    }
    else
    {
      while (true)
      {
        if (!localIterator.hasNext())
          break label137;
        List localList = ((ContactMatch)localIterator.next()).getPhoneData();
        if (localList != null)
        {
          ContactData localContactData = (ContactData)localList.get(0);
          if ((localContactData == null) || (!localContactData.isPhoneNumber()))
            continue;
          localArrayList.add(localContactData.address);
          continue;
          this.contactListContainer.setBackgroundResource(2130837590);
          break;
        }
        localArrayList.add("");
      }
      label137: ContactAdapter localContactAdapter = new ContactAdapter(this.mContext, paramList, localArrayList);
      this.contactList.setAdapter(localContactAdapter);
      this.contactList.setDivider(null);
      localContactAdapter.notifyDataSetChanged();
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
    private final List<String> contactPhone;
    private final List<ContactMatch> contacts;

    public ContactAdapter(List<ContactMatch> paramArrayList, ArrayList<String> arg3)
    {
      Object localObject1;
      this.contacts = localObject1;
      Object localObject2;
      this.contactPhone = localObject2;
    }

    public int getCount()
    {
      return this.contacts.size();
    }

    public Object getItem(int paramInt)
    {
      return this.contacts.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      ContactMatch localContactMatch = (ContactMatch)this.contacts.get(paramInt);
      String str = (String)this.contactPhone.get(paramInt);
      DrivingContactChoiceWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(DrivingContactChoiceWidget.this.getContext(), 2130903085, null);
        localViewHolder = new DrivingContactChoiceWidget.ViewHolder(DrivingContactChoiceWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558689));
        localViewHolder.title = ((TextView)paramView.findViewById(2131558692));
        localViewHolder.phone = ((TextView)paramView.findViewById(2131558693));
        localViewHolder.list_number = ((TextView)paramView.findViewById(2131558698));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        if (MidasSettings.isNightMode())
        {
          localViewHolder.list_number.setBackgroundResource(2130837883);
          localViewHolder.list_number.setTextColor(-2039584);
          localViewHolder.title.setTextColor(-2039584);
          localViewHolder.divider.setBackgroundColor(-16777216);
        }
        if ((DrivingContactChoiceWidget.this.getResources().getConfiguration().orientation != 1) || (!MidasSettings.isMultiwindowedMode()))
          break label356;
        LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams)localViewHolder.choice_container.getLayoutParams();
        localLayoutParams2.height = 249;
        localViewHolder.choice_container.setLayoutParams(localLayoutParams2);
        label234: localViewHolder.title.setText(localContactMatch.primaryDisplayName);
        localViewHolder.phone.setText(str);
        localViewHolder.list_number.setText(String.valueOf(paramInt + 1));
        if ((!MidasSettings.isMultiwindowedMode()) || (((DrivingContactChoiceWidget.this.getResources().getConfiguration().orientation != 1) || (paramInt != -1 + this.contacts.size())) && ((DrivingContactChoiceWidget.this.getResources().getConfiguration().orientation != 2) || (paramInt != 2))))
          break label390;
        localViewHolder.divider.setVisibility(8);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (DrivingContactChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label356: LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)localViewHolder.choice_container.getLayoutParams();
        localLayoutParams1.height = 312;
        localViewHolder.choice_container.setLayoutParams(localLayoutParams1);
        break label234;
        label390: localViewHolder.divider.setVisibility(0);
      }
    }
  }

  class ViewHolder
  {
    LinearLayout choice_container;
    View divider;
    TextView list_number;
    RelativeLayout list_number_container;
    TextView phone;
    TextView title;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingContactChoiceWidget
 * JD-Core Version:    0.6.0
 */