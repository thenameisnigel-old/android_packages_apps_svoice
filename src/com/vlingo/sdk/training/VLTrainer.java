package com.vlingo.sdk.training;

import com.vlingo.sdk.VLComponent;

public abstract interface VLTrainer extends VLComponent
{
  public abstract void clearAllItems(String paramString, VLTrainerListener paramVLTrainerListener);

  public abstract void clearContactItems(String paramString, VLTrainerListener paramVLTrainerListener);

  public abstract void clearMusicItems(String paramString, VLTrainerListener paramVLTrainerListener);

  public abstract void sendFullUpdate(VLTrainerUpdateList paramVLTrainerUpdateList, String paramString, VLTrainerListener paramVLTrainerListener);

  public abstract void sendPartialUpdate(VLTrainerUpdateList paramVLTrainerUpdateList, String paramString, VLTrainerListener paramVLTrainerListener);

  public abstract void updateTrainerModelLanguage(String paramString, VLTrainerListener paramVLTrainerListener);

  public static enum TrainerItemType
  {
    static
    {
      PLAYLIST = new TrainerItemType("PLAYLIST", 2);
      TrainerItemType[] arrayOfTrainerItemType = new TrainerItemType[3];
      arrayOfTrainerItemType[0] = CONTACT;
      arrayOfTrainerItemType[1] = SONG;
      arrayOfTrainerItemType[2] = PLAYLIST;
      $VALUES = arrayOfTrainerItemType;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.training.VLTrainer
 * JD-Core Version:    0.6.0
 */