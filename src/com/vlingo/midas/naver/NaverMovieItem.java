package com.vlingo.midas.naver;

import android.content.Context;
import java.util.Hashtable;

public class NaverMovieItem extends NaverItem
{
  private final Context mContext;

  public NaverMovieItem(Context paramContext, Hashtable<String, String> paramHashtable)
  {
    super(paramHashtable);
    this.mContext = paramContext;
  }

  public String getActors()
  {
    return getStringOrEmptyString("actors");
  }

  public String getBookingUrl()
  {
    return getString("bookingUrl");
  }

  public String getDirectors()
  {
    return getStringOrEmptyString("directors");
  }

  public String getGenre()
  {
    return getFirstCSV("genre");
  }

  public String getGrade()
  {
    return getStringOrEmptyString("grade");
  }

  public GradeInt getGradeToInt(String paramString)
  {
    GradeInt localGradeInt;
    if ((paramString == null) || (paramString.length() == 0))
      localGradeInt = GradeInt.MOVIE_GRADE_UNKNOWN;
    while (true)
    {
      return localGradeInt;
      if (paramString.startsWith("12"))
      {
        localGradeInt = GradeInt.MOVIE_GRADE_12_OVER;
        continue;
      }
      if (paramString.startsWith("15"))
      {
        localGradeInt = GradeInt.MOVIE_GRADE_15_OVER;
        continue;
      }
      if (paramString.startsWith("18"))
      {
        localGradeInt = GradeInt.MOVIE_GRADE_18_OVER;
        continue;
      }
      if (paramString.equals(this.mContext.getString(2131362635)))
      {
        localGradeInt = GradeInt.MOVIE_GRADE_18_OVER;
        continue;
      }
      if (paramString.equals(this.mContext.getString(2131362636)))
      {
        localGradeInt = GradeInt.MOVIE_GRADE_ALL;
        continue;
      }
      localGradeInt = GradeInt.MOVIE_GRADE_UNKNOWN;
    }
  }

  public String getOpenDate()
  {
    return getStringOrEmptyString("opendate").replace('-', '.');
  }

  public String getPlayFlags()
  {
    return getStringOrEmptyString("playflags");
  }

  public String getPosterUrl()
  {
    return getStringOrEmptyString("poster");
  }

  public Float getRating()
  {
    if (getString("rating") != null);
    for (Float localFloat = Float.valueOf(getString("rating")); ; localFloat = Float.valueOf(0.0F))
      return localFloat;
  }

  public String getRunningTime()
  {
    return getStringOrEmptyString("runtime");
  }

  public String getSameNameUrl()
  {
    return getString("samenamesUrl");
  }

  public String getServiceUrl()
  {
    return getString("serviceUrl");
  }

  public boolean isNowShowing()
  {
    getStringOrEmptyString("playflag");
    this.mContext.getString(2131362626);
    if (getStringOrEmptyString("playflag").equals(this.mContext.getString(2131362626)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean isOpenSoon()
  {
    if (getStringOrEmptyString("playflag").equals(this.mContext.getString(2131362625)));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static enum GradeInt
  {
    static
    {
      GradeInt[] arrayOfGradeInt = new GradeInt[5];
      arrayOfGradeInt[0] = MOVIE_GRADE_12_OVER;
      arrayOfGradeInt[1] = MOVIE_GRADE_15_OVER;
      arrayOfGradeInt[2] = MOVIE_GRADE_18_OVER;
      arrayOfGradeInt[3] = MOVIE_GRADE_ALL;
      arrayOfGradeInt[4] = MOVIE_GRADE_UNKNOWN;
      $VALUES = arrayOfGradeInt;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.naver.NaverMovieItem
 * JD-Core Version:    0.6.0
 */