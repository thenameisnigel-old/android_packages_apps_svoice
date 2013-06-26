package com.vlingo.midas.gui.customviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;

public class DrawAnimationJDevice
{
  public static void drawLandscapeAnimation(Canvas paramCanvas, Bitmap paramBitmap, NinePatchDrawable paramNinePatchDrawable, float paramFloat)
  {
    if (paramFloat < 31.0F)
    {
      int i64 = 452;
      for (int i65 = 40; i65 <= 100; i65 += 10)
      {
        paramCanvas.drawBitmap(paramBitmap, i65, i64, null);
        i64 += 7;
      }
      paramNinePatchDrawable.setBounds(40, 475, 70, 495);
      paramNinePatchDrawable.draw(paramCanvas);
      int i66 = 495;
      for (int i67 = 100; i67 <= 160; i67 += 10)
      {
        paramCanvas.drawBitmap(paramBitmap, i67, i66, null);
        i66 -= 7;
      }
      int i68 = 452;
      for (int i69 = 160; i69 <= 220; i69 += 10)
      {
        paramCanvas.drawBitmap(paramBitmap, i69, i68, null);
        i68 += 7;
      }
      paramNinePatchDrawable.setBounds(130, 475, 190, 495);
      paramNinePatchDrawable.draw(paramCanvas);
      int i70 = 495;
      for (int i71 = 220; i71 <= 280; i71 += 10)
      {
        paramCanvas.drawBitmap(paramBitmap, i71, i70, null);
        i70 -= 7;
      }
      paramNinePatchDrawable.setBounds(260, 475, 280, 495);
      paramNinePatchDrawable.draw(paramCanvas);
    }
    while (true)
    {
      return;
      if (paramFloat < 37.0F)
      {
        int i54 = 409;
        for (int i55 = 40; i55 <= 110; i55 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i55, i54, null);
          i54 += 6;
        }
        paramNinePatchDrawable.setBounds(40, 433, 80, 452);
        paramNinePatchDrawable.draw(paramCanvas);
        int i56 = 452;
        for (int i57 = 110; i57 <= 150; i57 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i57, i56, null);
          i56 -= 5;
        }
        int i58 = 409;
        for (int i59 = 150; i59 <= 190; i59 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i59, i58, null);
          i58 += 5;
        }
        paramNinePatchDrawable.setBounds(135, 433, 175, 452);
        paramNinePatchDrawable.draw(paramCanvas);
        int i60 = 452;
        for (int i61 = 200; i61 <= 230; i61 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i61, i60, null);
          i60 -= 7;
        }
        int i62 = 409;
        for (int i63 = 230; i63 <= 260; i63 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i63, i62, null);
          i62 += 7;
        }
        paramNinePatchDrawable.setBounds(220, 433, 240, 452);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 452, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 43.0F)
      {
        int i42 = 388;
        for (int i43 = 40; i43 <= 80; i43 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i43, i42, null);
          i42 -= 5;
        }
        int i44 = 366;
        for (int i45 = 80; i45 <= 120; i45 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i45, i44, null);
          i44 += 5;
        }
        paramNinePatchDrawable.setBounds(40, 388, 95, 409);
        paramNinePatchDrawable.draw(paramCanvas);
        int i46 = 409;
        for (int i47 = 120; i47 <= 160; i47 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i47, i46, null);
          i46 -= 5;
        }
        int i48 = 366;
        for (int i49 = 160; i49 <= 200; i49 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i49, i48, null);
          i48 += 5;
        }
        paramNinePatchDrawable.setBounds(140, 388, 180, 409);
        paramNinePatchDrawable.draw(paramCanvas);
        int i50 = 409;
        for (int i51 = 200; i51 <= 240; i51 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i51, i50, null);
          i50 -= 5;
        }
        int i52 = 366;
        for (int i53 = 240; i53 <= 280; i53 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i53, i52, null);
          i52 += 5;
        }
        paramNinePatchDrawable.setBounds(220, 388, 260, 409);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 409, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 49.0F)
      {
        int i36 = 323;
        for (int i37 = 40; i37 <= 140; i37 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i37, i36, null);
          i36 += 4;
        }
        paramNinePatchDrawable.setBounds(40, 345, 90, 366);
        paramNinePatchDrawable.draw(paramCanvas);
        int i38 = 366;
        for (int i39 = 150; i39 <= 220; i39 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i39, i38, null);
          i38 -= 6;
        }
        int i40 = 323;
        for (int i41 = 220; i41 <= 290; i41 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i41, i40, null);
          i40 += 6;
        }
        paramNinePatchDrawable.setBounds(180, 345, 260, 366);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 366, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 55.0F)
      {
        int i26 = 280;
        for (int i27 = 40; i27 <= 100; i27 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i27, i26, null);
          i26 += 7;
        }
        paramNinePatchDrawable.setBounds(40, 302, 70, 323);
        paramNinePatchDrawable.draw(paramCanvas);
        int i28 = 323;
        for (int i29 = 100; i29 <= 160; i29 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i29, i28, null);
          i28 -= 7;
        }
        int i30 = 280;
        for (int i31 = 160; i31 <= 220; i31 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i31, i30, null);
          i30 += 7;
        }
        paramNinePatchDrawable.setBounds(130, 302, 190, 323);
        paramNinePatchDrawable.draw(paramCanvas);
        int i32 = 323;
        for (int i33 = 220; i33 <= 250; i33 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i33, i32, null);
          i32 -= 7;
        }
        int i34 = 302;
        for (int i35 = 250; i35 <= 280; i35 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i35, i34, null);
          i34 += 7;
        }
        paramNinePatchDrawable.setBounds(40, 323, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 61.0F)
      {
        int i20 = 237;
        for (int i21 = 40; i21 <= 120; i21 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i21, i20, null);
          i20 += 5;
        }
        paramNinePatchDrawable.setBounds(40, 259, 80, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        int i22 = 280;
        for (int i23 = 120; i23 <= 180; i23 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i23, i22, null);
          i22 -= 7;
        }
        int i24 = 237;
        for (int i25 = 180; i25 <= 240; i25 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i25, i24, null);
          i24 += 7;
        }
        paramNinePatchDrawable.setBounds(150, 259, 210, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 280, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 67.0F)
      {
        int i10 = 237;
        for (int i11 = 40; i11 <= 80; i11 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i11, i10, null);
          i10 -= 5;
        }
        int i12 = 194;
        for (int i13 = 80; i13 <= 140; i13 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i13, i12, null);
          i12 += 7;
        }
        paramNinePatchDrawable.setBounds(40, 216, 110, 237);
        paramNinePatchDrawable.draw(paramCanvas);
        int i14 = 237;
        for (int i15 = 140; i15 <= 190; i15 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i15, i14, null);
          i14 -= 4;
        }
        int i16 = 194;
        for (int i17 = 190; i17 <= 240; i17 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i17, i16, null);
          i16 += 4;
        }
        paramNinePatchDrawable.setBounds(165, 216, 215, 237);
        paramNinePatchDrawable.draw(paramCanvas);
        int i18 = 237;
        for (int i19 = 240; i19 <= 280; i19 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i19, i18, null);
          i18 -= 5;
        }
        paramNinePatchDrawable.setBounds(40, 237, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 73.0F)
      {
        int i2 = 173;
        for (int i3 = 40; i3 <= 100; i3 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i3, i2, null);
          i2 += 4;
        }
        int i4 = 194;
        for (int i5 = 100; i5 <= 160; i5 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i5, i4, null);
          i4 -= 7;
        }
        int i6 = 151;
        for (int i7 = 160; i7 <= 220; i7 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i7, i6, null);
          i6 += 7;
        }
        paramNinePatchDrawable.setBounds(130, 173, 190, 194);
        paramNinePatchDrawable.draw(paramCanvas);
        int i8 = 194;
        for (int i9 = 220; i9 <= 280; i9 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i9, i8, null);
          i8 -= 7;
        }
        paramNinePatchDrawable.setBounds(250, 173, 280, 194);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 194, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat <= 79.0F)
      {
        int i = 150;
        for (int j = 40; j <= 120; j += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, j, i, null);
          i -= 5;
        }
        int k = 110;
        for (int m = 120; m <= 200; m += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, m, k, null);
          k += 5;
        }
        paramNinePatchDrawable.setBounds(80, 130, 160, 151);
        paramNinePatchDrawable.draw(paramCanvas);
        int n = 151;
        for (int i1 = 200; i1 <= 280; i1 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i1, n, null);
          n -= 5;
        }
        paramNinePatchDrawable.setBounds(240, 130, 280, 151);
        paramNinePatchDrawable.draw(paramCanvas);
        paramNinePatchDrawable.setBounds(40, 151, 280, 495);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat <= 79.0F)
        continue;
      paramNinePatchDrawable.setBounds(40, 65, 280, 495);
      paramNinePatchDrawable.draw(paramCanvas);
    }
  }

  public static void drawPotraitAnimation(Canvas paramCanvas, Bitmap paramBitmap, NinePatchDrawable paramNinePatchDrawable, float paramFloat)
  {
    if (paramFloat < 31.0F)
    {
      int i122 = 280;
      for (int i123 = 50; i123 <= 130; i123 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i123, i122, null);
        i122 -= 5;
      }
      int i124 = 260;
      for (int i125 = 130; i125 <= 230; i125 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i125, i124, null);
        i124 += 5;
      }
      int i126 = 280;
      for (int i127 = 390; i127 <= 490; i127 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i127, i126, null);
        i126 -= 4;
      }
    }
    if (paramFloat < 37.0F)
    {
      int i108 = 245;
      for (int i109 = 50; i109 <= 110; i109 += 10)
      {
        paramCanvas.drawBitmap(paramBitmap, i109, i108, null);
        i108 -= 2;
      }
      int i110 = 234;
      for (int i111 = 110; i111 <= 190; i111 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i111, i110, null);
        i110 += 5;
      }
      int i112 = 257;
      for (int i113 = 190; i113 <= 250; i113 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i113, i112, null);
        i112 -= 8;
      }
      int i114 = 234;
      for (int i115 = 250; i115 <= 310; i115 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i115, i114, null);
        i114 += 8;
      }
      int i116 = 257;
      for (int i117 = 310; i117 <= 370; i117 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i117, i116, null);
        i116 -= 8;
      }
      int i118 = 234;
      for (int i119 = 370; i119 <= 430; i119 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i119, i118, null);
        i118 += 8;
      }
      int i120 = 257;
      for (int i121 = 430; i121 <= 490; i121 += 20)
      {
        paramCanvas.drawBitmap(paramBitmap, i121, i120, null);
        i120 -= 8;
      }
      paramNinePatchDrawable.setBounds(50, 257, 490, 280);
      paramNinePatchDrawable.draw(paramCanvas);
    }
    while (true)
    {
      return;
      if (paramFloat < 43.0F)
      {
        int i96 = 222;
        for (int i97 = 50; i97 <= 110; i97 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i97, i96, null);
          i96 -= 2;
        }
        int i98 = 211;
        for (int i99 = 110; i99 <= 170; i99 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i99, i98, null);
          i98 += 8;
        }
        int i100 = 234;
        for (int i101 = 170; i101 <= 250; i101 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i101, i100, null);
          i100 -= 6;
        }
        int i102 = 211;
        for (int i103 = 250; i103 <= 330; i103 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i103, i102, null);
          i102 += 6;
        }
        int i104 = 234;
        for (int i105 = 330; i105 <= 370; i105 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i105, i104, null);
          i104 -= 6;
        }
        int i106 = 211;
        for (int i107 = 370; i107 <= 410; i107 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i107, i106, null);
          i106 += 6;
        }
        paramCanvas.drawBitmap(paramBitmap, 410.0F, 234.0F, null);
        paramCanvas.drawBitmap(paramBitmap, 420.0F, 222.0F, null);
        paramCanvas.drawBitmap(paramBitmap, 430.0F, 234.0F, null);
        paramCanvas.drawBitmap(paramBitmap, 470.0F, 222.0F, null);
        paramCanvas.drawBitmap(paramBitmap, 490.0F, 211.0F, null);
        paramNinePatchDrawable.setBounds(50, 234, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 49.0F)
      {
        int i78 = 188;
        for (int i79 = 50; i79 <= 90; i79 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i79, i78, null);
          i78 += 6;
        }
        int i80 = 211;
        for (int i81 = 90; i81 <= 150; i81 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i81, i80, null);
          i80 -= 4;
        }
        int i82 = 188;
        for (int i83 = 150; i83 <= 190; i83 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i83, i82, null);
          i82 += 6;
        }
        int i84 = 211;
        for (int i85 = 190; i85 <= 230; i85 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i85, i84, null);
          i84 -= 6;
        }
        int i86 = 188;
        for (int i87 = 230; i87 <= 290; i87 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i87, i86, null);
          i86 += 4;
        }
        int i88 = 211;
        for (int i89 = 290; i89 <= 350; i89 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i89, i88, null);
          i88 -= 4;
        }
        int i90 = 188;
        for (int i91 = 350; i91 <= 390; i91 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i91, i90, null);
          i90 += 6;
        }
        int i92 = 211;
        for (int i93 = 390; i93 <= 450; i93 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i93, i92, null);
          i92 -= 4;
        }
        int i94 = 188;
        for (int i95 = 450; i95 <= 490; i95 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i95, i94, null);
          i94 += 3;
        }
        paramNinePatchDrawable.setBounds(50, 211, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 55.0F)
      {
        int i60 = 177;
        for (int i61 = 50; i61 <= 90; i61 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i61, i60, null);
          i60 -= 3;
        }
        int i62 = 165;
        for (int i63 = 90; i63 <= 130; i63 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i63, i62, null);
          i62 += 6;
        }
        int i64 = 188;
        for (int i65 = 130; i65 <= 190; i65 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i65, i64, null);
          i64 -= 8;
        }
        int i66 = 165;
        for (int i67 = 190; i67 <= 250; i67 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i67, i66, null);
          i66 += 8;
        }
        int i68 = 188;
        for (int i69 = 250; i69 <= 290; i69 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i69, i68, null);
          i68 -= 4;
        }
        int i70 = 156;
        for (int i71 = 290; i71 <= 330; i71 += 5)
        {
          paramCanvas.drawBitmap(paramBitmap, i71, i70, null);
          i70 += 4;
        }
        paramNinePatchDrawable.setBounds(275, 172, 305, 188);
        paramNinePatchDrawable.draw(paramCanvas);
        int i72 = 188;
        for (int i73 = 330; i73 <= 370; i73 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i73, i72, null);
          i72 -= 6;
        }
        int i74 = 165;
        for (int i75 = 370; i75 <= 430; i75 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i75, i74, null);
          i74 += 8;
        }
        int i76 = 188;
        for (int i77 = 430; i77 <= 490; i77 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i77, i76, null);
          i76 -= 8;
        }
        paramNinePatchDrawable.setBounds(50, 188, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 61.0F)
      {
        int i44 = 145;
        for (int i45 = 50; i45 <= 90; i45 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i45, i44, null);
          i44 += 10;
        }
        int i46 = 165;
        for (int i47 = 90; i47 <= 150; i47 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i47, i46, null);
          i46 -= 6;
        }
        int i48 = 146;
        for (int i49 = 150; i49 <= 230; i49 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i49, i48, null);
          i48 += 5;
        }
        int i50 = 165;
        for (int i51 = 230; i51 <= 290; i51 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i51, i50, null);
          i50 -= 6;
        }
        int i52 = 146;
        for (int i53 = 290; i53 <= 350; i53 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i53, i52, null);
          i52 += 6;
        }
        int i54 = 165;
        for (int i55 = 350; i55 <= 390; i55 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i55, i54, null);
          i54 -= 10;
        }
        int i56 = 146;
        for (int i57 = 390; i57 <= 430; i57 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i57, i56, null);
          i56 += 10;
        }
        int i58 = 165;
        for (int i59 = 430; i59 <= 490; i59 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i59, i58, null);
          i58 -= 6;
        }
        paramNinePatchDrawable.setBounds(50, 165, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 67.0F)
      {
        int i30 = 119;
        for (int i31 = 50; i31 <= 110; i31 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i31, i30, null);
          i30 += 8;
        }
        int i32 = 142;
        for (int i33 = 110; i33 <= 170; i33 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i33, i32, null);
          i32 -= 8;
        }
        int i34 = 119;
        for (int i35 = 170; i35 <= 250; i35 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i35, i34, null);
          i34 += 6;
        }
        int i36 = 142;
        for (int i37 = 250; i37 <= 290; i37 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i37, i36, null);
          i36 -= 6;
        }
        int i38 = 119;
        for (int i39 = 290; i39 <= 330; i39 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i39, i38, null);
          i38 += 6;
        }
        int i40 = 142;
        for (int i41 = 330; i41 <= 410; i41 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i41, i40, null);
          i40 -= 6;
        }
        int i42 = 119;
        for (int i43 = 410; i43 <= 490; i43 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i43, i42, null);
          i42 += 6;
        }
        paramNinePatchDrawable.setBounds(50, 142, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat < 73.0F)
      {
        int i14 = 96;
        for (int i15 = 50; i15 <= 110; i15 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i15, i14, null);
          i14 += 8;
        }
        int i16 = 119;
        for (int i17 = 110; i17 <= 170; i17 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i17, i16, null);
          i16 -= 8;
        }
        int i18 = 96;
        for (int i19 = 170; i19 <= 230; i19 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i19, i18, null);
          i18 += 8;
        }
        int i20 = 119;
        for (int i21 = 230; i21 <= 290; i21 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i21, i20, null);
          i20 -= 5;
        }
        int i22 = 89;
        for (int i23 = 290; i23 <= 350; i23 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i23, i22, null);
          i22 += 5;
        }
        paramNinePatchDrawable.setBounds(250, 104, 320, 119);
        paramNinePatchDrawable.draw(paramCanvas);
        int i24 = 119;
        for (int i25 = 350; i25 <= 390; i25 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i25, i24, null);
          i24 -= 6;
        }
        int i26 = 96;
        for (int i27 = 390; i27 <= 430; i27 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i27, i26, null);
          i26 += 6;
        }
        int i28 = 119;
        for (int i29 = 430; i29 <= 490; i29 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i29, i28, null);
          i28 -= 8;
        }
        paramNinePatchDrawable.setBounds(50, 119, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat <= 79.0F)
      {
        int i = 60;
        for (int j = 50; j <= 90; j += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, j, i, null);
          i += 6;
        }
        int k = 85;
        for (int m = 90; m <= 130; m += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, m, k, null);
          k -= 6;
        }
        int n = 60;
        for (int i1 = 130; i1 <= 190; i1 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i1, n, null);
          n += 8;
        }
        int i2 = 85;
        for (int i3 = 190; i3 <= 250; i3 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i3, i2, null);
          i2 -= 8;
        }
        int i4 = 60;
        for (int i5 = 250; i5 <= 310; i5 += 20)
        {
          paramCanvas.drawBitmap(paramBitmap, i5, i4, null);
          i4 += 8;
        }
        int i6 = 85;
        for (int i7 = 310; i7 <= 370; i7 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i7, i6, null);
          i6 -= 6;
        }
        int i8 = 60;
        for (int i9 = 370; i9 <= 410; i9 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i9, i8, null);
          i8 += 6;
        }
        int i10 = 85;
        for (int i11 = 410; i11 <= 450; i11 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i11, i10, null);
          i10 -= 6;
        }
        int i12 = 60;
        for (int i13 = 450; i13 <= 490; i13 += 10)
        {
          paramCanvas.drawBitmap(paramBitmap, i13, i12, null);
          i12 += 6;
        }
        paramNinePatchDrawable.setBounds(50, 85, 490, 280);
        paramNinePatchDrawable.draw(paramCanvas);
        continue;
      }
      if (paramFloat <= 79.0F)
        continue;
      paramNinePatchDrawable.setBounds(50, 50, 490, 280);
      paramNinePatchDrawable.draw(paramCanvas);
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.customviews.DrawAnimationJDevice
 * JD-Core Version:    0.6.0
 */