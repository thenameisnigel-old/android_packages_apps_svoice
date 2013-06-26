package com.vlingo.sdk.training;

import java.util.HashMap;

public abstract interface VLTrainerListener
{
  public abstract void onError(VLTrainerErrors paramVLTrainerErrors, String paramString);

  public abstract void onUpdateReceived(HashMap<VLTrainer.TrainerItemType, Integer> paramHashMap);
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.training.VLTrainerListener
 * JD-Core Version:    0.6.0
 */