package com.vlingo.midas.tss;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVoxPronunciationManager
{
  private static final Map<String, String> smBigNumbers;
  private static SVoxPronunciationManager smInstance;
  private static final Pattern smLongPricePatternDollar;
  private static final Pattern smLongPricePatternEuro;
  private static final Pattern smLongPricePatternPound;
  private static final Pattern smLongPricePatternPoundLb;
  private static final Pattern smLongPricePatternPoundLbs;
  private static final Pattern smLongPricePatternPoundPriceLbs;
  private static final Pattern smLongPricePatternWonFullWidth;
  private static final Pattern smLongPricePatternWonHalfWidth;
  private static final Pattern smLongWeightPatternG;
  private static final Pattern smLongWeightPatternKg;
  private static final Pattern smLongWeightPatternL;
  private static final Pattern smLongWeightPatternOz;
  private static final Pattern smMemoName;
  private static final Pattern smPhoneNumberPattern = Pattern.compile("(\\d+\\-)+\\d+");
  private static final Map<String, String> smPhoneticWords;
  private static final Map<String, String> smSpecialDomains;
  private static final Map<Character, String> smSpecialSymbols;
  private static final Map<String, String> smSpecialWords;
  private static final Map<String, String> smTimeZones;
  private static final Pattern tzPattern;

  static
  {
    smLongPricePatternDollar = Pattern.compile("(\\$\\s*)(\\d+)[.,]?(\\d\\d*)?");
    smLongPricePatternPound = Pattern.compile("(\\£\\s*)(\\d+)[.,]?(\\d\\d*)?");
    smLongPricePatternEuro = Pattern.compile("(\\€\\s*)(\\d+)[.,]?(\\d\\d*)?");
    smLongPricePatternWonHalfWidth = Pattern.compile("(\\₩\\s*)(\\d+)[.,]?(\\d\\d*)?");
    smLongPricePatternWonFullWidth = Pattern.compile("(\\￦\\s*)(\\d+)[.,]?(\\d\\d*)?");
    smSpecialSymbols = new HashMap();
    smSpecialWords = new HashMap();
    smSpecialDomains = new HashMap();
    smTimeZones = new HashMap();
    smPhoneticWords = new HashMap();
    smBigNumbers = new HashMap();
    smLongPricePatternPoundPriceLbs = Pattern.compile("(\\d+)[.,]?(\\d\\d*)?\\s*((\\u004C\\u0062\\u0073)|(\\u004C\\u0062)|(\\u006C\\u0062\\u0073)|(\\u006C\\u0062))");
    smLongPricePatternPoundLbs = Pattern.compile("(\\s+((\\u004C\\u0062\\u0073)|(\\u006C\\u0062\\u0073))\\s+)");
    smLongPricePatternPoundLb = Pattern.compile("(\\s+((\\u004C\\u0062)|(\\u006C\\u0062))\\s+)");
    smLongWeightPatternKg = Pattern.compile("(\\d+)[.,]?(\\d\\d*)?\\s*(\\u006b\\u0067)([ \\ta-zA-Z]*)");
    smLongWeightPatternOz = Pattern.compile("(\\d+)[.,]?(\\d\\d*)?\\s*(\\u006f\\u007a)([ \\ta-zA-Z]*)");
    smLongWeightPatternG = Pattern.compile("(\\d+)[.,]?(\\d\\d*)?\\s*(\\u0067)([ \\ta-zA-Z]*)");
    smLongWeightPatternL = Pattern.compile("(\\d+)[.,]?(\\d\\d*)?\\s*(\\u006c|\\u004c)([ \\ta-zA-Z]*)");
    smMemoName = Pattern.compile("(((\\d*)[_](\\d*))+)[.](\\u0073\\u006e\\u0062)");
    tzPattern = Pattern.compile("([a-zA-Z])");
  }

  private SVoxPronunciationManager(Context paramContext)
  {
    initSpecialWords();
    initDomainNameFragments();
    initSpecialSymbols(paramContext);
    initTimeZones(paramContext);
    initPhoneticWords();
    initBigNumbers(paramContext);
  }

  private String checkPounds(String paramString)
  {
    String str1 = correctPriceString(paramString, smLongPricePatternPound.matcher(paramString), " pound", " pounds", " penny", " pence");
    String str2 = correctPriceStringSymbolAfterString(str1, smLongPricePatternPoundPriceLbs.matcher(str1), " pound", " pounds", " penny", " pence");
    Matcher localMatcher1 = smLongPricePatternPoundLbs.matcher(str2);
    while (localMatcher1.find())
      str2 = str2.replace(localMatcher1.group(1), " pounds");
    Matcher localMatcher2 = smLongPricePatternPoundLb.matcher(str2);
    while (localMatcher2.find())
      str2 = str2.replace(localMatcher2.group(1), " pound");
    return str2;
  }

  private String correctPriceString(String paramString1, Matcher paramMatcher, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramMatcher.find())
    {
      String str1 = paramMatcher.group();
      paramMatcher.group(1);
      String str2 = paramMatcher.group(2);
      String str3 = paramMatcher.group(3);
      if (Float.valueOf(Float.parseFloat(str2)).intValue() == 1);
      String str6;
      for (String str4 = paramString2; ; str4 = paramString3)
      {
        if (str3 != null)
          break label97;
        str6 = str2 + str4;
        paramString1 = paramString1.replace(str1, str6);
        break;
      }
      label97: if (Float.valueOf(Float.parseFloat(str3)).intValue() == 1);
      for (String str5 = paramString4; ; str5 = paramString5)
      {
        str6 = str2 + str4 + " " + str3 + str5;
        break;
      }
    }
    return paramString1;
  }

  private String correctPriceStringSymbolAfterString(String paramString1, Matcher paramMatcher, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (paramMatcher.find())
    {
      String str1 = paramMatcher.group();
      paramMatcher.group(3);
      String str2 = paramMatcher.group(1);
      String str3 = paramMatcher.group(2);
      if (Float.valueOf(Float.parseFloat(str2)).intValue() == 1);
      String str6;
      for (String str4 = paramString2; ; str4 = paramString3)
      {
        if (str3 != null)
          break label97;
        str6 = str2 + str4;
        paramString1 = paramString1.replaceFirst(str1, str6);
        break;
      }
      label97: if (Float.valueOf(Float.parseFloat(str3)).intValue() == 1);
      for (String str5 = paramString4; ; str5 = paramString5)
      {
        str6 = str2 + str4 + " " + str3 + str5;
        break;
      }
    }
    return paramString1;
  }

  private String correctWeightStringSymbol(String paramString1, Matcher paramMatcher, String paramString2, String paramString3)
  {
    while (paramMatcher.find())
    {
      String str1 = paramMatcher.group();
      paramMatcher.group(3);
      String str2 = paramMatcher.group(1);
      String str3 = paramMatcher.group(2);
      String str4 = paramMatcher.group(4).replaceAll(" ", "");
      Float localFloat = Float.valueOf(Float.parseFloat(str2));
      if ((str4 != null) && (str4.length() != 0))
        continue;
      String str5;
      if (localFloat.intValue() == 1)
      {
        str5 = paramString2;
        label82: if (str3 == null)
          break label138;
      }
      label138: for (String str6 = str2 + "." + str3 + str5; ; str6 = str2 + str5)
      {
        paramString1 = paramString1.replaceFirst(str1, str6);
        break;
        str5 = paramString3;
        break label82;
      }
    }
    return paramString1;
  }

  private String findAndFormatPhoneNumber(String paramString)
  {
    Vector localVector = new Vector();
    localVector.add(Pattern.compile("[Mm]essage to (\\d+)"));
    localVector.add(Pattern.compile("[Cc]alling (\\d+)"));
    Iterator localIterator = localVector.iterator();
    while (localIterator.hasNext())
    {
      Matcher localMatcher = ((Pattern)localIterator.next()).matcher(paramString);
      if (!localMatcher.find())
        continue;
      String str1 = localMatcher.group(1);
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder();
      localSpannableStringBuilder.clear();
      localSpannableStringBuilder.append(str1);
      PhoneNumberUtils.formatNumber(localSpannableStringBuilder, 1);
      String str2 = localSpannableStringBuilder.toString();
      if (!str2.contains("-"))
        str2 = str2.replace("", " ").trim();
      paramString = paramString.replaceAll(str1, str2).replace("-", "");
    }
    return paramString;
  }

  protected static SVoxPronunciationManager getInstance(Context paramContext)
  {
    if (smInstance == null)
      smInstance = new SVoxPronunciationManager(paramContext);
    return smInstance;
  }

  private void initBigNumbers(Context paramContext)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131165275);
    int i = arrayOfString1.length;
    for (int j = 0; j < i; j++)
    {
      String[] arrayOfString2 = arrayOfString1[j].split("/");
      smBigNumbers.put(arrayOfString2[0], arrayOfString2[1]);
    }
  }

  private void initDomainNameFragments()
  {
  }

  private void initDomainNameSpecialWordsFull()
  {
  }

  private void initPhoneticWords()
  {
  }

  private void initSpecialSymbols(Context paramContext)
  {
    String[] arrayOfString1 = paramContext.getResources().getStringArray(2131165273);
    int i = arrayOfString1.length;
    for (int j = 0; j < i; j++)
    {
      String[] arrayOfString2 = arrayOfString1[j].split(" ");
      smSpecialSymbols.put(Character.valueOf(arrayOfString2[0].charAt(0)), arrayOfString2[1]);
    }
  }

  private void initSpecialWords()
  {
    smSpecialWords.put("℃", "°C");
    smSpecialWords.put("℉", "°F");
    smSpecialWords.put("\\b1\\s*°[^C]", "1 degree");
    initDomainNameSpecialWordsFull();
  }

  private void initTimeZones(Context paramContext)
  {
    for (String str1 : paramContext.getResources().getStringArray(2131165274))
    {
      String str2 = tzPattern.matcher(str1).replaceAll("$1 ");
      smTimeZones.put(str1, str2);
    }
  }

  private String processHeight(String paramString)
  {
    return paramString.replaceAll("(\\d+)'(\\d+)\"", "$1 feet $2 inches").replaceAll("(\\d+)' (\\d+)\"", "$1 feet $2 inches");
  }

  protected String prepareText(String paramString)
  {
    return processMarks(processBigNumbers(processPhonetic(processHeight(processSpecialPrices(processTime(processPhoneNumbers(processSpecialDomainNames(processSpecialWords(processWeather(processSpecialSymbols(processDates(paramString))))))))))));
  }

  protected String processBigNumbers(String paramString)
  {
    Iterator localIterator = smBigNumbers.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((((String)localEntry.getKey()).length() == 4) && (paramString.contains((CharSequence)localEntry.getKey())))
      {
        for (int i = paramString.indexOf((String)localEntry.getKey()) + ((String)localEntry.getKey()).length(); paramString.charAt(i) != ' '; i++);
        paramString = paramString.substring(0, i).replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue()) + "th" + paramString.substring(i);
        continue;
      }
      paramString = paramString.replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue());
    }
    return paramString;
  }

  protected String processDates(String paramString)
  {
    return paramString.replaceAll("\\b(\\d\\d?th)\\s?-\\s?(\\d\\d?th)\\b", "$1 to $2").replaceAll("(\\d\\d?:\\d\\d)\\s*-\\s*(\\d\\d?:\\d\\d)", "$1 to $2");
  }

  protected String processMarks(String paramString)
  {
    return paramString.replaceAll("!+", "!").replaceAll("\\?+", "?").replaceAll("[!\\?]+", "!?");
  }

  protected String processPhoneNumbers(String paramString)
  {
    String str1 = findAndFormatPhoneNumber(paramString);
    Matcher localMatcher = smPhoneNumberPattern.matcher(str1);
    while (localMatcher.find())
    {
      String str2 = localMatcher.group().replace('-', ',').replaceAll("(\\d{1})", " $1");
      str1 = str1.replace(localMatcher.group(), str2);
    }
    return str1;
  }

  protected String processPhonetic(String paramString)
  {
    Iterator localIterator = smPhoneticWords.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramString = paramString.replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue());
    }
    return paramString;
  }

  protected String processSpecialDomainNames(String paramString)
  {
    Iterator localIterator = smSpecialDomains.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramString = paramString.replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue());
    }
    return paramString;
  }

  protected String processSpecialPrices(String paramString)
  {
    String str1 = paramString.replaceAll("([\\$£€₩])\\s*(\\d+)[Kk]", "$1$2000").replaceAll("([\\$£€₩])\\s*(\\d+[,.]?\\d*)\\s*([+-])\\s*(\\d+[,.]?\\d*)", "$1$2 $3 $1$4");
    String str2 = correctPriceString(str1, smLongPricePatternDollar.matcher(str1), " dollar", " dollars", " cent", " cents");
    String str3 = checkPounds(correctPriceString(str2, smLongPricePatternEuro.matcher(str2), " euro", " euros", " cent", " cents"));
    String str4 = correctWeightStringSymbol(str3, smLongWeightPatternKg.matcher(str3), " kilogram ", " kilograms ");
    String str5 = correctWeightStringSymbol(str4, smLongWeightPatternG.matcher(str4), " gram ", "  grams ");
    String str6 = correctWeightStringSymbol(str5, smLongWeightPatternL.matcher(str5), " liter ", " liters ");
    String str7 = correctWeightStringSymbol(str6, smLongWeightPatternOz.matcher(str6), " ounce ", " ounces ");
    String str8 = correctPriceString(str7, smLongPricePatternWonHalfWidth.matcher(str7), " won", " won", " won", " won");
    String str9 = correctPriceString(str8, smLongPricePatternWonFullWidth.matcher(str8), " won", " won", " won", " won");
    Matcher localMatcher = smMemoName.matcher(str9);
    while (localMatcher.find())
    {
      String str10 = localMatcher.group(1);
      str9 = str9.replaceFirst(localMatcher.group(1), str10.replace("_", "").trim().replace("", " ").trim());
    }
    return str9;
  }

  protected String processSpecialSymbols(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (!smSpecialSymbols.containsKey(Character.valueOf(c)))
        continue;
      localArrayList.add(Character.valueOf(c));
    }
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      Character localCharacter = (Character)localIterator.next();
      paramString = paramString.replace(localCharacter.toString(), (CharSequence)smSpecialSymbols.get(localCharacter));
    }
    return paramString;
  }

  protected String processSpecialWords(String paramString)
  {
    Iterator localIterator = smSpecialWords.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramString = paramString.replaceAll((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    return paramString;
  }

  protected String processTime(String paramString)
  {
    String str1 = paramString.replaceAll("\\bhrs\\b", "hours").replaceAll("(\\d+)(am)", "$1 A M").replaceAll("(\\d+)(pm)", "$1 P M");
    int i;
    String str2;
    if ((str1.contains("P M")) || (str1.contains("A M")) || (str1.contains(" AM")) || (str1.contains(" PM")) || (str1.contains(" am")) || (str1.contains(" pm")))
    {
      i = 1;
      if ((!str1.matches(".* (00:00).*")) && (!str1.matches(".* (0:00).*")))
        break label176;
      str2 = str1.replaceAll(" (00:00 AM)", " midnight ").replaceAll(" (0:00 AM)", " midnight ").replaceAll(" (00:00 A M)", " midnight ").replaceAll(" (0:00 A M)", " midnight ").replaceAll(" (00:00)", " midnight ").replaceAll(" (0:00)", " midnight ");
    }
    while (true)
    {
      return str2;
      i = 0;
      break;
      label176: if ((str1.matches(".* (12:00).*AM.*")) || (str1.matches(".* (12:00).*A M.*")) || (str1.matches(".* (12:00).*am.*")) || (str1.matches(".* (12:00).*a m.*")))
      {
        str2 = str1.replaceAll(" (12:00 AM)", " midnight ").replaceAll(" (12:00 A M)", " midnight ");
        continue;
      }
      if ((str1.matches(".* (12:00).*PM.*")) || (str1.matches(".* (12:00).*P M.*")) || (str1.matches(".* (12:00).*pm.*")) || (str1.matches(".* (12:00).*p m.*")))
      {
        str2 = str1.replaceAll(" (12:00 PM)", " noon ").replaceAll(" (12:00 P M)", " noon ");
        continue;
      }
      if (str1.matches(".* (12:00).*"))
      {
        str2 = str1.replaceAll(" (12:00 PM)", " noon ").replaceAll(" (12:00 P M)", " noon ");
        continue;
      }
      if (str1.matches(".* (12:00).*"))
      {
        str2 = str1.replaceAll(" (12:00 PM)", " noon ").replaceAll(" (12:00 P M)", " noon ");
        continue;
      }
      if ((str1.matches(".*(\\d+\\d+):(00).*")) && (i == 0))
      {
        str2 = str1.replaceAll("(\\d+\\d+):(00)", "$1 hundred ");
        continue;
      }
      if ((str1.matches(".*(\\d+\\d+):(00).*")) && (i != 0))
      {
        str2 = str1.replaceAll("(\\d+\\d+):(00)", "$1 ");
        continue;
      }
      if ((str1.matches(".*(\\d+):(00).*")) && (i == 0))
      {
        str2 = str1.replaceAll("(\\d+):(00)", "$1 hundred ");
        continue;
      }
      if ((str1.matches(".*(\\d+):(00).*")) && (i != 0))
      {
        str2 = str1.replaceAll("(\\d+):(00)", "$1 ");
        continue;
      }
      str2 = str1.replaceAll("(\\d+):(0)(\\d)", "$1 O $3 ");
    }
  }

  protected String processTimeZones(String paramString)
  {
    Iterator localIterator = smTimeZones.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramString = paramString.replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue());
    }
    return paramString;
  }

  protected String processWeather(String paramString)
  {
    return paramString.replaceAll("(\\d+)\\s*([FC])\\s*-\\s*(\\d+)\\s*([FC])\\b", "$1°$2 to $3°$4");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.tss.SVoxPronunciationManager
 * JD-Core Version:    0.6.0
 */