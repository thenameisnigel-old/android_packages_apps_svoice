package com.samsung.chatbot;

import java.io.File;

public class ChatbotFilter
{
  static final int CHATBOTFILTER_FAILURE = 1;
  static final int CHATBOTFILTER_SUCCEESS;

  private native int initializeChatbotFilter();

  private native int runChatbotFilter(short[] paramArrayOfShort, int paramInt1, int paramInt2);

  private native int shutdownChatbotFilter();

  public int initialize(String paramString)
  {
    System.load(paramString + File.separator + "libchatbotfilter.so");
    return initializeChatbotFilter();
  }

  public int run(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    return runChatbotFilter(paramArrayOfShort, paramInt1, paramInt2);
  }

  public int shutdown()
  {
    return shutdownChatbotFilter();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.chatbot.ChatbotFilter
 * JD-Core Version:    0.6.0
 */