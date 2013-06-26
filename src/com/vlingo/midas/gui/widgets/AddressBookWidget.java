package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddressBookWidget extends BargeInWidget<List<ContactMatch>>
  implements AdapterView.OnItemClickListener
{
  private ListView contactList;
  private List<ContactMatch> displayContactsLimited = null;
  private WidgetActionListener listener;

  public AddressBookWidget(Context paramContext, AttributeSet paramAttributeSet)
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
        break label241;
      this.displayContactsLimited = new ArrayList(6);
      for (int i = 0; i < 6; i++)
        this.displayContactsLimited.add(paramList.get(i));
    }
    label241: for (localContactAdapter = new ContactAdapter(getContext(), this.displayContactsLimited); ; localContactAdapter = new ContactAdapter(getContext(), paramList))
    {
      if (paramList != null)
      {
        this.contactList.setAdapter(localContactAdapter);
        this.contactList.setOnItemClickListener(this);
        this.contactList.setDivider(null);
        localContactAdapter.notifyDataSetChanged();
      }
      paramWidgetActionListener.handleIntent(new Intent("com.vlingo.core.internal.dialogmanager.DataTransfered"), Integer.valueOf(BargeInWidget.getDisplayCount(paramList.size())));
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
    this.contactList = ((ListView)findViewById(2131558893));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.vlingo.core.internal.dialogmanager.ContactChoice");
    localIntent.putExtra("choice", paramLong);
    this.listener.handleIntent(localIntent, null);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    measureListviewHeight(this.contactList, 2131427344, false);
    super.onMeasure(paramInt1, paramInt2);
  }

  public class ContactAdapter extends BaseAdapter
  {
    int[] DEFAULT_IMG;
    private final List<ContactMatch> contacts;
    private int img_cnt = 0;
    private Context mContext;

    public ContactAdapter(List<ContactMatch> arg2)
    {
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = 2130837566;
      arrayOfInt[1] = 2130837567;
      arrayOfInt[2] = 2130837568;
      arrayOfInt[3] = 2130837569;
      arrayOfInt[4] = 2130837570;
      this.DEFAULT_IMG = arrayOfInt;
      Object localObject2;
      this.contacts = localObject2;
      Object localObject1;
      this.mContext = localObject1;
    }

    private Bitmap getContactPhoto(Long paramLong)
    {
      Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, Long.toString(paramLong.longValue()));
      return BitmapFactory.decodeStream(ContactsContract.Contacts.openContactPhotoInputStream(this.mContext.getContentResolver(), localUri));
    }

    public int getCount()
    {
      int i = this.contacts.size();
      return AddressBookWidget.this.getLimitedCount(i);
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
      AddressBookWidget.ViewHolder localViewHolder;
      label218: Bitmap localBitmap1;
      if (paramView == null)
      {
        paramView = View.inflate(AddressBookWidget.this.getContext(), 2130903083, null);
        localViewHolder = new AddressBookWidget.ViewHolder(AddressBookWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558689));
        localViewHolder.title = ((TextView)paramView.findViewById(2131558692));
        localViewHolder.phone = ((TextView)paramView.findViewById(2131558693));
        localViewHolder.star_img = ((ImageView)paramView.findViewById(2131558691));
        localViewHolder.divider = paramView.findViewById(2131558694);
        localViewHolder.qcb = ((ImageView)paramView.findViewById(2131558690));
        paramView.setTag(localViewHolder);
        localViewHolder.title.setText(localContactMatch.primaryDisplayName);
        if ((localContactData != null) && (localContactData.isPhoneNumber() == true))
          localViewHolder.phone.setText(localContactData.address);
        if (!localContactMatch.starred)
          break label317;
        localViewHolder.star_img.setVisibility(0);
        localBitmap1 = getContactPhoto(Long.valueOf(localContactMatch.primaryContactID));
        if (localBitmap1 != null)
          break label330;
        Bitmap localBitmap2 = BitmapFactory.decodeResource(AddressBookWidget.this.getResources(), this.DEFAULT_IMG[(this.img_cnt % 5)]);
        localViewHolder.qcb.setImageBitmap(localBitmap2);
        this.img_cnt = (1 + this.img_cnt);
        label280: if (paramInt != 0)
          break label343;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        paramView.setOnClickListener(new AddressBookWidget.ContactAdapter.1(this, paramInt));
        return paramView;
        localViewHolder = (AddressBookWidget.ViewHolder)paramView.getTag();
        break;
        label317: localViewHolder.star_img.setVisibility(8);
        break label218;
        label330: localViewHolder.qcb.setImageBitmap(localBitmap1);
        break label280;
        label343: if (paramInt == -1 + this.contacts.size())
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
    LinearLayout choice_container;
    View divider;
    TextView phone;
    ImageView qcb;
    View star_img;
    TextView title;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.AddressBookWidget
 * JD-Core Version:    0.6.0
 */