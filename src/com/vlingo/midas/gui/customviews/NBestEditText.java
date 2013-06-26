package com.vlingo.midas.gui.customviews;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import com.vlingo.sdk.recognition.NBestData;
import java.util.List;

public class NBestEditText extends EditText
  implements View.OnLongClickListener, AdapterView.OnItemClickListener
{
  private List<String> choices = null;
  private NBestData nbest;
  private ListPopupWindow popup = null;

  public NBestEditText(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public NBestEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public NBestEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    setOnLongClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((this.choices == null) || (this.popup == null));
    while (true)
    {
      return;
      String str = (String)this.choices.get(paramInt);
      int i = getSelectionStart();
      int j = getSelectionEnd();
      getText().replace(Math.min(i, j), Math.max(i, j), str, 0, str.length());
      this.popup.dismiss();
    }
  }

  public boolean onLongClick(View paramView)
  {
    int i = 0;
    int j = getSelectionStart();
    int k = getSelectionEnd();
    Editable localEditable = getText();
    if (localEditable == null);
    while (true)
    {
      return i;
      if (this.nbest == null)
        continue;
      this.choices = this.nbest.getWordChoices(localEditable.toString(), j, k);
      if ((this.choices == null) || (this.choices.size() == 0))
        continue;
      this.popup = new ListPopupWindow(getContext());
      this.popup.setAnchorView(this);
      this.popup.setAdapter(new ArrayAdapter(getContext(), 17367043, 16908308, this.choices));
      this.popup.setOnItemClickListener(this);
      this.popup.show();
      i = 1;
    }
  }

  public void setNbest(NBestData paramNBestData)
  {
    this.nbest = paramNBestData;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.NBestEditText
 * JD-Core Version:    0.6.0
 */