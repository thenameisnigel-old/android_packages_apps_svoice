package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.midas.gui.Widget;
import com.vlingo.midas.naver.NaverAdaptor;
import com.vlingo.midas.naver.NaverXMLParser;
import java.util.List;

public class NaverWidget extends Widget<NaverAdaptor>
{
  private WidgetActionListener listener;
  private TextView mContentView;
  private final Context mContext;
  private NaverAdaptor wAdaptor;

  public NaverWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public void drawNHNLogo()
  {
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131559092);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.mContext);
    ImageView localImageView = new ImageView(this.mContext);
    localImageView.setImageResource(2130837556);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(116, 32);
    localLayoutParams.addRule(11);
    localLayoutParams.addRule(10);
    localLayoutParams.setMargins(0, 0, 24, 0);
    localRelativeLayout.addView(localImageView, localLayoutParams);
    localViewGroup.addView(localRelativeLayout, 0);
  }

  public void initialize(NaverAdaptor paramNaverAdaptor, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    this.wAdaptor = paramNaverAdaptor;
    VVSActionHandlerListener localVVSActionHandlerListener;
    NaverXMLParser localNaverXMLParser;
    List localList;
    if (this.wAdaptor != null)
    {
      localVVSActionHandlerListener = this.wAdaptor.getVVSActionHandlerListener();
      localNaverXMLParser = this.wAdaptor.getNaverXML();
      if (localNaverXMLParser.ErrorCode == 0)
      {
        localList = localNaverXMLParser.itemList;
        if ((localNaverXMLParser.ttsText != null) && (localNaverXMLParser.ttsText.length() > 0))
          localVVSActionHandlerListener.showVlingoTextAndTTS(localNaverXMLParser.ttsText, localNaverXMLParser.ttsText);
        ViewGroup localViewGroup4;
        int m;
        while ((localNaverXMLParser.domain.equals("MOVIE")) && (localList != null))
        {
          if (localList.size() == 1)
          {
            ViewGroup localViewGroup5 = (ViewGroup)View.inflate(this.mContext, 2130903186, (ViewGroup)findViewById(2131559092));
            for (int n = 0; localViewGroup5.getChildCount() > n; n++)
            {
              View localView5 = localViewGroup5.getChildAt(n);
              if (!(localView5 instanceof BargeInWidget))
                continue;
              ((Widget)localView5).initialize(paramNaverAdaptor, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
            }
            if ((localNaverXMLParser.svoiceQuery != null) && (localNaverXMLParser.svoiceQuery.length() > 0))
            {
              localVVSActionHandlerListener.showVlingoTextAndTTS(localNaverXMLParser.svoiceQuery, localNaverXMLParser.ttsText);
              continue;
            }
            localVVSActionHandlerListener.showVlingoTextAndTTS(localNaverXMLParser.query, localNaverXMLParser.ttsText);
            continue;
          }
          localViewGroup4 = (ViewGroup)View.inflate(this.mContext, 2130903187, (ViewGroup)findViewById(2131559092));
          m = 0;
        }
        while (localViewGroup4.getChildCount() > m)
        {
          View localView4 = localViewGroup4.getChildAt(m);
          if ((localView4 instanceof BargeInWidget))
            ((Widget)localView4).initialize(paramNaverAdaptor, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
          m++;
          continue;
          if ((!localNaverXMLParser.domain.equals("REGION")) || (localList == null))
            break label459;
          if (localList.size() == 1)
          {
            ViewGroup localViewGroup3 = (ViewGroup)View.inflate(this.mContext, 2130903197, (ViewGroup)findViewById(2131559092));
            for (int k = 0; localViewGroup3.getChildCount() > k; k++)
            {
              View localView3 = localViewGroup3.getChildAt(k);
              if (!(localView3 instanceof BargeInWidget))
                continue;
              ((Widget)localView3).initialize(paramNaverAdaptor, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
            }
          }
          drawNHNLogo();
          DefaultWebSearchHandler localDefaultWebSearchHandler2 = new DefaultWebSearchHandler();
          localDefaultWebSearchHandler2.setListener(localVVSActionHandlerListener);
          if (localNaverXMLParser.url == null)
            break;
          localDefaultWebSearchHandler2.executeSearchIntentFromURL(localNaverXMLParser.url);
        }
      }
    }
    while (true)
    {
      return;
      label459: if ((localNaverXMLParser.domain.equals("LOCAL")) && (localList != null))
      {
        if (localList.size() == 1)
        {
          ViewGroup localViewGroup2 = (ViewGroup)View.inflate(this.mContext, 2130903195, (ViewGroup)findViewById(2131559092));
          for (int j = 0; localViewGroup2.getChildCount() > j; j++)
          {
            View localView2 = localViewGroup2.getChildAt(j);
            if (!(localView2 instanceof BargeInWidget))
              continue;
            ((Widget)localView2).initialize(paramNaverAdaptor, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
          }
          continue;
        }
        ViewGroup localViewGroup1 = (ViewGroup)View.inflate(this.mContext, 2130903196, (ViewGroup)findViewById(2131559092));
        for (int i = 0; localViewGroup1.getChildCount() > i; i++)
        {
          View localView1 = localViewGroup1.getChildAt(i);
          if (!(localView1 instanceof BargeInWidget))
            continue;
          ((Widget)localView1).initialize(paramNaverAdaptor, paramWidgetDecorator, paramWidgetKey, paramWidgetActionListener);
        }
        continue;
      }
      drawNHNLogo();
      DefaultWebSearchHandler localDefaultWebSearchHandler1 = new DefaultWebSearchHandler();
      localDefaultWebSearchHandler1.setListener(localVVSActionHandlerListener);
      if (localNaverXMLParser.url == null)
        continue;
      localDefaultWebSearchHandler1.executeSearchIntentFromURL(localNaverXMLParser.url);
      continue;
      drawNHNLogo();
      continue;
      drawNHNLogo();
    }
  }

  public boolean isTranslated()
  {
    return true;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onFinishInflate()
  {
    this.mContentView = ((TextView)findViewById(2131559093));
  }

  public void onResponseReceived()
  {
    this.mContentView.setText(this.wAdaptor.getContent());
    this.mContentView.setVisibility(0);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.NaverWidget
 * JD-Core Version:    0.6.0
 */