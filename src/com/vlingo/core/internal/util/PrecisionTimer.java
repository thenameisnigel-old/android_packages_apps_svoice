package com.vlingo.core.internal.util;

public class PrecisionTimer
{
  private Precision precision = Precision.NANOSECONDS;
  private long start;
  private long stop;

  private long computeElapsedTime()
  {
    long l;
    if (running())
      l = now() - this.start;
    while (true)
    {
      return l;
      l = this.stop - this.start;
    }
  }

  public static PrecisionTimer newTimer()
  {
    return new PrecisionTimer();
  }

  private long now()
  {
    return System.nanoTime();
  }

  public static PrecisionTimer startTimer()
  {
    PrecisionTimer localPrecisionTimer = new PrecisionTimer();
    localPrecisionTimer.start();
    return localPrecisionTimer;
  }

  private boolean started()
  {
    if (this.start > 0L);
    for (int i = 1; ; i = 0)
      return i;
  }

  private String state()
  {
    String str = "not started";
    if (running())
      str = "running";
    while (true)
    {
      return str;
      if (!stopped())
        continue;
      str = "stopped";
    }
  }

  private boolean stopped()
  {
    if (this.stop > 0L);
    for (int i = 1; ; i = 0)
      return i;
  }

  public long elapsed()
  {
    return computeElapsedTime();
  }

  public Interval elapsedInterval()
  {
    return new Interval(computeElapsedTime(), null).precision(precision());
  }

  public Precision precision()
  {
    return this.precision;
  }

  public PrecisionTimer precision(Precision paramPrecision)
  {
    this.precision = paramPrecision;
    return this;
  }

  public boolean running()
  {
    if ((started()) && (!stopped()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public PrecisionTimer start()
  {
    this.start = now();
    this.stop = 0L;
    return this;
  }

  public PrecisionTimer stop()
  {
    this.stop = now();
    return this;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append("(");
    localStringBuilder.append(elapsedInterval());
    localStringBuilder.append(",");
    localStringBuilder.append(state());
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }

  public static class Interval
  {
    private static final long BASE_UNIT = 1L;
    private static final long PER_HOUR = 3600000000000L;
    private static final long PER_MICRO = 1000L;
    private static final long PER_MILLI = 1000000L;
    private static final long PER_MINUTE = 60000000000L;
    private static final long PER_NANO = 1L;
    private static final long PER_SECOND = 1000000000L;
    private long hours;
    private long micros;
    private long millis;
    private long minutes;
    private long nanos;
    private PrecisionTimer.Precision precision = PrecisionTimer.Precision.NANOSECONDS;
    private long seconds;
    private long span;

    private Interval(long paramLong)
    {
      setSpan(paramLong);
    }

    private void setSpan(long paramLong)
    {
      PrecisionTimer.Remainder localRemainder = new PrecisionTimer.Remainder(paramLong);
      localRemainder.value();
      this.hours = localRemainder.dividedBy(3600000000000L);
      this.minutes = localRemainder.dividedBy(60000000000L);
      this.seconds = localRemainder.dividedBy(1000000000L);
      this.millis = localRemainder.dividedBy(1000000L);
      this.micros = localRemainder.dividedBy(1000L);
      this.nanos = localRemainder.dividedBy(1L);
    }

    public int compare(Interval paramInterval)
    {
      int i = 1;
      if (paramInterval == null);
      while (true)
      {
        return i;
        if (this.span == paramInterval.span)
        {
          i = 0;
          continue;
        }
        if (this.span >= paramInterval.span)
          continue;
        i = -1;
      }
    }

    public boolean equals(Interval paramInterval)
    {
      int i = 0;
      if (paramInterval == null);
      while (true)
      {
        return i;
        if (super.equals(paramInterval))
        {
          i = 1;
          continue;
        }
        if (paramInterval.span != this.span)
          continue;
        i = 1;
      }
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof Interval));
      for (boolean bool = equals((Interval)paramObject); ; bool = false)
        return bool;
    }

    public boolean gt(Interval paramInterval)
    {
      if (compare(paramInterval) > 0);
      for (int i = 1; ; i = 0)
        return i;
    }

    public boolean lt(Interval paramInterval)
    {
      if (compare(paramInterval) < 0);
      for (int i = 1; ; i = 0)
        return i;
    }

    public Interval precision(PrecisionTimer.Precision paramPrecision)
    {
      this.precision = paramPrecision;
      return this;
    }

    public PrecisionTimer.Precision precision()
    {
      return this.precision;
    }

    public long span()
    {
      return this.span;
    }

    public String toString()
    {
      String str;
      if (precision() == PrecisionTimer.Precision.NANOSECONDS)
      {
        Object[] arrayOfObject5 = new Object[6];
        arrayOfObject5[0] = Long.valueOf(this.hours);
        arrayOfObject5[1] = Long.valueOf(this.minutes);
        arrayOfObject5[2] = Long.valueOf(this.seconds);
        arrayOfObject5[3] = Long.valueOf(this.millis);
        arrayOfObject5[4] = Long.valueOf(this.micros);
        arrayOfObject5[5] = Long.valueOf(this.nanos);
        str = String.format("%02d:%02d:%02d.%03d.%03d.%03d", arrayOfObject5);
      }
      while (true)
      {
        return str;
        if (precision() == PrecisionTimer.Precision.MICROSECONDS)
        {
          Object[] arrayOfObject4 = new Object[5];
          arrayOfObject4[0] = Long.valueOf(this.hours);
          arrayOfObject4[1] = Long.valueOf(this.minutes);
          arrayOfObject4[2] = Long.valueOf(this.seconds);
          arrayOfObject4[3] = Long.valueOf(this.millis);
          arrayOfObject4[4] = Long.valueOf(this.micros);
          str = String.format("%02d:%02d:%02d.%03d.%03d", arrayOfObject4);
          continue;
        }
        if (precision() == PrecisionTimer.Precision.MILLISECONDS)
        {
          Object[] arrayOfObject3 = new Object[4];
          arrayOfObject3[0] = Long.valueOf(this.hours);
          arrayOfObject3[1] = Long.valueOf(this.minutes);
          arrayOfObject3[2] = Long.valueOf(this.seconds);
          arrayOfObject3[3] = Long.valueOf(this.millis);
          str = String.format("%02d:%02d:%02d.%03d", arrayOfObject3);
          continue;
        }
        if (precision() == PrecisionTimer.Precision.SECONDS)
        {
          Object[] arrayOfObject2 = new Object[3];
          arrayOfObject2[0] = Long.valueOf(this.hours);
          arrayOfObject2[1] = Long.valueOf(this.minutes);
          arrayOfObject2[2] = Long.valueOf(this.seconds);
          str = String.format("%02d:%02d:%02d.%03d", arrayOfObject2);
          continue;
        }
        if (precision() == PrecisionTimer.Precision.MINUTES)
        {
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = Long.valueOf(this.hours);
          arrayOfObject1[1] = Long.valueOf(this.minutes);
          str = String.format("%02d:%02d", arrayOfObject1);
          continue;
        }
        str = String.format("%02d", new Object[0]);
      }
    }
  }

  public static enum Precision
  {
    static
    {
      MICROSECONDS = new Precision("MICROSECONDS", 1);
      MILLISECONDS = new Precision("MILLISECONDS", 2);
      SECONDS = new Precision("SECONDS", 3);
      MINUTES = new Precision("MINUTES", 4);
      HOURS = new Precision("HOURS", 5);
      Precision[] arrayOfPrecision = new Precision[6];
      arrayOfPrecision[0] = NANOSECONDS;
      arrayOfPrecision[1] = MICROSECONDS;
      arrayOfPrecision[2] = MILLISECONDS;
      arrayOfPrecision[3] = SECONDS;
      arrayOfPrecision[4] = MINUTES;
      arrayOfPrecision[5] = HOURS;
      $VALUES = arrayOfPrecision;
    }
  }

  private static class Remainder
  {
    private long value;

    public Remainder(long paramLong)
    {
      this.value = paramLong;
    }

    public long dividedBy(long paramLong)
    {
      long l = this.value / paramLong;
      this.value %= paramLong;
      return l;
    }

    public long value()
    {
      return this.value;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.util.PrecisionTimer
 * JD-Core Version:    0.6.0
 */