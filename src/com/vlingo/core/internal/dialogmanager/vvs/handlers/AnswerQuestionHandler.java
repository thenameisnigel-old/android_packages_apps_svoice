package com.vlingo.core.internal.dialogmanager.vvs.handlers;

import android.content.Intent;
import android.os.Handler;
import com.vlingo.core.internal.ResourceIdProvider.string;
import com.vlingo.core.internal.answers.AnswerManager;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.DialogDataType;
import com.vlingo.core.internal.dialogmanager.DialogTurn;
import com.vlingo.core.internal.dialogmanager.actions.LaunchActivityAction;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.util.WidgetUtil.WidgetKey;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionBase.UnifiedPrompter;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.DefaultWebSearchHandler;
import com.vlingo.core.internal.dialogmanager.vvs.handlers.clientdriven.WebSearchHandler;
import com.vlingo.core.internal.questions.Answer;
import com.vlingo.core.internal.questions.TrueKnowledgeAnswer;
import com.vlingo.core.internal.questions.TrueKnowledgeAnswer.Type;
import com.vlingo.core.internal.questions.WolframAlphaAnswer;
import com.vlingo.core.internal.questions.parser.AnswerParser;
import com.vlingo.core.internal.questions.parser.ProviderResponse;
import com.vlingo.core.internal.questions.parser.ServerResponse;
import com.vlingo.core.internal.userlogging.UserLoggingEngine;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.core.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLAction;
import java.lang.ref.WeakReference;
import java.util.Random;

public class AnswerQuestionHandler extends VVSActionHandler
{
  private static final int NUMBER_OF_ANSWER = 5;
  private static Handler mHandler;
  static String moreButtonText;
  static String sAnswerPrompt;

  private Handler getHandler()
  {
    if (mHandler == null);
    for (Handler localHandler = new Handler(); ; localHandler = mHandler)
    {
      mHandler = localHandler;
      return localHandler;
    }
  }

  private String getRandomAnswer()
  {
    String str;
    switch (new Random().nextInt(5))
    {
    default:
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH, new Object[0]);
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return str;
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH, new Object[0]);
      continue;
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_1, new Object[0]);
      continue;
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_2, new Object[0]);
      continue;
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_3, new Object[0]);
      continue;
      str = getString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_4, new Object[0]);
    }
  }

  private void handleWithGoogleNow(String paramString)
  {
    ((LaunchActivityAction)getAction(DMActionType.LAUNCH_ACTIVITY, LaunchActivityAction.class)).enclosingPackage("com.google.android.googlequicksearchbox").activity("com.google.android.googlequicksearchbox.SearchActivity").extra("query," + paramString).action("android.intent.action.WEB_SEARCH").app("Google Search").queue();
  }

  private void processLocally(ServerResponse paramServerResponse)
  {
    String str = AnswerManager.replaceAnswerVariables(paramServerResponse.getAnswer());
    unified().showSystemTurn(str);
  }

  public boolean executeAction(VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    super.executeAction(paramVLAction, paramVVSActionHandlerListener);
    UserLoggingEngine.getInstance().landingPageViewed("qanda");
    moreButtonText = getString(ResourceIdProvider.string.core_qa_more, new Object[0]);
    sAnswerPrompt = getString(ResourceIdProvider.string.core_answer_prompt, new Object[0]);
    ServerResponse localServerResponse = new ServerResponse();
    localServerResponse.setAnswer(paramVLAction.getParamValue("Answer"));
    localServerResponse.setQuestion(paramVLAction.getParamValue("Question"));
    localServerResponse.setXML(VLActionUtil.getParamString(paramVLAction, "ProviderSrcResponses", false));
    if (StringUtils.isNullOrWhiteSpace(localServerResponse.getXML()))
      processLocally(localServerResponse);
    new ParsingThread(this, localServerResponse, getHandler(), paramVVSActionHandlerListener).start();
    return true;
  }

  public class NoAnswer extends AnswerQuestionHandler.Reaction<Answer>
    implements Runnable, WidgetActionListener
  {
    private String question;

    public NoAnswer(String paramVVSActionHandlerListener, VVSActionHandlerListener paramDialogTurn, DialogTurn arg4)
    {
      super(paramDialogTurn, localDialogTurn);
      this.question = paramVVSActionHandlerListener;
    }

    public void handleIntent(Intent paramIntent, Object paramObject)
      throws IllegalArgumentException
    {
      if (StringUtils.isNullOrWhiteSpace(this.question))
        this.question = this.turn.getUserText();
      DefaultWebSearchHandler localDefaultWebSearchHandler = new DefaultWebSearchHandler();
      localDefaultWebSearchHandler.setListener(getListener());
      localDefaultWebSearchHandler.executeSearchIntentFromURL(localDefaultWebSearchHandler.getWebSearchURL(this.question));
      AnswerQuestionHandler.this.reset();
    }

    public void run()
    {
      if (this.turn.isCancelled());
      while (true)
      {
        return;
        if (!StringUtils.isNullOrWhiteSpace(this.question))
          getListener().storeState(DialogDataType.CURRENT_ACTION, this.question);
        if (ClientSuppliedValues.handleNoResultAnswersWithGoogleSearch())
        {
          ResourceIdProvider.string localstring = ResourceIdProvider.string.core_tts_NO_ANS_GOOGLE_NOW_SEARCH;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = this.question;
          String str3 = AnswerQuestionHandler.access$500(localstring, arrayOfObject);
          AnswerQuestionHandler.this.unified().showSystemTurn(str3);
          AnswerQuestionHandler.this.handleWithGoogleNow(this.question);
          continue;
        }
        String str1 = AnswerQuestionHandler.this.getRandomAnswer();
        getListener().showVlingoTextAndTTS(str1, str1);
        String str2 = AnswerQuestionHandler.access$900(ResourceIdProvider.string.core_search_web_label_button, new Object[0]);
        getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, str2, this);
      }
    }
  }

  public static class ParsingThread extends Thread
  {
    protected ServerResponse mAnswer;
    protected WeakReference<AnswerQuestionHandler> mAnswerQuestionHandler;
    protected Handler mHandler;
    protected VVSActionHandlerListener mListener;

    public ParsingThread(AnswerQuestionHandler paramAnswerQuestionHandler, ServerResponse paramServerResponse, Handler paramHandler, VVSActionHandlerListener paramVVSActionHandlerListener)
    {
      super();
      this.mAnswerQuestionHandler = new WeakReference(paramAnswerQuestionHandler);
      this.mAnswer = paramServerResponse;
      this.mHandler = paramHandler;
      this.mListener = paramVVSActionHandlerListener;
    }

    private void process(AnswerQuestionHandler paramAnswerQuestionHandler, ProviderResponse paramProviderResponse)
    {
      paramAnswerQuestionHandler.getClass();
      Object localObject = new AnswerQuestionHandler.NoAnswer(paramAnswerQuestionHandler, this.mAnswer.getQuestion(), this.mListener, paramAnswerQuestionHandler.turn);
      String str = paramProviderResponse.getProvider();
      WolframAlphaAnswer localWolframAlphaAnswer;
      if ("WolframAlphaContentProvider".equals(str))
      {
        localWolframAlphaAnswer = new WolframAlphaAnswer(paramProviderResponse);
        if (localWolframAlphaAnswer.hasAnswer());
      }
      while (true)
      {
        this.mHandler.post((Runnable)localObject);
        return;
        if ((localWolframAlphaAnswer.hasSection("Result")) && (!StringUtils.isNullOrWhiteSpace(localWolframAlphaAnswer.getSimpleResponse())))
        {
          localObject = new AnswerQuestionHandler.WolframAlpha.ResultAnswer(localWolframAlphaAnswer, this.mListener, paramAnswerQuestionHandler.turn);
          continue;
        }
        if (!localWolframAlphaAnswer.hasMoreInformation())
          continue;
        localObject = new AnswerQuestionHandler.WolframAlpha.FullAnswer(localWolframAlphaAnswer, this.mListener, paramAnswerQuestionHandler.turn);
        continue;
        if (!"TrueKnowledgeContentProvider".equals(str))
          continue;
        TrueKnowledgeAnswer localTrueKnowledgeAnswer = new TrueKnowledgeAnswer(paramProviderResponse);
        if (!localTrueKnowledgeAnswer.hasAnswer())
          continue;
        if (localTrueKnowledgeAnswer.is(TrueKnowledgeAnswer.Type.SIMPLE_ANSWER))
        {
          localObject = new AnswerQuestionHandler.TrueKnowledge.SimpleAnswer(localTrueKnowledgeAnswer, this.mListener, paramAnswerQuestionHandler.turn);
          continue;
        }
        localObject = new AnswerQuestionHandler.TrueKnowledge.FullAnswer(localTrueKnowledgeAnswer, this.mListener, paramAnswerQuestionHandler.turn);
      }
    }

    public void run()
    {
      AnswerQuestionHandler localAnswerQuestionHandler = (AnswerQuestionHandler)this.mAnswerQuestionHandler.get();
      if (localAnswerQuestionHandler == null);
      while (true)
      {
        return;
        try
        {
          if (!StringUtils.isNullOrWhiteSpace(this.mAnswer.getXML()))
            for (ProviderResponse localProviderResponse : AnswerParser.parse(this.mAnswer.getXML()))
            {
              localProviderResponse.setAnswer(this.mAnswer);
              process(localAnswerQuestionHandler, localProviderResponse);
            }
          if (StringUtils.isNullOrWhiteSpace(this.mAnswer.getAnswer()))
            process(localAnswerQuestionHandler, new ProviderResponse("null"));
          this.mListener.asyncHandlerDone();
          this.mListener = null;
        }
        finally
        {
          this.mListener.asyncHandlerDone();
          this.mListener = null;
        }
      }
    }
  }

  public static class Reaction<T>
  {
    private T answer;
    private VVSActionHandlerListener listener;
    protected DialogTurn turn;

    public Reaction(T paramT, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
    {
      this.answer = paramT;
      this.listener = paramVVSActionHandlerListener;
      this.turn = paramDialogTurn;
    }

    public T getAnswer()
    {
      return this.answer;
    }

    public VVSActionHandlerListener getListener()
    {
      return this.listener;
    }
  }

  public static class TrueKnowledge
  {
    public static class FullAnswer extends AnswerQuestionHandler.Reaction<Answer>
      implements Runnable, WidgetActionListener
    {
      public FullAnswer(Answer paramAnswer, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
      {
        super(paramVVSActionHandlerListener, paramDialogTurn);
      }

      public void handleIntent(Intent paramIntent, Object paramObject)
        throws IllegalArgumentException
      {
        getListener().showWidget(WidgetUtil.WidgetKey.ShowTrueKnowledgeWidget, null, getAnswer(), null);
      }

      public void run()
      {
        if (this.turn.isCancelled());
        while (true)
        {
          return;
          getListener().showVlingoTextAndTTS(((Answer)getAnswer()).getSimpleResponse(), ((Answer)getAnswer()).getSimpleResponse());
          getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, AnswerQuestionHandler.moreButtonText, this);
        }
      }
    }

    public static class SimpleAnswer extends AnswerQuestionHandler.Reaction<Answer>
      implements Runnable
    {
      public SimpleAnswer(Answer paramAnswer, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
      {
        super(paramVVSActionHandlerListener, paramDialogTurn);
      }

      public void run()
      {
        if (this.turn.isCancelled());
        while (true)
        {
          return;
          getListener().showVlingoTextAndTTS(((Answer)getAnswer()).getSimpleResponse(), ((Answer)getAnswer()).getSimpleResponse());
        }
      }
    }
  }

  public static class WolframAlpha
  {
    public static class FullAnswer extends AnswerQuestionHandler.Reaction<Answer>
      implements Runnable
    {
      public FullAnswer(Answer paramAnswer, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
      {
        super(paramVVSActionHandlerListener, paramDialogTurn);
      }

      public void run()
      {
        if (this.turn.isCancelled());
        while (true)
        {
          return;
          getListener().showVlingoTextAndTTS(AnswerQuestionHandler.sAnswerPrompt, AnswerQuestionHandler.sAnswerPrompt);
          WolframAlphaAnswer localWolframAlphaAnswer = new WolframAlphaAnswer((Answer)getAnswer());
          getListener().showWidget(WidgetUtil.WidgetKey.ShowWolframWidget, null, localWolframAlphaAnswer, null);
        }
      }
    }

    public static class ResultAnswer extends AnswerQuestionHandler.Reaction<Answer>
      implements Runnable, WidgetActionListener
    {
      public ResultAnswer(Answer paramAnswer, VVSActionHandlerListener paramVVSActionHandlerListener, DialogTurn paramDialogTurn)
      {
        super(paramVVSActionHandlerListener, paramDialogTurn);
      }

      public void handleIntent(Intent paramIntent, Object paramObject)
        throws IllegalArgumentException
      {
        getListener().showWidget(WidgetUtil.WidgetKey.ShowWolframWidget, null, getAnswer(), null);
      }

      public void run()
      {
        if (this.turn.isCancelled());
        while (true)
        {
          return;
          String str = ((Answer)getAnswer()).getSimpleResponse();
          if (!StringUtils.isNullOrWhiteSpace(str))
          {
            getListener().showVlingoTextAndTTS(str, str);
            if (!((Answer)getAnswer()).hasMoreInformation())
              continue;
            getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, AnswerQuestionHandler.moreButtonText, this);
            continue;
          }
          if (!((Answer)getAnswer()).hasMoreInformation())
            continue;
          getListener().showWidget(WidgetUtil.WidgetKey.ShowButton, null, AnswerQuestionHandler.moreButtonText, this);
        }
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.AnswerQuestionHandler
 * JD-Core Version:    0.6.0
 */