package com.vlingo.core.internal.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtils
{
  private static final String[][] XML_ESCAPE;
  public static final Set<Character.UnicodeBlock> chineseUnicodeBlocks;
  public static final Set<Character.UnicodeBlock> nonAsciiWithCaseUnicodeBlocks;
  private static final Pattern varPattern;

  static
  {
    String[][] arrayOfString; = new String[5][];
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "&quot;";
    arrayOfString1[1] = "\"";
    arrayOfString;[0] = arrayOfString1;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "&amp;";
    arrayOfString2[1] = "&";
    arrayOfString;[1] = arrayOfString2;
    String[] arrayOfString3 = new String[2];
    arrayOfString3[0] = "&lt;";
    arrayOfString3[1] = "<";
    arrayOfString;[2] = arrayOfString3;
    String[] arrayOfString4 = new String[2];
    arrayOfString4[0] = "&gt;";
    arrayOfString4[1] = ">";
    arrayOfString;[3] = arrayOfString4;
    String[] arrayOfString5 = new String[2];
    arrayOfString5[0] = "&apos;";
    arrayOfString5[1] = "'";
    arrayOfString;[4] = arrayOfString5;
    XML_ESCAPE = arrayOfString;;
    varPattern = Pattern.compile("\\$\\[(.+?)\\]");
    chineseUnicodeBlocks = Collections.unmodifiableSet(new HashSet()
    {
    });
    nonAsciiWithCaseUnicodeBlocks = Collections.unmodifiableSet(new HashSet()
    {
    });
  }

  public static boolean arePhoneNumbersTheSame(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    for (boolean bool = false; ; bool = paramString1.equals(paramString2))
    {
      return bool;
      if (paramString1.startsWith("+"))
        paramString1 = paramString1.substring(1, paramString1.length());
      if (paramString2.startsWith("+"))
        paramString2 = paramString2.substring(1, paramString2.length());
      if (paramString1.startsWith("1"))
        paramString1 = paramString1.substring(1, paramString1.length());
      if (!paramString2.startsWith("1"))
        continue;
      paramString2 = paramString2.substring(1, paramString2.length());
    }
  }

  public static String byteArrayToString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
      localStringBuffer.append(paramArrayOfByte[i]);
    return localStringBuffer.toString();
  }

  private static boolean canTTS(String paramString, int paramInt)
  {
    switch (paramString.charAt(paramInt))
    {
    default:
    case '(':
    case ')':
    case '[':
    case ']':
    case '{':
    case '|':
    case '}':
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public static String capitalize(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    while (true)
    {
      return paramString;
      char[] arrayOfChar = paramString.toCharArray();
      arrayOfChar[0] = Character.toUpperCase(arrayOfChar[0]);
      paramString = new String(arrayOfChar);
    }
  }

  public static String cleanPhoneNumber(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return paramString;
      StringBuffer localStringBuffer = new StringBuffer(paramString.length());
      for (int i = 0; i < paramString.length(); i++)
      {
        char c = paramString.charAt(i);
        if ((c < '0') || (c > '9'))
          continue;
        localStringBuffer.append(c);
      }
      paramString = localStringBuffer.toString();
    }
  }

  public static String cleanTts(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramString.length());
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      if (!canTTS(paramString, i))
        continue;
      localStringBuffer.append(c);
    }
    return localStringBuffer.toString();
  }

  public static int compareVersions(String paramString1, String paramString2)
  {
    int i = 0;
    if (paramString1.equals(paramString2));
    label116: 
    while (true)
    {
      return i;
      String[] arrayOfString1 = split(paramString1, '.');
      String[] arrayOfString2 = split(paramString2, '.');
      int j = Math.max(arrayOfString1.length, arrayOfString2.length);
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label116;
        int m = 0;
        int n = 0;
        if (k < arrayOfString1.length)
          m = Integer.parseInt(arrayOfString1[k]);
        if (k < arrayOfString2.length)
          n = Integer.parseInt(arrayOfString2[k]);
        if (m > n)
        {
          i = 1;
          break;
        }
        if (m >= n)
          continue;
        i = -1;
        break;
      }
    }
  }

  public static boolean containsString(String paramString1, String paramString2)
  {
    int i = 1;
    if ((paramString1 == null) || (paramString2 == null))
      if (paramString1 != paramString2);
    while (true)
    {
      return i;
      i = 0;
      continue;
      String str = paramString1.toLowerCase();
      if (paramString2.toLowerCase().contains(str))
        continue;
      i = 0;
    }
  }

  public static boolean containsWord(String paramString1, String paramString2)
  {
    int i = 1;
    if ((paramString1 == null) || (paramString2 == null))
      if (paramString1 != paramString2);
    while (true)
    {
      return i;
      i = 0;
      continue;
      String str1 = paramString1.toLowerCase();
      String str2 = paramString2.toLowerCase();
      String[] arrayOfString = str1.split("\\s+");
      int j = arrayOfString.length;
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label75;
        if (str2.contains(arrayOfString[k]))
          break;
      }
      label75: i = 0;
    }
  }

  public static final String convertBytesToString(byte[] paramArrayOfByte)
  {
    try
    {
      str = new String(paramArrayOfByte, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        String str = new String(paramArrayOfByte);
    }
  }

  public static int convertDayOfWeekToInt(String paramString)
  {
    int i;
    if ((paramString.equalsIgnoreCase("sunday")) || (paramString.equalsIgnoreCase("sun")))
      i = 0;
    while (true)
    {
      return i;
      if ((paramString.equalsIgnoreCase("monday")) || (paramString.equalsIgnoreCase("mon")))
      {
        i = 1;
        continue;
      }
      if ((paramString.equalsIgnoreCase("tuesday")) || (paramString.equalsIgnoreCase("tue")))
      {
        i = 2;
        continue;
      }
      if ((paramString.equalsIgnoreCase("wednesday")) || (paramString.equalsIgnoreCase("wed")))
      {
        i = 3;
        continue;
      }
      if ((paramString.equalsIgnoreCase("thursday")) || (paramString.equalsIgnoreCase("thu")))
      {
        i = 4;
        continue;
      }
      if ((paramString.equalsIgnoreCase("friday")) || (paramString.equalsIgnoreCase("fri")))
      {
        i = 5;
        continue;
      }
      if ((paramString.equalsIgnoreCase("saturday")) || (paramString.equalsIgnoreCase("sat")))
      {
        i = 6;
        continue;
      }
      i = -1;
    }
  }

  public static final byte[] convertStringToBytes(String paramString)
  {
    try
    {
      byte[] arrayOfByte2 = paramString.getBytes("UTF-8");
      arrayOfByte1 = arrayOfByte2;
      return arrayOfByte1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        byte[] arrayOfByte1 = null;
    }
  }

  public static String formatPhoneNumber(String paramString)
  {
    return formatPhoneNumber(paramString, true);
  }

  public static String formatPhoneNumber(String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.length() == 0))
      paramString = "";
    String str1;
    int i;
    StringBuffer localStringBuffer;
    label62: label117: 
    do
    {
      while (true)
      {
        return paramString;
        str1 = cleanPhoneNumber(paramString);
        i = str1.length();
        if (i == 0)
        {
          paramString = "";
          continue;
        }
        localStringBuffer = new StringBuffer(i + 16);
        int j;
        String str6;
        int m;
        String str12;
        if (str1.charAt(0) == '1')
        {
          j = 1;
          if (paramString.startsWith("+"))
            localStringBuffer.append("+");
          if (j == 0)
            break label485;
          str6 = str1.substring(1);
          m = i - 1;
          if ((m < 3) || (m > 6))
            break label197;
          if (!paramBoolean)
            break label183;
          str12 = "1 (";
          localStringBuffer.append(str12);
          localStringBuffer.append(str6.substring(0, 3));
          if (!paramBoolean)
            break label190;
        }
        for (String str13 = ") "; ; str13 = "-")
        {
          localStringBuffer.append(str13);
          localStringBuffer.append(str6.substring(3, m));
          paramString = localStringBuffer.toString();
          break;
          j = 0;
          break label62;
          str12 = "1-";
          break label117;
        }
        if ((m >= 7) && (m <= 10))
        {
          String str10;
          if (paramBoolean)
          {
            str10 = "1 (";
            localStringBuffer.append(str10);
            localStringBuffer.append(str6.substring(0, 3));
            if (!paramBoolean)
              break label309;
          }
          for (String str11 = ") "; ; str11 = "-")
          {
            localStringBuffer.append(str11);
            localStringBuffer.append(str6.substring(3, 6));
            localStringBuffer.append("-");
            localStringBuffer.append(str6.substring(6, m));
            paramString = localStringBuffer.toString();
            break;
            str10 = "1-";
            break label219;
          }
        }
        if (m <= 10)
          continue;
        int n = m - 10;
        String str7;
        String str8;
        if (paramBoolean)
        {
          str7 = "1 ";
          localStringBuffer.append(str7);
          localStringBuffer.append(str6.substring(0, n));
          if (!paramBoolean)
            break label471;
          str8 = " (";
          localStringBuffer.append(str8);
          localStringBuffer.append(str6.substring(n, n + 3));
          if (!paramBoolean)
            break label478;
        }
        for (String str9 = ") "; ; str9 = "-")
        {
          localStringBuffer.append(str9);
          localStringBuffer.append(str6.substring(n + 3, n + 6));
          localStringBuffer.append("-");
          localStringBuffer.append(str6.substring(n + 6, m));
          paramString = localStringBuffer.toString();
          break;
          str7 = "1-";
          break label338;
          str8 = "-";
          break label368;
        }
        if ((i < 4) || (i > 7))
          break;
        localStringBuffer.append(str1.substring(0, 3));
        localStringBuffer.append("-");
        localStringBuffer.append(str1.substring(3, i));
        paramString = localStringBuffer.toString();
      }
      if ((i < 8) || (i > 10))
        continue;
      String str4;
      if (paramBoolean)
      {
        str4 = "(";
        localStringBuffer.append(str4);
        localStringBuffer.append(str1.substring(0, 3));
        if (!paramBoolean)
          break label643;
      }
      for (String str5 = ") "; ; str5 = "-")
      {
        localStringBuffer.append(str5);
        localStringBuffer.append(str1.substring(3, 6));
        localStringBuffer.append("-");
        localStringBuffer.append(str1.substring(6, i));
        paramString = localStringBuffer.toString();
        break;
        str4 = "";
        break label557;
      }
    }
    while (i <= 10);
    label183: label190: label197: label219: label368: int k = i - 10;
    label309: label338: label471: label478: label485: localStringBuffer.append(str1.substring(0, k));
    label557: String str2;
    label643: if (paramBoolean)
    {
      str2 = " (";
      label683: localStringBuffer.append(str2);
      localStringBuffer.append(str1.substring(k, k + 3));
      if (!paramBoolean)
        break label782;
    }
    label782: for (String str3 = ") "; ; str3 = "-")
    {
      localStringBuffer.append(str3);
      localStringBuffer.append(str1.substring(k + 3, k + 6));
      localStringBuffer.append("-");
      localStringBuffer.append(str1.substring(k + 6, i));
      paramString = localStringBuffer.toString();
      break;
      str2 = "-";
      break label683;
    }
  }

  public static final String formatPhoneNumberForTTS(String paramString)
  {
    if ((!isNullOrWhiteSpace(paramString)) && (paramString.matches("[0-9()\\+\\-\\s]+")))
      paramString = paramString.replaceAll("\\D", "").replaceAll("([0-9])", "$1 ").trim();
    return paramString;
  }

  public static String getHostname(String paramString)
  {
    int i = paramString.indexOf("://");
    if (i == -1);
    int j;
    for (String str = ""; ; str = paramString.substring(i + 3, j))
    {
      return str;
      j = paramString.indexOf("/", i + 3);
      if (j != -1)
        continue;
      j = paramString.indexOf(";", i + 3);
      if (j != -1)
        continue;
      j = paramString.length();
    }
  }

  private static String getRandomEmail()
  {
    return getRandomString().replace('@', '_') + "@" + getRandomString().replace('@', '_') + ".com";
  }

  private static String getRandomPhoneNumber()
  {
    long l = (1L + Math.abs(new Random().nextLong())) % 10000000000L;
    return "1" + l;
  }

  private static String getRandomPhoneNumberType()
  {
    return String.valueOf(Math.abs(new Random().nextInt() % 8));
  }

  private static String getRandomString()
  {
    Random localRandom = new Random();
    String str = "";
    int i = 1 + Math.abs(localRandom.nextInt() % 9);
    for (int j = 0; j < i; j++)
    {
      char c = (char)localRandom.nextInt();
      str = str + c;
    }
    return str;
  }

  private static String getRandomUID()
  {
    long l = (1L + Math.abs(new Random().nextLong())) % 10000000000L;
    return "1" + l;
  }

  public static String getSubstring(String paramString1, String paramString2, String paramString3)
  {
    String str = null;
    if (paramString1 == null);
    while (true)
    {
      return str;
      int i = 0;
      int j = paramString1.length();
      if (paramString2 != null)
      {
        int k = paramString1.indexOf(paramString2);
        if (k < 0)
          continue;
        i = k + paramString2.length();
      }
      if (paramString3 != null)
      {
        j = paramString1.indexOf(paramString3, i);
        if (j < 0)
          continue;
      }
      str = paramString1.substring(i, j);
    }
  }

  public static final String getWordAtCursor(String paramString, int paramInt)
  {
    String str = "";
    if ((paramInt >= 0) && (paramInt < paramString.length()))
    {
      String[] arrayOfString = split(paramString.toLowerCase(), ' ');
      int i = arrayOfString[0].length();
      int j = 0;
      while ((paramInt > i) && (j < -1 + arrayOfString.length))
      {
        j++;
        i += arrayOfString[j].length();
      }
      if (j <= arrayOfString.length)
        str = arrayOfString[j];
    }
    return str;
  }

  public static boolean isChineseString(String paramString)
  {
    return chineseUnicodeBlocks.contains(Character.UnicodeBlock.of(paramString.charAt(0)));
  }

  public static boolean isDigit(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isEqual(String paramString1, String paramString2)
  {
    boolean bool = false;
    if ((paramString1 == null) && (paramString2 == null));
    for (bool = true; ; bool = paramString1.equals(paramString2))
      do
        return bool;
      while (((paramString1 != null) && (paramString2 == null)) || ((paramString1 == null) && (paramString2 != null)));
  }

  public static boolean isIPAddress(String paramString)
  {
    int i = 0;
    if ((isNullOrWhiteSpace(paramString)) || (paramString.indexOf('.') == -1));
    while (true)
    {
      return i;
      String str = paramString.trim();
      int j = 0;
      int[] arrayOfInt = new int[5];
      arrayOfInt[i] = -1;
      arrayOfInt[4] = str.length();
      for (int k = 0; ; k++)
      {
        if ((k >= str.length()) || (j >= 4))
          break label110;
        char c = str.charAt(k);
        if ((!Character.isDigit(c)) && (c != '.'))
          break;
        if (c != '.')
          continue;
        j++;
        arrayOfInt[j] = k;
      }
      label110: if (j != 3)
        continue;
      int m = 1;
      if (m < 5)
      {
        int n = m - 1;
        try
        {
          int i1 = Integer.valueOf(str.substring(1 + arrayOfInt[n], arrayOfInt[m])).intValue();
          if ((i1 < 0) || (i1 > 255))
            continue;
          m++;
        }
        catch (Exception localException)
        {
        }
        continue;
      }
      i = 1;
    }
  }

  public static boolean isNullOrWhiteSpace(String paramString)
  {
    if ((paramString == null) || ("".equals(paramString.trim())));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean isPhoneNumber(String paramString)
  {
    int i = 0;
    int j = paramString.length();
    if (j == 0);
    while (true)
    {
      return i;
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label82;
        int m = paramString.charAt(k);
        if (((m < 48) || (m > 57)) && (m != 32) && (m != 45) && (m != 43) && (m != 40) && (m != 41))
          break;
      }
      label82: i = 1;
    }
  }

  public static boolean isVersionAtLeast(String paramString1, String paramString2)
  {
    int i = 0;
    if ((paramString1 == null) || (paramString2 == null));
    while (true)
    {
      return i;
      int j = compareVersions(paramString1, paramString2);
      if ((j != 0) && (j != 1))
        continue;
      i = 1;
    }
  }

  public static String join(List paramList, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    ListIterator localListIterator = paramList.listIterator();
    while (localListIterator.hasNext())
    {
      localStringBuilder.append(localListIterator.next().toString());
      if (!localListIterator.hasNext())
        continue;
      localStringBuilder.append(paramString);
    }
    return localStringBuilder.toString();
  }

  public static boolean matchesKoreanNamePattern(String paramString1, String paramString2)
  {
    int i = 1;
    if ((paramString1 != null) && (paramString2 != null) && (paramString1.length() == 2) && (paramString2.length() > 2) && (!paramString2.endsWith(paramString1)))
    {
      if (paramString2.lastIndexOf(paramString1) != -3 + paramString2.length())
        break label73;
      if (symbolIsKorean(paramString2.charAt(-1 + paramString2.length())))
        break label68;
    }
    while (true)
    {
      return i;
      label68: i = 0;
      continue;
      label73: i = 0;
    }
  }

  public static boolean nameIsJapanese(String paramString)
  {
    int i = 0;
    if ((paramString != null) && (paramString.length() > 0) && (symbolIsJapanese(paramString.charAt(0))) && (paramString.length() >= 2))
      i = 1;
    return i;
  }

  public static boolean nameIsKorean(String paramString)
  {
    int i = 0;
    if ((paramString != null) && (paramString.length() > 0) && (symbolIsKorean(paramString.charAt(0))) && (paramString.length() >= 2))
      i = 1;
    return i;
  }

  public static boolean partialStringMatch(String paramString1, String paramString2)
  {
    int i = 1;
    if ((paramString1 == null) || (paramString2 == null))
      if (paramString1 != paramString2);
    while (true)
    {
      return i;
      i = 0;
      continue;
      String[] arrayOfString1 = paramString1.split("\\s+");
      String[] arrayOfString2 = paramString2.split("\\s+");
      int j = arrayOfString1.length;
      for (int k = 0; ; k++)
      {
        if (k >= j)
          break label139;
        String str1 = arrayOfString1[k];
        int m = arrayOfString2.length;
        int n = 0;
        if (n >= m)
          continue;
        String str2 = arrayOfString2[n];
        if (str1.length() < str2.length())
          if (str2.toLowerCase().contains(str1.toLowerCase()))
            break;
        do
        {
          n++;
          break;
        }
        while (!str1.toLowerCase().contains(str2.toLowerCase()));
        break;
      }
      label139: i = 0;
    }
  }

  public static String removeFromEnd(String paramString1, String paramString2)
  {
    String str = paramString1.trim();
    if (str.endsWith(paramString2))
      str = str.substring(0, str.length() - paramString2.length());
    return str.trim();
  }

  public static String removePossessives(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return paramString;
      paramString = paramString.replaceAll("'s", "").replaceAll("s'", "s");
    }
  }

  public static final String[] removeTopChoice(String[] paramArrayOfString)
  {
    String[] arrayOfString;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      arrayOfString = null;
    while (true)
    {
      return arrayOfString;
      int i = -1 + paramArrayOfString.length;
      arrayOfString = new String[i];
      for (int j = 0; j < i; j++)
        arrayOfString[j] = paramArrayOfString[(j + 1)];
    }
  }

  public static String replace(String paramString1, String paramString2, String paramString3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramString1.indexOf(paramString2);
    int j = 0;
    int k = paramString2.length();
    while (i != -1)
    {
      localStringBuffer.append(paramString1.substring(j, i)).append(paramString3);
      j = i + k;
      i = paramString1.indexOf(paramString2, j);
    }
    localStringBuffer.append(paramString1.substring(j, paramString1.length()));
    return localStringBuffer.toString();
  }

  public static String replaceHostname(String paramString1, String paramString2)
  {
    int i = paramString1.indexOf("://");
    if (i == -1);
    StringBuffer localStringBuffer;
    for (String str = ""; ; str = localStringBuffer.toString())
    {
      return str;
      int j = paramString1.indexOf("/", i + 3);
      if (j == -1)
      {
        j = paramString1.indexOf(";", i + 3);
        if (j == -1)
          j = paramString1.length();
      }
      localStringBuffer = new StringBuffer(paramString1);
      localStringBuffer.delete(i + 3, j);
      localStringBuffer.insert(i + 3, paramString2);
    }
  }

  public static String replaceTokens(String paramString, Map<String, String> paramMap)
  {
    Matcher localMatcher = varPattern.matcher(paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    while (localMatcher.find())
    {
      String str = (String)paramMap.get(localMatcher.group(1));
      if (str == null)
        continue;
      localMatcher.appendReplacement(localStringBuffer, "");
      localStringBuffer.append(str);
    }
    localMatcher.appendTail(localStringBuffer);
    return localStringBuffer.toString();
  }

  public static String[] split(String paramString, char paramChar)
  {
    String[] arrayOfString;
    if (paramString == null)
      arrayOfString = null;
    while (true)
    {
      return arrayOfString;
      int i = 0;
      for (int j = 0; j < paramString.length(); j++)
      {
        if (paramString.charAt(j) != paramChar)
          continue;
        i++;
      }
      arrayOfString = new String[i + 1];
      int k = 0;
      for (int m = 0; m < i; m++)
      {
        int n = paramString.indexOf(paramChar, k);
        arrayOfString[m] = paramString.substring(k, n);
        k = n + 1;
      }
      arrayOfString[i] = paramString.substring(k);
    }
  }

  public static boolean stringIsNonAsciiWithCase(String paramString)
  {
    int i = 0;
    if (i < paramString.length())
      if (!nonAsciiWithCaseUnicodeBlocks.contains(Character.UnicodeBlock.of(paramString.charAt(i))));
    for (int j = 1; ; j = 0)
    {
      return j;
      i++;
      break;
    }
  }

  public static boolean symbolIsJapanese(char paramChar)
  {
    if ((Character.UnicodeBlock.HIRAGANA == Character.UnicodeBlock.of(paramChar)) || (Character.UnicodeBlock.KATAKANA == Character.UnicodeBlock.of(paramChar)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static boolean symbolIsKorean(char paramChar)
  {
    if (Character.UnicodeBlock.HANGUL_SYLLABLES == Character.UnicodeBlock.of(paramChar));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static String unescapeXml(String paramString)
  {
    Object localObject;
    if (paramString == null)
      localObject = null;
    while (true)
    {
      return localObject;
      try
      {
        StringWriter localStringWriter = new StringWriter(2 * paramString.length());
        new LookupTranslator(XML_ESCAPE).translate(paramString, localStringWriter);
        String str = localStringWriter.toString();
        localObject = str;
      }
      catch (IOException localIOException)
      {
      }
    }
    throw new RuntimeException(localIOException);
  }

  private static class LookupTranslator
  {
    private final int longest;
    private final HashMap<CharSequence, CharSequence> lookupMap = new HashMap();
    private final int shortest;

    public LookupTranslator(CharSequence[][] paramArrayOfCharSequence)
    {
      int i = 2147483647;
      int j = 0;
      if (paramArrayOfCharSequence != null)
      {
        int k = paramArrayOfCharSequence.length;
        for (int m = 0; m < k; m++)
        {
          CharSequence[] arrayOfCharSequence = paramArrayOfCharSequence[m];
          this.lookupMap.put(arrayOfCharSequence[0], arrayOfCharSequence[1]);
          int n = arrayOfCharSequence[0].length();
          if (n < i)
            i = n;
          if (n <= j)
            continue;
          j = n;
        }
      }
      this.shortest = i;
      this.longest = j;
    }

    public int translate(CharSequence paramCharSequence, int paramInt, Writer paramWriter)
      throws IOException
    {
      int i = this.longest;
      if (paramInt + this.longest > paramCharSequence.length())
        i = paramCharSequence.length() - paramInt;
      int j = i;
      if (j >= this.shortest)
      {
        CharSequence localCharSequence1 = paramCharSequence.subSequence(paramInt, paramInt + j);
        CharSequence localCharSequence2 = (CharSequence)this.lookupMap.get(localCharSequence1);
        if (localCharSequence2 != null)
          paramWriter.write(localCharSequence2.toString());
      }
      while (true)
      {
        return j;
        j--;
        break;
        j = 0;
      }
    }

    public final void translate(CharSequence paramCharSequence, Writer paramWriter)
      throws IOException
    {
      if (paramWriter == null)
        throw new IllegalArgumentException("The Writer must not be null");
      if (paramCharSequence == null)
        return;
      int i = 0;
      int j = paramCharSequence.length();
      while (true)
      {
        int k;
        if (i < j)
        {
          k = translate(paramCharSequence, i, paramWriter);
          if (k == 0)
          {
            char[] arrayOfChar = Character.toChars(Character.codePointAt(paramCharSequence, i));
            paramWriter.write(arrayOfChar);
            i += arrayOfChar.length;
            continue;
          }
        }
        else
        {
          break;
        }
        for (int m = 0; m < k; m++)
          i += Character.charCount(Character.codePointAt(paramCharSequence, i));
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.StringUtils
 * JD-Core Version:    0.6.0
 */