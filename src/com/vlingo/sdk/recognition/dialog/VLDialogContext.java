package com.vlingo.sdk.recognition.dialog;

import com.vlingo.sdk.internal.util.StringUtils;
import com.vlingo.sdk.recognition.VLRecognitionContext;
import com.vlingo.sdk.recognition.VLRecognitionContext.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class VLDialogContext extends VLRecognitionContext
{
  private String dialogGUID;
  private byte[] dialogState;
  private int dialogTurnNumber;
  private HashMap<String, String> dmHeaderKVPairs;
  private List<VLDialogEvent> eventList;
  private String username;

  private VLDialogContext(Builder paramBuilder)
  {
    super(paramBuilder);
    this.dialogState = paramBuilder.dialogState;
    this.username = paramBuilder.username;
    this.dialogGUID = paramBuilder.dialogGUID;
    this.dialogTurnNumber = paramBuilder.dialogTurnNumber;
    this.eventList = new ArrayList(paramBuilder.eventList.size());
    this.eventList.addAll(paramBuilder.eventList);
    this.dmHeaderKVPairs = new HashMap(paramBuilder.dmHeaderKVPairs.size());
    this.dmHeaderKVPairs.putAll(paramBuilder.dmHeaderKVPairs);
  }

  public HashMap<String, String> getDMHeaderKVPairs()
  {
    return this.dmHeaderKVPairs;
  }

  public String getDialogGUID()
  {
    return this.dialogGUID;
  }

  public byte[] getDialogState()
  {
    return this.dialogState;
  }

  public int getDialogTurnNumber()
  {
    return this.dialogTurnNumber;
  }

  public List<VLDialogEvent> getEvents()
  {
    return this.eventList;
  }

  public String getUsername()
  {
    return this.username;
  }

  public static final class Builder extends VLRecognitionContext.Builder
  {
    private String dialogGUID;
    private byte[] dialogState;
    private int dialogTurnNumber = -1;
    private HashMap<String, String> dmHeaderKVPairs = new HashMap();
    private List<VLDialogEvent> eventList = new ArrayList();
    private String username;

    public Builder addDMHeaderKVPair(String paramString1, String paramString2)
    {
      if (StringUtils.isNullOrWhiteSpace(paramString1))
        throw new IllegalArgumentException("name cannot be null or empty");
      this.dmHeaderKVPairs.put(paramString1, paramString2);
      return this;
    }

    public Builder addEvent(VLDialogEvent paramVLDialogEvent)
    {
      this.eventList.add(paramVLDialogEvent);
      return this;
    }

    public VLDialogContext build()
    {
      return new VLDialogContext(this, null);
    }

    public Builder setDialogGUID(String paramString)
    {
      this.dialogGUID = paramString;
      return this;
    }

    public Builder setState(byte[] paramArrayOfByte)
    {
      this.dialogState = paramArrayOfByte;
      return this;
    }

    public Builder setTurnNumber(int paramInt)
    {
      this.dialogTurnNumber = paramInt;
      return this;
    }

    public Builder setUsername(String paramString)
    {
      this.username = paramString;
      return this;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.dialog.VLDialogContext
 * JD-Core Version:    0.6.0
 */