package com.vlingo.sdk.services;

public enum VLServicesErrors
{
  static
  {
    ERROR_NETWORK = new VLServicesErrors("ERROR_NETWORK", 1);
    ERROR_SERVER = new VLServicesErrors("ERROR_SERVER", 2);
    ERROR_CLIENT = new VLServicesErrors("ERROR_CLIENT", 3);
    VLServicesErrors[] arrayOfVLServicesErrors = new VLServicesErrors[4];
    arrayOfVLServicesErrors[0] = ERROR_NETWORK_TIMEOUT;
    arrayOfVLServicesErrors[1] = ERROR_NETWORK;
    arrayOfVLServicesErrors[2] = ERROR_SERVER;
    arrayOfVLServicesErrors[3] = ERROR_CLIENT;
    $VALUES = arrayOfVLServicesErrors;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.services.VLServicesErrors
 * JD-Core Version:    0.6.0
 */