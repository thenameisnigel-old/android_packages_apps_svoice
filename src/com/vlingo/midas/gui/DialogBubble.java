package com.vlingo.midas.gui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import com.vlingo.core.internal.dialogmanager.DialogFlow;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.midas.gui.customviews.EditText;
import com.vlingo.midas.gui.customviews.TextView;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class DialogBubble extends LinearLayout
  implements OnEditListener, OnHWRUpdateListener
{
  private static final int ANIM_SET_TEXT_COLOUR = 2;
  private static final int FADEOUT_ANIM_DURATION = 333;
  private static final int INPUT_MAX_LENGTH = 150;
  private static final int SCALE_ANIMATION_END = 1;
  private static final int SCALE_ANIM_DURATION = 300;
  private final int DELAY_SHOW_TEXT = 300;
  private TextView bubbleHwrText;
  private EditText bubbleUserText;
  private TextView bubbleVlingoText;
  private TextView bubbleWakeupText;
  int density;
  private boolean fillScreen;
  private LinearLayout hwr_body;
  private OnEditListener mEditListener;
  private DialogBubbleHandler mHandler = new DialogBubbleHandler(this);
  private boolean mIsReplaceAvailable;
  private boolean mIsSetInputFilter;
  private OnSlideTextListener mListener;
  private Message msg;
  private String orgText;
  private long startTime;
  private String text;
  private LinearLayout textContainer;
  private Timer timer;
  private BubbleType type;
  private LinearLayout user_body;
  private LinearLayout vlingo_body;
  private LinearLayout wakeup_body;

  public DialogBubble(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void submitTextRequest()
  {
    Object localObject = getTag();
    DialogFlow.getInstance().startUserFlow(this.text, localObject);
  }

  public String getText()
  {
    return this.text;
  }

  public BubbleType getType()
  {
    return this.type;
  }

  public void initialize(BubbleType paramBubbleType, String paramString)
  {
    this.type = paramBubbleType;
    this.text = paramString;
    switch (7.$SwitchMap$com$vlingo$midas$gui$DialogBubble$BubbleType[paramBubbleType.ordinal()])
    {
    default:
      this.textContainer = ((LinearLayout)findViewById(2131558558));
      this.user_body.setVisibility(0);
      this.bubbleUserText.post(new Runnable(paramString)
      {
        public void run()
        {
          DialogBubble.this.bubbleUserText.getText().replace(0, DialogBubble.this.bubbleUserText.getText().length(), this.val$text);
          DialogBubble.this.setTalkbackString(true);
          DialogBubble.this.bubbleUserText.setTypeface(Typeface.defaultFromStyle(0));
        }
      });
      this.user_body.setImportantForAccessibility(2);
      startBubbleAnimation(1.0F, 0.0F);
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      return;
      this.textContainer = ((LinearLayout)findViewById(2131558560));
      this.vlingo_body.setVisibility(0);
      this.bubbleVlingoText.post(new Runnable(paramString)
      {
        public void run()
        {
          DialogBubble.this.bubbleVlingoText.setText(this.val$text);
          DialogBubble.this.bubbleVlingoText.setTypeface(Typeface.defaultFromStyle(0));
        }
      });
      startBubbleAnimation(0.0F, 0.0F);
      continue;
      this.textContainer = ((LinearLayout)findViewById(2131558562));
      if (Settings.getBoolean("car_word_spotter_enabled", true))
        this.wakeup_body.setVisibility(0);
      while (true)
      {
        this.bubbleWakeupText.post(new Runnable(paramString)
        {
          public void run()
          {
            DialogBubble.this.bubbleWakeupText.setText(this.val$text);
            DialogBubble.this.bubbleWakeupText.setTypeface(Typeface.defaultFromStyle(0));
          }
        });
        this.bubbleWakeupText.setAccessibilityDelegate(new View.AccessibilityDelegate()
        {
          public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
          {
            super.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
            if ((paramAccessibilityEvent.getEventType() == 8) || (paramAccessibilityEvent.getEventType() == 32768))
            {
              ControlFragment localControlFragment = (ControlFragment)((ConversationActivity)DialogBubble.this.getContext()).getSupportFragmentManager().findFragmentById(2131558517);
              if (localControlFragment != null)
                localControlFragment.avoidWakeUpCommandTTS();
            }
          }
        });
        startBubbleAnimation(0.0F, 0.0F);
        break;
        this.wakeup_body.setVisibility(8);
      }
      this.textContainer = ((LinearLayout)findViewById(2131558556));
      this.hwr_body.setVisibility(0);
      this.bubbleHwrText.post(new Runnable()
      {
        public void run()
        {
          DialogBubble.this.bubbleHwrText.setText("");
          DialogBubble.this.bubbleHwrText.setTypeface(Typeface.defaultFromStyle(0));
        }
      });
      startBubbleAnimation(1.0F, 0.0F);
    }
  }

  public boolean isFillScreen()
  {
    return this.fillScreen;
  }

  public boolean isReplaceAvailable()
  {
    return this.mIsReplaceAvailable;
  }

  public boolean isUserTextReadyToTouch()
  {
    return this.bubbleUserText.isReadyToTouch();
  }

  public void onEditCanceled(View paramView)
  {
    if (this.mEditListener != null)
      this.mEditListener.onEditCanceled(this);
  }

  public void onEditFinished(View paramView)
  {
    if (this.orgText == null)
      return;
    if ((!this.orgText.equals(this.bubbleUserText.getText().toString().trim())) && (this.bubbleUserText.getText().length() > 0))
    {
      this.text = this.bubbleUserText.getText().toString();
      submitTextRequest();
      this.bubbleUserText.setFocusable(false);
      setEditable(false);
      if (this.mEditListener != null)
        this.mEditListener.onEditFinished(this);
    }
    while (true)
    {
      setTalkbackString(this.bubbleUserText.isEnabled());
      break;
      this.bubbleUserText.setText(this.orgText);
      setEditable(true);
      if (this.mEditListener == null)
        continue;
      this.mEditListener.onEditCanceled(this);
    }
  }

  public void onEditStarted(View paramView)
  {
    if (!this.mIsSetInputFilter)
    {
      int i = 150;
      if (this.text.length() >= 150)
        i = 150 + this.text.length();
      EditText localEditText = this.bubbleUserText;
      InputFilter[] arrayOfInputFilter = new InputFilter[1];
      arrayOfInputFilter[0] = new InputFilter.LengthFilter(i);
      localEditText.setFilters(arrayOfInputFilter);
      this.mIsSetInputFilter = true;
      this.orgText = this.text.trim();
    }
    this.text = this.bubbleUserText.getText().toString();
    this.bubbleUserText.setSelection(this.bubbleUserText.length());
    DialogFlow.getInstance().cancelTTS();
    DialogFlow.getInstance().cancelTurn();
    if (this.mEditListener != null)
      this.mEditListener.onEditStarted(this);
  }

  public void onFinishInflate()
  {
    this.bubbleVlingoText = ((TextView)findViewById(2131558559));
    this.bubbleUserText = ((EditText)findViewById(2131558557));
    if (this.bubbleUserText != null)
    {
      this.bubbleUserText.setOnEditListener(this);
      this.bubbleUserText.setPrivateImeOptions("disableVoiceInput=true");
      this.bubbleUserText.setImeOptions(268435456);
    }
    this.bubbleWakeupText = ((TextView)findViewById(2131558561));
    this.bubbleHwrText = ((TextView)findViewById(2131558555));
    this.textContainer = ((LinearLayout)findViewById(2131558548));
    this.vlingo_body = ((LinearLayout)findViewById(2131558549));
    this.user_body = ((LinearLayout)findViewById(2131558550));
    this.wakeup_body = ((LinearLayout)findViewById(2131558551));
    this.hwr_body = ((LinearLayout)findViewById(2131558552));
    this.density = (getContext().getResources().getDisplayMetrics().densityDpi / 160);
  }

  public void replaceOriginalText()
  {
    setText(this.orgText);
  }

  public void saveParam(BubbleType paramBubbleType, String paramString, boolean paramBoolean)
  {
    this.type = paramBubbleType;
    this.text = paramString;
    this.fillScreen = paramBoolean;
  }

  public void setEditable(boolean paramBoolean)
  {
    if (paramBoolean)
      this.bubbleUserText.setEnabled(true);
    while (true)
    {
      return;
      this.bubbleUserText.setEnabled(false);
    }
  }

  public void setHWRText(String paramString)
  {
    this.bubbleHwrText.setText(paramString);
  }

  public void setOnEditListener(OnEditListener paramOnEditListener)
  {
    this.mEditListener = paramOnEditListener;
  }

  public void setOnSlideTextListenr(OnSlideTextListener paramOnSlideTextListener)
  {
    this.mListener = paramOnSlideTextListener;
  }

  public void setReplaceAvailable(boolean paramBoolean)
  {
    this.mIsReplaceAvailable = paramBoolean;
  }

  public void setTalkbackString(boolean paramBoolean)
  {
    if (paramBoolean)
      this.bubbleUserText.setContentDescription(getResources().getString(2131362707) + this.bubbleUserText.getText() + ". " + getResources().getString(2131362670));
    while (true)
    {
      return;
      this.bubbleUserText.setContentDescription(getResources().getString(2131362707) + this.bubbleUserText.getText() + ".");
    }
  }

  public void setText(String paramString)
  {
    this.text = paramString;
    this.bubbleUserText.setText(paramString);
  }

  public void setType(BubbleType paramBubbleType)
  {
    this.type = paramBubbleType;
  }

  public void setUserTextReadyToTouch()
  {
    this.bubbleUserText.setReadyToTouch();
  }

  public void startBubbleAnimation(float paramFloat1, float paramFloat2)
  {
    ScaleAnimation localScaleAnimation = new ScaleAnimation(0.1F, 1.0F, 0.1F, 1.0F, 1, paramFloat1, 1, paramFloat2);
    localScaleAnimation.setDuration(300L);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(333L);
    AnimationSet localAnimationSet = new AnimationSet(true);
    localAnimationSet.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnimation)
      {
        DialogBubble.this.mHandler.sendEmptyMessage(1);
      }

      public void onAnimationRepeat(Animation paramAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnimation)
      {
      }
    });
    localAnimationSet.addAnimation(localAlphaAnimation);
    localAnimationSet.addAnimation(localScaleAnimation);
    localAnimationSet.setInterpolator(new AccelerateDecelerateInterpolator());
    this.textContainer.startAnimation(localAnimationSet);
    this.startTime = localAnimationSet.getStartTime();
    this.msg = Message.obtain(this.mHandler, 2);
    this.timer = new Timer();
    this.timer.schedule(new AnimationTask(), 300L + this.startTime);
  }

  class AnimationTask extends TimerTask
  {
    AnimationTask()
    {
    }

    public void run()
    {
      if (DialogBubble.this.mHandler != null)
        DialogBubble.this.mHandler.sendMessage(DialogBubble.this.msg);
      DialogBubble.this.timer.cancel();
    }
  }

  public static enum BubbleType
  {
    static
    {
      ERROR = new BubbleType("ERROR", 2);
      WARNING = new BubbleType("WARNING", 3);
      WAKE_UP = new BubbleType("WAKE_UP", 4);
      HWR = new BubbleType("HWR", 5);
      BubbleType[] arrayOfBubbleType = new BubbleType[6];
      arrayOfBubbleType[0] = USER;
      arrayOfBubbleType[1] = VLINGO;
      arrayOfBubbleType[2] = ERROR;
      arrayOfBubbleType[3] = WARNING;
      arrayOfBubbleType[4] = WAKE_UP;
      arrayOfBubbleType[5] = HWR;
      $VALUES = arrayOfBubbleType;
    }
  }

  private static class DialogBubbleHandler extends Handler
  {
    private final WeakReference<DialogBubble> outer;

    DialogBubbleHandler(DialogBubble paramDialogBubble)
    {
      this.outer = new WeakReference(paramDialogBubble);
    }

    public void handleMessage(Message paramMessage)
    {
      DialogBubble localDialogBubble = (DialogBubble)this.outer.get();
      if (localDialogBubble != null)
      {
        if (paramMessage.what != 2)
          break label154;
        localDialogBubble.mListener.onSlideTextStart(localDialogBubble.text);
        switch (DialogBubble.7.$SwitchMap$com$vlingo$midas$gui$DialogBubble$BubbleType[localDialogBubble.type.ordinal()])
        {
        default:
          localDialogBubble.bubbleUserText.setTextColor(localDialogBubble.getResources().getColor(2131230726));
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
      }
      while (true)
      {
        return;
        localDialogBubble.bubbleVlingoText.setTextColor(localDialogBubble.getResources().getColor(2131230724));
        continue;
        localDialogBubble.bubbleWakeupText.setTextColor(localDialogBubble.getResources().getColor(2131230725));
        continue;
        localDialogBubble.bubbleHwrText.setTextColor(localDialogBubble.getResources().getColor(2131230724));
        continue;
        label154: if (paramMessage.what != 1)
          continue;
        localDialogBubble.mListener.onSlideTextEnd(localDialogBubble.text);
      }
    }
  }

  public static abstract interface OnSlideTextListener
  {
    public abstract void onSlideTextEnd(String paramString);

    public abstract void onSlideTextStart(String paramString);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DialogBubble
 * JD-Core Version:    0.6.0
 */