package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneTypeSelectWidget extends BargeInWidget<Map<String, ContactData>>
  implements AdapterView.OnItemClickListener
{
  private ListView contactList;
  private final Context context;
  private WidgetActionListener listener;

  public PhoneTypeSelectWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(Map<String, ContactData> paramMap, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if (paramMap != null)
    {
      ArrayList localArrayList1 = new ArrayList(paramMap.keySet());
      ArrayList localArrayList2 = new ArrayList(paramMap.values());
      ContactAdapter localContactAdapter = new ContactAdapter(getContext(), localArrayList1, localArrayList2);
      this.contactList.setAdapter(localContactAdapter);
      this.contactList.setOnItemClickListener(this);
      this.contactList.setDivider(null);
      localContactAdapter.notifyDataSetChanged();
      paramWidgetActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.DataTransfered"), Integer.valueOf(BargeInWidget.getDisplayCount(localArrayList1.size())));
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
    this.contactList = ((ListView)findViewById(2131558893));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
    localIntent.putExtra("choice", paramLong);
    this.listener.handleIntent(localIntent, null);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.contactList);
    super.onMeasure(paramInt1, paramInt2);
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
      return PhoneTypeSelectWidget.this.getLimitedCount(i);
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
      if (paramString.equals(PhoneTypeSelectWidget.this.getResources().getString(2131362216)))
        str = "1";
      while (true)
      {
        return str;
        if (paramString.equals(PhoneTypeSelectWidget.this.getResources().getString(2131362217)))
        {
          str = "2";
          continue;
        }
        if (paramString.equals(PhoneTypeSelectWidget.this.getResources().getString(2131362219)))
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
      PhoneTypeSelectWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(PhoneTypeSelectWidget.this.getContext(), 2130903098, null);
        localViewHolder = new PhoneTypeSelectWidget.ViewHolder(PhoneTypeSelectWidget.this);
        localViewHolder.number = ((TextView)paramView.findViewById(2131558705));
        localViewHolder.type = ((TextView)paramView.findViewById(2131558704));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        localViewHolder.number.setText(localContactData.address);
        localViewHolder.type.setText(str);
        if (paramInt != 0)
          break label166;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        paramView.setOnClickListener(new PhoneTypeSelectWidget.ContactAdapter.1(this, paramInt));
        return paramView;
        localViewHolder = (PhoneTypeSelectWidget.ViewHolder)paramView.getTag();
        break;
        label166: if (paramInt == -1 + this.allContactInfo.size())
        {
          localViewHolder.divider.setVisibility(4);
          paramView.setBackgroundResource(2130837817);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class ViewHolder
  {
    View divider;
    TextView number;
    TextView type;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.PhoneTypeSelectWidget
 * JD-Core Version:    0.6.0
 */