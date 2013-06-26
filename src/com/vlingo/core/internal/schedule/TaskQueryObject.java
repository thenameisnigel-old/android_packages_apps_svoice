package com.vlingo.core.internal.schedule;

import com.vlingo.core.internal.util.StringUtils;

public class TaskQueryObject extends ScheduleTask
{
  private int count;
  private boolean countFromEnd;
  private boolean matchCompleted;
  private boolean matchUndated;

  private boolean matchTime(ScheduleTask paramScheduleTask)
  {
    int i = 0;
    if ((paramScheduleTask.getCompleted()) && (!this.matchCompleted));
    while (true)
    {
      return i;
      long l1 = getBegin();
      long l2 = getEnd();
      if ((!this.matchUndated) && (l1 > 0L) && ((!paramScheduleTask.hasDueDate()) || (paramScheduleTask.getBegin() < l1) || (paramScheduleTask.getBegin() > l2)) && ((paramScheduleTask.getReminderSet() <= 0) || (paramScheduleTask.getReminderTime() < l1) || (paramScheduleTask.getReminderTime() > l2)))
        continue;
      i = 1;
    }
  }

  public boolean containsString(ScheduleTask paramScheduleTask)
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!StringUtils.containsString(this.title, paramScheduleTask.getTitle())));
    for (boolean bool = false; ; bool = matchTime(paramScheduleTask))
      return bool;
  }

  public boolean containsWord(ScheduleTask paramScheduleTask)
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!StringUtils.containsWord(this.title, paramScheduleTask.getTitle())));
    for (boolean bool = false; ; bool = matchTime(paramScheduleTask))
      return bool;
  }

  public int getCount()
  {
    return this.count;
  }

  public long getEnd()
  {
    return this.end.getTime();
  }

  public boolean getMatchUndated()
  {
    return this.matchUndated;
  }

  public void matchCompletedItems()
  {
    this.matchCompleted = true;
  }

  public void matchUndatedItems()
  {
    this.matchUndated = true;
  }

  public boolean matches(ScheduleTask paramScheduleTask)
  {
    if ((!StringUtils.isNullOrWhiteSpace(this.title)) && (!this.title.equalsIgnoreCase(paramScheduleTask.getTitle())));
    for (boolean bool = false; ; bool = matchTime(paramScheduleTask))
      return bool;
  }

  public void setCount(int paramInt)
  {
    if (paramInt < 0)
      this.countFromEnd = true;
    this.count = Math.abs(paramInt);
  }

  public boolean shouldCountFromEnd()
  {
    return this.countFromEnd;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.schedule.TaskQueryObject
 * JD-Core Version:    0.6.0
 */