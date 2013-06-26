package com.samsung.commonimsservice;

import android.util.Log;
import com.sec.android.ims.IMSEventListener;
import com.sec.android.internal.ims.IIMSParams;
import java.util.List;

class SamsungAPCommonService$3 extends IMSEventListener
{
  public void handleEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte, IIMSParams paramIIMSParams)
  {
    monitorenter;
    label1077: label1092: label10309: label10567: label11335: 
    while (true)
    {
      int i81;
      String str10;
      label1149: int i80;
      int i79;
      label1223: int i78;
      label1361: int i77;
      label1490: int i76;
      label1628: int i75;
      label1774: int i74;
      String str9;
      int i73;
      label2119: String str8;
      label2265: int i71;
      label2294: String str7;
      int i70;
      String str6;
      int i69;
      String str5;
      int i68;
      String str4;
      int i67;
      label2893: String str3;
      label3033: int i66;
      label3045: String str2;
      label3185: int i65;
      try
      {
        SamsungAPCommonService.access$200(this.this$0, "IMS event received EventType[" + paramInt2 + "]\n" + "AppType [" + paramInt1 + "] \n" + "Arg1 [" + paramInt3 + "] \n" + "Arg2 [" + paramInt4 + "]\n " + "DATA [" + paramArrayOfByte + "]");
        if (paramIIMSParams == null)
          break label10238;
        SamsungAPCommonService.access$200(this.this$0, "IMS event received EventType[" + paramInt2 + "]\n" + "AppType [" + paramInt1 + "] \n" + "Arg1 [" + paramInt3 + "] \n" + "Arg2 [" + paramInt4 + "]\n " + "DATA [" + paramArrayOfByte + "]\nPDATA [" + paramIIMSParams.getPLettering() + "]" + "[\nHistoryDATA [" + paramIIMSParams.getHistoryInfo() + "]\nModify Supported : [" + paramIIMSParams.getModifyHeader() + "]\n isConferenceCall : [" + paramIIMSParams.getIsConferenceCall() + "]");
        break label10238;
        SamsungAPCommonService.access$200(this.this$0, "CallState listener list size [" + SamsungAPCommonService.access$700(this.this$0).size() + "]");
        SamsungAPCommonService.access$200(this.this$0, "EventType recieved[" + paramInt2 + "] call register len [" + SamsungAPCommonService.access$700(this.this$0).size() + "]");
        switch (paramInt2)
        {
        default:
          SamsungAPCommonService.access$200(this.this$0, "Default case entered");
          return;
          SamsungAPCommonService.access$502(this.this$0, true);
          i81 = 0;
          if (i81 >= SamsungAPCommonService.access$600(this.this$0).size())
            break label10264;
          IIMSRegisterStateListener localIIMSRegisterStateListener9 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i81);
          if (localIIMSRegisterStateListener9 == null)
            break label10289;
          if (paramArrayOfByte == null)
            break label10295;
          str10 = new String(paramArrayOfByte);
          localIIMSRegisterStateListener9.onRegistrationDone(str10, paramInt3);
          break label10289;
          if (i80 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          ((IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i80)).onNetworkTransition(paramInt3, paramInt4);
          i80++;
          break;
        case 300:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:IMS_CALL_REMOTE_CAPTURE_SUCCESS");
          i79 = 0;
          if (i79 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener74 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i79);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener74 + "]");
          if (localIIMSCallStateListener74 == null)
            break label10309;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener74 + "]");
          localIIMSCallStateListener74.onCaptureSuccess(paramInt3, false, new String(paramArrayOfByte));
          break;
        case 301:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:IMS_CALL_REMOTE_CAPTURE_FAILURE");
          i78 = 0;
          if (i78 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener73 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i78);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener73 + "]");
          if (localIIMSCallStateListener73 == null)
            break label10315;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener73 + "]");
          localIIMSCallStateListener73.onCaptureFailure(paramInt3, false);
          break;
        case 298:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:IMS_CALL_LOCAL_CAPTURE_SUCCESS");
          i77 = 0;
          if (i77 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener72 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i77);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener72 + "]");
          if (localIIMSCallStateListener72 == null)
            break label10321;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener72 + "]");
          localIIMSCallStateListener72.onCaptureSuccess(paramInt3, true, new String(paramArrayOfByte));
          break;
        case 299:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:IMS_CALL_LOCAL_CAPTURE_FAILURE");
          i76 = 0;
          if (i76 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener71 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i76);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener71 + "]");
          if (localIIMSCallStateListener71 == null)
            break label10327;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener71 + "]");
          localIIMSCallStateListener71.onCaptureFailure(paramInt3, true);
          break;
        case 313:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:" + paramInt2);
          i75 = 0;
          if (i75 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener70 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i75);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener70 + "]");
          if (localIIMSCallStateListener70 == null)
            break label10333;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener70 + "]");
          localIIMSCallStateListener70.onCameraEvent(paramInt3, false);
          break;
        case 312:
          SamsungAPCommonService.access$200(this.this$0, "VideoListener: Received Event:" + paramInt2);
          i74 = 0;
          if (i74 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener69 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i74);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener69 + "]");
          if (localIIMSCallStateListener69 == null)
            break label10339;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener69 + "]");
          localIIMSCallStateListener69.onCameraEvent(paramInt3, true);
          break;
        case 202:
          int i72 = SamsungAPCommonService.access$800(this.this$0, paramInt1, paramInt4);
          if (paramArrayOfByte == null)
            break label10351;
          str9 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$200(this.this$0, "SessionID [" + paramInt3 + "] RemoteURI [" + str9 + "] CallType [" + i72 + "]");
          i73 = 0;
          if (i73 >= SamsungAPCommonService.access$700(this.this$0).size())
            continue;
          IIMSCallStateListener localIIMSCallStateListener68 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i73);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener68 + "]");
          if (localIIMSCallStateListener68 == null)
            break label10345;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener68 + "]");
          localIIMSCallStateListener68.onRinging(paramInt3, str9, paramIIMSParams, SamsungAPCommonService.access$800(this.this$0, paramInt1, paramInt4));
          break;
        case 102:
          if (paramArrayOfByte == null)
            break label10365;
          str8 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$902(this.this$0, paramInt3);
          SamsungAPCommonService.access$1002(this.this$0, new String(str8));
          i71 = 0;
          if (i71 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener8 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i71);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener8 + "]");
          if (localIIMSRegisterStateListener8 == null)
            break label10359;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener8 + "]");
          localIIMSRegisterStateListener8.onRegistrationDone(str8, paramInt3);
          break;
        case 104:
          SamsungAPCommonService.access$502(this.this$0, false);
          if (paramArrayOfByte == null)
            break label10385;
          str7 = new String(paramArrayOfByte);
          break label10373;
          if (i70 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener7 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i70);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener7 + "]");
          if (localIIMSRegisterStateListener7 == null)
            break label10379;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener7 + "]");
          localIIMSRegisterStateListener7.onRegistrationFailed(str7, paramInt3, "Registration Failed");
          break;
        case 111:
          if (paramArrayOfByte == null)
            break label10399;
          str6 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$502(this.this$0, false);
          i69 = 0;
          if (i69 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener6 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i69);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener6 + "]");
          if (localIIMSRegisterStateListener6 == null)
            break label10393;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener6 + "]");
          localIIMSRegisterStateListener6.onRegistrationFailed(str6, -2, IIMSCallStateListener.IMSErrorCode.toString(-2));
          break;
        case 103:
          if (paramArrayOfByte == null)
            break label10413;
          str5 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$502(this.this$0, false);
          i68 = 0;
          if (i68 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener5 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i68);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener5 + "]");
          if (localIIMSRegisterStateListener5 == null)
            break label10407;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener5 + "]");
          localIIMSRegisterStateListener5.onRegistrationFailed(str5, -4, IIMSCallStateListener.IMSErrorCode.toString(-4));
          break;
        case 110:
          if (paramArrayOfByte == null)
            break label10427;
          str4 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$502(this.this$0, false);
          i67 = 0;
          if (i67 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener4 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i67);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener4 + "]");
          if (localIIMSRegisterStateListener4 == null)
            break label10421;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener4 + "]");
          localIIMSRegisterStateListener4.onRegistrationFailed(str4, -12, IIMSCallStateListener.IMSErrorCode.toString(-12));
          break;
        case 112:
          if (paramArrayOfByte == null)
            break label10441;
          str3 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$502(this.this$0, false);
          i66 = 0;
          if (i66 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener3 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i66);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener3 + "]");
          if (localIIMSRegisterStateListener3 == null)
            break label10435;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener3 + "]");
          localIIMSRegisterStateListener3.onRegistrationFailed(str3, -4, IIMSCallStateListener.IMSErrorCode.toString(-4));
          break;
        case 109:
          if (paramArrayOfByte == null)
            break label10455;
          str2 = new String(paramArrayOfByte);
          SamsungAPCommonService.access$502(this.this$0, false);
          i65 = 0;
          label3197: if (i65 >= SamsungAPCommonService.access$600(this.this$0).size())
            continue;
          IIMSRegisterStateListener localIIMSRegisterStateListener2 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i65);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener2 + "]");
          if (localIIMSRegisterStateListener2 == null)
            break label10449;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener2 + "]");
          localIIMSRegisterStateListener2.onRegistrationFailed(str2, -5, IIMSCallStateListener.IMSErrorCode.toString(-5));
          break label10449;
          this.this$0.writeErrorData("203", IIMSCallStateListener.IMSErrorCode.toString(-5));
          continue;
        case 113:
        case 213:
        case 351:
        case 205:
        case 356:
        case 358:
        case 209:
        case 204:
        case 207:
        case 228:
        case 229:
        case 230:
        case 231:
        case 234:
        case 222:
        case 237:
        case 311:
        case 359:
        case 306:
        case 309:
        case 303:
        case 352:
        case 353:
        case 354:
        case 355:
        case 304:
        case 305:
        case 314:
        case 308:
        case 317:
        case 307:
        case 214:
        case 321:
        case 210:
        case 225:
        case 212:
        case 217:
        case 216:
        case 310:
        case 360:
        case 361:
        case 215:
        case 357:
        case 318:
        case 219:
        case 235:
        case 218:
        case 238:
        case 265:
        case 266:
        case 319:
        case 322:
        case 327:
        case 208:
        case 203:
        case 331:
        case 337:
        case 332:
        case 339:
        case 343:
        case 344:
        case 334:
        case 333:
        case 340:
        case 341:
        case 338:
        case 342:
        case 345:
        case 347:
        case 346:
        case 348:
        case 335:
        case 336:
        case 263:
        case 264:
        case 302:
        }
      }
      finally
      {
        monitorexit;
      }
      String str1;
      int i64;
      int i63;
      int i62;
      int i61;
      int i60;
      int i59;
      int i58;
      int i57;
      label4295: int i56;
      label4351: int i55;
      int i54;
      label4463: int i53;
      int i52;
      int i51;
      int i50;
      label4721: int i49;
      int i48;
      label4834: int i47;
      int i46;
      int i45;
      int i44;
      int i43;
      label5092: int i42;
      int i41;
      label5204: int i40;
      int i39;
      int i38;
      label5372: int i37;
      int i36;
      label5487: int i35;
      int i34;
      int i33;
      int i32;
      int i31;
      int i30;
      int i29;
      int i28;
      int i27;
      int i26;
      int i25;
      int i24;
      int i23;
      int i22;
      int i21;
      int i20;
      int i19;
      int i18;
      int i17;
      label7677: int i16;
      label7796: int i15;
      label7915: int i14;
      label8034: int i13;
      label8153: int i12;
      label8272: int i11;
      label8391: int i10;
      int i9;
      int i8;
      int i7;
      int i6;
      int i5;
      int i4;
      int i3;
      int i2;
      label9463: int i1;
      label9579: int n;
      label9701: int m;
      label9823: int k;
      label9955: int j;
      if (paramArrayOfByte != null)
      {
        str1 = new String(paramArrayOfByte);
        SamsungAPCommonService.access$502(this.this$0, false);
        i64 = 0;
        if (i64 < SamsungAPCommonService.access$600(this.this$0).size())
        {
          IIMSRegisterStateListener localIIMSRegisterStateListener1 = (IIMSRegisterStateListener)SamsungAPCommonService.access$600(this.this$0).get(i64);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSRegisterStateListener1 + "]");
          if (localIIMSRegisterStateListener1 != null)
          {
            SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSRegisterStateListener1 + "]");
            localIIMSRegisterStateListener1.onRegistrationFailed(str1, -50, IIMSCallStateListener.IMSErrorCode.toString(-50));
          }
        }
        else
        {
          this.this$0.writeErrorData("206", IIMSCallStateListener.IMSErrorCode.toString(-50));
          continue;
          if (i63 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10487;
          IIMSCallStateListener localIIMSCallStateListener67 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i63);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener67 + "]");
          if (localIIMSCallStateListener67 == null)
            break label10483;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener67 + "]");
          localIIMSCallStateListener67.onRingingBack(paramInt3);
          break label10483;
          if (i62 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10499;
          IIMSCallStateListener localIIMSCallStateListener66 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i62);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener66 + "]");
          if (localIIMSCallStateListener66 == null)
            break label10495;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener66 + "]");
          localIIMSCallStateListener66.onEarlyMediaStart(paramInt3, SamsungAPCommonService.access$800(this.this$0, paramInt1, paramInt4), paramIIMSParams);
          break label10495;
          if (i61 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10511;
          IIMSCallStateListener localIIMSCallStateListener65 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i61);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener65 + "]");
          if (localIIMSCallStateListener65 == null)
            break label10507;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener65 + "]");
          localIIMSCallStateListener65.stopAlertTone(paramInt3);
          break label10507;
          if (i60 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener64 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i60);
            SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener64 + "]");
            if (localIIMSCallStateListener64 == null)
              break label10519;
            SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener64 + "]");
            localIIMSCallStateListener64.onCallEnded(paramInt3);
            break label10519;
          }
          this.this$0.writeErrorData("307", IIMSCallStateListener.IMSErrorCode.toString(-49));
          continue;
          if (i59 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10535;
          IIMSCallStateListener localIIMSCallStateListener63 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i59);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener63 + "]");
          if (localIIMSCallStateListener63 == null)
            break label10531;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener63 + "]");
          localIIMSCallStateListener63.onCalling(paramInt3);
          break label10531;
          if (i58 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10547;
          IIMSCallStateListener localIIMSCallStateListener62 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i58);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener62 + "]");
          if (localIIMSCallStateListener62 == null)
            break label10543;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener62 + "]");
          localIIMSCallStateListener62.onCallEnded(paramInt3);
          break label10543;
          if (i57 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10559;
          IIMSCallStateListener localIIMSCallStateListener61 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i57);
          if (localIIMSCallStateListener61 == null)
            break label10555;
          localIIMSCallStateListener61.onError(paramInt3, -26, "Bad Request");
          break label10555;
          if (i56 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10571;
          IIMSCallStateListener localIIMSCallStateListener60 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i56);
          if (localIIMSCallStateListener60 == null)
            break label10567;
          localIIMSCallStateListener60.onError(paramInt3, -27, "Bad Request");
          break label10567;
          if (i55 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10583;
          IIMSCallStateListener localIIMSCallStateListener59 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i55);
          if (localIIMSCallStateListener59 == null)
            break label10579;
          localIIMSCallStateListener59.onError(paramInt3, -28, "Bad Request");
          break label10579;
          if (i54 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10595;
          IIMSCallStateListener localIIMSCallStateListener58 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i54);
          if (localIIMSCallStateListener58 == null)
            break label10591;
          localIIMSCallStateListener58.onError(paramInt3, -29, "Bad Request");
          break label10591;
          if (i53 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener57 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i53);
            if (localIIMSCallStateListener57 == null)
              break label10603;
            localIIMSCallStateListener57.onError(paramInt3, -30, "Bad Request");
            break label10603;
          }
          this.this$0.writeErrorData("116", IIMSCallStateListener.IMSErrorCode.toString(-30));
          continue;
          if (i52 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener56 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i52);
            if (localIIMSCallStateListener56 == null)
              break label10615;
            localIIMSCallStateListener56.onError(paramInt3, -38, "Bad Request");
            break label10615;
          }
          this.this$0.writeErrorData("101", IIMSCallStateListener.IMSErrorCode.toString(-38));
          continue;
          if (i51 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10631;
          IIMSCallStateListener localIIMSCallStateListener55 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i51);
          if (localIIMSCallStateListener55 == null)
            break label10627;
          localIIMSCallStateListener55.onError(paramInt3, -31, "Bad Request");
          break label10627;
          if (i50 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10643;
          IIMSCallStateListener localIIMSCallStateListener54 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i50);
          if (localIIMSCallStateListener54 == null)
            break label10639;
          localIIMSCallStateListener54.onError(paramInt3, -32, "Bad Request");
          break label10639;
          if (i49 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10655;
          IIMSCallStateListener localIIMSCallStateListener53 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i49);
          if (localIIMSCallStateListener53 == null)
            break label10651;
          localIIMSCallStateListener53.onError(paramInt3, -10, "Network Lost");
          break label10651;
          if (i48 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10667;
          IIMSCallStateListener localIIMSCallStateListener52 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i48);
          if (localIIMSCallStateListener52 == null)
            break label10663;
          localIIMSCallStateListener52.onError(paramInt3, 415, "Bad Request");
          break label10663;
          if (i47 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10679;
          IIMSCallStateListener localIIMSCallStateListener51 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i47);
          if (localIIMSCallStateListener51 == null)
            break label10675;
          localIIMSCallStateListener51.onError(paramInt3, -33, "Bad Request");
          break label10675;
          if (i46 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener50 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i46);
            if (localIIMSCallStateListener50 == null)
              break label10687;
            localIIMSCallStateListener50.onError(paramInt3, -25, "Bad Request");
            break label10687;
          }
          this.this$0.writeErrorData("303", IIMSCallStateListener.IMSErrorCode.toString(-25));
          continue;
          if (i45 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener49 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i45);
            if (localIIMSCallStateListener49 == null)
              break label10713;
            localIIMSCallStateListener49.onError(paramInt3, i44, "Call failed");
            break label10713;
          }
          this.this$0.writeErrorData("303", IIMSCallStateListener.IMSErrorCode.toString(i44));
          continue;
          if (i43 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10771;
          IIMSCallStateListener localIIMSCallStateListener48 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i43);
          if (localIIMSCallStateListener48 == null)
            break label10767;
          localIIMSCallStateListener48.onError(paramInt3, -24, "Bad Request");
          break label10767;
          if (i42 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10783;
          IIMSCallStateListener localIIMSCallStateListener47 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i42);
          if (localIIMSCallStateListener47 == null)
            break label10779;
          localIIMSCallStateListener47.onError(paramInt3, -23, "Bad Request");
          break label10779;
          if (i41 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10795;
          IIMSCallStateListener localIIMSCallStateListener46 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i41);
          if (localIIMSCallStateListener46 == null)
            break label10791;
          localIIMSCallStateListener46.onError(paramInt3, -37, "Bad Request");
          break label10791;
          if (i40 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10807;
          IIMSCallStateListener localIIMSCallStateListener45 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i40);
          if (localIIMSCallStateListener45 == null)
            break label10803;
          localIIMSCallStateListener45.onError(paramInt3, -22, "Bad Request");
          break label10803;
          if (i39 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10819;
          IIMSCallStateListener localIIMSCallStateListener44 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i39);
          if (localIIMSCallStateListener44 == null)
            break label10815;
          localIIMSCallStateListener44.onError(paramInt3, -21, "Bad Request");
          break label10815;
          if (i38 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10831;
          IIMSCallStateListener localIIMSCallStateListener43 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i38);
          if (localIIMSCallStateListener43 == null)
            break label10827;
          localIIMSCallStateListener43.onError(paramInt3, -20, "Bad Request");
          break label10827;
          if (i37 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10843;
          IIMSCallStateListener localIIMSCallStateListener42 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i37);
          if (localIIMSCallStateListener42 == null)
            break label10839;
          localIIMSCallStateListener42.onError(paramInt3, -19, "Bad Request");
          break label10839;
          if (i36 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10855;
          IIMSCallStateListener localIIMSCallStateListener41 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i36);
          if (localIIMSCallStateListener41 == null)
            break label10851;
          localIIMSCallStateListener41.onError(paramInt3, -45, paramIIMSParams.getErrorReasonCode());
          break label10851;
          if (i35 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener40 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i35);
            if (localIIMSCallStateListener40 == null)
              break label10863;
            localIIMSCallStateListener40.onError(paramInt3, -18, "Bad Request");
            break label10863;
          }
          this.this$0.writeErrorData("304", IIMSCallStateListener.IMSErrorCode.toString(-18));
          continue;
          if (i34 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener39 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i34);
            if (localIIMSCallStateListener39 == null)
              break label10875;
            localIIMSCallStateListener39.onError(paramInt3, -17, "Bad Request");
            break label10875;
          }
          this.this$0.writeErrorData("202", IIMSCallStateListener.IMSErrorCode.toString(-17));
          continue;
          if (i33 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10891;
          IIMSCallStateListener localIIMSCallStateListener38 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i33);
          if (localIIMSCallStateListener38 == null)
            break label10887;
          localIIMSCallStateListener38.onError(paramInt3, -16, "Bad Request");
          break label10887;
          SamsungAPCommonService.access$1100(this.this$0, paramInt3);
          continue;
          if (i32 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10903;
          IIMSCallStateListener localIIMSCallStateListener37 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i32);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener37 + "]");
          if (localIIMSCallStateListener37 == null)
            break label10899;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener37 + "]");
          localIIMSCallStateListener37.onCallBusy(paramInt3);
          break label10899;
          if (i31 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10915;
          IIMSCallStateListener localIIMSCallStateListener36 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i31);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener36 + "]");
          if (localIIMSCallStateListener36 == null)
            break label10911;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener36 + "]");
          localIIMSCallStateListener36.onError(paramInt3, -7, "Called party not in LTE area");
          break label10911;
          if (i30 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10927;
          IIMSCallStateListener localIIMSCallStateListener35 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i30);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener35 + "]");
          if (localIIMSCallStateListener35 == null)
            break label10923;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener35 + "]");
          localIIMSCallStateListener35.onError(paramInt3, -47, paramIIMSParams.getErrorReasonCode());
          break label10923;
          if (i29 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10939;
          IIMSCallStateListener localIIMSCallStateListener34 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i29);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener34 + "]");
          if (localIIMSCallStateListener34 == null)
            break label10935;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener34 + "]");
          localIIMSCallStateListener34.onError(paramInt3, -52, "Cannot connect call, Divert to normal call");
          break label10935;
          if (i28 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10951;
          IIMSCallStateListener localIIMSCallStateListener33 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i28);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener33 + "]");
          if (localIIMSCallStateListener33 == null)
            break label10947;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener33 + "]");
          localIIMSCallStateListener33.onError(paramInt3, -6, "Called party does not exist");
          break label10947;
          if (i27 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10963;
          IIMSCallStateListener localIIMSCallStateListener32 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i27);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener32 + "]");
          if (localIIMSCallStateListener32 == null)
            break label10959;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener32 + "]");
          localIIMSCallStateListener32.onError(paramInt3, -44, "Called party has only VoLte Alternative Services");
          break label10959;
          if (i26 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label10975;
          IIMSCallStateListener localIIMSCallStateListener31 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i26);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener31 + "]");
          if (localIIMSCallStateListener31 == null)
            break label10971;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener31 + "]");
          localIIMSCallStateListener31.onError(paramInt3, -34, "Call Failed");
          break label10971;
          if (i25 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener30 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i25);
            SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener30 + "]" + "reason code = " + paramIIMSParams.getErrorReasonCode());
            if (localIIMSCallStateListener30 == null)
              break label10983;
            SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener30 + "]");
            localIIMSCallStateListener30.onError(paramInt3, -48, paramIIMSParams.getErrorReasonCode());
            break label10983;
          }
          this.this$0.writeErrorData("308", IIMSCallStateListener.IMSErrorCode.toString(-48));
          continue;
          if (i24 < SamsungAPCommonService.access$700(this.this$0).size())
          {
            IIMSCallStateListener localIIMSCallStateListener29 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i24);
            SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener29 + "]");
            if (localIIMSCallStateListener29 == null)
              break label10995;
            SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener29 + "]");
            localIIMSCallStateListener29.onError(paramInt3, -39, "Call Failed");
            break label10995;
          }
          this.this$0.writeErrorData("301", IIMSCallStateListener.IMSErrorCode.toString(-39));
          continue;
          if (i23 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11011;
          IIMSCallStateListener localIIMSCallStateListener28 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i23);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener28 + "]");
          if (localIIMSCallStateListener28 == null)
            break label11007;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener28 + "]");
          localIIMSCallStateListener28.onError(paramInt3, -5, "Call Failed");
          break label11007;
          if (i22 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11023;
          IIMSCallStateListener localIIMSCallStateListener27 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i22);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener27 + "]");
          if (localIIMSCallStateListener27 == null)
            break label11019;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener27 + "]");
          localIIMSCallStateListener27.onError(paramInt3, 415, "Called party does not support media");
          break label11019;
          if (i21 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11035;
          IIMSCallStateListener localIIMSCallStateListener26 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i21);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener26 + "]");
          if (localIIMSCallStateListener26 == null)
            break label11031;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener26 + "]");
          localIIMSCallStateListener26.onCallHeld(paramInt3);
          break label11031;
          if (i20 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11047;
          IIMSCallStateListener localIIMSCallStateListener25 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i20);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener25 + "]");
          if (localIIMSCallStateListener25 == null)
            break label11043;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener25 + "]");
          localIIMSCallStateListener25.onCallResumed(paramInt3);
          break label11043;
          if (i19 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11059;
          IIMSCallStateListener localIIMSCallStateListener24 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i19);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener24 + "]");
          if (localIIMSCallStateListener24 == null)
            break label11055;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener24 + "]");
          localIIMSCallStateListener24.onConferenceEstablished(paramInt3, SamsungAPCommonService.access$800(this.this$0, paramInt1, paramInt4), paramIIMSParams);
          break label11055;
          if (i18 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11071;
          IIMSCallStateListener localIIMSCallStateListener23 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i18);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener23 + "]");
          if (localIIMSCallStateListener23 == null)
            break label11067;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener23 + "]");
          localIIMSCallStateListener23.onCallEstablished(paramInt3, SamsungAPCommonService.access$800(this.this$0, paramInt1, paramInt4), paramIIMSParams);
          break label11067;
          if (i17 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11083;
          IIMSCallStateListener localIIMSCallStateListener22 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i17);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener22 + "]");
          if (localIIMSCallStateListener22 == null)
            break label11079;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener22 + "]");
          localIIMSCallStateListener22.onCalling(paramInt3);
          break label11079;
          if (i16 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11095;
          IIMSCallStateListener localIIMSCallStateListener21 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i16);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener21 + "]");
          if (localIIMSCallStateListener21 == null)
            break label11091;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener21 + "]");
          localIIMSCallStateListener21.onChangeRequest(paramInt3, 2, paramArrayOfByte);
          break label11091;
          if (i15 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11107;
          IIMSCallStateListener localIIMSCallStateListener20 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i15);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener20 + "]");
          if (localIIMSCallStateListener20 == null)
            break label11103;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener20 + "]");
          localIIMSCallStateListener20.onChangeRequest(paramInt3, 3, paramArrayOfByte);
          break label11103;
          if (i14 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11119;
          IIMSCallStateListener localIIMSCallStateListener19 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i14);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener19 + "]");
          if (localIIMSCallStateListener19 == null)
            break label11115;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener19 + "]");
          localIIMSCallStateListener19.onChangeRequest(paramInt3, 5, paramArrayOfByte);
          break label11115;
          if (i13 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11131;
          IIMSCallStateListener localIIMSCallStateListener18 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i13);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener18 + "]");
          if (localIIMSCallStateListener18 == null)
            break label11127;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener18 + "]");
          localIIMSCallStateListener18.onChangeRequest(paramInt3, 3, paramArrayOfByte);
          break label11127;
          if (i12 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11143;
          IIMSCallStateListener localIIMSCallStateListener17 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i12);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener17 + "]");
          if (localIIMSCallStateListener17 == null)
            break label11139;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener17 + "]");
          localIIMSCallStateListener17.onChangeRequest(paramInt3, 5, paramArrayOfByte);
          break label11139;
          if (i11 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11155;
          IIMSCallStateListener localIIMSCallStateListener16 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i11);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener16 + "]");
          if (localIIMSCallStateListener16 == null)
            break label11151;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener16 + "]");
          localIIMSCallStateListener16.onCallEstablished(paramInt3, 5, paramIIMSParams);
          break label11151;
          if (i10 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11167;
          IIMSCallStateListener localIIMSCallStateListener15 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i10);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener15 + "]");
          if (localIIMSCallStateListener15 == null)
            break label11163;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener15 + "]");
          localIIMSCallStateListener15.onCallSwitched(paramInt3, 334, paramIIMSParams);
          break label11163;
          if (i9 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11179;
          IIMSCallStateListener localIIMSCallStateListener14 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i9);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener14 + "]");
          if (localIIMSCallStateListener14 == null)
            break label11175;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener14 + "]");
          localIIMSCallStateListener14.onCallSwitched(paramInt3, 333, paramIIMSParams);
          break label11175;
          if (i8 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11191;
          IIMSCallStateListener localIIMSCallStateListener13 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i8);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener13 + "]");
          if (localIIMSCallStateListener13 == null)
            break label11187;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener13 + "]");
          localIIMSCallStateListener13.onCallEstablished(paramInt3, 3, paramIIMSParams);
          break label11187;
          if (i7 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11203;
          IIMSCallStateListener localIIMSCallStateListener12 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i7);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener12 + "]");
          if (localIIMSCallStateListener12 == null)
            break label11199;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener12 + "]");
          localIIMSCallStateListener12.onCallEstablished(paramInt3, 3, paramIIMSParams);
          break label11199;
          if (i6 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11215;
          IIMSCallStateListener localIIMSCallStateListener11 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i6);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener11 + "]");
          if (localIIMSCallStateListener11 == null)
            break label11211;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener11 + "]");
          localIIMSCallStateListener11.onChangeRequest(paramInt3, 2, paramArrayOfByte);
          break label11211;
          if (i5 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11227;
          IIMSCallStateListener localIIMSCallStateListener10 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i5);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener10 + "]");
          if (localIIMSCallStateListener10 == null)
            break label11223;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener10 + "]");
          localIIMSCallStateListener10.onCallEstablished(paramInt3, 2, paramIIMSParams);
          break label11223;
          if (i4 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11239;
          IIMSCallStateListener localIIMSCallStateListener9 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i4);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener9 + "]");
          if (localIIMSCallStateListener9 == null)
            break label11235;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener9 + "]");
          localIIMSCallStateListener9.onChangeRequest(paramInt3, 3, paramArrayOfByte);
          break label11235;
          if (i3 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11251;
          IIMSCallStateListener localIIMSCallStateListener8 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i3);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener8 + "]");
          if (localIIMSCallStateListener8 == null)
            break label11247;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener8 + "]");
          localIIMSCallStateListener8.onChangeRequest(paramInt3, 3, paramArrayOfByte);
          break label11247;
          if (i2 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11263;
          IIMSCallStateListener localIIMSCallStateListener7 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i2);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener7 + "]");
          if (localIIMSCallStateListener7 == null)
            break label11259;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener7 + "]");
          localIIMSCallStateListener7.onVideoHeld(paramInt3);
          break label11259;
          if (i1 >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11275;
          IIMSCallStateListener localIIMSCallStateListener6 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i1);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener6 + "]");
          if (localIIMSCallStateListener6 == null)
            break label11271;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener6 + "]");
          localIIMSCallStateListener6.onVideoResumed(paramInt3);
          break label11271;
          if (n >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11287;
          IIMSCallStateListener localIIMSCallStateListener5 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(n);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener5 + "]");
          if (localIIMSCallStateListener5 == null)
            break label11283;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener5 + "]");
          localIIMSCallStateListener5.onError(paramInt3, 700, "Upgrade/downgrade rejected");
          break label11283;
          if (m >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11299;
          IIMSCallStateListener localIIMSCallStateListener4 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(m);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener4 + "]");
          if (localIIMSCallStateListener4 == null)
            break label11295;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener4 + "]");
          localIIMSCallStateListener4.onError(paramInt3, 701, "Upgrade/downgrade rejected");
          break label11295;
          if (k >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11311;
          IIMSCallStateListener localIIMSCallStateListener3 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(k);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener3 + "]");
          if (localIIMSCallStateListener3 == null)
            break label11307;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener3 + "]");
          SamsungAPCommonService.access$200(this.this$0, "IMS_CALL_HOLD_FAILED_IND..");
          localIIMSCallStateListener3.onError(paramInt3, 702, "Hold has failed ");
          break label11307;
          if (j >= SamsungAPCommonService.access$700(this.this$0).size())
            break label11323;
          IIMSCallStateListener localIIMSCallStateListener2 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(j);
          SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener2 + "]");
          SamsungAPCommonService.access$200(this.this$0, "IMS_CALL_RETRIEVE_FAILED_IND...");
          if (localIIMSCallStateListener2 == null)
            break label11319;
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener2 + "]");
          localIIMSCallStateListener2.onError(paramInt3, 703, "Resume has failed ");
          break label11319;
        }
      }
      while (true)
      {
        int i;
        if (i >= SamsungAPCommonService.access$700(this.this$0).size())
          break label11335;
        IIMSCallStateListener localIIMSCallStateListener1 = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i);
        SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener1 + "]");
        if (localIIMSCallStateListener1 != null)
        {
          SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener1 + "]");
          localIIMSCallStateListener1.onVideoAvailable(paramInt3);
          break label11331;
          label10238: 
          do
          {
            Log.e("SamsungAPCommonService", "Event listener invalid app type [" + paramInt1 + "]");
            break label1077;
            if (paramInt1 == 10)
              switch (paramInt2)
              {
              case 1001:
              }
            if ((paramInt1 == 8) || (paramInt1 == 4) || (paramInt1 == 6))
              break;
          }
          while (paramInt1 != 5);
          break;
          i81++;
          break label1092;
          str10 = "";
          break label1149;
          i80 = 0;
          break label1163;
          i79++;
          break label1223;
          label10315: i78++;
          break label1361;
          label10321: i77++;
          break label1490;
          label10327: i76++;
          break label1628;
          label10333: i75++;
          break label1774;
          label10339: i74++;
          break label1920;
          label10345: i73++;
          break label2119;
          label10351: str9 = "";
          break label2065;
          label10359: i71++;
          break label2294;
          label10365: str8 = "";
          break label2265;
          while (true)
          {
            i70 = 0;
            break;
            i70++;
            break;
            str7 = "";
          }
          i69++;
          break label2589;
          str6 = "";
          break label2577;
          i68++;
          break label2741;
          str5 = "";
          break label2729;
          i67++;
          break label2893;
          str4 = "";
          break label2881;
          i66++;
          break label3045;
          label10441: str3 = "";
          break label3033;
          label10449: i65++;
          break label3197;
          label10455: str2 = "";
          break label3185;
          i64++;
          break label3373;
          str1 = "";
          break label3361;
          i63 = 0;
          break label3514;
          label10483: i63++;
          break label3514;
          label10487: break label1077;
          i62 = 0;
          break label3630;
          label10495: i62++;
          break label3630;
          break label1077;
          i61 = 0;
          break label3758;
          i61++;
          break label3758;
          break label1077;
          i60 = 0;
          break label3874;
          i60++;
          break label3874;
          i59 = 0;
          break label4007;
          i59++;
          break label4007;
          break label1077;
          i58 = 0;
          break label4123;
          i58++;
          break label4123;
          break label1077;
          i57 = 0;
          break label4239;
          i57++;
          break label4239;
          break label1077;
          i56 = 0;
          break label4295;
          i56++;
          break label4295;
          label10571: break label1077;
          i55 = 0;
          break label4351;
          label10579: i55++;
          break label4351;
          label10583: break label1077;
          i54 = 0;
          break label4407;
          label10591: i54++;
          break label4407;
          label10595: break label1077;
          i53 = 0;
          break label4463;
          label10603: i53++;
          break label4463;
          i52 = 0;
          break label4536;
          label10615: i52++;
          break label4536;
          i51 = 0;
          break label4609;
          i51++;
          break label4609;
          break label1077;
          i50 = 0;
          break label4665;
          i50++;
          break label4665;
          break label1077;
          i49 = 0;
          break label4721;
          i49++;
          break label4721;
          break label1077;
          i48 = 0;
          break label4777;
          i48++;
          break label4777;
          break label1077;
          i47 = 0;
          break label4834;
          i47++;
          break label4834;
          break label1077;
          i46 = 0;
          break label4890;
          i46++;
          break label4890;
          i44 = 0;
          if (paramInt2 == 354)
            i44 = -40;
          while (true)
          {
            i45 = 0;
            break;
            label10713: i45++;
            break;
            if (paramInt2 == 355)
            {
              i44 = -41;
              continue;
            }
            if (paramInt2 == 352)
            {
              i44 = -43;
              continue;
            }
            if (paramInt2 != 353)
              continue;
            i44 = -42;
          }
          i43 = 0;
          break label5036;
          i43++;
          break label5036;
          break label1077;
          i42 = 0;
          break label5092;
          i42++;
          break label5092;
          break label1077;
          i41 = 0;
          break label5148;
          i41++;
          break label5148;
          break label1077;
          i40 = 0;
          break label5204;
          i40++;
          break label5204;
          break label1077;
          i39 = 0;
          break label5260;
          i39++;
          break label5260;
          break label1077;
          i38 = 0;
          break label5316;
          label10827: i38++;
          break label5316;
          label10831: break label1077;
          i37 = 0;
          break label5372;
          label10839: i37++;
          break label5372;
          label10843: break label1077;
          i36 = 0;
          break label5428;
          label10851: i36++;
          break label5428;
          label10855: break label1077;
          i35 = 0;
          break label5487;
          label10863: i35++;
          break label5487;
          i34 = 0;
          break label5560;
          label10875: i34++;
          break label5560;
          i33 = 0;
          break label5633;
          i33++;
          break label5633;
          break label1077;
          i32 = 0;
          break label5700;
          i32++;
          break label5700;
          break label1077;
          i31 = 0;
          break label5816;
          i31++;
          break label5816;
          break label1077;
          i30 = 0;
          break label5936;
          i30++;
          break label5936;
          break label1077;
          i29 = 0;
          break label6059;
          i29++;
          break label6059;
          break label1077;
          i28 = 0;
          break label6179;
          i28++;
          break label6179;
          break label1077;
          i27 = 0;
          break label6299;
          label10959: i27++;
          break label6299;
          label10963: break label1077;
          i26 = 0;
          break label6419;
          label10971: i26++;
          break label6419;
          label10975: break label1077;
          i25 = 0;
          break label6539;
          label10983: i25++;
          break label6539;
          i24 = 0;
          break label6693;
          label10995: i24++;
          break label6693;
          i23 = 0;
          break label6831;
          label11007: i23++;
          break label6831;
          break label1077;
          i22 = 0;
          break label6951;
          i22++;
          break label6951;
          break label1077;
          i21 = 0;
          break label7073;
          i21++;
          break label7073;
          break label1077;
          i20 = 0;
          break label7189;
          i20++;
          break label7189;
          break label1077;
          i19 = 0;
          break label7305;
          i19++;
          break label7305;
          break label1077;
          i18 = 0;
          break label7433;
          i18++;
          break label7433;
          break label1077;
          i17 = 0;
          break label7561;
          i17++;
          break label7561;
          label11083: break label1077;
          i16 = 0;
          break label7677;
          label11091: i16++;
          break label7677;
          label11095: break label1077;
          i15 = 0;
          break label7796;
          label11103: i15++;
          break label7796;
          label11107: break label1077;
          i14 = 0;
          break label7915;
          label11115: i14++;
          break label7915;
          label11119: break label1077;
          i13 = 0;
          break label8034;
          label11127: i13++;
          break label8034;
          label11131: break label1077;
          i12 = 0;
          break label8153;
          i12++;
          break label8153;
          break label1077;
          i11 = 0;
          break label8272;
          i11++;
          break label8272;
          break label1077;
          i10 = 0;
          break label8391;
          i10++;
          break label8391;
          break label1077;
          i9 = 0;
          break label8512;
          i9++;
          break label8512;
          break label1077;
          i8 = 0;
          break label8633;
          i8++;
          break label8633;
          break label1077;
          i7 = 0;
          break label8752;
          i7++;
          break label8752;
          break label1077;
          i6 = 0;
          break label8871;
          label11211: i6++;
          break label8871;
          label11215: break label1077;
          i5 = 0;
          break label8990;
          label11223: i5++;
          break label8990;
          label11227: break label1077;
          i4 = 0;
          break label9109;
          label11235: i4++;
          break label9109;
          label11239: break label1077;
          i3 = 0;
          break label9228;
          label11247: i3++;
          break label9228;
          label11251: break label1077;
          i2 = 0;
          break label9347;
          label11259: i2++;
          break label9347;
          label11263: break label1077;
          i1 = 0;
          break label9463;
          i1++;
          break label9463;
          break label1077;
          n = 0;
          break label9579;
          n++;
          break label9579;
          break label1077;
          m = 0;
          break label9701;
          m++;
          break label9701;
          break label1077;
          k = 0;
          break label9823;
          k++;
          break label9823;
          break label1077;
          j = 0;
          break label9955;
          j++;
          break label9955;
          break label1077;
          i = 0;
          continue;
        }
        i++;
      }
    }
  }

  public void notifyEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, String[] paramArrayOfString)
  {
    SamsungAPCommonService.access$200(this.this$0, "IMS event received EventType[" + paramInt2 + "]\n" + "AppType [" + paramInt1 + "] \n" + "Arg1 [" + paramInt3 + "] \n" + "Arg2 [" + paramInt4 + "]\n ");
    switch (paramInt2)
    {
    default:
    case 325:
    }
    while (true)
    {
      return;
      for (int i = 0; i < SamsungAPCommonService.access$700(this.this$0).size(); i++)
      {
        IIMSCallStateListener localIIMSCallStateListener = (IIMSCallStateListener)SamsungAPCommonService.access$700(this.this$0).get(i);
        SamsungAPCommonService.access$200(this.this$0, "Listener[" + localIIMSCallStateListener + "]");
        if (localIIMSCallStateListener == null)
          continue;
        SamsungAPCommonService.access$200(this.this$0, "Notifying listener[" + localIIMSCallStateListener + "]");
        localIIMSCallStateListener.onNotifyReceived(paramInt3, paramArrayOfInt, paramArrayOfString);
      }
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.samsung.commonimsservice.SamsungAPCommonService.3
 * JD-Core Version:    0.6.0
 */