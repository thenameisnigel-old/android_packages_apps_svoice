package com.vlingo.midas.gui.widgets;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.music.MusicDetails;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.io.PrintStream;
import java.util.List;

public class DrivingMusicWidget extends BargeInWidget<List<MusicDetails>>
  implements DrivingWidgetInterface
{
  private final Context context;
  private LinearLayout layout_main;
  private LinearLayout layout_thumbnail;
  private boolean mMinimized = false;
  private TextView mMusicAlbum;
  private TextView mMusicLength;
  private TextView mMusicProgress;
  private TextView mMusicTitle;
  private SeekBar mSeekBar;
  private ImageView mThumbnail;
  private ImageView mThumbnail_land;
  private ImageView mThumbnail_small;
  private BroadcastReceiver musicBroadcastReceiver;

  public DrivingMusicWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.musicBroadcastReceiver = new musicBroadcastReceiver();
    paramContext.registerReceiver(this.musicBroadcastReceiver, new IntentFilter("com.android.music.metachanged"));
    paramContext.registerReceiver(this.musicBroadcastReceiver, new IntentFilter("com.sec.android.music.musicservicecommnad.mediainfo"));
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 1);
    for (int i = 340; ; i = 900)
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(List<MusicDetails> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    if (paramList == null);
  }

  public boolean isDecreasedSize()
  {
    return this.mMinimized;
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    setMusicplayerLayout();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.musicBroadcastReceiver != null)
    {
      getContext().unregisterReceiver(this.musicBroadcastReceiver);
      this.musicBroadcastReceiver = null;
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.layout_main = ((LinearLayout)findViewById(2131558433));
    this.layout_thumbnail = ((LinearLayout)findViewById(2131559086));
    this.mMusicTitle = ((TextView)findViewById(2131559085));
    this.mMusicAlbum = ((TextView)findViewById(2131559084));
    this.mSeekBar = ((SeekBar)findViewById(2131559089));
    this.mMusicProgress = ((TextView)findViewById(2131559090));
    this.mMusicLength = ((TextView)findViewById(2131559091));
    this.mThumbnail = ((ImageView)findViewById(2131559087));
    this.mThumbnail_land = ((ImageView)findViewById(2131559083));
    this.mThumbnail_small = ((ImageView)findViewById(2131559088));
    this.mSeekBar.setVisibility(8);
    this.mMusicProgress.setVisibility(8);
    this.mMusicLength.setVisibility(8);
    setMusicplayerLayout();
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onStop()
  {
    super.onStop();
    System.out.println("DriveMusicWidget:: onStop called");
  }

  public void setMusicplayerLayout()
  {
    if (getResources().getConfiguration().orientation == 1)
    {
      this.mThumbnail.setVisibility(0);
      this.mThumbnail_land.setVisibility(8);
      this.mThumbnail_small.setVisibility(8);
      this.mMusicTitle.setSingleLine(true);
      if ((MidasSettings.isMultiwindowedMode()) || (this.mMinimized))
      {
        this.mThumbnail.setVisibility(8);
        if (MidasSettings.isNightMode())
          this.layout_main.setBackgroundResource(2130837874);
      }
      while (true)
      {
        this.layout_main.setPadding(8, 0, 8, 0);
        return;
        this.layout_main.setBackgroundResource(2130837587);
        continue;
        if (MidasSettings.isNightMode())
        {
          this.layout_main.setBackgroundResource(2130837876);
          continue;
        }
        this.layout_main.setBackgroundResource(2130837589);
      }
    }
    this.mThumbnail.setVisibility(8);
    if (MidasSettings.isMultiwindowedMode())
    {
      this.mThumbnail_land.setVisibility(8);
      this.mThumbnail_small.setVisibility(0);
      this.mMusicTitle.setSingleLine(true);
      this.layout_thumbnail.setVisibility(0);
      this.layout_thumbnail.setPadding(0, 0, 0, 0);
      if (MidasSettings.isNightMode())
        this.layout_main.setBackgroundResource(2130837876);
      while (true)
      {
        this.layout_main.setPadding(8, 21, 8, 21);
        break;
        this.layout_main.setBackgroundResource(2130837589);
      }
    }
    if (this.mMinimized)
    {
      this.mThumbnail_land.setVisibility(8);
      this.mThumbnail_small.setVisibility(8);
      this.mMusicTitle.setSingleLine(true);
      if (MidasSettings.isNightMode())
        this.layout_main.setBackgroundResource(2130837874);
      while (true)
      {
        this.layout_main.setPadding(8, 0, 8, 0);
        break;
        this.layout_main.setBackgroundResource(2130837587);
      }
    }
    this.mThumbnail_land.setVisibility(0);
    this.mThumbnail_small.setVisibility(8);
    this.mMusicTitle.setSingleLine(false);
    this.mMusicTitle.setMaxLines(2);
    if (MidasSettings.isNightMode())
      this.layout_main.setBackgroundResource(2130837881);
    while (true)
    {
      this.layout_main.setPadding(8, 21, 8, 21);
      break;
      this.layout_main.setBackgroundResource(2130837743);
    }
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.mMinimized = paramBoolean;
    if (this.mMinimized)
      if ((!MidasSettings.isMultiwindowedMode()) || (getResources().getConfiguration().orientation == 1))
      {
        this.layout_thumbnail.setVisibility(8);
        if (this.mThumbnail_land.isShown())
          this.mThumbnail_land.setVisibility(8);
        if (!MidasSettings.isNightMode())
          break label98;
        this.layout_main.setBackgroundResource(2130837874);
        this.layout_main.setPadding(8, 0, 8, 0);
        this.mMusicTitle.setSingleLine(true);
      }
    while (true)
    {
      return 0;
      label98: this.layout_main.setBackgroundResource(2130837587);
      break;
      this.layout_thumbnail.setVisibility(0);
    }
  }

  public void startAnimationTranslate(View paramView)
  {
  }

  public class musicBroadcastReceiver extends BroadcastReceiver
  {
    public musicBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str;
      Cursor localCursor;
      if (paramIntent != null)
      {
        str = paramIntent.getAction();
        if (!str.equals("com.android.music.metachanged"))
          break label338;
        DrivingMusicWidget.this.mMusicTitle.setText(paramIntent.getExtras().getString("track"));
        DrivingMusicWidget.this.mMusicAlbum.setText(paramIntent.getExtras().getString("artist"));
        DrivingMusicWidget.this.mMusicProgress.setText(paramIntent.getExtras().getString("position"));
        DrivingMusicWidget.this.mMusicLength.setText(paramIntent.getExtras().getString("trackLength"));
        if (!MidasSettings.isNightMode())
          break label284;
        DrivingMusicWidget.this.mMusicTitle.setTextColor(Color.parseColor("#e0e0e0"));
        DrivingMusicWidget.this.mMusicAlbum.setTextColor(Color.parseColor("#e0e0e0"));
        DrivingMusicWidget.this.mMusicProgress.setTextColor(-1);
        DrivingMusicWidget.this.mMusicLength.setTextColor(-1);
        localCursor = paramContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
      }
      while (true)
      {
        try
        {
          localCursor.moveToFirst();
          if (!localCursor.getString(localCursor.getColumnIndex("album")).equals(paramIntent.getExtras().getString("album")))
            continue;
          Uri localUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart/"), localCursor.getLong(localCursor.getColumnIndex("album_id")));
          DrivingMusicWidget.this.mThumbnail.setImageURI(localUri);
          DrivingMusicWidget.this.mThumbnail_land.setImageURI(localUri);
          DrivingMusicWidget.this.mThumbnail_small.setImageURI(localUri);
          if (localCursor == null)
            continue;
          localCursor.close();
          return;
          label284: DrivingMusicWidget.this.mMusicTitle.setTextColor(-16777216);
          DrivingMusicWidget.this.mMusicAlbum.setTextColor(-16777216);
          break;
          boolean bool = localCursor.moveToNext();
          if (bool)
            continue;
          continue;
        }
        catch (CursorIndexOutOfBoundsException localCursorIndexOutOfBoundsException)
        {
          localCursorIndexOutOfBoundsException.printStackTrace();
          continue;
        }
        label338: if (!str.equals("com.sec.android.music.musicservicecommnad.mediainfo"))
          continue;
        DrivingMusicWidget.access$702(DrivingMusicWidget.this, (LinearLayout)DrivingMusicWidget.this.findViewById(2131558433));
        if (paramIntent.getExtras().getBoolean("isPlaying"))
        {
          DrivingMusicWidget.this.layout_main.setVisibility(0);
          continue;
        }
        DrivingMusicWidget.this.layout_main.setVisibility(8);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingMusicWidget
 * JD-Core Version:    0.6.0
 */