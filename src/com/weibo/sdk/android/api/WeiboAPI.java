package com.weibo.sdk.android.api;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.WeiboParameters;
import com.weibo.sdk.android.net.AsyncWeiboRunner;
import com.weibo.sdk.android.net.RequestListener;

public abstract class WeiboAPI
{
  public static final String API_SERVER = "https://api.weibo.com/2";
  public static final String HTTPMETHOD_GET = "GET";
  public static final String HTTPMETHOD_POST = "POST";
  private String accessToken;
  private Oauth2AccessToken oAuth2accessToken;

  public WeiboAPI(Oauth2AccessToken paramOauth2AccessToken)
  {
    this.oAuth2accessToken = paramOauth2AccessToken;
    if (this.oAuth2accessToken != null)
      this.accessToken = this.oAuth2accessToken.getToken();
  }

  protected void request(String paramString1, WeiboParameters paramWeiboParameters, String paramString2, RequestListener paramRequestListener)
  {
    paramWeiboParameters.add("access_token", this.accessToken);
    AsyncWeiboRunner.request(paramString1, paramWeiboParameters, paramString2, paramRequestListener);
  }

  public static enum AUTHOR_FILTER
  {
    static
    {
      AUTHOR_FILTER[] arrayOfAUTHOR_FILTER = new AUTHOR_FILTER[3];
      arrayOfAUTHOR_FILTER[0] = ALL;
      arrayOfAUTHOR_FILTER[1] = ATTENTIONS;
      arrayOfAUTHOR_FILTER[2] = STRANGER;
      ENUM$VALUES = arrayOfAUTHOR_FILTER;
    }
  }

  public static enum CAPITAL
  {
    static
    {
      CAPITAL[] arrayOfCAPITAL = new CAPITAL[26];
      arrayOfCAPITAL[0] = A;
      arrayOfCAPITAL[1] = B;
      arrayOfCAPITAL[2] = C;
      arrayOfCAPITAL[3] = D;
      arrayOfCAPITAL[4] = E;
      arrayOfCAPITAL[5] = F;
      arrayOfCAPITAL[6] = G;
      arrayOfCAPITAL[7] = H;
      arrayOfCAPITAL[8] = I;
      arrayOfCAPITAL[9] = J;
      arrayOfCAPITAL[10] = K;
      arrayOfCAPITAL[11] = L;
      arrayOfCAPITAL[12] = M;
      arrayOfCAPITAL[13] = N;
      arrayOfCAPITAL[14] = O;
      arrayOfCAPITAL[15] = P;
      arrayOfCAPITAL[16] = Q;
      arrayOfCAPITAL[17] = R;
      arrayOfCAPITAL[18] = S;
      arrayOfCAPITAL[19] = T;
      arrayOfCAPITAL[20] = U;
      arrayOfCAPITAL[21] = V;
      arrayOfCAPITAL[22] = W;
      arrayOfCAPITAL[23] = X;
      arrayOfCAPITAL[24] = Y;
      arrayOfCAPITAL[25] = Z;
      ENUM$VALUES = arrayOfCAPITAL;
    }
  }

  public static enum COMMENTS_TYPE
  {
    static
    {
      CUR_STATUSES = new COMMENTS_TYPE("CUR_STATUSES", 1);
      ORIGAL_STATUSES = new COMMENTS_TYPE("ORIGAL_STATUSES", 2);
      BOTH = new COMMENTS_TYPE("BOTH", 3);
      COMMENTS_TYPE[] arrayOfCOMMENTS_TYPE = new COMMENTS_TYPE[4];
      arrayOfCOMMENTS_TYPE[0] = NONE;
      arrayOfCOMMENTS_TYPE[1] = CUR_STATUSES;
      arrayOfCOMMENTS_TYPE[2] = ORIGAL_STATUSES;
      arrayOfCOMMENTS_TYPE[3] = BOTH;
      ENUM$VALUES = arrayOfCOMMENTS_TYPE;
    }
  }

  public static enum COUNT_TYPE
  {
    static
    {
      FOLLOWER = new COUNT_TYPE("FOLLOWER", 1);
      CMT = new COUNT_TYPE("CMT", 2);
      DM = new COUNT_TYPE("DM", 3);
      MENTION_STATUS = new COUNT_TYPE("MENTION_STATUS", 4);
      MENTION_CMT = new COUNT_TYPE("MENTION_CMT", 5);
      COUNT_TYPE[] arrayOfCOUNT_TYPE = new COUNT_TYPE[6];
      arrayOfCOUNT_TYPE[0] = STATUS;
      arrayOfCOUNT_TYPE[1] = FOLLOWER;
      arrayOfCOUNT_TYPE[2] = CMT;
      arrayOfCOUNT_TYPE[3] = DM;
      arrayOfCOUNT_TYPE[4] = MENTION_STATUS;
      arrayOfCOUNT_TYPE[5] = MENTION_CMT;
      ENUM$VALUES = arrayOfCOUNT_TYPE;
    }
  }

  public static enum EMOTION_TYPE
  {
    static
    {
      ANI = new EMOTION_TYPE("ANI", 1);
      CARTOON = new EMOTION_TYPE("CARTOON", 2);
      EMOTION_TYPE[] arrayOfEMOTION_TYPE = new EMOTION_TYPE[3];
      arrayOfEMOTION_TYPE[0] = FACE;
      arrayOfEMOTION_TYPE[1] = ANI;
      arrayOfEMOTION_TYPE[2] = CARTOON;
      ENUM$VALUES = arrayOfEMOTION_TYPE;
    }
  }

  public static enum FEATURE
  {
    static
    {
      MUSICE = new FEATURE("MUSICE", 4);
      FEATURE[] arrayOfFEATURE = new FEATURE[5];
      arrayOfFEATURE[0] = ALL;
      arrayOfFEATURE[1] = ORIGINAL;
      arrayOfFEATURE[2] = PICTURE;
      arrayOfFEATURE[3] = VIDEO;
      arrayOfFEATURE[4] = MUSICE;
      ENUM$VALUES = arrayOfFEATURE;
    }
  }

  public static enum FRIEND_TYPE
  {
    static
    {
      FRIEND_TYPE[] arrayOfFRIEND_TYPE = new FRIEND_TYPE[2];
      arrayOfFRIEND_TYPE[0] = ATTENTIONS;
      arrayOfFRIEND_TYPE[1] = FELLOWS;
      ENUM$VALUES = arrayOfFRIEND_TYPE;
    }
  }

  public static enum LANGUAGE
  {
    static
    {
      LANGUAGE[] arrayOfLANGUAGE = new LANGUAGE[2];
      arrayOfLANGUAGE[0] = cnname;
      arrayOfLANGUAGE[1] = twname;
      ENUM$VALUES = arrayOfLANGUAGE;
    }
  }

  public static enum RANGE
  {
    static
    {
      ALL = new RANGE("ALL", 2);
      RANGE[] arrayOfRANGE = new RANGE[3];
      arrayOfRANGE[0] = ATTENTIONS;
      arrayOfRANGE[1] = ATTENTION_TAGS;
      arrayOfRANGE[2] = ALL;
      ENUM$VALUES = arrayOfRANGE;
    }
  }

  public static enum SCHOOL_TYPE
  {
    static
    {
      JUNIOR = new SCHOOL_TYPE("JUNIOR", 3);
      PRIMARY = new SCHOOL_TYPE("PRIMARY", 4);
      SCHOOL_TYPE[] arrayOfSCHOOL_TYPE = new SCHOOL_TYPE[5];
      arrayOfSCHOOL_TYPE[0] = COLLEGE;
      arrayOfSCHOOL_TYPE[1] = SENIOR;
      arrayOfSCHOOL_TYPE[2] = TECHNICAL;
      arrayOfSCHOOL_TYPE[3] = JUNIOR;
      arrayOfSCHOOL_TYPE[4] = PRIMARY;
      ENUM$VALUES = arrayOfSCHOOL_TYPE;
    }
  }

  public static enum SORT2
  {
    static
    {
      SORT_BY_HOT = new SORT2("SORT_BY_HOT", 1);
      SORT2[] arrayOfSORT2 = new SORT2[2];
      arrayOfSORT2[0] = SORT_BY_TIME;
      arrayOfSORT2[1] = SORT_BY_HOT;
      ENUM$VALUES = arrayOfSORT2;
    }
  }

  public static enum SORT3
  {
    static
    {
      SORT_BY_DISTENCE = new SORT3("SORT_BY_DISTENCE", 1);
      SORT3[] arrayOfSORT3 = new SORT3[2];
      arrayOfSORT3[0] = SORT_BY_TIME;
      arrayOfSORT3[1] = SORT_BY_DISTENCE;
      ENUM$VALUES = arrayOfSORT3;
    }
  }

  public static enum SORT
  {
    static
    {
      SORT[] arrayOfSORT = new SORT[2];
      arrayOfSORT[0] = Oauth2AccessToken;
      arrayOfSORT[1] = SORT_AROUND;
      ENUM$VALUES = arrayOfSORT;
    }
  }

  public static enum SRC_FILTER
  {
    static
    {
      SRC_FILTER[] arrayOfSRC_FILTER = new SRC_FILTER[3];
      arrayOfSRC_FILTER[0] = ALL;
      arrayOfSRC_FILTER[1] = WEIBO;
      arrayOfSRC_FILTER[2] = WEIQUN;
      ENUM$VALUES = arrayOfSRC_FILTER;
    }
  }

  public static enum STATUSES_TYPE
  {
    static
    {
      BEAUTY = new STATUSES_TYPE("BEAUTY", 2);
      VIDEO = new STATUSES_TYPE("VIDEO", 3);
      CONSTELLATION = new STATUSES_TYPE("CONSTELLATION", 4);
      LOVELY = new STATUSES_TYPE("LOVELY", 5);
      FASHION = new STATUSES_TYPE("FASHION", 6);
      CARS = new STATUSES_TYPE("CARS", 7);
      CATE = new STATUSES_TYPE("CATE", 8);
      MUSIC = new STATUSES_TYPE("MUSIC", 9);
      STATUSES_TYPE[] arrayOfSTATUSES_TYPE = new STATUSES_TYPE[10];
      arrayOfSTATUSES_TYPE[0] = ENTERTAINMENT;
      arrayOfSTATUSES_TYPE[1] = FUNNY;
      arrayOfSTATUSES_TYPE[2] = BEAUTY;
      arrayOfSTATUSES_TYPE[3] = VIDEO;
      arrayOfSTATUSES_TYPE[4] = CONSTELLATION;
      arrayOfSTATUSES_TYPE[5] = LOVELY;
      arrayOfSTATUSES_TYPE[6] = FASHION;
      arrayOfSTATUSES_TYPE[7] = CARS;
      arrayOfSTATUSES_TYPE[8] = CATE;
      arrayOfSTATUSES_TYPE[9] = MUSIC;
      ENUM$VALUES = arrayOfSTATUSES_TYPE;
    }
  }

  public static enum TYPE
  {
    static
    {
      COMMENTS = new TYPE("COMMENTS", 1);
      MESSAGE = new TYPE("MESSAGE", 2);
      TYPE[] arrayOfTYPE = new TYPE[3];
      arrayOfTYPE[0] = STATUSES;
      arrayOfTYPE[1] = COMMENTS;
      arrayOfTYPE[2] = MESSAGE;
      ENUM$VALUES = arrayOfTYPE;
    }
  }

  public static enum TYPE_FILTER
  {
    static
    {
      TYPE_FILTER[] arrayOfTYPE_FILTER = new TYPE_FILTER[2];
      arrayOfTYPE_FILTER[0] = ALL;
      arrayOfTYPE_FILTER[1] = ORIGAL;
      ENUM$VALUES = arrayOfTYPE_FILTER;
    }
  }

  public static enum USER_CATEGORY
  {
    static
    {
      cooking = new USER_CATEGORY("cooking", 4);
      sports = new USER_CATEGORY("sports", 5);
      finance = new USER_CATEGORY("finance", 6);
      tech = new USER_CATEGORY("tech", 7);
      singer = new USER_CATEGORY("singer", 8);
      writer = new USER_CATEGORY("writer", 9);
      moderator = new USER_CATEGORY("moderator", 10);
      medium = new USER_CATEGORY("medium", 11);
      stockplayer = new USER_CATEGORY("stockplayer", 12);
      USER_CATEGORY[] arrayOfUSER_CATEGORY = new USER_CATEGORY[13];
      arrayOfUSER_CATEGORY[0] = DEFAULT;
      arrayOfUSER_CATEGORY[1] = ent;
      arrayOfUSER_CATEGORY[2] = hk_famous;
      arrayOfUSER_CATEGORY[3] = model;
      arrayOfUSER_CATEGORY[4] = cooking;
      arrayOfUSER_CATEGORY[5] = sports;
      arrayOfUSER_CATEGORY[6] = finance;
      arrayOfUSER_CATEGORY[7] = tech;
      arrayOfUSER_CATEGORY[8] = singer;
      arrayOfUSER_CATEGORY[9] = writer;
      arrayOfUSER_CATEGORY[10] = moderator;
      arrayOfUSER_CATEGORY[11] = medium;
      arrayOfUSER_CATEGORY[12] = stockplayer;
      ENUM$VALUES = arrayOfUSER_CATEGORY;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.weibo.sdk.android.api.WeiboAPI
 * JD-Core Version:    0.6.0
 */