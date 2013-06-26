package com.sec.android.facedetection;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera.Face;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class SecFace extends Camera.Face
  implements Parcelable
{
  public static final Parcelable.Creator<SecFace> CREATOR = new SecFace.1();
  public int orientation;

  public SecFace()
  {
  }

  public SecFace(Rect paramRect, int paramInt1, int paramInt2, Point paramPoint1, Point paramPoint2, Point paramPoint3)
  {
    this.rect = paramRect;
    this.id = paramInt1;
    this.score = paramInt2;
    this.leftEye = paramPoint1;
    this.rightEye = paramPoint2;
    this.mouth = paramPoint3;
    getOrientation();
  }

  public SecFace(Camera.Face paramFace)
  {
    this.rect = paramFace.rect;
    this.id = paramFace.id;
    this.score = paramFace.score;
    this.leftEye = paramFace.leftEye;
    this.rightEye = paramFace.rightEye;
    this.mouth = paramFace.mouth;
    getOrientation();
  }

  public SecFace(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
    getOrientation();
  }

  public SecFace(SecFace paramSecFace)
  {
    this.rect = paramSecFace.rect;
    this.id = paramSecFace.id;
    this.score = paramSecFace.score;
    this.leftEye = paramSecFace.leftEye;
    this.rightEye = paramSecFace.rightEye;
    this.mouth = paramSecFace.mouth;
    this.orientation = paramSecFace.orientation;
  }

  private void getOrientation()
  {
    double d5;
    if ((this.leftEye != null) && (this.rightEye != null) && ((this.leftEye.x != -1000) || (this.leftEye.y != -1000) || (this.rightEye.x != -1000) || (this.leftEye.y != -1000)))
    {
      double d1 = this.rightEye.x - this.leftEye.x;
      double d2 = this.rightEye.y - this.leftEye.y;
      double d3 = d1 / Math.sqrt(d1 * d1 + d2 * d2);
      double d4 = d2 / Math.sqrt(d3 * d3 + d2 * d2);
      d5 = 180.0D * Math.acos(1.0D * d3 + 0.0D * d4) / 3.141592653589793D;
      if (d4 > 0.0D)
        d5 = 360.0D - d5;
    }
    for (this.orientation = (int)d5; ; this.orientation = -1)
      return;
  }

  public int describeContents()
  {
    return 0;
  }

  public void readFromParcel(Parcel paramParcel)
  {
    this.rect = new Rect(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
    this.score = paramParcel.readInt();
    this.id = paramParcel.readInt();
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k;
    int m;
    label105: int n;
    int i1;
    if ((i == -2000) && (j == -2000))
    {
      this.leftEye = null;
      k = paramParcel.readInt();
      m = paramParcel.readInt();
      if ((k != -2000) || (m != -2000))
        break label163;
      this.rightEye = null;
      n = paramParcel.readInt();
      i1 = paramParcel.readInt();
      if ((n != -2000) || (i1 != -2000))
        break label181;
    }
    label163: label181: for (this.mouth = null; ; this.mouth = new Point(n, i1))
    {
      this.orientation = paramParcel.readInt();
      return;
      this.leftEye = new Point(i, j);
      break;
      this.rightEye = new Point(k, m);
      break label105;
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.rect.left);
    paramParcel.writeInt(this.rect.top);
    paramParcel.writeInt(this.rect.right);
    paramParcel.writeInt(this.rect.bottom);
    paramParcel.writeInt(this.score);
    paramParcel.writeInt(this.id);
    if (this.leftEye != null)
    {
      paramParcel.writeInt(this.leftEye.x);
      paramParcel.writeInt(this.leftEye.y);
      if (this.rightEye == null)
        break label173;
      paramParcel.writeInt(this.rightEye.x);
      paramParcel.writeInt(this.rightEye.y);
      label118: if (this.mouth == null)
        break label190;
      paramParcel.writeInt(this.mouth.x);
      paramParcel.writeInt(this.mouth.y);
    }
    while (true)
    {
      paramParcel.writeInt(this.orientation);
      return;
      paramParcel.writeInt(-2000);
      paramParcel.writeInt(-2000);
      break;
      label173: paramParcel.writeInt(-2000);
      paramParcel.writeInt(-2000);
      break label118;
      label190: paramParcel.writeInt(-2000);
      paramParcel.writeInt(-2000);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.sec.android.facedetection.SecFace
 * JD-Core Version:    0.6.0
 */