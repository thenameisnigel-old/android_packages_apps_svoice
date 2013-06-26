package com.vlingo.core.internal.dialogmanager.vvs.handlers.create;

import android.content.Intent;
import com.vlingo.core.internal.contacts.ContactData;
import com.vlingo.core.internal.contacts.ContactMatch;
import com.vlingo.core.internal.dialogmanager.DMActionType;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendEmailInterface;
import com.vlingo.core.internal.dialogmanager.actions.interfaces.SendMessageInterface;
import com.vlingo.core.internal.dialogmanager.events.ActionCancelledEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionCompletedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionConfirmedEvent;
import com.vlingo.core.internal.dialogmanager.events.ActionFailedEvent;
import com.vlingo.core.internal.dialogmanager.events.ContentChangedEvent;
import com.vlingo.core.internal.dialogmanager.types.MessageType;
import com.vlingo.core.internal.dialogmanager.types.RecipientType;
import com.vlingo.core.internal.dialogmanager.util.DialogDataUtil;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandler;
import com.vlingo.core.internal.dialogmanager.vvs.VVSActionHandlerListener;
import com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener;
import com.vlingo.core.internal.messages.SMSMMSAlert;
import com.vlingo.core.internal.recognition.acceptedtext.SMSAcceptedText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.json.JSONException;

public class ShowComposeMessageHandler extends VVSActionHandler
  implements WidgetActionListener
{
  private List<ContactMatch> allMatches;
  protected String forwardedMessage;
  protected LinkedList<SMSMMSAlert> messages;
  protected RecipientType replyRecipient;

  private List<String> extractAddresses(MessageType paramMessageType)
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = paramMessageType.getRecipients().iterator();
    while (localIterator.hasNext())
    {
      RecipientType localRecipientType = (RecipientType)localIterator.next();
      if (localLinkedList.contains(localRecipientType.getAddress()))
        continue;
      localLinkedList.add(localRecipientType.getAddress());
    }
    return localLinkedList;
  }

  private List<String> extractContactNames(String paramString)
    throws JSONException
  {
    LinkedList localLinkedList = new LinkedList();
    Set localSet1 = DialogDataUtil.getMessageRecipientContacts(this.allMatches, paramString);
    Set localSet2 = DialogDataUtil.getMessageRawPhoneNumbers(paramString);
    if (localSet1 != null)
    {
      Iterator localIterator2 = localSet1.iterator();
      while (localIterator2.hasNext())
      {
        ContactData localContactData = (ContactData)localIterator2.next();
        if (localContactData.contact != null)
        {
          localLinkedList.add(localContactData.contact.primaryDisplayName);
          continue;
        }
        localLinkedList.add("");
      }
    }
    if (localSet2 != null)
    {
      Iterator localIterator1 = localSet2.iterator();
      while (localIterator1.hasNext())
        localLinkedList.add((String)localIterator1.next());
    }
    return localLinkedList;
  }

  private List<String> extractPhoneNumbers(String paramString)
    throws JSONException
  {
    LinkedList localLinkedList = new LinkedList();
    Set localSet1 = DialogDataUtil.getMessageRecipientContacts(this.allMatches, paramString);
    Set localSet2 = DialogDataUtil.getMessageRawPhoneNumbers(paramString);
    if (localSet1 != null)
    {
      Iterator localIterator2 = localSet1.iterator();
      while (localIterator2.hasNext())
        localLinkedList.add(((ContactData)localIterator2.next()).address);
    }
    if (localSet2 != null)
    {
      Iterator localIterator1 = localSet2.iterator();
      while (localIterator1.hasNext())
        localLinkedList.add((String)localIterator1.next());
    }
    return localLinkedList;
  }

  private void sendEmail(String paramString1, String paramString2, Set<ContactData> paramSet)
    throws JSONException
  {
    if ((paramSet != null) && (!paramSet.isEmpty()))
      ((SendEmailInterface)getAction(DMActionType.SEND_EMAIL, SendEmailInterface.class)).contacts(new ArrayList(paramSet)).subject(paramString1).message(paramString2).queue();
  }

  private void sendSMS(String paramString, MessageType paramMessageType)
    throws JSONException
  {
    List localList1 = extractAddresses(paramMessageType);
    if ((localList1 != null) && (!localList1.isEmpty()))
      ((SendMessageInterface)getAction(DMActionType.SEND_MESSAGE, SendMessageInterface.class)).addresses(localList1).message(paramString).queue();
    if ((paramMessageType != null) && (paramString != null))
      try
      {
        List localList2 = extractAddresses(paramMessageType);
        if (localList2 != null)
        {
          Iterator localIterator = localList2.iterator();
          while (localIterator.hasNext())
            sendAcceptedText(new SMSAcceptedText(paramString, (String)localIterator.next()));
        }
      }
      catch (Exception localException)
      {
      }
  }

  public void actionFail(String paramString)
  {
    ActionFailedEvent localActionFailedEvent = new ActionFailedEvent(paramString);
    getListener().sendEvent(localActionFailedEvent, this.turn);
    getListener().asyncHandlerDone();
  }

  public void actionSuccess()
  {
    ActionCompletedEvent localActionCompletedEvent = new ActionCompletedEvent();
    getListener().sendEvent(localActionCompletedEvent, this.turn);
    getListener().asyncHandlerDone();
  }

  // ERROR //
  public boolean executeAction(com.vlingo.sdk.recognition.VLAction paramVLAction, VVSActionHandlerListener paramVVSActionHandlerListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokespecial 194	com/vlingo/core/internal/dialogmanager/vvs/VVSActionHandler:executeAction	(Lcom/vlingo/sdk/recognition/VLAction;Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;)Z
    //   6: pop
    //   7: invokestatic 200	com/vlingo/core/internal/userlogging/UserLoggingEngine:getInstance	()Lcom/vlingo/core/internal/userlogging/UserLoggingEngine;
    //   10: ldc 202
    //   12: invokevirtual 205	com/vlingo/core/internal/userlogging/UserLoggingEngine:landingPageViewed	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: aload_0
    //   17: invokevirtual 173	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:getListener	()Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;
    //   20: getstatic 211	com/vlingo/core/internal/dialogmanager/DialogDataType:CONTACT_MATCHES	Lcom/vlingo/core/internal/dialogmanager/DialogDataType;
    //   23: invokeinterface 215 2 0
    //   28: checkcast 33	java/util/List
    //   31: putfield 66	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:allMatches	Ljava/util/List;
    //   34: aload_1
    //   35: ldc 216
    //   37: invokeinterface 222 2 0
    //   42: astore 4
    //   44: aload_1
    //   45: ldc 223
    //   47: invokeinterface 222 2 0
    //   52: astore 5
    //   54: aload 5
    //   56: invokestatic 229	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   59: ifeq +45 -> 104
    //   62: aload_0
    //   63: getfield 231	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:messages	Ljava/util/LinkedList;
    //   66: ifnull +38 -> 104
    //   69: aload_0
    //   70: getfield 231	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:messages	Ljava/util/LinkedList;
    //   73: invokevirtual 232	java/util/LinkedList:isEmpty	()Z
    //   76: ifne +28 -> 104
    //   79: aload_0
    //   80: getfield 231	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:messages	Ljava/util/LinkedList;
    //   83: iconst_0
    //   84: invokevirtual 236	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   87: checkcast 238	com/vlingo/core/internal/messages/SMSMMSAlert
    //   90: aload_0
    //   91: invokevirtual 173	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:getListener	()Lcom/vlingo/core/internal/dialogmanager/vvs/VVSActionHandlerListener;
    //   94: invokeinterface 242 1 0
    //   99: invokevirtual 246	com/vlingo/core/internal/messages/SMSMMSAlert:getDisplayableMessageText	(Landroid/content/Context;)Ljava/lang/String;
    //   102: astore 5
    //   104: aload_1
    //   105: ldc 248
    //   107: invokeinterface 222 2 0
    //   112: astore 6
    //   114: new 27	com/vlingo/core/internal/dialogmanager/types/MessageType
    //   117: dup
    //   118: aload 5
    //   120: ldc 92
    //   122: ldc 92
    //   124: invokespecial 251	com/vlingo/core/internal/dialogmanager/types/MessageType:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   127: astore 7
    //   129: aload_1
    //   130: ldc 253
    //   132: iconst_1
    //   133: invokestatic 259	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamString	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;Z)Ljava/lang/String;
    //   136: astore 8
    //   138: aconst_null
    //   139: astore 9
    //   141: aload_0
    //   142: getfield 66	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:allMatches	Ljava/util/List;
    //   145: ifnull +30 -> 175
    //   148: aload_0
    //   149: getfield 66	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:allMatches	Ljava/util/List;
    //   152: invokeinterface 141 1 0
    //   157: ifne +18 -> 175
    //   160: aload_0
    //   161: getfield 66	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:allMatches	Ljava/util/List;
    //   164: aload 8
    //   166: invokestatic 72	com/vlingo/core/internal/dialogmanager/util/DialogDataUtil:getMessageRecipientContacts	(Ljava/util/List;Ljava/lang/String;)Ljava/util/Set;
    //   169: astore 28
    //   171: aload 28
    //   173: astore 9
    //   175: aload 8
    //   177: invokestatic 76	com/vlingo/core/internal/dialogmanager/util/DialogDataUtil:getMessageRawPhoneNumbers	(Ljava/lang/String;)Ljava/util/Set;
    //   180: astore 12
    //   182: aload 9
    //   184: ifnull +98 -> 282
    //   187: aload 9
    //   189: invokeinterface 79 1 0
    //   194: astore 24
    //   196: aload 24
    //   198: invokeinterface 43 1 0
    //   203: ifeq +79 -> 282
    //   206: aload 24
    //   208: invokeinterface 47 1 0
    //   213: checkcast 81	com/vlingo/core/internal/contacts/ContactData
    //   216: astore 25
    //   218: aload 25
    //   220: ifnull -24 -> 196
    //   223: aload 25
    //   225: getfield 98	com/vlingo/core/internal/contacts/ContactData:address	Ljava/lang/String;
    //   228: invokestatic 229	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   231: ifne -35 -> 196
    //   234: new 49	com/vlingo/core/internal/dialogmanager/types/RecipientType
    //   237: dup
    //   238: aload 25
    //   240: getfield 85	com/vlingo/core/internal/contacts/ContactData:contact	Lcom/vlingo/core/internal/contacts/ContactMatch;
    //   243: getfield 90	com/vlingo/core/internal/contacts/ContactMatch:primaryDisplayName	Ljava/lang/String;
    //   246: aload 25
    //   248: getfield 98	com/vlingo/core/internal/contacts/ContactData:address	Ljava/lang/String;
    //   251: invokespecial 260	com/vlingo/core/internal/dialogmanager/types/RecipientType:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   254: astore 26
    //   256: aload 7
    //   258: aload 26
    //   260: invokevirtual 264	com/vlingo/core/internal/dialogmanager/types/MessageType:addRecipient	(Lcom/vlingo/core/internal/dialogmanager/types/RecipientType;)V
    //   263: goto -67 -> 196
    //   266: astore 27
    //   268: iconst_0
    //   269: istore 11
    //   271: iload 11
    //   273: ireturn
    //   274: astore 10
    //   276: iconst_0
    //   277: istore 11
    //   279: goto -8 -> 271
    //   282: aload 12
    //   284: ifnull +57 -> 341
    //   287: aload 12
    //   289: invokeinterface 79 1 0
    //   294: astore 21
    //   296: aload 21
    //   298: invokeinterface 43 1 0
    //   303: ifeq +38 -> 341
    //   306: aload 21
    //   308: invokeinterface 47 1 0
    //   313: checkcast 94	java/lang/String
    //   316: astore 22
    //   318: new 49	com/vlingo/core/internal/dialogmanager/types/RecipientType
    //   321: dup
    //   322: ldc 92
    //   324: aload 22
    //   326: invokespecial 260	com/vlingo/core/internal/dialogmanager/types/RecipientType:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: astore 23
    //   331: aload 7
    //   333: aload 23
    //   335: invokevirtual 264	com/vlingo/core/internal/dialogmanager/types/MessageType:addRecipient	(Lcom/vlingo/core/internal/dialogmanager/types/RecipientType;)V
    //   338: goto -42 -> 296
    //   341: aload_0
    //   342: getfield 266	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:replyRecipient	Lcom/vlingo/core/internal/dialogmanager/types/RecipientType;
    //   345: ifnull +12 -> 357
    //   348: aload 7
    //   350: aload_0
    //   351: getfield 266	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:replyRecipient	Lcom/vlingo/core/internal/dialogmanager/types/RecipientType;
    //   354: invokevirtual 264	com/vlingo/core/internal/dialogmanager/types/MessageType:addRecipient	(Lcom/vlingo/core/internal/dialogmanager/types/RecipientType;)V
    //   357: aload 7
    //   359: aload 4
    //   361: invokevirtual 269	com/vlingo/core/internal/dialogmanager/types/MessageType:setSubject	(Ljava/lang/String;)V
    //   364: aload 7
    //   366: aload 6
    //   368: invokevirtual 272	com/vlingo/core/internal/dialogmanager/types/MessageType:setType	(Ljava/lang/String;)V
    //   371: aconst_null
    //   372: astore 13
    //   374: aload_1
    //   375: ldc_w 274
    //   378: iconst_1
    //   379: iconst_0
    //   380: invokestatic 278	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamBool	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;ZZ)Z
    //   383: istore 14
    //   385: aload_1
    //   386: ldc_w 280
    //   389: iconst_1
    //   390: iconst_0
    //   391: invokestatic 278	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamBool	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;ZZ)Z
    //   394: istore 15
    //   396: iload 14
    //   398: ifne +150 -> 548
    //   401: iload 15
    //   403: ifeq +145 -> 548
    //   406: iconst_1
    //   407: istore 16
    //   409: invokestatic 286	com/vlingo/core/internal/dialogmanager/WidgetDecorator:makeSendButton	()Lcom/vlingo/core/internal/dialogmanager/WidgetDecorator;
    //   412: invokevirtual 289	com/vlingo/core/internal/dialogmanager/WidgetDecorator:cancelButton	()Lcom/vlingo/core/internal/dialogmanager/WidgetDecorator;
    //   415: astore 17
    //   417: iload 16
    //   419: ifne +135 -> 554
    //   422: aload 7
    //   424: invokevirtual 292	com/vlingo/core/internal/dialogmanager/types/MessageType:getMessage	()Ljava/lang/String;
    //   427: invokestatic 229	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   430: ifne +124 -> 554
    //   433: aload 17
    //   435: astore 13
    //   437: aload 7
    //   439: invokevirtual 295	com/vlingo/core/internal/dialogmanager/types/MessageType:getType	()Ljava/lang/String;
    //   442: ldc_w 297
    //   445: invokevirtual 300	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   448: ifeq +141 -> 589
    //   451: getstatic 306	com/vlingo/core/internal/dialogmanager/util/WidgetUtil$WidgetKey:ComposeEmail	Lcom/vlingo/core/internal/dialogmanager/util/WidgetUtil$WidgetKey;
    //   454: astore 18
    //   456: aload_2
    //   457: aload 18
    //   459: aload 13
    //   461: aload 7
    //   463: aload_0
    //   464: invokeinterface 310 5 0
    //   469: aload_1
    //   470: ldc_w 280
    //   473: iconst_0
    //   474: iconst_0
    //   475: invokestatic 278	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamBool	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;ZZ)Z
    //   478: ifeq +172 -> 650
    //   481: aload_1
    //   482: ldc 223
    //   484: iconst_0
    //   485: invokestatic 259	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamString	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;Z)Ljava/lang/String;
    //   488: astore 19
    //   490: aload 19
    //   492: invokestatic 229	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   495: ifeq +21 -> 516
    //   498: aload 7
    //   500: invokevirtual 292	com/vlingo/core/internal/dialogmanager/types/MessageType:getMessage	()Ljava/lang/String;
    //   503: invokestatic 229	com/vlingo/core/internal/util/StringUtils:isNullOrWhiteSpace	(Ljava/lang/String;)Z
    //   506: ifne +91 -> 597
    //   509: aload 7
    //   511: invokevirtual 292	com/vlingo/core/internal/dialogmanager/types/MessageType:getMessage	()Ljava/lang/String;
    //   514: astore 19
    //   516: aload_1
    //   517: ldc 248
    //   519: iconst_0
    //   520: invokestatic 259	com/vlingo/core/internal/dialogmanager/util/VLActionUtil:getParamString	(Lcom/vlingo/sdk/recognition/VLAction;Ljava/lang/String;Z)Ljava/lang/String;
    //   523: ldc_w 297
    //   526: invokevirtual 300	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   529: ifeq +79 -> 608
    //   532: aload_0
    //   533: aload 4
    //   535: aload 19
    //   537: aload 9
    //   539: invokespecial 312	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:sendEmail	(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V
    //   542: iconst_1
    //   543: istore 11
    //   545: goto -274 -> 271
    //   548: iconst_0
    //   549: istore 16
    //   551: goto -142 -> 409
    //   554: aload 7
    //   556: invokevirtual 31	com/vlingo/core/internal/dialogmanager/types/MessageType:getRecipients	()Ljava/util/List;
    //   559: ifnonnull +10 -> 569
    //   562: aload 17
    //   564: astore 13
    //   566: goto -129 -> 437
    //   569: aload 7
    //   571: invokevirtual 31	com/vlingo/core/internal/dialogmanager/types/MessageType:getRecipients	()Ljava/util/List;
    //   574: invokeinterface 141 1 0
    //   579: ifeq -142 -> 437
    //   582: aload 17
    //   584: astore 13
    //   586: goto -149 -> 437
    //   589: getstatic 315	com/vlingo/core/internal/dialogmanager/util/WidgetUtil$WidgetKey:ComposeMessage	Lcom/vlingo/core/internal/dialogmanager/util/WidgetUtil$WidgetKey;
    //   592: astore 18
    //   594: goto -138 -> 456
    //   597: new 317	java/security/InvalidParameterException
    //   600: dup
    //   601: ldc_w 319
    //   604: invokespecial 320	java/security/InvalidParameterException:<init>	(Ljava/lang/String;)V
    //   607: athrow
    //   608: aload_0
    //   609: aload 19
    //   611: aload 7
    //   613: invokespecial 322	com/vlingo/core/internal/dialogmanager/vvs/handlers/create/ShowComposeMessageHandler:sendSMS	(Ljava/lang/String;Lcom/vlingo/core/internal/dialogmanager/types/MessageType;)V
    //   616: goto -74 -> 542
    //   619: astore 20
    //   621: new 317	java/security/InvalidParameterException
    //   624: dup
    //   625: new 324	java/lang/StringBuilder
    //   628: dup
    //   629: invokespecial 325	java/lang/StringBuilder:<init>	()V
    //   632: ldc_w 327
    //   635: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: aload 8
    //   640: invokevirtual 331	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   643: invokevirtual 334	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   646: invokespecial 320	java/security/InvalidParameterException:<init>	(Ljava/lang/String;)V
    //   649: athrow
    //   650: iconst_0
    //   651: istore 11
    //   653: goto -382 -> 271
    //
    // Exception table:
    //   from	to	target	type
    //   160	171	266	org/json/JSONException
    //   175	182	274	org/json/JSONException
    //   516	542	619	org/json/JSONException
    //   608	616	619	org/json/JSONException
  }

  public void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException
  {
    if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Default"))
    {
      ActionConfirmedEvent localActionConfirmedEvent = new ActionConfirmedEvent();
      getListener().sendEvent(localActionConfirmedEvent, this.turn);
    }
    while (true)
    {
      return;
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.Cancel"))
      {
        ActionCancelledEvent localActionCancelledEvent = new ActionCancelledEvent();
        getListener().sendEvent(localActionCancelledEvent, this.turn);
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.BodyChange"))
      {
        String str = "";
        if (paramIntent.hasExtra("message"))
          str = VLActionUtil.extractParamString(paramIntent, "message");
        ContentChangedEvent localContentChangedEvent2 = new ContentChangedEvent("message", str);
        getListener().queueEvent(localContentChangedEvent2, this.turn);
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.SubjectChange"))
      {
        if (!paramIntent.hasExtra("subject"))
          continue;
        ContentChangedEvent localContentChangedEvent1 = new ContentChangedEvent("subject", VLActionUtil.extractParamString(paramIntent, "subject"));
        getListener().queueEvent(localContentChangedEvent1, this.turn);
        continue;
      }
      if (paramIntent.getAction().equals("com.vlingo.core.internal.dialogmanager.EditTextClicked"))
      {
        getListener().finishTurn();
        continue;
      }
      throwUnknownActionException(paramIntent.getAction());
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.handlers.create.ShowComposeMessageHandler
 * JD-Core Version:    0.6.0
 */