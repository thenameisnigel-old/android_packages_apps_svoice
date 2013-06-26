package com.vlingo.midas.settings.debug;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Collections;

public class ServerPreference extends DialogPreference
{
  private EditText editText;
  private final int server_list_id;
  ArrayList<String> servers;
  private Spinner spinner;
  private String value;

  public ServerPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setPersistent(false);
    setDialogLayoutResource(2130903119);
    this.servers = new ArrayList();
    Resources localResources = paramContext.getResources();
    if ("SERVER_NAME".equals(getKey()))
      this.server_list_id = 2131165184;
    while (true)
    {
      Collections.addAll(this.servers, localResources.getStringArray(this.server_list_id));
      return;
      if ("EVENTLOG_HOST_NAME".equals(getKey()))
      {
        this.server_list_id = 2131165187;
        continue;
      }
      if ("HELLO_HOST_NAME".equals(getKey()))
      {
        this.server_list_id = 2131165188;
        continue;
      }
      if ("LMTT_HOST_NAME".equals(getKey()))
      {
        this.server_list_id = 2131165189;
        continue;
      }
      this.server_list_id = 2131165186;
    }
  }

  public String getValue()
  {
    return this.value;
  }

  protected void onBindDialogView(View paramView)
  {
    this.editText = ((EditText)paramView.findViewById(2131558824));
    this.spinner = ((Spinner)paramView.findViewById(2131558825));
    this.value = getSharedPreferences().getString(getKey(), "");
    this.editText.setText(this.value);
    ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(paramView.getContext(), this.server_list_id, 17367048);
    localArrayAdapter.setDropDownViewResource(17367050);
    this.spinner.setAdapter(localArrayAdapter);
    updateSpinner();
    this.spinner.setOnItemSelectedListener(new ServerPreference.1(this));
    this.editText.addTextChangedListener(new ServerPreference.2(this));
    super.onBindDialogView(paramView);
  }

  protected void onDialogClosed(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      SharedPreferences.Editor localEditor = getEditor();
      localEditor.putString(getKey(), this.value);
      localEditor.commit();
    }
    super.onDialogClosed(paramBoolean);
  }

  void setValue(String paramString)
  {
    this.value = paramString;
  }

  void updateSpinner()
  {
    this.value = this.editText.getText().toString();
    int i = this.servers.indexOf(this.value);
    if (i == -1)
      i = 0;
    this.spinner.setSelection(i);
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.settings.debug.ServerPreference
 * JD-Core Version:    0.6.0
 */