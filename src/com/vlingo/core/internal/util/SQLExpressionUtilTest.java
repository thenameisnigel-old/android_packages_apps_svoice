package com.vlingo.core.internal.util;

import android.test.AndroidTestCase;
import android.util.Log;
import java.util.ArrayList;

public class SQLExpressionUtilTest extends AndroidTestCase
{
  private static final String TAG = SQLExpressionUtilTest.class.getName();

  public void escapeQuotesTest()
  {
    assertEquals("Mc''Dc", SQLExpressionUtil.escape("Mc'Dc"));
  }

  public void likeArrayTest()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Lenon";
    arrayOfString[1] = "Star";
    String str = SQLExpressionUtil.like("Artist", arrayOfString);
    Log.d(TAG, "expectedResult=" + "Artist like '%Lenon%' or Artist like '%Star%'");
    Log.d(TAG, "result=" + str);
    assertEquals("Artist like '%Lenon%' or Artist like '%Star%'", str);
  }

  public void likeListTest()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Lenon");
    localArrayList.add("Star");
    String str = SQLExpressionUtil.like("Artist", localArrayList);
    Log.d(TAG, "expectedResult=" + "Artist like '%Lenon%' or Artist like '%Star%'");
    Log.d(TAG, "result=" + str);
    assertEquals("Artist like '%Lenon%' or Artist like '%Star%'", str);
  }

  public void likeTest()
  {
    String str = SQLExpressionUtil.like("Artist", "John Lenon");
    Log.d(TAG, "expectedResult=" + "Artist like '%John Lenon%'");
    Log.d(TAG, "result=" + str);
    assertEquals("Artist like '%John Lenon%'", str);
  }

  public void orTest()
  {
    assertEquals("a=1 or b=2", SQLExpressionUtil.or("a=1", "b=2"));
  }

  protected void setUp()
    throws Exception
  {
    super.setUp();
  }

  protected void tearDown()
    throws Exception
  {
    super.tearDown();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.SQLExpressionUtilTest
 * JD-Core Version:    0.6.0
 */