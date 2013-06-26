package com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven;

import android.content.Context;
import com.vlingo.core.internal.dialogmanager.WebSearchButtonHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.util.ApplicationAdapter;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.WebSearchUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MidasWebSearchButtonHandler extends WebSearchButtonHandler
{
  private static final List<Integer> POSSIBLE_RANDOM_TEXTS;

  static
  {
    Integer[] arrayOfInteger = new Integer[6];
    arrayOfInteger[0] = Integer.valueOf(2131362701);
    arrayOfInteger[1] = Integer.valueOf(2131362702);
    arrayOfInteger[2] = Integer.valueOf(2131362703);
    arrayOfInteger[3] = Integer.valueOf(2131362704);
    arrayOfInteger[4] = Integer.valueOf(2131362705);
    arrayOfInteger[5] = Integer.valueOf(2131362706);
    POSSIBLE_RANDOM_TEXTS = Arrays.asList(arrayOfInteger);
  }

  private String getRandomAnswer(String paramString)
  {
    Context localContext = ApplicationAdapter.getInstance().getApplicationContext();
    int i = ((Integer)POSSIBLE_RANDOM_TEXTS.get(new Random().nextInt(POSSIBLE_RANDOM_TEXTS.size()))).intValue();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    return localContext.getString(i, arrayOfObject);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    if ("true".equals(paramVLAction.getParamValue("RandomText")))
    {
      String str = paramVLAction.getParamValue("Query");
      getListener().showVlingoTextAndTTS(getRandomAnswer(str), getRandomAnswer(str));
    }
    return super.executeAction(paramVLAction, paramVVSActionHandlerListener);
  }

  protected String getLabel()
  {
    if ((ClientSuppliedValues.handleNoResultAnswersWithGoogleSearch()) && (WebSearchUtils.isDefaultGoogleSearch()));
    for (String str = getListener().getActivityContext().getString(2131362700); ; str = super.getLabel())
      return str;
  }

  protected void handleSearch(String paramString)
  {
    if ((ClientSuppliedValues.handleNoResultAnswersWithGoogleSearch()) && (WebSearchUtils.isDefaultGoogleSearch()))
      WebSearchUtils.googleNowSearch(paramString);
    while (true)
    {
      return;
      super.handleSearch(paramString);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.clientdriven.MidasWebSearchButtonHandler
 * JD-Core Version:    0.6.0
 */