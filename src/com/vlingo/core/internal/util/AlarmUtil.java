package com.vlingo.core.internal.util;

import com.vlingo.core.internal.alarms.AlarmQueryObject;
import com.vlingo.core.internal.dialogmanager.util.VLActionUtil;
import com.vlingo.sdk.recognition.VLAction;

public class AlarmUtil
{
  public static final String DAYS_ACTION_PARAM = "days";
  public static final String DAYS_MATCH_TYPE_ACTION_PARAM = "days.match.type";
  public static final String EXACT_DAYS_MATCH_TYPE_PARAM_VALUE = "exact";
  public static final String LOOSE_DAYS_MATCH_TYPE_PARAM_VALUE = "loose";
  public static final String REPEAT_ACTION_PARAM = "repeat";

  public static Alarm extractAlarm(VLAction paramVLAction)
  {
    Alarm localAlarm = new Alarm();
    localAlarm.setActive(VLActionUtil.getParamBoolOrNull(paramVLAction, "set", false).booleanValue());
    localAlarm.setTimeFromCanonical(VLActionUtil.getParamString(paramVLAction, "time", false));
    localAlarm.setWeeklyRepeating(VLActionUtil.getParamBool(paramVLAction, "repeat", false, false));
    localAlarm.addDayFromDaysCanonicalForm(VLActionUtil.getParamString(paramVLAction, "days", false));
    return localAlarm;
  }

  public static AlarmQueryObject extractAlarmQuery(VLAction paramVLAction)
  {
    AlarmQueryObject localAlarmQueryObject = new AlarmQueryObject();
    localAlarmQueryObject.setBegin(VLActionUtil.getParamString(paramVLAction, "range.start", false));
    localAlarmQueryObject.setEnd(VLActionUtil.getParamString(paramVLAction, "range.end", false));
    localAlarmQueryObject.setCount(VLActionUtil.getParamInt(paramVLAction, "count", 0, false));
    localAlarmQueryObject.setActive(VLActionUtil.getParamBoolOrNull(paramVLAction, "set", false));
    localAlarmQueryObject.setRepeating(VLActionUtil.getParamBoolOrNull(paramVLAction, "repeat", false));
    if ("exact".equalsIgnoreCase(VLActionUtil.getParamString(paramVLAction, "days.match.type", false)))
      localAlarmQueryObject.setDaysMatchType(Alarm.MatchType.EXACT);
    while (true)
    {
      Alarm localAlarm = new Alarm();
      String str = VLActionUtil.getParamString(paramVLAction, "days", false);
      if (str != null)
      {
        localAlarm.addDayFromDaysCanonicalForm(str);
        localAlarmQueryObject.setDayMask(Integer.valueOf(localAlarm.getDayMask()));
      }
      localAlarmQueryObject.normalize();
      return localAlarmQueryObject;
      localAlarmQueryObject.setDaysMatchType(Alarm.MatchType.LOOSE);
    }
  }

  private static boolean isDeleteKeyword(String paramString)
  {
    if ((paramString != null) && (paramString.equals("#NULL#")));
    for (int i = 1; ; i = 0)
      return i;
  }

  public static Alarm mergeChanges(VLAction paramVLAction, Alarm paramAlarm)
  {
    Alarm localAlarm = paramAlarm.clone();
    localAlarm.setTimeFromCanonical(mergeValues(paramVLAction, "time", paramAlarm.getTimeCanonical()));
    String str1 = mergeValues(paramVLAction, "set", String.valueOf(true));
    if (isDeleteKeyword(str1))
      localAlarm.setActive(false);
    while (true)
    {
      Boolean localBoolean = VLActionUtil.getParamBoolOrNull(paramVLAction, "repeat", false);
      if (localBoolean != null)
        localAlarm.setWeeklyRepeating(localBoolean.booleanValue());
      String str2 = VLActionUtil.getParamString(paramVLAction, "days", false);
      if (str2 != null)
        localAlarm.setDayFromDaysCanonicalForm(str2);
      return localAlarm;
      localAlarm.setActive(Boolean.valueOf(str1).booleanValue());
    }
  }

  private static String mergeValues(VLAction paramVLAction, String paramString1, String paramString2)
  {
    return mergeValues(VLActionUtil.getParamString(paramVLAction, paramString1, false), paramString2);
  }

  private static String mergeValues(String paramString1, String paramString2)
  {
    if (StringUtils.isNullOrWhiteSpace(paramString1));
    while (true)
    {
      return paramString2;
      if (isDeleteKeyword(paramString1))
      {
        paramString2 = null;
        continue;
      }
      paramString2 = paramString1;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.AlarmUtil
 * JD-Core Version:    0.6.0
 */