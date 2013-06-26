package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.questions.Answer;
import com.vlingo.core.internal.questions.Answer.Section;
import com.vlingo.core.internal.questions.Answer.Subsection;
import com.vlingo.core.internal.questions.DownloadableImage;
import com.vlingo.core.internal.questions.DownloadableImage.Listener;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.midas.gui.Widget;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AnswerQuestionWidget extends Widget<Answer>
{
  private static int density;
  private LinearLayout mContentLayout;
  private Context mContext;
  private int yValue = 0;

  public AnswerQuestionWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private View createImageContent(Answer.Subsection paramSubsection, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (isUsingOverlays());
    for (View localView = createImageContentWithOverlays(paramSubsection, paramLayoutParams); ; localView = createImageContentWithoutOverlays(paramSubsection, paramLayoutParams))
      return localView;
  }

  private View createImageContentWithOverlays(Answer.Subsection paramSubsection, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.yValue += paramSubsection.getHeight();
    RelativeLayout localRelativeLayout = new RelativeLayout(getContext());
    localRelativeLayout.setLayoutParams(paramLayoutParams);
    localRelativeLayout.setPadding(0, 0, 0, 0);
    localRelativeLayout.setGravity(17);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.setMargins(0, 0, 0, 0);
    localLayoutParams.addRule(13, -1);
    RenderedImage localRenderedImage = new RenderedImage(getContext());
    localRenderedImage.setLayoutParams(localLayoutParams);
    localRenderedImage.setAdjustViewBounds(true);
    localRenderedImage.setPadding(0, 0, 0, 0);
    localRenderedImage.setFocusable(false);
    localRenderedImage.requestLayout();
    localRelativeLayout.addView(localRenderedImage);
    if (!paramSubsection.getImage().isDownloaded())
    {
      ProgressBar localProgressBar = new ProgressBar(getContext());
      localRelativeLayout.addView(localProgressBar);
      localRenderedImage.setImageBitmap(Bitmap.createBitmap((1 + paramSubsection.getWidth()) / density, (1 + paramSubsection.getHeight()) / density, Bitmap.Config.ALPHA_8));
      int i = 1000 * Integer.parseInt(Settings.getString("wa.download.timeout", "30"));
      Timer localTimer = new Timer("AnswerQuestionWidget:DownloadTimer");
      localTimer.schedule(new TimerTask(localTimer, paramSubsection)
      {
        public void run()
        {
          this.val$downloadTimer.cancel();
          this.val$subsection.getImage().timedOut(true);
        }
      }
      , i);
      paramSubsection.getImage().addListener(new DownloadableImage.Listener(localTimer, localProgressBar, localRenderedImage)
      {
        private void updateUI(DownloadableImage paramDownloadableImage, boolean paramBoolean)
        {
          this.val$downloadTimer.cancel();
          paramDownloadableImage.removeListener(this);
          new Handler(AnswerQuestionWidget.this.getContext().getMainLooper()).post(new AnswerQuestionWidget.3.1(this, paramBoolean, paramDownloadableImage));
        }

        public void onDownloaded(DownloadableImage paramDownloadableImage)
        {
          updateUI(paramDownloadableImage, true);
        }

        public void onTimeout(DownloadableImage paramDownloadableImage)
        {
          updateUI(paramDownloadableImage, false);
        }
      });
    }
    while (true)
    {
      return localRelativeLayout;
      localRenderedImage.setImageDrawable(paramSubsection.getImage().getDrawable());
    }
  }

  private View createImageContentWithoutOverlays(Answer.Subsection paramSubsection, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.yValue += paramSubsection.getHeight();
    RenderedImage localRenderedImage = new RenderedImage(getContext());
    localRenderedImage.setLayoutParams(paramLayoutParams);
    localRenderedImage.setPadding(0, 0, 0, 0);
    localRenderedImage.setFocusable(false);
    localRenderedImage.requestLayout();
    Object localObject = localRenderedImage;
    if (!paramSubsection.getImage().isDownloaded())
    {
      ProgressBar localProgressBar = new ProgressBar(getContext());
      localRenderedImage.setVisibility(8);
      paramSubsection.getImage().addListener(new DownloadableImage.Listener(localRenderedImage, localProgressBar)
      {
        public void onDownloaded(DownloadableImage paramDownloadableImage)
        {
          paramDownloadableImage.removeListener(this);
          Drawable localDrawable = paramDownloadableImage.getDrawable();
          new Handler(AnswerQuestionWidget.this.getContext().getMainLooper()).post(new AnswerQuestionWidget.1.1(this, localDrawable));
        }

        public void onTimeout(DownloadableImage paramDownloadableImage)
        {
        }
      });
      getContentLayout().addView(localRenderedImage);
      localObject = localProgressBar;
    }
    while (true)
    {
      return localObject;
      localRenderedImage.setImageDrawable(paramSubsection.getImage().getDrawable());
    }
  }

  private void createSection(Answer.Section paramSection, LinearLayout.LayoutParams paramLayoutParams)
  {
    getContentLayout().addView(createSectionBanner(paramSection, paramLayoutParams));
    populateSection(paramSection, paramLayoutParams);
  }

  private TextView createSectionBanner(Answer.Section paramSection, LinearLayout.LayoutParams paramLayoutParams)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramSection.getName());
    localTextView.setTextColor(getResources().getColor(2131230720));
    localTextView.setTextSize(15.0F);
    localTextView.setMinimumHeight(24 * density);
    localTextView.setBackgroundResource(2130838184);
    this.yValue += localTextView.getHeight();
    localTextView.setPadding(12 * density, 0, 0, 0);
    localTextView.setLayoutParams(paramLayoutParams);
    localTextView.requestLayout();
    return localTextView;
  }

  private TextView createTextualContent(Answer.Subsection paramSubsection, ViewGroup.LayoutParams paramLayoutParams)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramSubsection.getText());
    localTextView.setTextColor(getResources().getColor(2131230727));
    localTextView.setTextSize(15.0F);
    this.yValue += localTextView.getHeight();
    localTextView.setPadding(0, 0, 0, 0);
    localTextView.requestLayout();
    return localTextView;
  }

  private boolean isUsingOverlays()
  {
    return Settings.getBoolean("wa.image.overlays", true);
  }

  private void populateSection(Answer.Section paramSection, ViewGroup.LayoutParams paramLayoutParams)
  {
    Answer.Subsection[] arrayOfSubsection = paramSection.getSubsections();
    int i = arrayOfSubsection.length;
    int j = 0;
    if (j < i)
    {
      Answer.Subsection localSubsection = arrayOfSubsection[j];
      if (localSubsection.hasImage())
        getContentLayout().addView(createImageContent(localSubsection, paramLayoutParams));
      while (true)
      {
        j++;
        break;
        if (StringUtils.isNullOrWhiteSpace(localSubsection.getText()))
          continue;
        getContentLayout().addView(createTextualContent(localSubsection, paramLayoutParams));
      }
    }
  }

  protected LinearLayout getContentLayout()
  {
    return this.mContentLayout;
  }

  protected abstract int getLayoutID();

  public void initialize(Answer paramAnswer, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins(0, 15 * density, 0, 15 * density);
    getContentLayout().setOrientation(1);
    getContentLayout().setGravity(3);
    Answer.Section[] arrayOfSection1 = paramAnswer.getInformationalSections();
    int i = arrayOfSection1.length;
    for (int j = 0; ; j++)
    {
      if (j < i)
      {
        Answer.Section localSection2 = arrayOfSection1[j];
        if ((!localSection2.getName().equals("Image")) && (!localSection2.getName().equals("Input interpretation")))
          continue;
        createSection(localSection2, localLayoutParams);
        if (!localSection2.getName().equals("Image"))
          continue;
      }
      for (Answer.Section localSection1 : paramAnswer.getInformationalSections())
      {
        if ((localSection1.getName().equals("Image")) || (localSection1.getName().equals("Input interpretation")))
          continue;
        createSection(localSection1, localLayoutParams);
      }
    }
  }

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mContentLayout = ((LinearLayout)findViewById(getLayoutID()));
    density = this.mContext.getResources().getDisplayMetrics().densityDpi / 160;
  }

  protected static class RenderedImage extends ImageView
  {
    public RenderedImage(Context paramContext)
    {
      super();
      init();
    }

    public RenderedImage(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      init();
    }

    public RenderedImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
      init();
    }

    private void init()
    {
    }

    protected void onDraw(Canvas paramCanvas)
    {
      if (paramCanvas.isHardwareAccelerated())
      {
        Drawable localDrawable = getDrawable();
        if ((localDrawable instanceof BitmapDrawable))
        {
          int i = 0;
          Bitmap localBitmap = ((BitmapDrawable)localDrawable).getBitmap();
          int j = localBitmap.getHeight();
          int k = localBitmap.getWidth();
          if (localBitmap.getHeight() > paramCanvas.getMaximumBitmapHeight())
          {
            j = paramCanvas.getMaximumBitmapHeight();
            i = 1;
          }
          if (localBitmap.getWidth() > paramCanvas.getMaximumBitmapWidth())
          {
            k = paramCanvas.getMaximumBitmapWidth();
            i = 1;
          }
          if (i != 0)
          {
            float f1 = k / localBitmap.getWidth();
            float f2 = j / localBitmap.getHeight();
            Matrix localMatrix = new Matrix();
            localMatrix.postScale(f1, f2);
            setImageDrawable(new BitmapDrawable(Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true)));
          }
        }
      }
      super.onDraw(paramCanvas);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.AnswerQuestionWidget
 * JD-Core Version:    0.6.0
 */