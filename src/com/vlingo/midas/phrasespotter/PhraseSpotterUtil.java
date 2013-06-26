package com.vlingo.midas.phrasespotter;

import android.content.res.Resources;
import com.vlingo.core.internal.audio.MicrophoneStream.AudioSourceType;
import com.vlingo.core.internal.phrasespotter.CoreSpotterParameters;
import com.vlingo.core.internal.phrasespotter.CoreSpotterParameters.Builder;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters;
import com.vlingo.core.internal.phrasespotter.PhraseSpotterParameters.Builder;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.ClientSuppliedValues;
import com.vlingo.midas.VlingoApplication;
import java.io.File;

public class PhraseSpotterUtil
{
  private static final File CG_DIR = VlingoApplication.getInstance().getDir("phrasespotter", 0);
  private static final int DEFAULT_DELTA_D = 0;
  private static final int DEFAULT_DELTA_S = 50;
  public static final int SEAMLESS_TIMEOUT_MS = 400;
  private static final String WAKE_UP_FILES_EXTERNAL_STORAGE = "/system/wakeupdata/sensory";

  public static MicrophoneStream.AudioSourceType chooseAudioSourceType()
  {
    if (ClientSuppliedValues.isDrivingModeEnabled());
    for (MicrophoneStream.AudioSourceType localAudioSourceType = MicrophoneStream.AudioSourceType.SPOTTER_DRIVING_MODE; ; localAudioSourceType = MicrophoneStream.AudioSourceType.SPOTTER_REGULAR)
      return localAudioSourceType;
  }

  // ERROR //
  private static String createFileIfNecessary(Resources paramResources, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: invokestatic 25	com/vlingo/midas/VlingoApplication:getInstance	()Lcom/vlingo/midas/VlingoApplication;
    //   5: invokevirtual 63	com/vlingo/midas/VlingoApplication:getApplicationContext	()Landroid/content/Context;
    //   8: astore_3
    //   9: new 65	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   16: aload_1
    //   17: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: ldc 72
    //   22: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: astore 4
    //   30: new 78	java/io/File
    //   33: dup
    //   34: getstatic 33	com/vlingo/midas/phrasespotter/PhraseSpotterUtil:CG_DIR	Ljava/io/File;
    //   37: aload 4
    //   39: invokespecial 81	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   42: astore 5
    //   44: aload 5
    //   46: invokevirtual 84	java/io/File:exists	()Z
    //   49: ifeq +27 -> 76
    //   52: aload 5
    //   54: invokevirtual 88	java/io/File:length	()J
    //   57: lconst_0
    //   58: lcmp
    //   59: ifle +11 -> 70
    //   62: aload 5
    //   64: invokevirtual 91	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   67: astore_2
    //   68: aload_2
    //   69: areturn
    //   70: aload 5
    //   72: invokevirtual 94	java/io/File:delete	()Z
    //   75: pop
    //   76: aload_0
    //   77: aload_1
    //   78: ldc 96
    //   80: aload_3
    //   81: invokevirtual 101	android/content/Context:getPackageName	()Ljava/lang/String;
    //   84: invokevirtual 107	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   87: istore 6
    //   89: iload 6
    //   91: ifeq -23 -> 68
    //   94: aload_0
    //   95: iload 6
    //   97: invokevirtual 111	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   100: astore 7
    //   102: aconst_null
    //   103: astore 8
    //   105: new 113	java/io/FileOutputStream
    //   108: dup
    //   109: aload 5
    //   111: invokespecial 116	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   114: astore 9
    //   116: sipush 1024
    //   119: newarray byte
    //   121: astore 18
    //   123: aload 7
    //   125: aload 18
    //   127: invokevirtual 122	java/io/InputStream:read	([B)I
    //   130: istore 19
    //   132: iload 19
    //   134: ifle +40 -> 174
    //   137: aload 9
    //   139: aload 18
    //   141: iconst_0
    //   142: iload 19
    //   144: invokevirtual 126	java/io/FileOutputStream:write	([BII)V
    //   147: goto -24 -> 123
    //   150: astore 14
    //   152: aload 9
    //   154: astore 8
    //   156: aload 8
    //   158: invokevirtual 129	java/io/FileOutputStream:close	()V
    //   161: aload 7
    //   163: invokevirtual 130	java/io/InputStream:close	()V
    //   166: goto -98 -> 68
    //   169: astore 16
    //   171: goto -103 -> 68
    //   174: aload 9
    //   176: invokevirtual 133	java/io/FileOutputStream:flush	()V
    //   179: aload 5
    //   181: invokevirtual 91	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   184: astore 20
    //   186: aload 20
    //   188: astore_2
    //   189: aload 9
    //   191: invokevirtual 129	java/io/FileOutputStream:close	()V
    //   194: aload 7
    //   196: invokevirtual 130	java/io/InputStream:close	()V
    //   199: goto -131 -> 68
    //   202: astore 22
    //   204: goto -136 -> 68
    //   207: astore 10
    //   209: aload 8
    //   211: invokevirtual 129	java/io/FileOutputStream:close	()V
    //   214: aload 7
    //   216: invokevirtual 130	java/io/InputStream:close	()V
    //   219: aload 10
    //   221: athrow
    //   222: astore 23
    //   224: goto -30 -> 194
    //   227: astore 21
    //   229: goto -35 -> 194
    //   232: astore 17
    //   234: goto -73 -> 161
    //   237: astore 15
    //   239: goto -78 -> 161
    //   242: astore 13
    //   244: goto -30 -> 214
    //   247: astore 11
    //   249: goto -35 -> 214
    //   252: astore 12
    //   254: goto -35 -> 219
    //   257: astore 10
    //   259: aload 9
    //   261: astore 8
    //   263: goto -54 -> 209
    //   266: astore 24
    //   268: goto -112 -> 156
    //
    // Exception table:
    //   from	to	target	type
    //   116	147	150	java/io/IOException
    //   174	186	150	java/io/IOException
    //   161	166	169	java/io/IOException
    //   194	199	202	java/io/IOException
    //   105	116	207	finally
    //   189	194	222	java/io/IOException
    //   189	194	227	java/lang/NullPointerException
    //   156	161	232	java/io/IOException
    //   156	161	237	java/lang/NullPointerException
    //   209	214	242	java/io/IOException
    //   209	214	247	java/lang/NullPointerException
    //   214	219	252	java/io/IOException
    //   116	147	257	finally
    //   174	186	257	finally
    //   105	116	266	java/io/IOException
  }

  public static PhraseSpotterParameters getPhraseSpotterParameters(Resources paramResources)
  {
    return getPhraseSpotterParameters(paramResources, chooseAudioSourceType());
  }

  public static PhraseSpotterParameters getPhraseSpotterParameters(Resources paramResources, MicrophoneStream.AudioSourceType paramAudioSourceType)
  {
    CoreSpotterParameters localCoreSpotterParameters = getWakeupCoreParameters(paramResources);
    PhraseSpotterParameters.Builder localBuilder = new PhraseSpotterParameters.Builder(Settings.getLanguageApplication(), localCoreSpotterParameters);
    localBuilder.setSeamlessTimeout(400);
    localBuilder.setAudioSourceType(paramAudioSourceType);
    return localBuilder.build();
  }

  public static CoreSpotterParameters getWakeupCoreParameters(Resources paramResources)
  {
    String str = createFileIfNecessary(paramResources, paramResources.getString(2131362043));
    CoreSpotterParameters.Builder localBuilder = new CoreSpotterParameters.Builder(Settings.getLanguageApplication(), str);
    localBuilder.setSensoryParams(Settings.getFloat("phrasespot_beam", 20.0F), Settings.getFloat("phrasespot_absbeam", 40.0F), Settings.getFloat("phrasespot_aoffset", 0.0F), Settings.getFloat("phrasespot_delay", 100.0F), "/system/wakeupdata/sensory");
    localBuilder.setDeltas(0, 50);
    return localBuilder.build();
  }

  public static boolean isCustomWakeupWordPhrase(String paramString)
  {
    try
    {
      Integer.parseInt(paramString);
      i = 1;
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        int i = 0;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.phrasespotter.PhraseSpotterUtil
 * JD-Core Version:    0.6.0
 */