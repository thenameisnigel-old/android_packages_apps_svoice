package com.vlingo.sdk.internal.recognizer;

public class Queue
{
  protected Element m_Last;
  protected Element m_Nodes;

  public void add(Object paramObject)
  {
    monitorenter;
    try
    {
      Element localElement = new Element(paramObject);
      if (this.m_Last == null)
        this.m_Nodes = localElement;
      while (true)
      {
        this.m_Last = localElement;
        notifyAll();
        return;
        this.m_Last.m_Next = localElement;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean clear()
  {
    monitorenter;
    try
    {
      if (this.m_Nodes != null)
      {
        i = 1;
        this.m_Last = null;
        this.m_Nodes = null;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public boolean isEmpty()
  {
    monitorenter;
    try
    {
      Element localElement = this.m_Nodes;
      if (localElement == null)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public Object pop()
  {
    monitorenter;
    Object localObject1 = null;
    try
    {
      if (this.m_Nodes != null)
      {
        localObject1 = this.m_Nodes.m_Element;
        if (this.m_Nodes != this.m_Last)
          break label44;
        this.m_Last = null;
      }
      label44: for (this.m_Nodes = null; ; this.m_Nodes = this.m_Nodes.m_Next)
        return localObject1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject2;
  }

  static final class Element
  {
    Object m_Element;
    Element m_Next;

    Element(Object paramObject)
    {
      this.m_Element = paramObject;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.internal.recognizer.Queue
 * JD-Core Version:    0.6.0
 */