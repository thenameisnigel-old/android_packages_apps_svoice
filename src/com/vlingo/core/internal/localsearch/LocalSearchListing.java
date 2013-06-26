package com.vlingo.core.internal.localsearch;

import com.vlingo.core.internal.util.StringUtils;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class LocalSearchListing
{
  ArrayList<Review> m_reviews = new ArrayList();
  private Hashtable<String, Field> m_values = new Hashtable();

  public LocalSearchListing(String paramString1, String paramString2)
  {
    putValue(paramString1, paramString2);
  }

  private Review getReviewById(String paramString)
  {
    int j;
    Review localReview;
    if (!StringUtils.isNullOrWhiteSpace(paramString))
    {
      int i = this.m_reviews.size();
      j = 0;
      if (j < i)
      {
        localReview = (Review)this.m_reviews.get(j);
        if (!paramString.equalsIgnoreCase(localReview.id));
      }
    }
    while (true)
    {
      return localReview;
      j++;
      break;
      localReview = null;
    }
  }

  private void removeReviewById(String paramString)
  {
    Review localReview = getReviewById(paramString);
    if (localReview != null)
      this.m_reviews.remove(localReview);
  }

  void addReview(Review paramReview)
  {
    removeReviewById(paramReview.id);
    this.m_reviews.add(paramReview);
  }

  public boolean areMoreDetailsAvailable()
  {
    Enumeration localEnumeration = this.m_values.keys();
    String str;
    do
    {
      if (!localEnumeration.hasMoreElements())
        break;
      str = (String)localEnumeration.nextElement();
    }
    while (!((Field)this.m_values.get(str)).isMoreAvailable);
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean areMoreDetailsAvailable(String paramString)
  {
    Field localField = (Field)this.m_values.get(paramString);
    if (localField != null);
    for (boolean bool = localField.isMoreAvailable; ; bool = false)
      return bool;
  }

  public abstract String getAddressLine1();

  public abstract String getAddressLine2();

  public abstract String getCaption();

  public abstract String getCityState();

  public abstract String getClickUrl();

  public abstract String getDistanceString();

  public double getDouble(String paramString, double paramDouble)
  {
    String str;
    if (hasValue(paramString))
      str = getString(paramString);
    try
    {
      double d = Double.parseDouble(str);
      paramDouble = d;
      label25: return paramDouble;
    }
    catch (Exception localException)
    {
      break label25;
    }
  }

  public abstract String getFullAddress();

  public int getInteger(String paramString, int paramInt)
  {
    String str;
    if (hasValue(paramString))
      str = getString(paramString);
    try
    {
      int i = Integer.parseInt(str);
      paramInt = i;
      label23: return paramInt;
    }
    catch (Exception localException)
    {
      break label23;
    }
  }

  public abstract String getListingID();

  public abstract String getName();

  public abstract String getPhoneNumber();

  public abstract String getPhoneNumberFormatted();

  public abstract String getProvider();

  public abstract double getRating();

  public abstract String getReserveUrl();

  public Review getReview(int paramInt)
  {
    return (Review)this.m_reviews.get(paramInt);
  }

  public abstract int getReviewCount();

  public ArrayList<Review> getReviews()
  {
    return this.m_reviews;
  }

  public String getString(String paramString)
  {
    if (hasValue(paramString));
    for (String str = ((Field)this.m_values.get(paramString)).value; ; str = null)
      return str;
  }

  public String getString(String paramString1, String paramString2)
  {
    String str = getString(paramString1);
    if (str == null);
    while (true)
    {
      return paramString2;
      paramString2 = str;
    }
  }

  public abstract String getSynopsis();

  public abstract String getUrl();

  public boolean hasReviews()
  {
    if ((this.m_reviews.size() > 0) || (getReviewCount() > 0));
    for (int i = 1; ; i = 0)
      return i;
  }

  public boolean hasValue(String paramString)
  {
    if (this.m_values.containsKey(paramString))
    {
      Field localField = (Field)this.m_values.get(paramString);
      if ((localField.value == null) || (localField.value.length() <= 0));
    }
    for (int i = 1; ; i = 0)
      return i;
  }

  public abstract boolean isOrganic();

  public abstract boolean isSponsored();

  public void markValueAsAvailable(String paramString)
  {
    if (this.m_values.containsKey(paramString))
      ((Field)this.m_values.get(paramString)).isMoreAvailable = true;
    while (true)
    {
      return;
      this.m_values.put(paramString, new Field(true));
    }
  }

  public void putValue(String paramString1, String paramString2)
  {
    this.m_values.put(paramString1, new Field(paramString2));
  }

  public class Field
  {
    public boolean isMoreAvailable;
    public String value;

    public Field(String arg2)
    {
      Object localObject;
      this.value = localObject;
      this.isMoreAvailable = false;
    }

    public Field(boolean arg2)
    {
      this.value = null;
      boolean bool;
      this.isMoreAvailable = bool;
    }

    public boolean hasMoreInfo(Field paramField)
    {
      if ((this.value != null) && (!this.value.equals(paramField.value)));
      for (int i = 1; ; i = 0)
        return i;
    }

    public String toString()
    {
      return " Field { value [ " + this.value + " ], isMoreAvailable [" + this.isMoreAvailable + "]";
    }
  }

  public static class Review
  {
    public String author;
    public String body;
    public String date;
    public String id;
    public String rating;
    public String title;

    public float getRating()
    {
      if ((this.rating != null) && (this.rating.length() > 0));
      while (true)
      {
        try
        {
          float f2 = Float.parseFloat(this.rating);
          f1 = f2;
          return f1;
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
        float f1 = 0.0F;
      }
    }

    public String toString()
    {
      return "[id:" + this.id + "] + [date:" + this.date + "] + [rating:" + getRating() + "] + [body:" + this.body + "] + [author:" + this.author + "] + [title:" + this.title + "]";
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.LocalSearchListing
 * JD-Core Version:    0.6.0
 */