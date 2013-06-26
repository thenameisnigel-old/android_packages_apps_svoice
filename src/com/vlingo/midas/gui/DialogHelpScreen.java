package com.vlingo.midas.gui;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.vlingo.midas.gui.customviews.TextView;
import java.util.ArrayList;
import java.util.Iterator;

public class DialogHelpScreen extends RelativeLayout
{
  private final int DELAY_SHOW_TEXT = 500;
  private final String QUOTE = "\"";
  private TextView bubbleText;
  private LinearLayout exampleContainer;
  private RelativeLayout mainContainer;
  private BubbleType type;

  public DialogHelpScreen(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private String removeCharAt(String paramString, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(-1 + paramString.length());
    localStringBuffer.append(paramString.substring(0, paramInt)).append(paramString.substring(paramInt + 1));
    return localStringBuffer.toString();
  }

  private void setIconBubbleText()
  {
    this.bubbleText.setTypeface(Typeface.defaultFromStyle(0));
  }

  private CharSequence setKeywordsNew(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    while (paramString.indexOf("[") != -1)
    {
      int i = paramString.indexOf("[");
      int j = i + paramString.substring(i).indexOf("]");
      String str = removeCharAt(paramString, i);
      int k = j - 1;
      paramString = removeCharAt(str, k);
      localArrayList.add(new SpanArea(i, k));
    }
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      SpanArea localSpanArea = (SpanArea)localIterator.next();
      localSpannableStringBuilder.setSpan(new StyleSpan(1), localSpanArea.getStart(), localSpanArea.getEnd(), 33);
    }
    return localSpannableStringBuilder;
  }

  private void setQuotoBubbleText()
  {
    this.bubbleText.setTypeface(Typeface.defaultFromStyle(0));
  }

  private PromptContent updateText(PromptContent paramPromptContent)
  {
    PromptContent localPromptContent = setQuote(paramPromptContent);
    localPromptContent.ex1 = setKeywordsNew((String)localPromptContent.ex1);
    localPromptContent.ex2 = setKeywordsNew((String)localPromptContent.ex2);
    localPromptContent.ex3 = setKeywordsNew((String)localPromptContent.ex3);
    localPromptContent.ex4 = setKeywordsNew((String)localPromptContent.ex4);
    localPromptContent.ex5 = setKeywordsNew((String)localPromptContent.ex5);
    localPromptContent.ex6 = setKeywordsNew((String)localPromptContent.ex6);
    localPromptContent.ex7 = setKeywordsNew((String)localPromptContent.ex7);
    return localPromptContent;
  }

  public BubbleType getType()
  {
    return this.type;
  }

  public void initialize(BubbleType paramBubbleType, String paramString)
  {
    this.type = paramBubbleType;
    switch (2.$SwitchMap$com$vlingo$midas$gui$DialogHelpScreen$BubbleType[paramBubbleType.ordinal()])
    {
    default:
      setQuotoBubbleText();
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      new Handler().postDelayed(new Runnable(paramString)
      {
        public void run()
        {
          DialogHelpScreen.this.bubbleText.setText(this.val$text);
        }
      }
      , 500L);
      return;
      setIconBubbleText();
      continue;
      setIconBubbleText();
      continue;
      setIconBubbleText();
    }
  }

  public void onFinishInflate()
  {
    this.bubbleText = ((TextView)findViewById(2131558559));
    this.mainContainer = ((RelativeLayout)findViewById(2131558580));
    if (this.mainContainer != null)
      this.exampleContainer = ((LinearLayout)this.mainContainer.findViewById(2131558582));
  }

  public void resetScroll()
  {
    ScrollView localScrollView = (ScrollView)findViewById(2131558581);
    if (localScrollView != null)
      localScrollView.scrollTo(0, 0);
  }

  public void setDialogScreen()
  {
    PromptContent localPromptContent1 = PromptContent.getGenericPrompt(getResources());
    TextView localTextView1 = (TextView)this.exampleContainer.findViewById(2131558584);
    TextView localTextView2 = (TextView)this.exampleContainer.findViewById(2131558586);
    TextView localTextView3 = (TextView)this.exampleContainer.findViewById(2131558588);
    TextView localTextView4 = (TextView)this.exampleContainer.findViewById(2131558590);
    TextView localTextView5 = (TextView)this.exampleContainer.findViewById(2131558592);
    TextView localTextView6 = (TextView)this.exampleContainer.findViewById(2131558594);
    TextView localTextView7 = (TextView)this.exampleContainer.findViewById(2131558596);
    PromptContent localPromptContent2 = updateText(localPromptContent1);
    localTextView1.setText(localPromptContent2.ex1);
    localTextView2.setText(localPromptContent2.ex2);
    localTextView3.setText(localPromptContent2.ex3);
    localTextView4.setText(localPromptContent2.ex4);
    localTextView5.setText(localPromptContent2.ex5);
    localTextView6.setText(localPromptContent2.ex6);
    localTextView7.setText(localPromptContent2.ex7);
    ImageView localImageView1 = (ImageView)this.exampleContainer.findViewById(2131558583);
    ImageView localImageView2 = (ImageView)this.exampleContainer.findViewById(2131558585);
    ImageView localImageView3 = (ImageView)this.exampleContainer.findViewById(2131558587);
    ImageView localImageView4 = (ImageView)this.exampleContainer.findViewById(2131558589);
    ImageView localImageView5 = (ImageView)this.exampleContainer.findViewById(2131558591);
    ImageView localImageView6 = (ImageView)this.exampleContainer.findViewById(2131558593);
    ImageView localImageView7 = (ImageView)this.exampleContainer.findViewById(2131558595);
    localImageView1.setImageResource(localPromptContent2.exIcon1);
    localImageView2.setImageResource(localPromptContent2.exIcon2);
    localImageView3.setImageResource(localPromptContent2.exIcon3);
    localImageView4.setImageResource(localPromptContent2.exIcon4);
    localImageView5.setImageResource(localPromptContent2.exIcon5);
    localImageView6.setImageResource(localPromptContent2.exIcon6);
    localImageView7.setImageResource(localPromptContent2.exIcon7);
  }

  public void setEffect(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    AnimationSet localAnimationSet = new AnimationSet(false);
    ScaleAnimation localScaleAnimation = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    localScaleAnimation.setDuration(paramInt);
    localAnimationSet.addAnimation(localScaleAnimation);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramFloat3, paramFloat4);
    localAlphaAnimation.setDuration(paramInt);
    localAnimationSet.addAnimation(localAlphaAnimation);
    setAnimation(localAnimationSet);
  }

  public PromptContent setQuote(PromptContent paramPromptContent)
  {
    paramPromptContent.ex1 = ("\"" + paramPromptContent.ex1 + "\"");
    paramPromptContent.ex2 = ("\"" + paramPromptContent.ex2 + "\"");
    paramPromptContent.ex3 = ("\"" + paramPromptContent.ex3 + "\"");
    paramPromptContent.ex4 = ("\"" + paramPromptContent.ex4 + "\"");
    paramPromptContent.ex5 = ("\"" + paramPromptContent.ex5 + "\"");
    paramPromptContent.ex6 = ("\"" + paramPromptContent.ex6 + "\"");
    paramPromptContent.ex7 = ("\"" + paramPromptContent.ex7 + "\"");
    return paramPromptContent;
  }

  public static enum BubbleType
  {
    static
    {
      ERROR = new BubbleType("ERROR", 2);
      WARNING = new BubbleType("WARNING", 3);
      BubbleType[] arrayOfBubbleType = new BubbleType[4];
      arrayOfBubbleType[0] = USER;
      arrayOfBubbleType[1] = VLINGO;
      arrayOfBubbleType[2] = ERROR;
      arrayOfBubbleType[3] = WARNING;
      $VALUES = arrayOfBubbleType;
    }
  }

  private class SpanArea
  {
    private int end;
    private int start;

    SpanArea(int paramInt1, int arg3)
    {
      this.start = paramInt1;
      int i;
      this.end = i;
    }

    int getEnd()
    {
      return this.end;
    }

    int getStart()
    {
      return this.start;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DialogHelpScreen
 * JD-Core Version:    0.6.0
 */