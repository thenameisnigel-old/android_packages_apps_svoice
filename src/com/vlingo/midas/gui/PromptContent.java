package com.vlingo.midas.gui;

import android.content.res.Resources;
import android.text.Html;
import com.vlingo.midas.ClientConfiguration;
import java.util.Random;

public class PromptContent
{
  public CharSequence ex1;
  public CharSequence ex2;
  public CharSequence ex3;
  public CharSequence ex4;
  public CharSequence ex5;
  public CharSequence ex6;
  public CharSequence ex7;
  public int exIcon1;
  public int exIcon2;
  public int exIcon3;
  public int exIcon4;
  public int exIcon5;
  public int exIcon6;
  public int exIcon7;
  public int icon = -1;

  public static PromptContent getGenericPrompt(Resources paramResources)
  {
    PromptContent localPromptContent = new PromptContent();
    getRandHintSetFromSetOne(paramResources, localPromptContent);
    getRandHintSetFromSetTwo(paramResources, localPromptContent);
    getRandHintSetFromSetThree(paramResources, localPromptContent);
    getRandHintSetFromSetFour(paramResources, localPromptContent);
    getRandHintSetFromSetFive(paramResources, localPromptContent);
    getRandHintSetFromSetSix(paramResources, localPromptContent);
    getRandHintSetFromSetSeven(paramResources, localPromptContent);
    localPromptContent.ex1 = Html.fromHtml(localPromptContent.ex1.toString());
    localPromptContent.ex2 = Html.fromHtml(localPromptContent.ex2.toString());
    localPromptContent.ex3 = Html.fromHtml(localPromptContent.ex3.toString());
    localPromptContent.ex4 = Html.fromHtml(localPromptContent.ex4.toString());
    localPromptContent.ex5 = Html.fromHtml(localPromptContent.ex5.toString());
    localPromptContent.ex6 = Html.fromHtml(localPromptContent.ex6.toString());
    localPromptContent.ex7 = Html.fromHtml(localPromptContent.ex7.toString());
    return localPromptContent;
  }

  private static void getRandHintSetFromSetFive(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 4)
    {
    default:
      str = paramResources.getString(2131362079);
      i = 2130837906;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      paramPromptContent.ex5 = str;
      paramPromptContent.exIcon5 = i;
      return;
      str = paramResources.getString(2131362076);
      i = 2130837903;
      continue;
      str = paramResources.getString(2131362077);
      i = 2130837903;
      continue;
      str = paramResources.getString(2131362078);
      i = 2130837903;
    }
  }

  private static void getRandHintSetFromSetFour(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 4)
    {
    default:
      str = paramResources.getString(2131362075);
      i = 2130837907;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      paramPromptContent.ex4 = str;
      paramPromptContent.exIcon4 = i;
      return;
      str = paramResources.getString(2131362072);
      i = 2130837907;
      continue;
      str = paramResources.getString(2131362073);
      i = 2130837907;
      continue;
      str = paramResources.getString(2131362074);
      i = 2130837907;
    }
  }

  private static void getRandHintSetFromSetOne(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 4)
    {
    default:
      str = paramResources.getString(2131362066);
      i = 2130837897;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      paramPromptContent.ex1 = str;
      paramPromptContent.exIcon1 = i;
      return;
      str = paramResources.getString(2131362063);
      i = 2130837897;
      continue;
      str = paramResources.getString(2131362064);
      i = 2130837897;
      continue;
      str = paramResources.getString(2131362065);
      i = 2130837897;
    }
  }

  private static void getRandHintSetFromSetSeven(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 7)
    {
    default:
      str = paramResources.getString(2131362092);
      i = 2130837898;
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      paramPromptContent.ex7 = str;
      paramPromptContent.exIcon7 = i;
      return;
      str = paramResources.getString(2131362086);
      i = 2130837908;
      continue;
      if (!ClientConfiguration.isChineseBuild())
      {
        str = paramResources.getString(2131362087);
        i = 2130837908;
        continue;
      }
      str = paramResources.getString(2131362088);
      i = 2130837908;
      continue;
      if (!ClientConfiguration.isChineseBuild())
      {
        str = paramResources.getString(2131362089);
        i = 2130837899;
        continue;
      }
      str = paramResources.getString(2131362090);
      i = 2130837905;
      continue;
      str = paramResources.getString(2131362091);
      i = 2130837910;
    }
  }

  private static void getRandHintSetFromSetSix(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 6)
    {
    default:
      str = paramResources.getString(2131362085);
      i = 2130837904;
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      paramPromptContent.ex6 = str;
      paramPromptContent.exIcon6 = i;
      return;
      str = paramResources.getString(2131362080);
      i = 2130837909;
      continue;
      str = paramResources.getString(2131362081);
      i = 2130837909;
      continue;
      str = paramResources.getString(2131362082);
      i = 2130837909;
      continue;
      str = paramResources.getString(2131362083);
      i = 2130837900;
      continue;
      str = paramResources.getString(2131362084);
      i = 2130837904;
    }
  }

  private static void getRandHintSetFromSetThree(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    switch (Math.abs(new Random().nextInt()) % 2)
    {
    default:
      str = paramResources.getString(2131362071);
    case 0:
    }
    for (int i = 2130837901; ; i = 2130837901)
    {
      paramPromptContent.ex3 = str;
      paramPromptContent.exIcon3 = i;
      return;
      str = paramResources.getString(2131362070);
    }
  }

  private static void getRandHintSetFromSetTwo(Resources paramResources, PromptContent paramPromptContent)
  {
    String str;
    int i;
    switch (Math.abs(new Random().nextInt()) % 3)
    {
    default:
      str = paramResources.getString(2131362069);
      i = 2130837902;
    case 0:
    case 1:
    }
    while (true)
    {
      paramPromptContent.ex2 = str;
      paramPromptContent.exIcon2 = i;
      return;
      str = paramResources.getString(2131362067);
      i = 2130837902;
      continue;
      str = paramResources.getString(2131362068);
      i = 2130837902;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.PromptContent
 * JD-Core Version:    0.6.0
 */