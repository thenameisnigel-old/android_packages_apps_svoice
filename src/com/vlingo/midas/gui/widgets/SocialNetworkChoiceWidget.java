package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import java.util.ArrayList;
import java.util.List;

public class SocialNetworkChoiceWidget extends BargeInWidget<ArrayList<String>>
  implements AdapterView.OnItemClickListener
{
  private final Context context;
  private WidgetActionListener listener;
  private ListView serviceList;

  public SocialNetworkChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(ArrayList<String> paramArrayList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if (paramArrayList != null)
    {
      ContactAdapter localContactAdapter = new ContactAdapter(getContext(), paramArrayList);
      this.serviceList.setAdapter(localContactAdapter);
      this.serviceList.setOnItemClickListener(this);
      localContactAdapter.notifyDataSetChanged();
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
    this.serviceList = ((ListView)findViewById(2131559131));
    this.serviceList.setDivider(null);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    retire();
    Intent localIntent = new Intent();
    localIntent.setAction("com.vlingo.core.internal.dialogmanager.Choice");
    localIntent.putExtra("choice", paramLong);
    this.listener.handleIntent(localIntent, null);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.serviceList, 2131427347, true);
    super.onMeasure(paramInt1, paramInt2);
  }

  public class ContactAdapter extends BaseAdapter
  {
    private final List<String> socialNetworkList;

    public ContactAdapter(List<String> arg2)
    {
      Object localObject;
      this.socialNetworkList = localObject;
    }

    public int getCount()
    {
      return this.socialNetworkList.size();
    }

    public Object getItem(int paramInt)
    {
      return this.socialNetworkList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      String str1 = (String)this.socialNetworkList.get(paramInt);
      SocialNetworkChoiceWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = LayoutInflater.from(SocialNetworkChoiceWidget.this.context).inflate(2130903100, paramViewGroup, false);
        localViewHolder = new SocialNetworkChoiceWidget.ViewHolder(SocialNetworkChoiceWidget.this);
        localViewHolder.type = ((TextView)paramView.findViewById(2131558778));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        String str2 = SocialNetworkType.localizedSocialNetworkTypeString(str1);
        if (str2 != null)
          localViewHolder.type.setText(str2);
        paramView.setOnClickListener(new SocialNetworkChoiceWidget.ContactAdapter.1(this, paramInt));
        if (paramInt != 0)
          break label140;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        return paramView;
        localViewHolder = (SocialNetworkChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label140: if (paramInt == -1 + getCount())
        {
          paramView.setBackgroundResource(2130837817);
          localViewHolder.divider.setVisibility(4);
          continue;
        }
        paramView.setBackgroundResource(2130837819);
      }
    }
  }

  class ViewHolder
  {
    View divider;
    TextView type;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.SocialNetworkChoiceWidget
 * JD-Core Version:    0.6.0
 */