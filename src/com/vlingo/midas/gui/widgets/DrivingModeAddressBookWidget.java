package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrivingModeAddressBookWidget extends BargeInWidget<List<ContactMatch>>
  implements AdapterView.OnItemClickListener
{
  private ListView contactList;
  private List<ContactMatch> displayContactsLimited = null;
  private WidgetActionListener listener;

  public DrivingModeAddressBookWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void initialize(List<ContactMatch> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    ContactAdapter localContactAdapter = null;
    if (paramList != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ContactMatch localContactMatch = (ContactMatch)localIterator.next();
        if (!localContactMatch.starred)
          continue;
        localArrayList.add(localContactMatch);
      }
      paramList.removeAll(localArrayList);
      localArrayList.addAll(paramList);
      paramList.clear();
      paramList.addAll(localArrayList);
      if (paramList.size() <= 6)
        break label288;
      this.displayContactsLimited = new ArrayList(6);
      for (int i = 0; i < 6; i++)
        this.displayContactsLimited.add(paramList.get(i));
    }
    label288: for (localContactAdapter = new ContactAdapter(getContext(), this.displayContactsLimited); ; localContactAdapter = new ContactAdapter(getContext(), paramList))
    {
      if (paramList != null)
      {
        ((RelativeLayout)findViewById(2131558610));
        ImageView localImageView = (ImageView)findViewById(2131558613);
        TextView localTextView1 = (TextView)findViewById(2131558611);
        TextView localTextView2 = (TextView)findViewById(2131558612);
        localImageView.setImageResource(2130837676);
        localTextView1.setText("Call to :");
        localTextView2.setText(((ContactMatch)paramList.get(0)).primaryDisplayName);
        this.contactList.setAdapter(localContactAdapter);
        this.contactList.setOnItemClickListener(this);
        this.contactList.setDivider(null);
        localContactAdapter.notifyDataSetChanged();
      }
      return;
    }
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
    this.contactList = ((ListView)findViewById(2131558614));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.contactList);
    super.onMeasure(paramInt1, paramInt2);
  }

  public class ContactAdapter extends BaseAdapter
  {
    private final List<ContactMatch> contacts;
    private Context mContext;

    public ContactAdapter(List<ContactMatch> arg2)
    {
      Object localObject2;
      this.contacts = localObject2;
      Object localObject1;
      this.mContext = localObject1;
    }

    public int getCount()
    {
      int i = this.contacts.size();
      return DrivingModeAddressBookWidget.this.getLimitedCount(i);
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
      List localList = localContactMatch.getPhoneData();
      ContactData localContactData = null;
      if (localList != null)
        localContactData = (ContactData)localList.get(0);
      DrivingModeAddressBookWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(DrivingModeAddressBookWidget.this.getContext(), 2130903070, null);
        localViewHolder = new DrivingModeAddressBookWidget.ViewHolder(DrivingModeAddressBookWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558615));
        localViewHolder.title = ((TextView)paramView.findViewById(2131558617));
        localViewHolder.phone = ((TextView)paramView.findViewById(2131558618));
        localViewHolder.list_number = ((TextView)paramView.findViewById(2131558616));
        localViewHolder.divider = paramView.findViewById(2131558619);
        paramView.setTag(localViewHolder);
        localViewHolder.title.setText(localContactMatch.primaryDisplayName);
        localViewHolder.phone.setText(localContactData.address);
        localViewHolder.list_number.setText(String.valueOf(paramInt + 1));
        if (paramInt == -1 + getCount())
          break label231;
        paramView.setBackgroundResource(2130837819);
      }
      while (true)
      {
        paramView.setOnClickListener(new DrivingModeAddressBookWidget.ContactAdapter.1(this, paramInt));
        return paramView;
        localViewHolder = (DrivingModeAddressBookWidget.ViewHolder)paramView.getTag();
        break;
        label231: localViewHolder.divider.setVisibility(4);
        paramView.setBackgroundResource(2130837817);
      }
    }
  }

  class ViewHolder
  {
    LinearLayout choice_container;
    View divider;
    TextView list_number;
    TextView phone;
    TextView title;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingModeAddressBookWidget
 * JD-Core Version:    0.6.0
 */