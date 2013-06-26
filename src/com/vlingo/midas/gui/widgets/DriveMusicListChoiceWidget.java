package com.vlingo.midas.gui.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.vlingo.core.internal.dialogmanager.WidgetDecorator;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.midas.music.MusicDetails;
import com.vlingo.midas.settings.MidasSettings;
import com.vlingo.midas.util.DrivingWidgetInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DriveMusicListChoiceWidget extends BargeInWidget<List<MusicDetails>>
  implements AdapterView.OnItemClickListener, DrivingWidgetInterface
{
  private final Context context;
  private MusicBaseAdapter mAdapter;
  private ListView mListView;
  private boolean mMinimized = false;
  private View music_mainlayout;

  public DriveMusicListChoiceWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public int getDecreasedHeight()
  {
    if (getResources().getConfiguration().orientation == 1);
    for (int i = 750; ; i = 900)
      return i;
  }

  public int getProperHeight()
  {
    return 0;
  }

  public void initialize(List<MusicDetails> paramList, WidgetDecorator paramWidgetDecorator, WidgetUtil.WidgetKey paramWidgetKey, WidgetActionListener paramWidgetActionListener)
  {
    if (paramList == null);
    while (true)
    {
      return;
      this.music_mainlayout = findViewById(2131558931);
      new ArrayList();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        MusicDetails localMusicDetails = (MusicDetails)localIterator.next();
        if (localMusicDetails.getTitle() != null)
        {
          localArrayList.add(localMusicDetails.getTitle());
          continue;
        }
        localArrayList.add("");
      }
      this.mAdapter = new MusicBaseAdapter(this.context, paramList, localArrayList);
      if ((this.mAdapter != null) && (paramList != null))
        this.mListView.setAdapter(this.mAdapter);
      this.mListView.setDivider(null);
      this.mListView.setOnItemClickListener(this);
      this.mAdapter.notifyDataSetChanged();
    }
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

  public void onFinishInflate()
  {
    super.onFinishInflate();
    this.mListView = ((ListView)findViewById(2131558932));
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    setListViewHeightBasedOnChildren(this.mListView);
    super.onMeasure(paramInt1, paramInt2);
  }

  public int setNightMode(boolean paramBoolean)
  {
    return 0;
  }

  public int setWidgetToDecreasedSize(boolean paramBoolean)
  {
    this.mMinimized = paramBoolean;
    return 0;
  }

  public void startAnimationTranslate(View paramView)
  {
  }

  public class MusicBaseAdapter extends BaseAdapter
  {
    private final List<MusicDetails> albumInfo;

    public MusicBaseAdapter(List<MusicDetails> paramArrayList, ArrayList<String> arg3)
    {
      Object localObject;
      this.albumInfo = localObject;
    }

    public int getCount()
    {
      int i = this.albumInfo.size();
      return DriveMusicListChoiceWidget.this.getLimitedCount(i);
    }

    public Object getItem(int paramInt)
    {
      return this.albumInfo.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      String str1 = ((MusicDetails)this.albumInfo.get(paramInt)).getTitle();
      String str2 = ((MusicDetails)this.albumInfo.get(paramInt)).getArtist();
      DriveMusicListChoiceWidget.ViewHolder localViewHolder;
      if (paramView == null)
      {
        paramView = View.inflate(DriveMusicListChoiceWidget.this.getContext(), 2130903087, null);
        localViewHolder = new DriveMusicListChoiceWidget.ViewHolder(DriveMusicListChoiceWidget.this);
        localViewHolder.choice_container = ((LinearLayout)paramView.findViewById(2131558689));
        localViewHolder.title = ((TextView)paramView.findViewById(2131558692));
        localViewHolder.artist = ((TextView)paramView.findViewById(2131558703));
        localViewHolder.list_number = ((TextView)paramView.findViewById(2131558698));
        localViewHolder.divider = paramView.findViewById(2131558694);
        paramView.setTag(localViewHolder);
        localViewHolder.title.setText(str1);
        localViewHolder.artist.setText(str2);
        localViewHolder.list_number.setText(String.valueOf(paramInt + 1));
        if ((MidasSettings.isMultiwindowedMode()) && (paramInt > 2))
        {
          localViewHolder.choice_container.setVisibility(8);
          localViewHolder.divider.setVisibility(8);
        }
        if (!MidasSettings.isNightMode())
          break label308;
        DriveMusicListChoiceWidget.this.music_mainlayout.setBackgroundResource(2130837874);
        localViewHolder.title.setTextColor(Color.parseColor("#e0e0e0"));
        localViewHolder.artist.setTextColor(Color.parseColor("#1f83ad"));
        localViewHolder.divider.setBackgroundColor(-16777216);
        localViewHolder.list_number.setTextColor(Color.parseColor("#e0e0e0"));
        localViewHolder.list_number.setBackgroundResource(2130837883);
      }
      while (true)
      {
        paramView.setOnClickListener(new DriveMusicListChoiceWidget.MusicBaseAdapter.1(this));
        return paramView;
        localViewHolder = (DriveMusicListChoiceWidget.ViewHolder)paramView.getTag();
        break;
        label308: DriveMusicListChoiceWidget.this.music_mainlayout.setBackgroundResource(2130837590);
        localViewHolder.title.setTextColor(-16777216);
        localViewHolder.artist.setTextColor(Color.parseColor("#1f83ad"));
        localViewHolder.divider.setBackgroundColor(Color.parseColor("#8d8d8d"));
        localViewHolder.list_number.setTextColor(Color.parseColor("#2f2f2f"));
        localViewHolder.list_number.setBackgroundResource(2130837816);
      }
    }
  }

  class ViewHolder
  {
    TextView artist;
    LinearLayout choice_container;
    View divider;
    TextView list_number;
    TextView title;

    ViewHolder()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.widgets.DriveMusicListChoiceWidget
 * JD-Core Version:    0.6.0
 */