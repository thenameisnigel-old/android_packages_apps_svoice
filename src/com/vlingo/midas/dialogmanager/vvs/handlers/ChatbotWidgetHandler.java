package com.vlingo.midas.dialogmanager.vvs.handlers;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.vcs.WidgetResponseReceivedListener;
import com.vlingo.midas.dialogmanager.actions.PlayOutNewsMultiAction;
import com.vlingo.midas.news.NewsItem;
import com.vlingo.midas.news.NewsManager;
import com.vlingo.midas.tss.TtsUtil;
import com.vlingo.sdk.recognition.VLAction;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ChatbotWidgetHandler extends VVSActionHandler
  implements WidgetResponseReceivedListener
{
  private final String ACTION_TYPE;
  private final String XML_PREFIX;
  private String errorPrompt;
  Handler handler = null;
  NewsManager newsManager = NewsManager.getInstance();
  private String prompt;
  private String xmlResponse;

  public ChatbotWidgetHandler()
  {
    this.newsManager.setWidgetListener(this);
    this.handler = new Handler(Looper.getMainLooper());
    this.XML_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    this.xmlResponse = null;
    this.ACTION_TYPE = "action_type";
    this.prompt = null;
    this.errorPrompt = null;
  }

  private NewsCommand getNewsCommand(String paramString)
  {
    NewsCommand localNewsCommand;
    if ((paramString != null) && (!paramString.isEmpty()))
      if (paramString.contains("news:read"))
        localNewsCommand = NewsCommand.Read;
    while (true)
    {
      return localNewsCommand;
      if (paramString.contains("news:previous"))
      {
        localNewsCommand = NewsCommand.Prev;
        continue;
      }
      if (paramString.contains("news:next"))
      {
        localNewsCommand = NewsCommand.Next;
        continue;
      }
      if (paramString.contains("news:stop"))
      {
        localNewsCommand = NewsCommand.Stop;
        continue;
      }
      localNewsCommand = NewsCommand.None;
    }
  }

  private boolean isNewsActionType(String paramString)
  {
    return isNewsActionTypeFaked(paramString);
  }

  private boolean isNewsActionTypeFaked(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("<custom_action><action_type>news:")));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isNewsActionTypeFull(String paramString)
  {
    String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + paramString;
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(str);
      NodeList localNodeList = localDocument.getDocumentElement().getElementsByTagName("action_type");
      if (localNodeList.getLength() > 0)
        localNodeList.item(0);
      return false;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
        localIllegalStateException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  private void playOutNews(String paramString)
  {
    if (paramString != null)
      paramString = paramString.replaceAll("­", "");
    ArrayList localArrayList = TtsUtil.getTtsArray(paramString);
    PlayOutNewsMultiAction localPlayOutNewsMultiAction = (PlayOutNewsMultiAction)getAction(DMActionType.PLAY_OUT_NEWS_MULTI, PlayOutNewsMultiAction.class);
    if (localPlayOutNewsMultiAction != null)
    {
      localPlayOutNewsMultiAction.tts(localArrayList);
      localPlayOutNewsMultiAction.queue();
    }
    while (true)
    {
      return;
      getListener().asyncHandlerDone();
    }
  }

  public void actionFail(String paramString)
  {
    getListener().asyncHandlerDone();
    super.actionFail(paramString);
  }

  public void actionSuccess()
  {
    getListener().asyncHandlerDone();
    super.actionSuccess();
    this.newsManager.setWidgetListener(null);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("chatbot");
    this.xmlResponse = VLActionUtil.getParamString(paramVLAction, "xmlresponse", false);
    Log.d("VoiceNews", "xmlresponse='" + this.xmlResponse + "'");
    NewsManager localNewsManager;
    Context localContext;
    int i;
    if (isNewsActionType(this.xmlResponse))
    {
      localNewsManager = NewsManager.getInstance();
      NewsCommand localNewsCommand = getNewsCommand(this.xmlResponse);
      localContext = paramVVSActionHandlerListener.getActivityContext();
      NewsItem localNewsItem = null;
      this.errorPrompt = localContext.getResources().getString(2131362766);
      if (!localNewsManager.isAvailable())
        break label557;
      Resources localResources;
      if (localNewsCommand == NewsCommand.Read)
      {
        localNewsItem = localNewsManager.getCurrentNews();
        if (localNewsItem != null)
        {
          localResources = localContext.getResources();
          if (localNewsItem.getNewsCP() == 1)
          {
            String str3 = localResources.getString(2131362760);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = localResources.getString(2131362797);
            this.prompt = String.format(str3, arrayOfObject2);
          }
        }
        else
        {
          if ((localNewsItem == null) || (localNewsItem.getTitle() == null) || (localNewsItem.getText() == null))
            break label497;
          paramVVSActionHandlerListener.showVlingoTextAndTTS(this.prompt, this.prompt);
          getListener().showWidget(WidgetUtil.WidgetKey.DriveNewsWidget, null, localNewsItem, null);
          paramVVSActionHandlerListener.ttsAnyway(localNewsItem.getTitle());
          playOutNews(localNewsItem.getText());
        }
      }
      for (i = 1; ; i = 0)
      {
        return i;
        String str2 = localResources.getString(2131362760);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localResources.getString(2131362802);
        this.prompt = String.format(str2, arrayOfObject1);
        break;
        if (localNewsCommand == NewsCommand.Next)
        {
          localNewsItem = localNewsManager.getNextNews();
          if (localNewsItem != null)
            localNewsManager.updateCurrent(1);
          this.prompt = localContext.getResources().getString(2131362761);
          if (!localNewsManager.isHavingNewsCache())
            break;
          this.errorPrompt = localContext.getResources().getString(2131362769);
          break;
        }
        if (localNewsCommand == NewsCommand.Prev)
        {
          localNewsItem = localNewsManager.getPrevNews();
          if (localNewsItem != null)
            localNewsManager.updateCurrent(-1);
          this.prompt = localContext.getResources().getString(2131362762);
          if (!localNewsManager.isHavingNewsCache())
            break;
          this.errorPrompt = localContext.getResources().getString(2131362768);
          break;
        }
        if (localNewsCommand != NewsCommand.Stop)
          break;
        this.prompt = localContext.getResources().getString(2131362770);
        paramVVSActionHandlerListener.showVlingoTextAndTTS(this.prompt, this.prompt);
      }
      label497: if ((localNewsItem != null) || ((localNewsCommand != NewsCommand.Prev) && (localNewsCommand != NewsCommand.Next)) || (this.errorPrompt == null))
        break label545;
      paramVVSActionHandlerListener.showVlingoTextAndTTS(this.errorPrompt, this.errorPrompt);
    }
    while (true)
    {
      i = 0;
      break;
      label545: localNewsManager.fetchNews();
      i = 1;
      break;
      label557: String str1 = localContext.getResources().getString(2131362766);
      paramVVSActionHandlerListener.showVlingoTextAndTTS(str1, str1);
    }
  }

  public void onRequestFailed()
  {
    this.newsManager.setWidgetListener(null);
    getListener().showVlingoTextAndTTS(this.errorPrompt, this.errorPrompt);
    getListener().asyncHandlerDone();
  }

  public void onRequestScheduled()
  {
    getListener().asyncHandlerDone();
  }

  public void onResponseReceived()
  {
    this.newsManager.setWidgetListener(null);
    this.handler.post(new Runnable()
    {
      public void run()
      {
        NewsItem localNewsItem = ChatbotWidgetHandler.this.newsManager.getCurrentNews();
        Resources localResources;
        if ((localNewsItem != null) && (localNewsItem.getText() != null) && (localNewsItem.getTitle() != null))
          if (ChatbotWidgetHandler.this.prompt == null)
          {
            localResources = ChatbotWidgetHandler.this.getListener().getActivityContext().getResources();
            if (localNewsItem.getNewsCP() == 1)
            {
              ChatbotWidgetHandler localChatbotWidgetHandler2 = ChatbotWidgetHandler.this;
              String str2 = localResources.getString(2131362760);
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = localResources.getString(2131362797);
              ChatbotWidgetHandler.access$002(localChatbotWidgetHandler2, String.format(str2, arrayOfObject2));
            }
          }
          else
          {
            ChatbotWidgetHandler.this.getListener().showVlingoTextAndTTS(ChatbotWidgetHandler.this.prompt, ChatbotWidgetHandler.this.prompt);
            ChatbotWidgetHandler.this.getListener().showWidget(WidgetUtil.WidgetKey.DriveNewsWidget, null, localNewsItem, null);
            ChatbotWidgetHandler.this.getListener().ttsAnyway(localNewsItem.getTitle());
            ChatbotWidgetHandler.this.playOutNews(localNewsItem.getText());
          }
        while (true)
        {
          return;
          ChatbotWidgetHandler localChatbotWidgetHandler1 = ChatbotWidgetHandler.this;
          String str1 = localResources.getString(2131362760);
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localResources.getString(2131362802);
          ChatbotWidgetHandler.access$002(localChatbotWidgetHandler1, String.format(str1, arrayOfObject1));
          break;
          ChatbotWidgetHandler.this.getListener().showVlingoTextAndTTS(ChatbotWidgetHandler.this.errorPrompt, ChatbotWidgetHandler.this.errorPrompt);
          ChatbotWidgetHandler.this.getListener().asyncHandlerDone();
        }
      }
    });
  }

  private static enum NewsCommand
  {
    static
    {
      Next = new NewsCommand("Next", 2);
      Prev = new NewsCommand("Prev", 3);
      Stop = new NewsCommand("Stop", 4);
      NewsCommand[] arrayOfNewsCommand = new NewsCommand[5];
      arrayOfNewsCommand[0] = None;
      arrayOfNewsCommand[1] = Read;
      arrayOfNewsCommand[2] = Next;
      arrayOfNewsCommand[3] = Prev;
      arrayOfNewsCommand[4] = Stop;
      $VALUES = arrayOfNewsCommand;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.dialogmanager.vvs.handlers.ChatbotWidgetHandler
 * JD-Core Version:    0.6.0
 */