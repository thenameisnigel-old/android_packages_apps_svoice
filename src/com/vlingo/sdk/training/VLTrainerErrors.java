package com.vlingo.sdk.training;

public enum VLTrainerErrors
{
  static
  {
    ERROR_NETWORK = new VLTrainerErrors("ERROR_NETWORK", 1);
    ERROR_SERVER = new VLTrainerErrors("ERROR_SERVER", 2);
    VLTrainerErrors[] arrayOfVLTrainerErrors = new VLTrainerErrors[3];
    arrayOfVLTrainerErrors[0] = ERROR_NETWORK_TIMEOUT;
    arrayOfVLTrainerErrors[1] = ERROR_NETWORK;
    arrayOfVLTrainerErrors[2] = ERROR_SERVER;
    $VALUES = arrayOfVLTrainerErrors;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.training.VLTrainerErrors
 * JD-Core Version:    0.6.0
 */