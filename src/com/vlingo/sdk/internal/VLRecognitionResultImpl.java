package com.vlingo.sdk.internal;

import com.vlingo.sdk.internal.recognizer.results.RecResults;
import com.vlingo.sdk.internal.recognizer.results.SRRecognitionResponse;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults;
import com.vlingo.sdk.internal.recognizer.results.TaggedResults.ParseGroup;
import com.vlingo.sdk.internal.vlservice.response.Action;
import com.vlingo.sdk.internal.vlservice.response.ActionList;
import com.vlingo.sdk.recognition.NBestData;
import com.vlingo.sdk.recognition.VLAction;
import com.vlingo.sdk.recognition.VLRecognitionResult;
import java.util.ArrayList;
import java.util.List;

public class VLRecognitionResultImpl
  implements VLRecognitionResult
{
  private List<VLAction> mActionList;
  private String mDialogGuid;
  private byte[] mDialogState;
  private int mDialogTurn;
  private String mGUttId;
  private NBestData mNBestData;
  private TaggedResults.ParseGroup mParseGroup;
  private String mResult;

  public VLRecognitionResultImpl(SRRecognitionResponse paramSRRecognitionResponse)
  {
    if (paramSRRecognitionResponse != null)
    {
      TaggedResults localTaggedResults = paramSRRecognitionResponse.getResults();
      if (localTaggedResults != null)
      {
        this.mParseGroup = localTaggedResults.getParseGroup();
        this.mGUttId = paramSRRecognitionResponse.getResults().getGUttID();
        this.mResult = localTaggedResults.getUttResults().toString();
        this.mNBestData = new NBestData(localTaggedResults.getUttResults());
      }
      this.mDialogGuid = paramSRRecognitionResponse.getDialogGuid();
      this.mDialogTurn = paramSRRecognitionResponse.getDialogTurn();
      this.mDialogState = paramSRRecognitionResponse.getDialogState();
      if (paramSRRecognitionResponse.hasActions())
      {
        ActionList localActionList = paramSRRecognitionResponse.getActionList();
        int i = localActionList.size();
        if (i > 0)
        {
          this.mActionList = new ArrayList(i);
          for (int j = 0; j < i; j++)
          {
            Action localAction = localActionList.elementAt(j);
            this.mActionList.add(new VLActionImpl(localAction));
          }
        }
      }
    }
  }

  public List<VLAction> getActions()
  {
    return this.mActionList;
  }

  public String getDialogGUID()
  {
    return this.mDialogGuid;
  }

  public byte[] getDialogState()
  {
    return this.mDialogState;
  }

  public int getDialogTurn()
  {
    return this.mDialogTurn;
  }

  public String getGUttId()
  {
    return this.mGUttId;
  }

  public NBestData getNBestData()
  {
    return this.mNBestData;
  }

  public TaggedResults.ParseGroup getParseGroup()
  {
    return this.mParseGroup;
  }

  public String getResultString()
  {
    return this.mResult;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLRecognitionResultImpl
 * JD-Core Version:    0.6.0
 */