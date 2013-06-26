package com.vlingo.core.internal.phrasespotter;

import com.vlingo.core.internal.audio.AudioLogger;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class PhraseSpotterAudioLogger
  implements AudioLogger
{
  private static final int BUFFER_LENGTH_MS = 20000;
  private static int smLoggerId = 0;
  private ByteArrayOutputStream mBAOutputStream;
  private int mBytesLeft;
  private DataOutputStream mDataOutputStream;
  private boolean mGotLowByte;
  private int mId;
  private boolean mIsDone;
  private byte mLowByte;
  private int mPStartingSample;
  private PhraseSpotterParameters mPhraseSpotterParams;
  private int mSStartingSample;
  private int mSampleRate;
  private int mSamplesPerChunk;
  private float mScore;
  private int mSpeechSample;

  PhraseSpotterAudioLogger(int paramInt1, int paramInt2, int paramInt3, PhraseSpotterParameters paramPhraseSpotterParameters)
  {
    this.mId = paramInt1;
    this.mSamplesPerChunk = paramInt2;
    this.mSampleRate = paramInt3;
    this.mPhraseSpotterParams = paramPhraseSpotterParameters;
    this.mBytesLeft = (2 * (paramInt3 * 20000 / 1000));
    this.mBAOutputStream = new ByteArrayOutputStream(this.mBytesLeft);
    this.mDataOutputStream = new DataOutputStream(this.mBAOutputStream);
  }

  static PhraseSpotterAudioLogger getInstance(int paramInt1, int paramInt2, PhraseSpotterParameters paramPhraseSpotterParameters)
  {
    int i = 1 + smLoggerId;
    smLoggerId = i;
    return new PhraseSpotterAudioLogger(i, paramInt1, paramInt2, paramPhraseSpotterParameters);
  }

  // ERROR //
  public void dumpToFile()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 71	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mIsDone	Z
    //   6: ifne +960 -> 966
    //   9: new 57	java/io/DataOutputStream
    //   12: dup
    //   13: new 73	java/io/BufferedOutputStream
    //   16: dup
    //   17: new 75	java/io/FileOutputStream
    //   20: dup
    //   21: new 77	java/io/File
    //   24: dup
    //   25: new 79	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   32: ldc 82
    //   34: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_0
    //   38: getfield 40	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mId	I
    //   41: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   44: ldc 91
    //   46: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokespecial 98	java/io/File:<init>	(Ljava/lang/String;)V
    //   55: invokespecial 101	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   58: invokespecial 102	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   61: invokespecial 60	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   64: astore 4
    //   66: aload_0
    //   67: getfield 55	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mBAOutputStream	Ljava/io/ByteArrayOutputStream;
    //   70: invokevirtual 106	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   73: astore 5
    //   75: iconst_0
    //   76: istore 6
    //   78: iload 6
    //   80: aload 5
    //   82: arraylength
    //   83: if_icmpge +19 -> 102
    //   86: aload 4
    //   88: aload 5
    //   90: iload 6
    //   92: baload
    //   93: invokevirtual 109	java/io/DataOutputStream:write	(I)V
    //   96: iinc 6 1
    //   99: goto -21 -> 78
    //   102: aload 4
    //   104: invokevirtual 112	java/io/DataOutputStream:flush	()V
    //   107: aload 4
    //   109: invokevirtual 115	java/io/DataOutputStream:close	()V
    //   112: sipush 1000
    //   115: aload_0
    //   116: getfield 117	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPStartingSample	I
    //   119: imul
    //   120: aload_0
    //   121: getfield 44	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSampleRate	I
    //   124: idiv
    //   125: istore 7
    //   127: sipush 1000
    //   130: aload_0
    //   131: getfield 119	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSStartingSample	I
    //   134: imul
    //   135: aload_0
    //   136: getfield 44	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSampleRate	I
    //   139: idiv
    //   140: istore 8
    //   142: sipush 1000
    //   145: aload_0
    //   146: getfield 121	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSpeechSample	I
    //   149: imul
    //   150: aload_0
    //   151: getfield 44	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSampleRate	I
    //   154: idiv
    //   155: istore 9
    //   157: iload 7
    //   159: aload_0
    //   160: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   163: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   166: invokevirtual 133	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getDeltaD	()I
    //   169: isub
    //   170: istore 10
    //   172: iload 10
    //   174: aload_0
    //   175: getfield 44	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSampleRate	I
    //   178: imul
    //   179: sipush 1000
    //   182: idiv
    //   183: istore 11
    //   185: new 79	java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   192: astore 12
    //   194: aload 12
    //   196: ldc 135
    //   198: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: aload 12
    //   204: ldc 137
    //   206: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 12
    //   212: ldc 139
    //   214: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload 12
    //   220: aload_0
    //   221: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   224: invokevirtual 142	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getChunkLength	()I
    //   227: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   230: pop
    //   231: aload 12
    //   233: ldc 144
    //   235: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: aload 12
    //   241: ldc 146
    //   243: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: pop
    //   247: aload 12
    //   249: aload_0
    //   250: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   253: invokevirtual 149	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getPreBufferLength	()I
    //   256: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   259: pop
    //   260: aload 12
    //   262: ldc 144
    //   264: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload 12
    //   270: ldc 151
    //   272: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: pop
    //   276: aload 12
    //   278: aload_0
    //   279: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   282: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   285: invokevirtual 133	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getDeltaD	()I
    //   288: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   291: pop
    //   292: aload 12
    //   294: ldc 144
    //   296: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: pop
    //   300: aload 12
    //   302: ldc 153
    //   304: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: pop
    //   308: aload 12
    //   310: aload_0
    //   311: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   314: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   317: invokevirtual 156	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getDeltaS	()I
    //   320: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   323: pop
    //   324: aload 12
    //   326: ldc 144
    //   328: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: aload 12
    //   334: ldc 158
    //   336: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: aload 12
    //   342: aload_0
    //   343: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   346: invokevirtual 161	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getSeamlessTimeout	()I
    //   349: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   352: pop
    //   353: aload 12
    //   355: ldc 144
    //   357: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: aload 12
    //   363: ldc 163
    //   365: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: pop
    //   369: aload 12
    //   371: ldc 137
    //   373: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: pop
    //   377: aload 12
    //   379: ldc 165
    //   381: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   384: pop
    //   385: aload 12
    //   387: aload_0
    //   388: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   391: invokevirtual 168	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getLanguage	()Ljava/lang/String;
    //   394: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: pop
    //   398: aload 12
    //   400: ldc 144
    //   402: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: pop
    //   406: aload 12
    //   408: ldc 170
    //   410: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: pop
    //   414: aload 12
    //   416: aload_0
    //   417: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   420: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   423: invokevirtual 173	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getCGFilename	()Ljava/lang/String;
    //   426: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: pop
    //   430: aload 12
    //   432: ldc 144
    //   434: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: pop
    //   438: aload 12
    //   440: ldc 175
    //   442: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: aload 12
    //   448: aload_0
    //   449: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   452: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   455: invokevirtual 179	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getBeam	()F
    //   458: invokevirtual 182	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   461: pop
    //   462: aload 12
    //   464: ldc 144
    //   466: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: pop
    //   470: aload 12
    //   472: ldc 184
    //   474: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: pop
    //   478: aload 12
    //   480: aload_0
    //   481: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   484: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   487: invokevirtual 187	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getAbsbeam	()F
    //   490: invokevirtual 182	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   493: pop
    //   494: aload 12
    //   496: ldc 144
    //   498: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: pop
    //   502: aload 12
    //   504: ldc 189
    //   506: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: pop
    //   510: aload 12
    //   512: aload_0
    //   513: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   516: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   519: invokevirtual 192	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getAoffset	()F
    //   522: invokevirtual 182	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   525: pop
    //   526: aload 12
    //   528: ldc 144
    //   530: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: pop
    //   534: aload 12
    //   536: ldc 194
    //   538: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: pop
    //   542: aload 12
    //   544: aload_0
    //   545: getfield 46	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPhraseSpotterParams	Lcom/vlingo/core/internal/phrasespotter/PhraseSpotterParameters;
    //   548: invokevirtual 127	com/vlingo/core/internal/phrasespotter/PhraseSpotterParameters:getCoreSpotterParams	()Lcom/vlingo/core/internal/phrasespotter/CoreSpotterParameters;
    //   551: invokevirtual 197	com/vlingo/core/internal/phrasespotter/CoreSpotterParameters:getDelay	()F
    //   554: invokevirtual 182	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   557: pop
    //   558: aload 12
    //   560: ldc 144
    //   562: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   565: pop
    //   566: aload 12
    //   568: ldc 199
    //   570: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: pop
    //   574: aload 12
    //   576: ldc 137
    //   578: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: pop
    //   582: aload 12
    //   584: ldc 201
    //   586: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   589: pop
    //   590: aload 12
    //   592: aload_0
    //   593: getfield 42	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSamplesPerChunk	I
    //   596: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   599: pop
    //   600: aload 12
    //   602: ldc 144
    //   604: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: pop
    //   608: aload 12
    //   610: ldc 203
    //   612: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: pop
    //   616: aload 12
    //   618: aload_0
    //   619: getfield 44	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSampleRate	I
    //   622: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   625: pop
    //   626: aload 12
    //   628: ldc 144
    //   630: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: pop
    //   634: aload 12
    //   636: ldc 205
    //   638: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: pop
    //   642: aload 12
    //   644: iload 11
    //   646: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   649: pop
    //   650: aload 12
    //   652: new 79	java/lang/StringBuilder
    //   655: dup
    //   656: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   659: ldc 207
    //   661: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: iload 10
    //   666: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   669: ldc 209
    //   671: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   677: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: pop
    //   681: aload 12
    //   683: ldc 144
    //   685: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   688: pop
    //   689: aload 12
    //   691: ldc 211
    //   693: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: pop
    //   697: aload 12
    //   699: aload_0
    //   700: getfield 117	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mPStartingSample	I
    //   703: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   706: pop
    //   707: aload 12
    //   709: new 79	java/lang/StringBuilder
    //   712: dup
    //   713: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   716: ldc 207
    //   718: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   721: iload 7
    //   723: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   726: ldc 209
    //   728: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   731: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   734: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   737: pop
    //   738: aload 12
    //   740: ldc 144
    //   742: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: pop
    //   746: aload 12
    //   748: ldc 213
    //   750: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: pop
    //   754: aload 12
    //   756: aload_0
    //   757: getfield 119	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSStartingSample	I
    //   760: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   763: pop
    //   764: aload 12
    //   766: new 79	java/lang/StringBuilder
    //   769: dup
    //   770: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   773: ldc 207
    //   775: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   778: iload 8
    //   780: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   783: ldc 209
    //   785: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   791: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   794: pop
    //   795: aload 12
    //   797: ldc 144
    //   799: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: pop
    //   803: aload 12
    //   805: ldc 215
    //   807: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: pop
    //   811: aload 12
    //   813: aload_0
    //   814: getfield 121	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mSpeechSample	I
    //   817: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   820: pop
    //   821: aload 12
    //   823: new 79	java/lang/StringBuilder
    //   826: dup
    //   827: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   830: ldc 207
    //   832: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: iload 9
    //   837: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   840: ldc 209
    //   842: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   845: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   848: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   851: pop
    //   852: aload 12
    //   854: ldc 144
    //   856: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   859: pop
    //   860: aload 12
    //   862: ldc 217
    //   864: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   867: pop
    //   868: aload 12
    //   870: aload_0
    //   871: getfield 219	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mScore	F
    //   874: invokevirtual 182	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   877: pop
    //   878: aload 12
    //   880: ldc 144
    //   882: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   885: pop
    //   886: new 57	java/io/DataOutputStream
    //   889: dup
    //   890: new 73	java/io/BufferedOutputStream
    //   893: dup
    //   894: new 75	java/io/FileOutputStream
    //   897: dup
    //   898: new 77	java/io/File
    //   901: dup
    //   902: new 79	java/lang/StringBuilder
    //   905: dup
    //   906: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   909: ldc 82
    //   911: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   914: aload_0
    //   915: getfield 40	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mId	I
    //   918: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   921: ldc 221
    //   923: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   926: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   929: invokespecial 98	java/io/File:<init>	(Ljava/lang/String;)V
    //   932: invokespecial 101	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   935: invokespecial 102	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   938: invokespecial 60	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   941: astore 77
    //   943: aload 77
    //   945: aload 12
    //   947: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   950: invokevirtual 226	java/lang/String:getBytes	()[B
    //   953: invokevirtual 229	java/io/DataOutputStream:write	([B)V
    //   956: aload 77
    //   958: invokevirtual 112	java/io/DataOutputStream:flush	()V
    //   961: aload 77
    //   963: invokevirtual 115	java/io/DataOutputStream:close	()V
    //   966: aload_0
    //   967: iconst_1
    //   968: putfield 71	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mIsDone	Z
    //   971: aload_0
    //   972: monitorexit
    //   973: return
    //   974: astore_3
    //   975: aload_0
    //   976: iconst_1
    //   977: putfield 71	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mIsDone	Z
    //   980: goto -9 -> 971
    //   983: astore_2
    //   984: aload_0
    //   985: monitorexit
    //   986: aload_2
    //   987: athrow
    //   988: astore_1
    //   989: aload_0
    //   990: iconst_1
    //   991: putfield 71	com/vlingo/core/internal/phrasespotter/PhraseSpotterAudioLogger:mIsDone	Z
    //   994: aload_1
    //   995: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	966	974	java/io/IOException
    //   966	971	983	finally
    //   975	980	983	finally
    //   989	996	983	finally
    //   2	966	988	finally
  }

  void markDecisionSample(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      this.mSpeechSample = (this.mDataOutputStream.size() / 2);
      while (true)
      {
        return;
        dumpToFile();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void markP()
  {
    monitorenter;
    try
    {
      this.mPStartingSample = (this.mDataOutputStream.size() / 2);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  void markS()
  {
    monitorenter;
    try
    {
      this.mSStartingSample = (this.mDataOutputStream.size() / 2);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  void setScore(float paramFloat)
  {
    monitorenter;
    try
    {
      this.mScore = paramFloat;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void writeData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramInt2;
    for (int j = paramInt1; ; j++)
      if ((j < i) && (this.mBytesLeft > 0))
        try
        {
          if (!this.mGotLowByte)
            this.mLowByte = paramArrayOfByte[j];
          for (this.mGotLowByte = true; ; this.mGotLowByte = false)
          {
            this.mBytesLeft = (-1 + this.mBytesLeft);
            if (this.mBytesLeft != 0)
              break;
            dumpToFile();
            break;
            this.mDataOutputStream.writeByte(paramArrayOfByte[j]);
            this.mDataOutputStream.writeByte(this.mLowByte);
          }
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      else
        return;
  }

  public void writeData(short[] paramArrayOfShort, int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramInt2;
    int j = paramInt1;
    while (true)
      if ((j < i) && (this.mBytesLeft > 0))
        try
        {
          this.mDataOutputStream.writeShort(paramArrayOfShort[j]);
          this.mBytesLeft = (-2 + this.mBytesLeft);
          if (this.mBytesLeft == 0)
            dumpToFile();
          j++;
        }
        catch (IOException localIOException)
        {
          while (true)
            localIOException.printStackTrace();
        }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.phrasespotter.PhraseSpotterAudioLogger
 * JD-Core Version:    0.6.0
 */