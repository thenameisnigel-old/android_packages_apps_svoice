package com.vlingo.core.internal.memo;

import com.vlingo.core.internal.util.StringUtils;

public class Memo
{
  private static final String ELLIPSE_STRING = "...";
  public static final int MEMO_ID_CREATING_NEW = -1;
  public static final int MEMO_ID_UNKNOWN_ID = -2;
  private String content;
  private String date;
  private int id = -1;
  private String memoNameSpoken;
  private String memoNameText;
  private String text;
  private String title;

  public String getContent()
  {
    return this.content;
  }

  public String getDate()
  {
    return this.date;
  }

  public int getId()
  {
    return this.id;
  }

  public String getMemoName(boolean paramBoolean)
  {
    if (this.memoNameText == null)
    {
      this.memoNameText = getTitle();
      if (StringUtils.isNullOrWhiteSpace(this.memoNameText))
      {
        this.memoNameText = getContent();
        if (StringUtils.isNullOrWhiteSpace(this.memoNameText))
          this.memoNameText = getText();
      }
      int i = 30;
      if ((this.memoNameText != null) && (this.memoNameText.length() > i))
      {
        while ((this.memoNameText.charAt(i) != ' ') && (i > 10))
          i--;
        this.memoNameText = (this.memoNameText.substring(0, i).trim() + "...");
        this.memoNameSpoken = this.memoNameText.substring(0, this.memoNameText.length() - "...".length()).trim();
      }
    }
    if ((paramBoolean) && (this.memoNameSpoken != null));
    for (String str = this.memoNameSpoken; ; str = this.memoNameText)
      return str;
  }

  public String getText()
  {
    return this.text;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.memo.Memo
 * JD-Core Version:    0.6.0
 */