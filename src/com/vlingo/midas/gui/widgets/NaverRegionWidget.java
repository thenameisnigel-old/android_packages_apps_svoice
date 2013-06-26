package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverRegionItem;
import com.vlingo.midas.naver.NaverXMLParser;
import java.util.Hashtable;
import java.util.List;

public class NaverRegionWidget extends BargeInWidget<NaverAdaptor>
  implements View.OnClickListener
{
  private Button btnFindRoute;
  private Button btnMore;
  private NaverRegionItem item;
  private final Context mContext;
  private TextView mMainAddress;
  private ImageView mMap;
  private TextView mNewAddress;
  private TextView mZipCode;
  private VVSActionHandlerListener mhandler;
  private NaverAdaptor wAdaptor;

  public NaverRegionWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.wAdaptor = paramNaverAdaptor;
    this.mhandler = this.wAdaptor.getVVSActionHandlerListener();
    this.item = new NaverRegionItem((Hashtable)this.wAdaptor.getNaverXML().itemList.get(0));
    this.mMainAddress.setText(this.item.getAddress());
    if (this.item.getMatchAddress().equals("-"))
    {
      findViewById(2131559107).setVisibility(8);
      if (!this.item.getZipCode().equals("-"))
        break label168;
      findViewById(2131559109).setVisibility(8);
      label112: new Thread(new NaverRegionWidget.1(this)).start();
      if (this.item.getRouteUrl() == null)
        break label185;
      label140: if (this.item.getMoreUrl() == null)
        break label197;
    }
    while (true)
    {
      return;
      this.mNewAddress.setText(this.item.getMatchAddress());
      break;
      label168: this.mZipCode.setText(this.item.getZipCode());
      break label112;
      label185: this.btnFindRoute.setVisibility(8);
      break label140;
      label197: this.btnMore.setVisibility(8);
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
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    if ((paramView == this.btnFindRoute) && (this.item.getRouteUrl() != null))
      localIntent.setData(Uri.parse(this.item.getRouteUrl()));
    while (true)
    {
      this.mContext.startActivity(localIntent);
      do
        return;
      while ((paramView != this.btnMore) || (this.item.getMoreUrl() == null));
      localIntent.setData(Uri.parse(this.item.getMoreUrl()));
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mMainAddress = ((TextView)findViewById(2131559106));
    this.mMap = ((ImageView)findViewById(2131559098));
    this.mNewAddress = ((TextView)findViewById(2131559108));
    this.mZipCode = ((TextView)findViewById(2131559110));
    this.btnFindRoute = ((Button)findViewById(2131559111));
    this.btnFindRoute.setOnClickListener(this);
    this.btnMore = ((Button)findViewById(2131559112));
    this.btnMore.setOnClickListener(this);
  }

  public void retire()
  {
    super.retire();
    this.btnMore.setVisibility(8);
    this.btnFindRoute.setVisibility(8);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverRegionWidget
 * JD-Core Version:    0.6.0
 */