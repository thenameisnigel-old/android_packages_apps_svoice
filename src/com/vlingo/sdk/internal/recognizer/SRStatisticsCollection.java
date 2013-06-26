package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.http.BaseHttpCallback;
import com.vlingo.sdk.internal.http.HttpUtil;
import java.util.Enumeration;
import java.util.Vector;

public class SRStatisticsCollection extends BaseHttpCallback
{
  private static final int SEND_WAIT_TIME = 10000;
  private String acceptedText;
  private final Vector<Object> collection = new Vector();

  public void addStatistics(SRStatistics paramSRStatistics)
  {
    if (paramSRStatistics == null);
    while (true)
    {
      return;
      int i = this.collection.size();
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label44;
        if (((SRStatisticsGroup)this.collection.elementAt(j)).mergeStatistics(paramSRStatistics))
          break;
      }
      label44: SRStatisticsGroup localSRStatisticsGroup = new SRStatisticsGroup(paramSRStatistics, null);
      this.collection.addElement(localSRStatisticsGroup);
    }
  }

  public String getAcceptedText()
  {
    return this.acceptedText;
  }

  public Enumeration<?> getCollectionElements()
  {
    return this.collection.elements();
  }

  public void onRetrysExhausted()
  {
  }

  public void schedule(SRServerDetails paramSRServerDetails, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
  {
    Enumeration localEnumeration = this.collection.elements();
    while (localEnumeration.hasMoreElements())
      ((SRStatisticsGroup)localEnumeration.nextElement()).schedule(paramSRServerDetails, paramClientMeta, paramSoftwareMeta);
  }

  public void setAcceptedText(String paramString)
  {
    this.acceptedText = paramString;
  }

  public static final class SRStatisticsGroup
  {
    private final Vector<Object> groupedStatistics = new Vector();
    private final String guttID;

    private SRStatisticsGroup(SRStatistics paramSRStatistics)
    {
      this.guttID = paramSRStatistics.getGuttId();
      this.groupedStatistics.addElement(paramSRStatistics);
    }

    private String getXML()
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("<Stats ");
      localStringBuffer.append(HttpUtil.genAtr("guttid", this.guttID));
      localStringBuffer.append(">");
      int i = this.groupedStatistics.size();
      for (int j = 0; j < i; j++)
        localStringBuffer.append(((SRStatistics)this.groupedStatistics.elementAt(j)).getXML(false));
      localStringBuffer.append("</Stats>");
      return localStringBuffer.toString();
    }

    private boolean mergeStatistics(SRStatistics paramSRStatistics)
    {
      if (this.guttID.equals(paramSRStatistics.getGuttId()))
        this.groupedStatistics.addElement(paramSRStatistics);
      for (int i = 1; ; i = 0)
        return i;
    }

    // ERROR //
    public void schedule(SRServerDetails paramSRServerDetails, ClientMeta paramClientMeta, SoftwareMeta paramSoftwareMeta)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 91	com/vlingo/sdk/internal/recognizer/SRStatisticsCollection$SRStatisticsGroup:getXML	()Ljava/lang/String;
      //   4: astore 6
      //   6: ldc 93
      //   8: new 95	com/vlingo/sdk/internal/http/BaseHttpCallback
      //   11: dup
      //   12: invokespecial 96	com/vlingo/sdk/internal/http/BaseHttpCallback:<init>	()V
      //   15: aload_1
      //   16: invokeinterface 102 1 0
      //   21: aload 6
      //   23: invokestatic 108	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:createVLRequest	(Ljava/lang/String;Lcom/vlingo/sdk/internal/http/HttpCallback;Lcom/vlingo/sdk/internal/http/URL;Ljava/lang/String;)Lcom/vlingo/sdk/internal/vlservice/VLHttpServiceRequest;
      //   26: astore 7
      //   28: aload 7
      //   30: aload_2
      //   31: invokevirtual 112	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:setClientMeta	(Lcom/vlingo/sdk/internal/recognizer/ClientMeta;)V
      //   34: aload 7
      //   36: aload_3
      //   37: invokevirtual 116	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:setSoftwareMeta	(Lcom/vlingo/sdk/internal/recognizer/SoftwareMeta;)V
      //   40: aload 7
      //   42: iconst_2
      //   43: invokevirtual 120	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:setMaxRetry	(I)V
      //   46: aload 7
      //   48: ldc2_w 121
      //   51: iconst_0
      //   52: iconst_0
      //   53: invokevirtual 125	com/vlingo/sdk/internal/vlservice/VLHttpServiceRequest:schedule	(JZZ)V
      //   56: return
      //   57: astore 5
      //   59: aload 5
      //   61: athrow
      //   62: astore 4
      //   64: goto -8 -> 56
      //
      // Exception table:
      //   from	to	target	type
      //   0	56	57	finally
      //   0	56	62	java/lang/Exception
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRStatisticsCollection
 * JD-Core Version:    0.6.0
 */