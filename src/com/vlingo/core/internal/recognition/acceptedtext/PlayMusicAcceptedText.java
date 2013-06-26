package com.vlingo.core.internal.recognition.acceptedtext;

import android.content.Context;
import android.util.Log;
import com.vlingo.core.internal.util.MusicUtil;
import java.util.ArrayList;

public class PlayMusicAcceptedText extends AcceptedText
{
  private static PlayMusicAcceptedText instance;
  private Context context;
  private ArrayList<Long> ids;
  private String requestedName;
  private Type requestedType;
  private String resolvedName;
  private Type resolvedType;

  private PlayMusicAcceptedText(Context paramContext)
  {
    super(null);
    Log.d("PlayMusicAcceptedText", "context: " + paramContext);
    this.context = paramContext;
    this.ids = new ArrayList();
  }

  public static void requested(Context paramContext, String paramString1, String paramString2)
  {
    instance = new PlayMusicAcceptedText(paramContext);
    instance.requestedName = paramString2;
    if (paramString1.equalsIgnoreCase("Music:Title"))
      instance.requestedType = Type.TITLE;
    while (true)
    {
      return;
      if (paramString1.equalsIgnoreCase("Music:Album"))
      {
        instance.requestedType = Type.ALBUM;
        continue;
      }
      if (paramString1.equalsIgnoreCase("Music:Artist"))
      {
        instance.requestedType = Type.ARTIST;
        continue;
      }
      if (paramString1.equalsIgnoreCase("Music:Generic"))
      {
        instance.requestedType = Type.GENERIC;
        continue;
      }
      instance.requestedType = Type.ELSE;
    }
  }

  public static PlayMusicAcceptedText resolved(String paramString1, String paramString2, long[] paramArrayOfLong)
  {
    PlayMusicAcceptedText localPlayMusicAcceptedText = null;
    if (instance != null)
    {
      localPlayMusicAcceptedText = instance;
      instance = null;
      if (paramString1.equalsIgnoreCase("TITLE"))
        localPlayMusicAcceptedText.resolvedType = Type.TITLE;
      while (true)
      {
        localPlayMusicAcceptedText.resolvedName = paramString2;
        int i = paramArrayOfLong.length;
        for (int j = 0; j < i; j++)
        {
          Long localLong = Long.valueOf(paramArrayOfLong[j]);
          localPlayMusicAcceptedText.ids.add(localLong);
        }
        if (paramString1.equalsIgnoreCase("ALBUM"))
        {
          localPlayMusicAcceptedText.resolvedType = Type.ALBUM;
          continue;
        }
        if (paramString1.equalsIgnoreCase("ARTIST"))
        {
          localPlayMusicAcceptedText.resolvedType = Type.ARTIST;
          continue;
        }
        localPlayMusicAcceptedText.resolvedType = Type.ELSE;
      }
    }
    return localPlayMusicAcceptedText;
  }

  protected String getAcceptedTextXML()
  {
    int i = MusicUtil.getPlayCount(this.context, this.ids);
    StringBuilder localStringBuilder = new StringBuilder();
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = this.requestedType.getPt();
    localStringBuilder.append(String.format("<AcceptedText pt=\"music:play%s\">", arrayOfObject1));
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = this.requestedType.toString();
    arrayOfObject2[1] = this.requestedName;
    localStringBuilder.append(String.format("<Tag u=\"req_%s\">%s</Tag>", arrayOfObject2));
    Object[] arrayOfObject3 = new Object[3];
    arrayOfObject3[0] = this.resolvedType.toString();
    arrayOfObject3[1] = Integer.valueOf(i);
    arrayOfObject3[2] = this.resolvedName;
    localStringBuilder.append(String.format("<Tag u=\"%s\" c=\"%d\">%s</Tag>", arrayOfObject3));
    localStringBuilder.append("</AcceptedText>");
    return localStringBuilder.toString();
  }

  private static enum Type
  {
    static
    {
      ALBUM = new Type("ALBUM", 1);
      TITLE = new Type("TITLE", 2);
      GENERIC = new Type("GENERIC", 3);
      ELSE = new Type("ELSE", 4);
      Type[] arrayOfType = new Type[5];
      arrayOfType[0] = ARTIST;
      arrayOfType[1] = ALBUM;
      arrayOfType[2] = TITLE;
      arrayOfType[3] = GENERIC;
      arrayOfType[4] = ELSE;
      $VALUES = arrayOfType;
    }

    public String getPt()
    {
      if (this == GENERIC);
      for (String str = ""; ; str = toString())
        return str;
    }

    public String toString()
    {
      if (this == ELSE);
      for (String str = ""; ; str = super.toString().toLowerCase())
        return str;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.recognition.acceptedtext.PlayMusicAcceptedText
 * JD-Core Version:    0.6.0
 */