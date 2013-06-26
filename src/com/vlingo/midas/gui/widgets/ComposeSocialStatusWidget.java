package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.types.SocialType;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.social.api.SocialNetworkType;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.ClientConfiguration;

public class ComposeSocialStatusWidget extends BargeInWidget<SocialType>
{
  private Button cancelBtn;
  LinearLayout compose_social_button_container;
  ImageView contactImage;
  private TextView contactName;
  private Context context;
  ImageView facebookIcon;
  private boolean isClicked = false;
  private WidgetActionListener listener;
  private EditText msgBody;
  Bitmap thumbImg;
  ImageView twitterIcon;
  private Button updateBtn;

  public ComposeSocialStatusWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(SocialType paramSocialType, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    String str1 = "";
    String str2 = "";
    String str3 = Settings.getLanguageApplication();
    SocialNetworkType localSocialNetworkType;
    if (paramSocialType != null)
    {
      str2 = paramSocialType.getMessage();
      localSocialNetworkType = paramSocialType.getSocialNetwork();
      if (localSocialNetworkType != SocialNetworkType.FACEBOOK)
        break label175;
      this.thumbImg = Settings.getImage("facebook_picture");
      str1 = Settings.getString("facebook_account_name", "");
      this.facebookIcon.setVisibility(0);
      this.twitterIcon.setVisibility(8);
    }
    while (true)
    {
      if (this.thumbImg != null)
        this.contactImage.setImageBitmap(this.thumbImg);
      if (str1 != null)
        this.contactName.setText(str1);
      if (str2 != null)
      {
        this.msgBody.setText(str2);
        if ((StringUtils.isNullOrWhiteSpace(str3)) || (!str3.equals("en-US")));
      }
      if ((StringUtils.isNullOrWhiteSpace(str1)) || ((StringUtils.isNullOrWhiteSpace(str2)) && (this.updateBtn != null)))
        this.updateBtn.setVisibility(8);
      return;
      label175: if (localSocialNetworkType == SocialNetworkType.TWITTER)
      {
        this.thumbImg = Settings.getImage("twitter_picture");
        str1 = Settings.getString("twitter_account_name", "");
        this.twitterIcon.setVisibility(0);
        this.facebookIcon.setVisibility(8);
        continue;
      }
      if (localSocialNetworkType == SocialNetworkType.WEIBO)
      {
        this.thumbImg = Settings.getImage("weibo_picture");
        str1 = Settings.getString("weibo_account_name", "");
        this.facebookIcon.setVisibility(0);
        this.twitterIcon.setVisibility(8);
        continue;
      }
      if (localSocialNetworkType != SocialNetworkType.QZONE)
        break;
      this.thumbImg = Settings.getImage("qzone_picture");
      str1 = Settings.getString("qzone_account_name", "");
      this.twitterIcon.setVisibility(0);
      this.facebookIcon.setVisibility(8);
    }
    if (Settings.getImage("facebook_picture") != null)
    {
      this.thumbImg = Settings.getImage("facebook_picture");
      label330: if (Settings.getString("facebook_account_name", "") == null)
        break label388;
      str1 = Settings.getString("facebook_account_name", "");
    }
    while (true)
    {
      this.twitterIcon.setVisibility(0);
      this.facebookIcon.setVisibility(0);
      break;
      if (Settings.getImage("twitter_picture") == null)
        break label330;
      this.thumbImg = Settings.getImage("twitter_picture");
      break label330;
      label388: if (Settings.getString("twitter_account_name", "") == null)
        continue;
      str1 = Settings.getString("twitter_account_name", "");
    }
  }

  public boolean isTranslated()
  {
    return this.isClicked;
  }

  public boolean isWidgetReplaceable()
  {
    return true;
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.contactName = ((TextView)findViewById(2131558898));
    this.msgBody = ((EditText)findViewById(2131558900));
    this.contactImage = ((ImageView)findViewById(2131558897));
    this.facebookIcon = ((ImageView)findViewById(2131558902));
    this.twitterIcon = ((ImageView)findViewById(2131558903));
    if (ClientConfiguration.isChineseBuild())
    {
      this.facebookIcon.setImageResource(2130838183);
      this.twitterIcon.setImageResource(2130838181);
    }
    this.compose_social_button_container = ((LinearLayout)findViewById(2131558904));
    this.msgBody.setOnFocusChangeListener(new ComposeSocialStatusWidget.1(this));
    this.cancelBtn = ((Button)findViewById(2131558905));
    this.updateBtn = ((Button)findViewById(2131558906));
    if (this.updateBtn != null)
      this.updateBtn.setOnClickListener(new ComposeSocialStatusWidget.2(this));
    if (this.cancelBtn != null)
      this.cancelBtn.setOnClickListener(new ComposeSocialStatusWidget.3(this));
    this.msgBody.setEnabled(false);
  }

  public void retire()
  {
    super.retire();
    if (this.updateBtn != null)
      this.updateBtn.setVisibility(8);
    if (this.cancelBtn != null)
      this.cancelBtn.setVisibility(8);
    if (this.compose_social_button_container != null)
      this.compose_social_button_container.setVisibility(8);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.ComposeSocialStatusWidget
 * JD-Core Version:    0.6.0
 */