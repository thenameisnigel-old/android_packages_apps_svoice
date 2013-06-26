package com.vlingo.core.internal.questions.parser;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.vlingo.core.internal.questions.DownloadableImage;
import com.vlingo.core.internal.settings.Settings;
import com.vlingo.core.internal.util.HttpKeepAlive;
import com.vlingo.sdk.internal.util.Screen;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class AnswerParser extends DefaultHandler
{
  public static int DEBUG;
  public static int DEBUG_ALL;
  public static int DEBUG_ATTRIBUTES;
  public static int DEBUG_DETAILS;
  public static int DEBUG_NONE = 0;
  public static int DEBUG_OPS = 1;
  private Stack<Runnable> elements = new Stack();
  private Map<String, FutureTask<Drawable>> imageCache = Collections.synchronizedMap(new HashMap());
  private ConcurrentLinkedQueue<Runnable> imageQueue = new ConcurrentLinkedQueue();
  private ImageElement mCurrentImage;
  private ProviderResponse mCurrentResponse;
  private SectionElement mCurrentSection;
  private SubsectionElement mCurrentSubsection;
  private List<ProviderResponse> responses = new ArrayList();

  static
  {
    DEBUG_ATTRIBUTES = 2;
    DEBUG_DETAILS = 3;
    DEBUG_ALL = 999;
    DEBUG = DEBUG_ALL;
  }

  private Drawable loadImage(String paramString)
  {
    Drawable localDrawable = null;
    if (this.imageCache.containsKey(paramString));
    try
    {
      localDrawable = (Drawable)((FutureTask)this.imageCache.get(paramString)).get();
      while (true)
      {
        label35: return localDrawable;
        try
        {
          FutureTask localFutureTask = new FutureTask(new Callable(paramString)
          {
            public Drawable call()
            {
              Drawable localDrawable = null;
              try
              {
                HttpKeepAlive.on();
                InputStream localInputStream = new URL(this.val$url).openConnection().getInputStream();
                localDrawable = Drawable.createFromStream(localInputStream, "src");
                ((BitmapDrawable)localDrawable).getBitmap().setDensity(160);
                ((BitmapDrawable)localDrawable).setTargetDensity((int)(160.0F * Screen.getMagnification()));
                localInputStream.close();
                label65: return localDrawable;
              }
              catch (Exception localException)
              {
                break label65;
              }
            }
          });
          this.imageCache.put(paramString, localFutureTask);
          localFutureTask.run();
          localDrawable = (Drawable)localFutureTask.get();
        }
        catch (Exception localException1)
        {
        }
      }
    }
    catch (Exception localException2)
    {
      break label35;
    }
  }

  private ResponseElement newElement(String paramString)
  {
    Object localObject;
    if ("ProviderSrcResponse".equals(paramString))
    {
      localObject = new ProviderResponse(paramString);
      this.mCurrentResponse = ((ProviderResponse)localObject);
      this.elements.push(new Runnable()
      {
        public void run()
        {
          AnswerParser.this.responses.add(AnswerParser.this.mCurrentResponse);
        }
      });
    }
    while (true)
    {
      return localObject;
      if ("Section".equals(paramString))
      {
        localObject = new SectionElement(paramString);
        this.mCurrentSection = ((SectionElement)localObject);
        this.elements.push(new Runnable()
        {
          public void run()
          {
            AnswerParser.this.mCurrentResponse.add(AnswerParser.this.mCurrentSection);
          }
        });
        continue;
      }
      if ("Subsection".equals(paramString))
      {
        localObject = new SubsectionElement(paramString);
        this.mCurrentSubsection = ((SubsectionElement)localObject);
        this.elements.push(new Runnable()
        {
          public void run()
          {
            AnswerParser.this.mCurrentSection.add(AnswerParser.this.mCurrentSubsection);
          }
        });
        continue;
      }
      if ("Image".equals(paramString))
      {
        localObject = new ImageElement(paramString);
        this.mCurrentImage = ((ImageElement)localObject);
        this.elements.push(new Runnable()
        {
          public void run()
          {
            AnswerParser.this.mCurrentSubsection.attributes.put("Width", AnswerParser.this.mCurrentImage.getAttribute("Width"));
            AnswerParser.this.mCurrentSubsection.attributes.put("Height", AnswerParser.this.mCurrentImage.getAttribute("Height"));
            AnswerParser.this.mCurrentSubsection.attributes.put("Src", AnswerParser.this.mCurrentImage.getAttribute("Src"));
            DownloadableImage localDownloadableImage = new DownloadableImage(AnswerParser.this.mCurrentImage.getURL());
            AnswerParser.this.mCurrentSubsection.setImage(localDownloadableImage);
            new AnswerParser.4.1(this, localDownloadableImage).start();
          }
        });
        continue;
      }
      localObject = new ResponseElement(paramString);
      this.elements.push(new Runnable()
      {
        public void run()
        {
        }
      });
    }
  }

  public static ProviderResponse[] parse(String paramString)
  {
    ProviderResponse[] arrayOfProviderResponse;
    if (paramString == null)
      arrayOfProviderResponse = null;
    while (true)
    {
      return arrayOfProviderResponse;
      AnswerParser localAnswerParser = new AnswerParser();
      InputSource localInputSource = new InputSource(new StringReader(paramString));
      localInputSource.setEncoding("UTF-8");
      try
      {
        SAXParserFactory.newInstance().newSAXParser().parse(localInputSource, localAnswerParser);
        label51: if (Settings.getBoolean("wa.image.preloads", true))
          localAnswerParser.waitForDownloads();
        arrayOfProviderResponse = localAnswerParser.getResponses();
      }
      catch (Exception localException)
      {
        break label51;
      }
    }
  }

  // ERROR //
  private void waitForDownloads()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   11: invokevirtual 242	java/util/concurrent/ConcurrentLinkedQueue:isEmpty	()Z
    //   14: ifne +29 -> 43
    //   17: aload_0
    //   18: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   21: invokevirtual 247	java/lang/Object:wait	()V
    //   24: goto -17 -> 7
    //   27: astore 4
    //   29: aload_3
    //   30: monitorexit
    //   31: aload 4
    //   33: athrow
    //   34: astore_2
    //   35: aload_0
    //   36: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   39: invokevirtual 250	java/util/concurrent/ConcurrentLinkedQueue:clear	()V
    //   42: return
    //   43: aload_3
    //   44: monitorexit
    //   45: aload_0
    //   46: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   49: invokevirtual 250	java/util/concurrent/ConcurrentLinkedQueue:clear	()V
    //   52: goto -10 -> 42
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 82	com/vlingo/core/internal/questions/parser/AnswerParser:imageQueue	Ljava/util/concurrent/ConcurrentLinkedQueue;
    //   60: invokevirtual 250	java/util/concurrent/ConcurrentLinkedQueue:clear	()V
    //   63: aload_1
    //   64: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   7	31	27	finally
    //   43	45	27	finally
    //   0	7	34	java/lang/Exception
    //   31	34	34	java/lang/Exception
    //   0	7	55	finally
    //   31	34	55	finally
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    ((Runnable)this.elements.pop()).run();
  }

  public void error(SAXParseException paramSAXParseException)
  {
  }

  public void fatalError(SAXParseException paramSAXParseException)
  {
  }

  public ProviderResponse[] getResponses()
  {
    return (ProviderResponse[])this.responses.toArray(new ProviderResponse[0]);
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    ResponseElement localResponseElement = newElement(paramString2);
    for (int i = 0; i < paramAttributes.getLength(); i++)
      localResponseElement.getAttributes().put(paramAttributes.getLocalName(i), paramAttributes.getValue(i));
  }

  public void warning(SAXParseException paramSAXParseException)
  {
  }

  public static abstract interface ATTRIBUTES
  {
    public static final String HEIGHT = "Height";
    public static final String SOURCE = "Src";
    public static final String WIDTH = "Width";
  }

  public static abstract interface TAGS
  {
    public static final String IMAGE = "Image";
    public static final String IMAGES = "Images";
    public static final String RESPONSE = "ProviderSrcResponse";
    public static final String SECTION = "Section";
    public static final String SECTIONS = "Sections";
    public static final String SUB_SECTION = "Subsection";
    public static final String SUB_SECTIONS = "Subsections";
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.questions.parser.AnswerParser
 * JD-Core Version:    0.6.0
 */