package com.vlingo.core.internal.dialogmanager.vvs;

import android.content.Intent;

public abstract interface WidgetActionListener
{
  public static final String ACTION_ACCEPTED_TEXT = "com.vlingo.core.internal.dialogmanager.AcceptedText";
  public static final String ACTION_BODY_CHANGE = "com.vlingo.core.internal.dialogmanager.BodyChange";
  public static final String ACTION_CALL = "com.vlingo.core.internal.dialogmanager.Call";
  public static final String ACTION_CANCEL = "com.vlingo.core.internal.dialogmanager.Cancel";
  public static final String ACTION_CHOICE = "com.vlingo.core.internal.dialogmanager.Choice";
  public static final String ACTION_CONTACT_CHOICE = "com.vlingo.core.internal.dialogmanager.ContactChoice";
  public static final String ACTION_DATA_TRANSFER = "com.vlingo.core.internal.dialogmanager.DataTransfered";
  public static final String ACTION_DEFAULT = "com.vlingo.core.internal.dialogmanager.Default";
  public static final String ACTION_EDIT = "com.vlingo.core.internal.dialogmanager.Edit";
  public static final String ACTION_EDIT_TEXT_CLICKED = "com.vlingo.core.internal.dialogmanager.EditTextClicked";
  public static final String ACTION_FORWARD = "com.vlingo.core.internal.dialogmanager.Forward";
  public static final String ACTION_LOCAL_SEARCH_RETRY = "com.vlingo.core.internal.dialogmanager.LocalSearchRetry";
  public static final String ACTION_NEXT = "com.vlingo.core.internal.dialogmanager.Next";
  public static final String ACTION_NO_DATA = "com.vlingo.core.internal.dialogmanager.NoData";
  public static final String ACTION_READOUT = "com.vlingo.core.internal.dialogmanager.Readout";
  public static final String ACTION_RECO = "com.vlingo.core.internal.dialogmanager.PerformReco";
  public static final String ACTION_REPLY = "com.vlingo.core.internal.dialogmanager.Reply";
  public static final String ACTION_SAVE = "com.vlingo.core.internal.dialogmanager.Save";
  public static final String ACTION_SOCIAL_CHANGE = "com.vlingo.core.internal.dialogmanager.SocialChange";
  public static final String ACTION_SUBJECT_CHANGE = "com.vlingo.core.internal.dialogmanager.SubjectChange";
  public static final String ACTION_UPDATE_TIME = "com.vlingo.core.internal.dialogmanager.UpdateTime";
  public static final String ACTION_VIEW = "com.vlingo.core.internal.dialogmanager.View";
  public static final String BUNDLE_ACCEPTED_TEXT = "accepted_text";
  public static final String BUNDLE_ADDRESS = "address";
  public static final String BUNDLE_CHOICE = "choice";
  public static final String BUNDLE_DESCRIPTION = "description";
  public static final String BUNDLE_EDITED_TEXT = "edited_text";
  public static final String BUNDLE_ENABLE = "enable";
  public static final String BUNDLE_FROM_ITEM_COUNT = "item_count";
  public static final String BUNDLE_FROM_READ_MESSAGES = "from_read_messages";
  public static final String BUNDLE_ID = "id";
  public static final String BUNDLE_MESSAGE = "message";
  public static final String BUNDLE_MESSAGE_TYPE = "message_type";
  public static final String BUNDLE_PHONE_CHOICE = "phone choice";
  public static final String BUNDLE_SEARCH_LOCATION = "location";
  public static final String BUNDLE_SEARCH_TERM = "search";
  public static final String BUNDLE_SOCIAL_CHOICE = "social choice";
  public static final String BUNDLE_SUBJECT = "subject";
  public static final String BUNDLE_TIME = "time";
  public static final String BUNDLE_TITLE = "title";

  public abstract void handleIntent(Intent paramIntent, Object paramObject)
    throws IllegalArgumentException;
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.dialogmanager.vvs.WidgetActionListener
 * JD-Core Version:    0.6.0
 */