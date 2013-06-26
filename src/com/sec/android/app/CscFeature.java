package com.sec.android.app;

import java.util.Hashtable;

public class CscFeature
{
  public static final boolean Bool_NoTag = false;
  private static final String FEATURE_XML = "/system/csc/feature.xml";
  public static final int Int_NoTag = 0;
  private static final String MPS_FEATURE_XML = "/system/csc/others.xml";
  public static final String Str_NoTag = "";
  public static final String TAG_BOOLEAN_TEST_FALSE = "CscFeature_BooleanTestFalse";
  public static final String TAG_BOOLEAN_TEST_NULL = "CscFeature_BooleanTestNull";
  public static final String TAG_BOOLEAN_TEST_TRUE = "CscFeature_BooleanTestTrue";
  public static final String TAG_INTEGER_TEST = "CscFeature_IntegerTest";
  public static final String TAG_INTEGER_TEST_NULL = "CscFeature_IntegerTestNull";
  public static final String TAG_STRING_TEST = "CscFeature_StringTest";
  public static final String TAG_STRING_TEST_NULL = "CscFeature_StringTestNull";
  private static CscFeature sInstance = null;
  private Hashtable<String, String> mFeatureList = new Hashtable();

  private CscFeature()
  {
    try
    {
      loadFeatureFile();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static CscFeature getInstance()
  {
    if (sInstance == null)
      sInstance = new CscFeature();
    return sInstance;
  }

  // ERROR //
  private void loadFeatureFile()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 59	com/sec/android/app/CscFeature:mFeatureList	Ljava/util/Hashtable;
    //   8: invokevirtual 77	java/util/Hashtable:clear	()V
    //   11: new 79	java/io/File
    //   14: dup
    //   15: ldc 11
    //   17: invokespecial 82	java/io/File:<init>	(Ljava/lang/String;)V
    //   20: astore 9
    //   22: aload 9
    //   24: invokevirtual 86	java/io/File:exists	()Z
    //   27: ifeq +13 -> 40
    //   30: aload 9
    //   32: invokevirtual 90	java/io/File:length	()J
    //   35: lconst_0
    //   36: lcmp
    //   37: ifgt +55 -> 92
    //   40: new 79	java/io/File
    //   43: dup
    //   44: ldc 16
    //   46: invokespecial 82	java/io/File:<init>	(Ljava/lang/String;)V
    //   49: astore 9
    //   51: aload 9
    //   53: invokevirtual 86	java/io/File:exists	()Z
    //   56: ifeq +17 -> 73
    //   59: aload 9
    //   61: invokevirtual 90	java/io/File:length	()J
    //   64: lstore 11
    //   66: lload 11
    //   68: lconst_0
    //   69: lcmp
    //   70: ifgt +22 -> 92
    //   73: iconst_0
    //   74: ifeq +7 -> 81
    //   77: aconst_null
    //   78: invokevirtual 95	java/io/InputStream:close	()V
    //   81: return
    //   82: astore 10
    //   84: aload 10
    //   86: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   89: goto -8 -> 81
    //   92: invokestatic 102	org/xmlpull/v1/XmlPullParserFactory:newInstance	()Lorg/xmlpull/v1/XmlPullParserFactory;
    //   95: astore 13
    //   97: aload 13
    //   99: iconst_1
    //   100: invokevirtual 106	org/xmlpull/v1/XmlPullParserFactory:setNamespaceAware	(Z)V
    //   103: aload 13
    //   105: invokevirtual 110	org/xmlpull/v1/XmlPullParserFactory:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   108: astore 14
    //   110: new 112	java/io/FileInputStream
    //   113: dup
    //   114: aload 9
    //   116: invokespecial 115	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   119: astore 15
    //   121: aload 14
    //   123: aload 15
    //   125: aconst_null
    //   126: invokeinterface 121 3 0
    //   131: aload 14
    //   133: invokeinterface 125 1 0
    //   138: istore 16
    //   140: iload 16
    //   142: iconst_1
    //   143: if_icmpeq +195 -> 338
    //   146: iload 16
    //   148: iconst_2
    //   149: if_icmpne +31 -> 180
    //   152: aload 14
    //   154: invokeinterface 129 1 0
    //   159: astore 27
    //   161: aload 27
    //   163: astore_2
    //   164: aload 14
    //   166: invokeinterface 132 1 0
    //   171: istore 23
    //   173: iload 23
    //   175: istore 16
    //   177: goto -37 -> 140
    //   180: iload 16
    //   182: iconst_4
    //   183: if_icmpne -19 -> 164
    //   186: aload 14
    //   188: invokeinterface 135 1 0
    //   193: invokevirtual 140	java/lang/String:trim	()Ljava/lang/String;
    //   196: astore 19
    //   198: aload_2
    //   199: ifnull -35 -> 164
    //   202: aload 19
    //   204: ifnull -40 -> 164
    //   207: aload_0
    //   208: getfield 59	com/sec/android/app/CscFeature:mFeatureList	Ljava/util/Hashtable;
    //   211: aload_2
    //   212: invokevirtual 144	java/util/Hashtable:containsKey	(Ljava/lang/Object;)Z
    //   215: istore 20
    //   217: iload 20
    //   219: ifeq +48 -> 267
    //   222: aload 14
    //   224: invokeinterface 132 1 0
    //   229: istore 26
    //   231: iload 26
    //   233: istore 16
    //   235: goto -95 -> 140
    //   238: astore 25
    //   240: aload 25
    //   242: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   245: goto -105 -> 140
    //   248: astore_3
    //   249: aload 15
    //   251: astore_1
    //   252: aload_3
    //   253: invokevirtual 145	org/xmlpull/v1/XmlPullParserException:printStackTrace	()V
    //   256: aload_1
    //   257: ifnull -176 -> 81
    //   260: aload_1
    //   261: invokevirtual 95	java/io/InputStream:close	()V
    //   264: goto -183 -> 81
    //   267: aload_0
    //   268: getfield 59	com/sec/android/app/CscFeature:mFeatureList	Ljava/util/Hashtable;
    //   271: aload_2
    //   272: aload 19
    //   274: invokevirtual 149	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   277: pop
    //   278: goto -114 -> 164
    //   281: astore 21
    //   283: aload 21
    //   285: invokevirtual 65	java/lang/Exception:printStackTrace	()V
    //   288: goto -124 -> 164
    //   291: astore 7
    //   293: aload 15
    //   295: astore_1
    //   296: aload 7
    //   298: invokevirtual 150	java/io/FileNotFoundException:printStackTrace	()V
    //   301: aload_1
    //   302: ifnull -221 -> 81
    //   305: aload_1
    //   306: invokevirtual 95	java/io/InputStream:close	()V
    //   309: goto -228 -> 81
    //   312: astore 22
    //   314: aload 22
    //   316: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   319: goto -179 -> 140
    //   322: astore 4
    //   324: aload 15
    //   326: astore_1
    //   327: aload_1
    //   328: ifnull +7 -> 335
    //   331: aload_1
    //   332: invokevirtual 95	java/io/InputStream:close	()V
    //   335: aload 4
    //   337: athrow
    //   338: aload 15
    //   340: invokevirtual 95	java/io/InputStream:close	()V
    //   343: aload 15
    //   345: ifnull +75 -> 420
    //   348: aload 15
    //   350: invokevirtual 95	java/io/InputStream:close	()V
    //   353: goto -272 -> 81
    //   356: astore 17
    //   358: aload 17
    //   360: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   363: goto -20 -> 343
    //   366: astore 18
    //   368: aload 18
    //   370: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   373: goto -292 -> 81
    //   376: astore 6
    //   378: aload 6
    //   380: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   383: goto -302 -> 81
    //   386: astore 8
    //   388: aload 8
    //   390: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   393: goto -312 -> 81
    //   396: astore 5
    //   398: aload 5
    //   400: invokevirtual 96	java/io/IOException:printStackTrace	()V
    //   403: goto -68 -> 335
    //   406: astore 4
    //   408: goto -81 -> 327
    //   411: astore 7
    //   413: goto -117 -> 296
    //   416: astore_3
    //   417: goto -165 -> 252
    //   420: goto -339 -> 81
    //
    // Exception table:
    //   from	to	target	type
    //   77	81	82	java/io/IOException
    //   222	231	238	java/io/IOException
    //   121	161	248	org/xmlpull/v1/XmlPullParserException
    //   164	173	248	org/xmlpull/v1/XmlPullParserException
    //   186	217	248	org/xmlpull/v1/XmlPullParserException
    //   222	231	248	org/xmlpull/v1/XmlPullParserException
    //   240	245	248	org/xmlpull/v1/XmlPullParserException
    //   267	278	248	org/xmlpull/v1/XmlPullParserException
    //   283	288	248	org/xmlpull/v1/XmlPullParserException
    //   314	319	248	org/xmlpull/v1/XmlPullParserException
    //   338	343	248	org/xmlpull/v1/XmlPullParserException
    //   358	363	248	org/xmlpull/v1/XmlPullParserException
    //   267	278	281	java/lang/Exception
    //   121	161	291	java/io/FileNotFoundException
    //   164	173	291	java/io/FileNotFoundException
    //   186	217	291	java/io/FileNotFoundException
    //   222	231	291	java/io/FileNotFoundException
    //   240	245	291	java/io/FileNotFoundException
    //   267	278	291	java/io/FileNotFoundException
    //   283	288	291	java/io/FileNotFoundException
    //   314	319	291	java/io/FileNotFoundException
    //   338	343	291	java/io/FileNotFoundException
    //   358	363	291	java/io/FileNotFoundException
    //   164	173	312	java/io/IOException
    //   121	161	322	finally
    //   164	173	322	finally
    //   186	217	322	finally
    //   222	231	322	finally
    //   240	245	322	finally
    //   267	278	322	finally
    //   283	288	322	finally
    //   314	319	322	finally
    //   338	343	322	finally
    //   358	363	322	finally
    //   338	343	356	java/io/IOException
    //   348	353	366	java/io/IOException
    //   260	264	376	java/io/IOException
    //   305	309	386	java/io/IOException
    //   331	335	396	java/io/IOException
    //   4	66	406	finally
    //   92	121	406	finally
    //   252	256	406	finally
    //   296	301	406	finally
    //   4	66	411	java/io/FileNotFoundException
    //   92	121	411	java/io/FileNotFoundException
    //   4	66	416	org/xmlpull/v1/XmlPullParserException
    //   92	121	416	org/xmlpull/v1/XmlPullParserException
  }

  public boolean getEnableStatus(String paramString)
  {
    try
    {
      boolean bool2;
      if (this.mFeatureList.get(paramString) != null)
        bool2 = Boolean.parseBoolean((String)this.mFeatureList.get(paramString));
      for (bool1 = bool2; ; bool1 = false)
        return bool1;
    }
    catch (Exception localException)
    {
      while (true)
        boolean bool1 = false;
    }
  }

  public boolean getEnableStatus(String paramString, boolean paramBoolean)
  {
    try
    {
      if (this.mFeatureList.get(paramString) != null)
      {
        boolean bool = Boolean.parseBoolean((String)this.mFeatureList.get(paramString));
        paramBoolean = bool;
      }
      label30: return paramBoolean;
    }
    catch (Exception localException)
    {
      break label30;
    }
  }

  public int getInteger(String paramString)
  {
    try
    {
      int j;
      if (this.mFeatureList.get(paramString) != null)
        j = Integer.parseInt((String)this.mFeatureList.get(paramString));
      for (i = j; ; i = -1)
        return i;
    }
    catch (Exception localException)
    {
      while (true)
        int i = -1;
    }
  }

  public int getInteger(String paramString, int paramInt)
  {
    try
    {
      if (this.mFeatureList.get(paramString) != null)
      {
        int i = Integer.parseInt((String)this.mFeatureList.get(paramString));
        paramInt = i;
      }
      label30: return paramInt;
    }
    catch (Exception localException)
    {
      break label30;
    }
  }

  public String getString(String paramString)
  {
    String str;
    try
    {
      if (this.mFeatureList.get(paramString) != null)
        str = (String)this.mFeatureList.get(paramString);
      else
        str = "";
    }
    catch (Exception localException)
    {
      str = "";
    }
    return str;
  }

  public String getString(String paramString1, String paramString2)
  {
    try
    {
      if (this.mFeatureList.get(paramString1) != null)
      {
        String str = (String)this.mFeatureList.get(paramString1);
        paramString2 = str;
      }
      label27: return paramString2;
    }
    catch (Exception localException)
    {
      break label27;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.app.CscFeature
 * JD-Core Version:    0.6.0
 */