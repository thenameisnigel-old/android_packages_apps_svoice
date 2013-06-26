package com.vlingo.sdk.internal.recognizer;

import com.vlingo.sdk.internal.http.HttpUtil;
import java.util.Enumeration;
import java.util.Hashtable;

public class SRStatistics
{
  public static String STAT_ACCEPTED_TEXT;
  public static String STAT_BOR;
  public static String STAT_CDEL;
  public static String STAT_CLR;
  public static String STAT_CREC;
  public static String STAT_CSEL;
  public static String STAT_EOD;
  public static String STAT_EOR;
  public static String STAT_EOS;
  public static String STAT_KEY;
  public static String STAT_NAV;
  public static String STAT_NBCOR;
  public static String STAT_PAR;
  public static String STAT_PDEL;
  public static String STAT_PNBNAV;
  public static String STAT_PREC;
  public static String STAT_PSEL;
  public static String STAT_RES;
  public static String STAT_SED;
  public static String STAT_UTT;
  public static String STAT_WDEL;
  public static String STAT_WNAV;
  public static String STAT_WNBNAV;
  public static String STAT_WREC;
  public static String STAT_WSEL;
  public static String TYPE_ACCEPTED_TEXT = "AcceptedText";
  public static String TYPE_REC_TIMING = "RecTiming";
  public static String TYPE_USAGE_COUNTS = "UsageCounts";
  private String guttId;
  private Hashtable<Object, Object> stats = new Hashtable();
  private String type;

  static
  {
    STAT_ACCEPTED_TEXT = "ACCEPTED_TEXT";
    STAT_BOR = "BOR";
    STAT_EOS = "EOS";
    STAT_EOR = "EOR";
    STAT_EOD = "EOD";
    STAT_SED = "SED";
    STAT_RES = "RES";
    STAT_PAR = "PAR";
    STAT_UTT = "UTT";
    STAT_KEY = "KEY";
    STAT_NAV = "NAV";
    STAT_WNBNAV = "WNBNAV";
    STAT_PNBNAV = "PNBNAV";
    STAT_WNAV = "WNAV";
    STAT_NBCOR = "NBCOR";
    STAT_CDEL = "CDEL";
    STAT_WDEL = "WDEL";
    STAT_PDEL = "PDEL";
    STAT_CLR = "CLR";
    STAT_CREC = "CREC";
    STAT_WREC = "WREC";
    STAT_PREC = "PREC";
    STAT_CSEL = "CSEL";
    STAT_WSEL = "WSEL";
    STAT_PSEL = "PSEL";
  }

  public SRStatistics(String paramString1, String paramString2)
  {
    this.guttId = paramString1;
    this.type = paramString2;
  }

  public void addStatistic(String paramString1, String paramString2)
  {
    this.stats.put(paramString1, paramString2);
  }

  public String getGuttId()
  {
    return this.guttId;
  }

  public Hashtable<Object, Object> getStatistics()
  {
    return this.stats;
  }

  public String getType()
  {
    return this.type;
  }

  public String getXML()
  {
    return getXML(true);
  }

  String getXML(boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramBoolean)
      localStringBuffer.append("<Stats " + HttpUtil.genAtr("guttid", getGuttId()) + ">");
    Enumeration localEnumeration = getStatistics().keys();
    while (localEnumeration.hasMoreElements())
    {
      String str1 = (String)localEnumeration.nextElement();
      String str2 = (String)getStatistics().get(str1);
      localStringBuffer.append("<Stat ");
      localStringBuffer.append(HttpUtil.genAtr("n", str1));
      localStringBuffer.append(HttpUtil.genAtr("v", str2));
      localStringBuffer.append("/>");
    }
    if (paramBoolean)
      localStringBuffer.append("</Stats>");
    return localStringBuffer.toString();
  }

  public void setStatistics(Hashtable<Object, Object> paramHashtable)
  {
    this.stats = paramHashtable;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.SRStatistics
 * JD-Core Version:    0.6.0
 */