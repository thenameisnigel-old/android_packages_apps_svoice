package com.sec.android.internal.ims.external;

import android.net.Uri;
import android.provider.BaseColumns;

public class TableDetails
{
  public static final String AUTHORITY = "com.android.VideoChatTableProvider";

  public static final class User
    implements BaseColumns
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.android.VideoChatTableProvider");
    public static final String DATABASE_NAME_VT_CHAT_TABLE = "VideoChatTableContent1.db";
    public static final Uri DB_VT_CHAT_TABLE_DEL_URI;
    public static final Uri DB_VT_CHAT_TABLE_URI = Uri.parse("content://com.android.VideoChatTableProvider/VideoChatTable");
    public static final String DateTime = "DateTime";
    public static final String FilePath = "FilePath";
    public static final String ID = "_id";
    public static final String MyFOntSize = "MyFOntSize";
    public static final String MyFontColor = "MyFontColor";
    public static final String Number = "Number";
    public static final String OtherFontColor = "OtherFontColor";
    public static final String TABLE_NAME_VT_CHAT_TABLE = "VideoChatTable";

    static
    {
      DB_VT_CHAT_TABLE_DEL_URI = Uri.parse("content://com.android.VideoChatTableProvider/VideoChatTable/0");
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.internal.ims.external.TableDetails
 * JD-Core Version:    0.6.0
 */