package com.vlingo.midas.gui;

import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import java.lang.ref.WeakReference;

class DialogFragment$AnimateDialogExpandThread$1
  implements Runnable
{
  public void run()
  {
    ViewGroup.LayoutParams localLayoutParams = DialogFragment.access$500((DialogFragment)DialogFragment.AnimateDialogExpandThread.access$1000(this.this$0).get()).getLayoutParams();
    localLayoutParams.height = -1;
    DialogFragment.access$500((DialogFragment)DialogFragment.AnimateDialogExpandThread.access$1000(this.this$0).get()).setLayoutParams(localLayoutParams);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.gui.DialogFragment.AnimateDialogExpandThread.1
 * JD-Core Version:    0.6.0
 */