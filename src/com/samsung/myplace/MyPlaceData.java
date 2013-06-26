package com.samsung.myplace;

public class MyPlaceData
{
  private int id;
  private String placeName;
  private int profile_id;
  private String type;
  private String value;

  public MyPlaceData()
  {
  }

  public MyPlaceData(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2)
  {
    this.id = paramInt1;
    this.placeName = paramString1;
    this.type = paramString2;
    this.value = paramString3;
    this.profile_id = paramInt2;
  }

  public int getId()
  {
    return this.id;
  }

  public String getPlaceName()
  {
    return this.placeName;
  }

  public int getProfile_id()
  {
    return this.profile_id;
  }

  public String getType()
  {
    return this.type;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setPlaceName(String paramString)
  {
    this.placeName = paramString;
  }

  public void setProfile_id(int paramInt)
  {
    this.profile_id = paramInt;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public void setValue(String paramString)
  {
    this.value = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.myplace.MyPlaceData
 * JD-Core Version:    0.6.0
 */