package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DecoratorType;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.util.DialUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactDetailWidget extends BargeInWidget<ContactMatch>
  implements View.OnClickListener
{
  private ListView contactList;
  protected String phoneType = "call";
  private Uri uri;

  public ContactDetailWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private Bitmap getContactPhoto(Long paramLong)
  {
    this.uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, Long.toString(paramLong.longValue()));
    return BitmapFactory.decodeStream(ContactsContract.Contacts.openContactPhotoInputStream(getContext().getContentResolver(), this.uri));
  }

  private void launchNativeContact()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", this.uri);
    getContext().startActivity(localIntent);
  }

  public void initialize(ContactMatch paramContactMatch, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (paramContactMatch != null)
    {
      showHeaderView(paramContactMatch);
      List localList1 = paramContactMatch.getPhoneData();
      List localList2 = paramContactMatch.getEmailData();
      List localList3 = paramContactMatch.getAddressData();
      List localList4 = paramContactMatch.getBirthdayData();
      Map localMap = null;
      if ((localList1 != null) && ((paramWidgetDecorator == null) || (paramWidgetDecorator.has(DecoratorType.ContactShowPhone))))
      {
        if (!localList1.isEmpty())
        {
          localMap = DialUtil.getPhoneTypeMap(getResources(), localList1, this.phoneType);
          if (localMap.isEmpty())
            localMap = DialUtil.getPhoneTypeMap(getResources(), localList1, "call");
        }
        if (localMap != null)
        {
          ArrayList localArrayList3 = new ArrayList(localMap.values());
          ArrayList localArrayList4 = new ArrayList(localMap.keySet());
          for (int k = 0; k < localList1.size(); k++)
          {
            localArrayList2.add((ContactData)localArrayList3.get(k));
            localArrayList1.add((String)localArrayList4.get(k));
          }
        }
      }
      if ((localList3 != null) && ((paramWidgetDecorator == null) || (paramWidgetDecorator.has(DecoratorType.ContactShowAddress))))
      {
        int j = 0;
        if (j < localList3.size())
        {
          localArrayList2.add(localList3.get(j));
          switch (((ContactData)localList3.get(j)).type)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            j++;
            break;
            localArrayList1.add(((ContactData)localList3.get(j)).label);
            continue;
            localArrayList1.add(getResources().getString(2131362216));
            continue;
            localArrayList1.add(getResources().getString(2131362219));
            continue;
            localArrayList1.add(getResources().getString(2131362218));
          }
        }
      }
      if ((localList2 != null) && ((paramWidgetDecorator == null) || (paramWidgetDecorator.has(DecoratorType.ContactShowEmail))))
      {
        int i = 0;
        if (i < localList2.size())
        {
          localArrayList2.add(localList2.get(i));
          switch (((ContactData)localList2.get(i)).type)
          {
          case 2:
          case 4:
          case 5:
          case 6:
          default:
            localArrayList1.add("custom");
          case 0:
          case 1:
          case 3:
          case 7:
          }
          while (true)
          {
            i++;
            break;
            localArrayList1.add(((ContactData)localList2.get(i)).label);
            continue;
            localArrayList1.add(getResources().getString(2131362216));
            continue;
            localArrayList1.add(getResources().getString(2131362219));
            continue;
            localArrayList1.add(getResources().getString(2131362218));
          }
        }
      }
      if ((localList4 != null) && ((paramWidgetDecorator == null) || (paramWidgetDecorator.has(DecoratorType.ContactShowBirthday))))
      {
        ContactData localContactData = (ContactData)localList4.get(0);
        if (localContactData != null)
        {
          localArrayList2.add(localContactData);
          localArrayList1.add(getResources().getString(2131362532));
        }
      }
      ContactAdapter localContactAdapter = new ContactAdapter(getContext(), localArrayList2, localArrayList1);
      this.contactList.setAdapter(localContactAdapter);
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

  public void onClick(View paramView)
  {
    paramView.getId();
    launchNativeContact();
    retire();
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.contactList = ((ListView)findViewById(2131558912));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.contactList);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void showHeaderView(ContactMatch paramContactMatch)
  {
    ImageView localImageView = (ImageView)findViewById(2131558910);
    TextView localTextView = (TextView)findViewById(2131558911);
    localTextView.setOnClickListener(this);
    Bitmap localBitmap = getContactPhoto(Long.valueOf(paramContactMatch.primaryContactID));
    if (localBitmap == null)
      localImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), 2130837566));
    while (true)
    {
      localTextView.setText(paramContactMatch.primaryDisplayName);
      return;
      localImageView.setImageBitmap(localBitmap);
    }
  }

  public class ContactAdapter extends BaseAdapter
  {
    private final List<ContactData> contacts;
    private final List<String> titleInfo;

    public ContactAdapter(List<ContactData> paramList, List<String> arg3)
    {
      Object localObject1;
      this.contacts = localObject1;
      Object localObject2;
      this.titleInfo = localObject2;
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
      ContactData localContactData = (ContactData)this.contacts.get(paramInt);
      String str = (String)this.titleInfo.get(paramInt);
      ContactDetailWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(ContactDetailWidget.this.getContext(), 2130903084, null);
        localViewHolder = new ContactDetailWidget.ViewHolder(ContactDetailWidget.this);
        localViewHolder.subject = ((TextView)paramView.findViewById(2131558695));
        localViewHolder.data_view = ((TextView)paramView.findViewById(2131558696));
        localViewHolder.divider = paramView.findViewById(2131558697);
        paramView.setTag(localViewHolder);
      }
      while (true)
      {
        localViewHolder.subject.setText(str);
        localViewHolder.data_view.setText(localContactData.address);
        if (paramInt == -1 + this.contacts.size())
          localViewHolder.divider.setVisibility(4);
        paramView.setOnClickListener(new ContactDetailWidget.ContactAdapter.1(this));
        return paramView;
        localViewHolder = (ContactDetailWidget.ViewHolder)paramView.getTag();
      }
    }
  }

  class ViewHolder
  {
    TextView data_view;
    View divider;
    TextView subject;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ContactDetailWidget
 * JD-Core Version:    0.6.0
 */