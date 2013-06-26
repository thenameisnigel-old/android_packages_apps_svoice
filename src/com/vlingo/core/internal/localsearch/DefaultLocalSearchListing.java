package com.vlingo.core.internal.localsearch;

import android.telephony.PhoneNumberUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DefaultLocalSearchListing extends LocalSearchListing
{
  public static final String FIELD_ADDR_CITY = "City";
  public static final String FIELD_ADDR_STATE = "State";
  public static final String FIELD_ADDR_STREET_NAME = "StName";
  public static final String FIELD_ADDR_STREET_NUMBER = "StNum";
  public static final String FIELD_ADDR_ZIP = "Zip";
  public static final String FIELD_BUSINESS_HOURS = "BizHrs";
  public static final String FIELD_CALL_PAYABLE = "CallPayable";
  public static final String FIELD_CAPTION = "Caption";
  public static final String FIELD_CATEGORY = "Category";
  public static final String FIELD_CLICK_PAYABLE = "ClickPayable";
  public static final String FIELD_CLICK_URL = "ClickUrl";
  public static final String FIELD_DISTANCE = "Dist";
  public static final String FIELD_IMAGE_URL = "ImgUrl";
  public static final String FIELD_LATITUDE = "Lat";
  public static final String FIELD_LISTING_ID = "ListingID";
  public static final String FIELD_LONGITUDE = "Lon";
  public static final String FIELD_NAME = "Name";
  public static final String FIELD_PHONE_NUMBER = "PhoneNumber";
  public static final String FIELD_PROVIDER = "Provider";
  public static final String FIELD_RATING = "Rating";
  public static final String FIELD_RESERVE_URL = "ReservationURL";
  public static final String FIELD_REVIEW_AUTHOR = "Author";
  public static final String FIELD_REVIEW_BODY = "Body";
  public static final String FIELD_REVIEW_COUNT = "RevCnt";
  public static final String FIELD_REVIEW_DATE = "Date";
  public static final String FIELD_REVIEW_ID = "Id";
  public static final String FIELD_REVIEW_RATING = "Rating";
  public static final String FIELD_REVIEW_TITLE = "Title";
  public static final String FIELD_SYNOPSIS = "Synopsis";
  public static final String FIELD_TEASER = "Teaser";
  public static final String FIELD_TYPE = "Type";
  public static final String FIELD_URL = "Url";
  public static final String TYPE_ORGANIC = "Reg";
  public static final String TYPE_SPONSORED = "Spon";

  public DefaultLocalSearchListing(String paramString)
  {
    super("ListingID", paramString);
  }

  private void getAddressLine1(StringBuilder paramStringBuilder)
  {
    if (hasValue("StName"))
    {
      if (hasValue("StNum"))
      {
        paramStringBuilder.append(getString("StNum"));
        paramStringBuilder.append(" ");
      }
      paramStringBuilder.append(getString("StName"));
    }
  }

  private void getAddressLine2(StringBuilder paramStringBuilder)
  {
    if (hasValue("City"))
    {
      if (paramStringBuilder.length() > 0)
        paramStringBuilder.append(", ");
      paramStringBuilder.append(getString("City"));
    }
    if (hasValue("State"))
    {
      if (paramStringBuilder.length() > 0)
        paramStringBuilder.append(", ");
      paramStringBuilder.append(getString("State"));
    }
  }

  public String getAddressLine1()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    getAddressLine1(localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getAddressLine2()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    getAddressLine2(localStringBuilder);
    return localStringBuilder.toString();
  }

  public String getCaption()
  {
    return getString("Caption");
  }

  public String getCityState()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (hasValue("City"))
    {
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(", ");
      localStringBuilder.append(getString("City"));
      if (hasValue("State"))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(" ");
        localStringBuilder.append(getString("State"));
      }
    }
    return localStringBuilder.toString();
  }

  public String getClickUrl()
  {
    return getString("ClickUrl");
  }

  public String getDistanceString()
  {
    double d;
    DecimalFormat localDecimalFormat;
    if (hasValue("Dist"))
    {
      d = getDouble("Dist", -1.0D);
      localDecimalFormat = new DecimalFormat("######.##");
    }
    for (String str = localDecimalFormat.format(d) + " mi"; ; str = "")
      return str;
  }

  public String getFullAddress()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (hasValue("StName"))
    {
      if (hasValue("StNum"))
      {
        localStringBuilder.append(getString("StNum"));
        localStringBuilder.append(" ");
      }
      localStringBuilder.append(getString("StName"));
    }
    if (hasValue("City"))
    {
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("\n");
      localStringBuilder.append(getString("City"));
      if (hasValue("State"))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(", ");
        localStringBuilder.append(getString("State"));
      }
    }
    return localStringBuilder.toString();
  }

  public String getListingID()
  {
    return getString("ListingID");
  }

  public String getName()
  {
    return getString("Name");
  }

  public String getPhoneNumber()
  {
    return getString("PhoneNumber");
  }

  public String getPhoneNumberFormatted()
  {
    return PhoneNumberUtils.formatNumber(getPhoneNumber());
  }

  public String getProvider()
  {
    return getString("Provider");
  }

  public double getRating()
  {
    return getDouble("Rating", 0.0D);
  }

  public String getReserveUrl()
  {
    return getString("ReservationURL");
  }

  public int getReviewCount()
  {
    return getInteger("RevCnt", 0);
  }

  public String getSynopsis()
  {
    return getString("Synopsis");
  }

  public String getUrl()
  {
    return getString("Url");
  }

  public boolean isOrganic()
  {
    return "Reg".equalsIgnoreCase(getString("Type"));
  }

  public boolean isSponsored()
  {
    return "Spon".equalsIgnoreCase(getString("Type"));
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(" LocalSearchListing [");
    localStringBuilder.append("\n Name :").append(getName());
    localStringBuilder.append("\n AddressLine1 :").append(getAddressLine1());
    localStringBuilder.append("\n AddressLine2 :").append(getAddressLine2());
    localStringBuilder.append("\n CityState :").append(getCityState());
    localStringBuilder.append("\n FullAddress :").append(getFullAddress());
    localStringBuilder.append("\n DistanceString :").append(getDistanceString());
    localStringBuilder.append("\n ListingID :").append(getListingID());
    localStringBuilder.append("\n PhoneNumber :").append(getPhoneNumber());
    localStringBuilder.append("\n Rating :").append(getRating());
    localStringBuilder.append("\n ReviewCount :").append(getReviewCount());
    localStringBuilder.append("\n Reviews : {").append(getReviews().toString()).append("}");
    localStringBuilder.append("\n hasReviews :").append(hasReviews());
    localStringBuilder.append("\n isOrganic :").append(isOrganic());
    localStringBuilder.append("\n isSponsored :").append(isSponsored());
    localStringBuilder.append("\n areMoreDetailsAvailable :").append(areMoreDetailsAvailable());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.localsearch.DefaultLocalSearchListing
 * JD-Core Version:    0.6.0
 */