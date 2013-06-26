package com.sec.android.smartface;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class FaceInfo
  implements Parcelable
{
  public static final int CHECK_FACE_EXISTENCE = 1;
  public static final int CHECK_FACE_EXISTENCE_WITH_ORIENTATION = 64;
  public static final int CHECK_FACE_ROTATION = 4;
  public static final Parcelable.Creator<FaceInfo> CREATOR = new Parcelable.Creator()
  {
    public FaceInfo createFromParcel(Parcel paramParcel)
    {
      return new FaceInfo(paramParcel);
    }

    public FaceInfo[] newArray(int paramInt)
    {
      return new FaceInfo[paramInt];
    }
  };
  public static final int FIND_FACES = 2;
  public static final int FIND_FACE_AND_PERSON_INFO = 8;
  public static final int FIND_FACE_COMPONENT = 32;
  public static final int FIND_FACE_POSE_INFO = 16;
  public static final int NEED_TO_PAUSE = 1;
  public static final int NEED_TO_PLAY;
  public boolean bFaceDetected;
  public int guideDir;
  public int horizontalMovement;
  public int needToPause;
  public int needToRotate;
  public int needToStay;
  public int numberOfPerson;
  public Person[] person = null;
  public int processStatus;
  public int responseType;
  public int verticalMovement;

  public FaceInfo()
  {
  }

  public FaceInfo(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    int i = 1;
    this.responseType = paramParcel.readInt();
    this.numberOfPerson = paramParcel.readInt();
    this.horizontalMovement = paramParcel.readInt();
    this.verticalMovement = paramParcel.readInt();
    this.processStatus = paramParcel.readInt();
    this.needToRotate = paramParcel.readInt();
    this.needToPause = paramParcel.readInt();
    this.needToStay = paramParcel.readInt();
    this.guideDir = paramParcel.readInt();
    if (paramParcel.readByte() == i);
    while (true)
    {
      this.bFaceDetected = i;
      this.person = new Person[this.numberOfPerson];
      for (int j = 0; j < this.numberOfPerson; j++)
      {
        this.person[j].face = new Face();
        this.person[j].face.rect = new Rect();
        this.person[j].face.rect.left = paramParcel.readInt();
        this.person[j].face.rect.top = paramParcel.readInt();
        this.person[j].face.rect.bottom = paramParcel.readInt();
        this.person[j].face.rect.right = paramParcel.readInt();
        this.person[j].face.score = paramParcel.readInt();
        this.person[j].face.id = paramParcel.readInt();
        this.person[j].face.leftEye = new Point();
        this.person[j].face.leftEye.x = paramParcel.readInt();
        this.person[j].face.leftEye.y = paramParcel.readInt();
        this.person[j].face.rightEye = new Point();
        this.person[j].face.rightEye.x = paramParcel.readInt();
        this.person[j].face.rightEye.y = paramParcel.readInt();
        this.person[j].face.mouth = new Point();
        this.person[j].face.mouth.x = paramParcel.readInt();
        this.person[j].face.mouth.y = paramParcel.readInt();
        this.person[j].face.nose = new Point();
        this.person[j].face.nose.x = paramParcel.readInt();
        this.person[j].face.nose.y = paramParcel.readInt();
        this.person[j].face.pose = new FacePoseInfo();
        this.person[j].face.pose.pitch = paramParcel.readInt();
        this.person[j].face.pose.roll = paramParcel.readInt();
        this.person[j].face.pose.yaw = paramParcel.readInt();
        this.person[j].face.expression = new FaceExpression();
        this.person[j].face.expression.expression = paramParcel.readInt();
        this.person[j].personInfo = new PersonInfo();
        this.person[j].personInfo.addressEMail = paramParcel.readString();
        this.person[j].personInfo.phoneNumber = paramParcel.readString();
        this.person[j].personInfo.address = paramParcel.readString();
        this.person[j].personInfo.name = paramParcel.readString();
      }
      i = 0;
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.responseType);
    paramParcel.writeInt(this.numberOfPerson);
    paramParcel.writeInt(this.horizontalMovement);
    paramParcel.writeInt(this.verticalMovement);
    paramParcel.writeInt(this.processStatus);
    paramParcel.writeInt(this.needToRotate);
    paramParcel.writeInt(this.needToPause);
    paramParcel.writeInt(this.needToStay);
    paramParcel.writeInt(this.guideDir);
    if (this.bFaceDetected);
    for (int i = 1; ; i = 0)
    {
      paramParcel.writeByte((byte)i);
      for (int j = 0; j < this.numberOfPerson; j++)
      {
        paramParcel.writeInt(this.person[j].face.rect.left);
        paramParcel.writeInt(this.person[j].face.rect.top);
        paramParcel.writeInt(this.person[j].face.rect.bottom);
        paramParcel.writeInt(this.person[j].face.rect.right);
        paramParcel.writeInt(this.person[j].face.score);
        paramParcel.writeInt(this.person[j].face.id);
        paramParcel.writeInt(this.person[j].face.leftEye.x);
        paramParcel.writeInt(this.person[j].face.leftEye.y);
        paramParcel.writeInt(this.person[j].face.rightEye.x);
        paramParcel.writeInt(this.person[j].face.rightEye.y);
        paramParcel.writeInt(this.person[j].face.mouth.x);
        paramParcel.writeInt(this.person[j].face.mouth.y);
        paramParcel.writeInt(this.person[j].face.nose.x);
        paramParcel.writeInt(this.person[j].face.nose.y);
        paramParcel.writeInt(this.person[j].face.pose.pitch);
        paramParcel.writeInt(this.person[j].face.pose.roll);
        paramParcel.writeInt(this.person[j].face.pose.yaw);
        paramParcel.writeInt(this.person[j].face.expression.expression);
        paramParcel.writeString(this.person[j].personInfo.addressEMail);
        paramParcel.writeString(this.person[j].personInfo.phoneNumber);
        paramParcel.writeString(this.person[j].personInfo.address);
        paramParcel.writeString(this.person[j].personInfo.name);
      }
    }
  }

  public class Face
  {
    public FaceInfo.FaceExpression expression = null;
    public int id;
    public Point leftEye = null;
    public Point mouth = null;
    public Point nose = null;
    public FaceInfo.FacePoseInfo pose = null;
    public Rect rect = null;
    public Point rightEye = null;
    public int score;

    public Face()
    {
    }
  }

  public class FaceExpression
  {
    public static final int FACIAL_EXPRESSION_ANGER = 8;
    public static final int FACIAL_EXPRESSION_DISGUST = 4;
    public static final int FACIAL_EXPRESSION_FEAR = 32;
    public static final int FACIAL_EXPRESSION_JOY = 2;
    public static final int FACIAL_EXPRESSION_NONE = 1;
    public static final int FACIAL_EXPRESSION_SADNESS = 64;
    public static final int FACIAL_EXPRESSION_SURPRISE = 16;
    public int expression;

    public FaceExpression()
    {
    }
  }

  public class FacePoseInfo
  {
    public int pitch;
    public int roll;
    public int yaw;

    public FacePoseInfo()
    {
    }
  }

  public class Person
  {
    public FaceInfo.Face face = null;
    public FaceInfo.PersonInfo personInfo = null;

    public Person()
    {
    }
  }

  public class PersonInfo
  {
    public String address = null;
    public String addressEMail = null;
    public String name = null;
    public String phoneNumber = null;

    public PersonInfo()
    {
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.smartface.FaceInfo
 * JD-Core Version:    0.6.0
 */