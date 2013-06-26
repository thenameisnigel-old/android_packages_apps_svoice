package com.vlingo.midas.gui.widgets;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.provider.MediaStore.Images.Media;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.music.MusicDetails;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayMusicWidget extends BargeInWidget<List<MusicDetails>>
  implements AdapterView.OnItemClickListener
{
  private static HashMap<Integer, Bitmap> mHashMap = new HashMap(getMultiWidgetItemsInitialMax());
  private int COUNTER_HASHMAP_ENTRY = 0;
  private int FIRST_TIME = 1;
  private int VALUE_COUNTER_HASHMAP = 0;
  private final Context context;
  private WidgetActionListener listener;
  private ListView mListView;

  public PlayMusicWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void initialize(List<MusicDetails> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    this.listener = paramWidgetActionListener;
    if (paramList == null);
    while (true)
    {
      return;
      this.mListView.setAdapter(new MusicBaseAdapter(this.context, new ArrayList(paramList)));
      this.mListView.setOnItemClickListener(this);
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
    this.mListView = ((ListView)findViewById(2131558893));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mListView);
    super.onMeasure(paramInt1, paramInt2);
  }

  private class AudioHolder
  {
    TextView audioArtist;
    String audioID;
    ImageView audioImage;
    TextView audioTitle;

    private AudioHolder()
    {
    }
  }

  private class MusicBaseAdapter extends BaseAdapter
  {
    private List<MusicDetails> arrayMusic;
    private LayoutInflater layoutInflater;

    public MusicBaseAdapter(List<MusicDetails> arg2)
    {
      Context localContext;
      this.layoutInflater = LayoutInflater.from(localContext);
      Object localObject;
      this.arrayMusic = localObject;
    }

    private void imageBitmapProcessing(PlayMusicWidget.AudioHolder paramAudioHolder, int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (PlayMusicWidget.this.COUNTER_HASHMAP_ENTRY > 5)
        PlayMusicWidget.access$302(PlayMusicWidget.this, 0);
      try
      {
        InputStream localInputStream1 = PlayMusicWidget.this.context.getApplicationContext().getContentResolver().openInputStream(((MusicDetails)this.arrayMusic.get(paramInt)).getMusicThumbnail());
        BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
        localOptions1.inJustDecodeBounds = true;
        localOptions1.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeStream(localInputStream1, null, localOptions1);
        localInputStream1.close();
        int i = localOptions1.outWidth;
        int j = localOptions1.outHeight;
        InputStream localInputStream2 = PlayMusicWidget.this.context.getContentResolver().openInputStream(((MusicDetails)this.arrayMusic.get(paramInt)).getMusicThumbnail());
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = Math.max(i / 100, j / 100);
        Bitmap localBitmap1 = BitmapFactory.decodeStream(localInputStream2, null, localOptions2);
        if (localBitmap1 != null)
        {
          Matrix localMatrix = new Matrix();
          localMatrix.setRectToRect(new RectF(0.0F, 0.0F, localBitmap1.getWidth(), localBitmap1.getHeight()), new RectF(0.0F, 0.0F, 110.0F, 110.0F), Matrix.ScaleToFit.CENTER);
          float[] arrayOfFloat = new float[9];
          localMatrix.getValues(arrayOfFloat);
          localBitmap2 = null;
          if ((PlayMusicWidget.mHashMap != null) && ((!PlayMusicWidget.mHashMap.containsValue(null)) || (PlayMusicWidget.this.FIRST_TIME == 1)))
          {
            localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, (int)(localBitmap1.getWidth() * arrayOfFloat[0]), (int)(localBitmap1.getHeight() * arrayOfFloat[4]), true);
            if (localBitmap2 != null)
            {
              PlayMusicWidget.mHashMap.put(Integer.valueOf(PlayMusicWidget.this.COUNTER_HASHMAP_ENTRY), localBitmap2);
              PlayMusicWidget.access$602(PlayMusicWidget.this, PlayMusicWidget.this.COUNTER_HASHMAP_ENTRY);
              PlayMusicWidget.access$308(PlayMusicWidget.this);
            }
            PlayMusicWidget.access$508(PlayMusicWidget.this);
          }
          localBitmap1.recycle();
        }
      }
      catch (IOException localIOException)
      {
        try
        {
          Bitmap localBitmap2;
          if ((PlayMusicWidget.mHashMap != null) && (PlayMusicWidget.mHashMap.containsValue(localBitmap2)))
            paramAudioHolder.audioImage.setImageBitmap((Bitmap)PlayMusicWidget.mHashMap.get(Integer.valueOf(PlayMusicWidget.this.VALUE_COUNTER_HASHMAP)));
          while (true)
          {
            label408: return;
            localIOException = localIOException;
            paramAudioHolder.audioImage.setImageResource(2130837872);
          }
        }
        catch (Exception localException)
        {
          break label408;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          break label408;
        }
      }
    }

    public int getCount()
    {
      int i = this.arrayMusic.size();
      return PlayMusicWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.arrayMusic.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      PlayMusicWidget.AudioHolder localAudioHolder;
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130903113, null);
        localAudioHolder = new PlayMusicWidget.AudioHolder(PlayMusicWidget.this, null);
        localAudioHolder.audioImage = ((ImageView)paramView.findViewById(2131558808));
        localAudioHolder.audioTitle = ((TextView)paramView.findViewById(2131558809));
        localAudioHolder.audioArtist = ((TextView)paramView.findViewById(2131558810));
        paramView.setTag(localAudioHolder);
        paramView.setOnClickListener(new PlayMusicWidget.MusicBaseAdapter.1(this, paramInt));
      }
      try
      {
        while (true)
        {
          Bitmap localBitmap = MediaStore.Images.Media.getBitmap(PlayMusicWidget.this.context.getContentResolver(), ((MusicDetails)this.arrayMusic.get(paramInt)).getMusicThumbnail());
          localAudioHolder.audioImage.setImageBitmap(localBitmap);
          localAudioHolder.audioTitle.setText(((MusicDetails)this.arrayMusic.get(paramInt)).getTitle());
          localAudioHolder.audioArtist.setText(((MusicDetails)this.arrayMusic.get(paramInt)).getArtist());
          return paramView;
          localAudioHolder = (PlayMusicWidget.AudioHolder)paramView.getTag();
        }
      }
      catch (Exception localException)
      {
        while (true)
          localAudioHolder.audioImage.setImageResource(2130837872);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.PlayMusicWidget
 * JD-Core Version:    0.6.0
 */