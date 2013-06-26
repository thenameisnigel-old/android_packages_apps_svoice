package com.vlingo.sdk.internal;

import android.os.Handler;
import com.vlingo.sdk.VLComponent;
import com.vlingo.sdk.recognition.VLRecognizer;
import com.vlingo.sdk.recognition.speech.VLSpeechDetector;
import com.vlingo.sdk.recognition.spotter.VLSpotter;
import com.vlingo.sdk.services.VLServices;
import com.vlingo.sdk.training.VLTrainer;
import com.vlingo.sdk.tts.VLTextToSpeech;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class VLComponentManager
{
  private ConcurrentHashMap<Class<? extends VLComponentImpl>, VLComponent> mComponents;
  private Handler mHandler;

  public VLComponentManager(Handler paramHandler)
  {
    this.mHandler = paramHandler;
    this.mComponents = new ConcurrentHashMap();
  }

  public void destroyAll()
  {
    Iterator localIterator = this.mComponents.keySet().iterator();
    while (localIterator.hasNext())
    {
      Class localClass = (Class)localIterator.next();
      ((VLComponent)this.mComponents.remove(localClass)).destroy();
    }
  }

  VLComponent getComponent(Class<? extends VLComponentImpl> paramClass)
  {
    VLComponent localVLComponent = (VLComponent)this.mComponents.get(paramClass);
    if (localVLComponent == null);
    try
    {
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = getClass();
      arrayOfClass[1] = Handler.class;
      Constructor localConstructor = paramClass.getConstructor(arrayOfClass);
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this;
      arrayOfObject[1] = this.mHandler;
      localVLComponent = (VLComponent)localConstructor.newInstance(arrayOfObject);
      this.mComponents.put(paramClass, localVLComponent);
      return localVLComponent;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public VLRecognizer getRecognizer()
  {
    return (VLRecognizer)getComponent(VLRecognizerImpl.class);
  }

  public VLSpeechDetector getSpeechDetector()
  {
    return (VLSpeechDetector)getComponent(VLSpeechDetectorImpl.class);
  }

  public VLSpotter getSpotter()
  {
    return (VLSpotter)getComponent(VLSpotterImpl.class);
  }

  public VLTextToSpeech getTextToSpeech()
  {
    return (VLTextToSpeech)getComponent(VLTextToSpeechImpl.class);
  }

  public VLTrainer getTrainer()
  {
    return (VLTrainer)getComponent(VLTrainerImpl.class);
  }

  public VLServices getVLServices()
  {
    return (VLServices)getComponent(VLServicesImpl.class);
  }

  void removeComponent(Class<? extends VLComponentImpl> paramClass)
  {
    this.mComponents.remove(paramClass);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.VLComponentManager
 * JD-Core Version:    0.6.0
 */