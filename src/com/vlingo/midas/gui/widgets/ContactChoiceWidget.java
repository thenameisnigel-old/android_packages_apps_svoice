package com.vlingo.midas.gui.widgets;

import android.content.Context;
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

public class ContactChoiceWidget extends BargeInWidget<List<ContactMatch>>
  implements AdapterView.OnItemClickListener
{
  private ListView contactList;
  private WidgetActionListener listener;
  private Context mContext;

  public ContactChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(List<ContactMatch> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if (paramList != null)
    {
      ArrayList localArrayList1 = new ArrayList(paramList.size());
      Object localObject = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ContactMatch localContactMatch = (ContactMatch)localIterator.next();
        Uri localUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, Long.toString(localContactMatch.primaryContactID));
        localArrayList1.add(BitmapFactory.decodeStream(ContactsContract.Contacts.openContactPhotoInputStream(this.mContext.getContentResolver(), localUri)));
        List localList1 = localContactMatch.getPhoneData();
        List localList2 = localContactMatch.getEmailData();
        if (localList1 != null)
        {
          ContactData localContactData2 = (ContactData)localList1.get(0);
          if ((localContactData2 == null) || (!localContactData2.isPhoneNumber()))
            continue;
          ((ArrayList)localObject).add(localContactData2.address);
          continue;
        }
        ((ArrayList)localObject).add("");
        if (localList2 != null)
        {
          ContactData localContactData1 = (ContactData)localList2.get(0);
          if (localContactData1 == null)
            continue;
          localArrayList2.add(localContactData1.address);
          continue;
        }
        localArrayList2.add("");
      }
      if (((String)((ArrayList)localObject).get(0)).toString().equals(""))
        localObject = localArrayList2;
      ContactAdapter localContactAdapter = new ContactAdapter(getContext(), paramList, localArrayList1, (ArrayList)localObject);
      this.contactList.setAdapter(localContactAdapter);
      this.contactList.setOnItemClickListener(this);
      this.contactList.setDivider(null);
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
    this.contactList = ((ListView)findViewById(2131558893));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.contactList);
    super.onMeasure(paramInt1, paramInt2);
  }

  public class ContactAdapter extends BaseAdapter
  {
    private int[] DEFAULT_IMG;
    private final List<Bitmap> avatars;
    private final List<String> contactPhone;
    private final List<ContactMatch> contacts;
    private int img_cnt = 0;

    public ContactAdapter(List<ContactMatch> paramList, List<Bitmap> paramArrayList, ArrayList<String> arg4)
    {
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = 2130837566;
      arrayOfInt[1] = 2130837567;
      arrayOfInt[2] = 2130837568;
      arrayOfInt[3] = 2130837569;
      arrayOfInt[4] = 2130837570;
      this.DEFAULT_IMG = arrayOfInt;
      this.contacts = paramArrayList;
      Object localObject1;
      this.avatars = localObject1;
      Object localObject2;
      this.contactPhone = localObject2;
    }

    public int getCount()
    {
      int i = this.contacts.size();
      return ContactChoiceWidget.this.getLimitedCount(i);
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
      Bitmap localBitmap1 = (Bitmap)this.avatars.get(paramInt);
      String str = (String)this.contactPhone.get(paramInt);
      ContactChoiceWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(ContactChoiceWidget.this.getContext(), 2130903083, null);
        localViewHolder = new ContactChoiceWidget.ViewHolder(ContactChoiceWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558689));
        localViewHolder.title = ((TextView)paramView.findViewById(2131558692));
        localViewHolder.qcb = ((ImageView)paramView.findViewById(2131558690));
        localViewHolder.phone = ((TextView)paramView.findViewById(2131558693));
        localViewHolder.star_img = ((ImageView)paramView.findViewById(2131558691));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        localViewHolder.title.setText(localContactMatch.primaryDisplayName);
        localViewHolder.phone.setText(str);
        if (!localContactMatch.starred)
          break label288;
        localViewHolder.star_img.setVisibility(0);
        label203: if (localBitmap1 != null)
          break label301;
        Bitmap localBitmap2 = BitmapFactory.decodeResource(ContactChoiceWidget.this.getResources(), this.DEFAULT_IMG[(this.img_cnt % 5)]);
        localViewHolder.qcb.setImageBitmap(localBitmap2);
        this.img_cnt = (1 + this.img_cnt);
        label251: if (paramInt != 0)
          break label314;
        paramView.setBackgroundResource(2130837820);
      }
      while (true)
      {
        paramView.setOnClickListener(new ContactChoiceWidget.ContactAdapter.1(this, paramInt));
        return paramView;
        localViewHolder = (ContactChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label288: localViewHolder.star_img.setVisibility(8);
        break label203;
        label301: localViewHolder.qcb.setImageBitmap(localBitmap1);
        break label251;
        label314: if (paramInt == -1 + this.contacts.size())
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
 * Qualified Name:     com.vlingo.midas.gui.widgets.ContactChoiceWidget
 * JD-Core Version:    0.6.0
 */