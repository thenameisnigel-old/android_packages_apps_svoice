package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.gui.Widget;

public class DrivingContents extends RelativeLayout
{
  public static final int MULTIWINDOW_HEIGHT_LEVEL_0 = 160;
  public static final int MULTIWINDOW_HEIGHT_LEVEL_1 = 370;
  public static final int MULTIWINDOW_HEIGHT_LEVEL_2 = 600;
  public static final int MULTIWINDOW_HEIGHT_LEVEL_3 = 1000;
  private final Context mContext;
  private int mCurrentHeight = 1000;
  protected DriveMode mCurrentLowerMode = DriveMode.Null;
  private Handler mHandle;
  private LinearLayout mLowerContainer;
  private boolean mMinimized = false;

  public DrivingContents(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    setClickable(false);
  }

  public <T> void dataChange(Object paramObject, Widget<T> paramWidget)
  {
    this.mLowerContainer.removeAllViews();
    if (paramWidget != null)
      this.mLowerContainer.addView(paramWidget);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public int getMaxHeight()
  {
    return this.mCurrentHeight;
  }

  public void initialize(Object paramObject, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener, Handler paramHandler)
  {
    this.mHandle = paramHandler;
  }

  public boolean isTranslated()
  {
    return false;
  }

  public boolean isWidgetReplaceable()
  {
    return false;
  }

  public void minimize(boolean paramBoolean)
  {
    if (this.mMinimized != paramBoolean)
      this.mMinimized = paramBoolean;
  }

  public void onFinishInflate()
  {
  }

  public void onWindowStatusChanged(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Rect paramRect)
  {
    if (paramBoolean1 == true);
  }

  public static enum DriveMode
  {
    static
    {
      ScreenSaver = new DriveMode("ScreenSaver", 2);
      Message = new DriveMode("Message", 3);
      Memo = new DriveMode("Memo", 4);
      Music = new DriveMode("Music", 5);
      News = new DriveMode("News", 6);
      Mini = new DriveMode("Mini", 7);
      Weather = new DriveMode("Weather", 8);
      ContactChoice = new DriveMode("ContactChoice", 9);
      AddressBook = new DriveMode("AddressBook", 10);
      MusicListChoice = new DriveMode("MusicListChoice", 11);
      Null = new DriveMode("Null", 12);
      DriveMode[] arrayOfDriveMode = new DriveMode[13];
      arrayOfDriveMode[0] = Greeting;
      arrayOfDriveMode[1] = TimeScreen;
      arrayOfDriveMode[2] = ScreenSaver;
      arrayOfDriveMode[3] = Message;
      arrayOfDriveMode[4] = Memo;
      arrayOfDriveMode[5] = Music;
      arrayOfDriveMode[6] = News;
      arrayOfDriveMode[7] = Mini;
      arrayOfDriveMode[8] = Weather;
      arrayOfDriveMode[9] = ContactChoice;
      arrayOfDriveMode[10] = AddressBook;
      arrayOfDriveMode[11] = MusicListChoice;
      arrayOfDriveMode[12] = Null;
      $VALUES = arrayOfDriveMode;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DrivingContents
 * JD-Core Version:    0.6.0
 */