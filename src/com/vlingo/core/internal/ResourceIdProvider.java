package com.vlingo.core.internal;

import android.graphics.drawable.Drawable;

public abstract interface ResourceIdProvider
{
  public abstract Drawable getDrawable(drawable paramdrawable);

  public abstract int getResourceId(array paramarray);

  public abstract int getResourceId(drawable paramdrawable);

  public abstract int getResourceId(id paramid);

  public abstract int getResourceId(layout paramlayout);

  public abstract int getResourceId(raw paramraw);

  public abstract int getResourceId(string paramstring);

  public abstract String getString(string paramstring);

  public abstract String getString(string paramstring, Object[] paramArrayOfObject);

  public abstract String[] getStringArray(array paramarray);

  public static enum array
  {
    static
    {
      core_dayMatchers = new array("core_dayMatchers", 2);
      core_weather_phenomenon = new array("core_weather_phenomenon", 3);
      core_weather_wind_force = new array("core_weather_wind_force", 4);
      core_weather_wind_direction = new array("core_weather_wind_direction", 5);
      core_languages_names = new array("core_languages_names", 6);
      core_russian_weekdays = new array("core_russian_weekdays", 7);
      array[] arrayOfarray = new array[8];
      arrayOfarray[0] = core_languages_iso;
      arrayOfarray[1] = core_monthMatchers;
      arrayOfarray[2] = core_dayMatchers;
      arrayOfarray[3] = core_weather_phenomenon;
      arrayOfarray[4] = core_weather_wind_force;
      arrayOfarray[5] = core_weather_wind_direction;
      arrayOfarray[6] = core_languages_names;
      arrayOfarray[7] = core_russian_weekdays;
      $VALUES = arrayOfarray;
    }
  }

  public static enum drawable
  {
    static
    {
      core_facebook_icon = new drawable("core_facebook_icon", 5);
      core_qzone_icon = new drawable("core_qzone_icon", 6);
      core_weibo_icon = new drawable("core_weibo_icon", 7);
      core_all_logo = new drawable("core_all_logo", 8);
      core_stat_safereader_silent = new drawable("core_stat_safereader_silent", 9);
      drawable[] arrayOfdrawable = new drawable[10];
      arrayOfdrawable[0] = core_sr_notification_skip;
      arrayOfdrawable[1] = core_stat_safereader_off;
      arrayOfdrawable[2] = core_stat_safereader_on;
      arrayOfdrawable[3] = core_stat_safereader_sms;
      arrayOfdrawable[4] = core_twitter_icon;
      arrayOfdrawable[5] = core_facebook_icon;
      arrayOfdrawable[6] = core_qzone_icon;
      arrayOfdrawable[7] = core_weibo_icon;
      arrayOfdrawable[8] = core_all_logo;
      arrayOfdrawable[9] = core_stat_safereader_silent;
      $VALUES = arrayOfdrawable;
    }
  }

  public static enum id
  {
    static
    {
      id[] arrayOfid = new id[4];
      arrayOfid[0] = core_btn_skip;
      arrayOfid[1] = core_icon;
      arrayOfid[2] = core_text;
      arrayOfid[3] = core_title;
      $VALUES = arrayOfid;
    }
  }

  public static enum layout
  {
    static
    {
      layout[] arrayOflayout = new layout[2];
      arrayOflayout[0] = core_main;
      arrayOflayout[1] = core_notification_safereader_on;
      $VALUES = arrayOflayout;
    }
  }

  public static enum raw
  {
    static
    {
      core_start_tone_bt = new raw("core_start_tone_bt", 4);
      core_stop_tone_bt = new raw("core_stop_tone_bt", 5);
      core_processing_tone = new raw("core_processing_tone", 6);
      raw[] arrayOfraw = new raw[7];
      arrayOfraw[0] = core_start_tone;
      arrayOfraw[1] = core_start_tone_drv;
      arrayOfraw[2] = core_stop_tone;
      arrayOfraw[3] = core_stop_tone_drv;
      arrayOfraw[4] = core_start_tone_bt;
      arrayOfraw[5] = core_stop_tone_bt;
      arrayOfraw[6] = core_processing_tone;
      $VALUES = arrayOfraw;
    }
  }

  public static enum string
  {
    static
    {
      core_safereader_notif_reading_ticker = new string("core_safereader_notif_reading_ticker", 15);
      core_safereader_notif_title = new string("core_safereader_notif_title", 16);
      core_safereader_subject = new string("core_safereader_subject", 17);
      core_safereader_new_sms_from = new string("core_safereader_new_sms_from", 18);
      core_settings_default_support_url = new string("core_settings_default_support_url", 19);
      core_answer_prompt = new string("core_answer_prompt", 20);
      core_alert_message = new string("core_alert_message", 21);
      core_util_WEB_SEARCH_NAME_DEFAULT = new string("core_util_WEB_SEARCH_NAME_DEFAULT", 22);
      core_util_WEB_SEARCH_URL_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_HOME_DEFAULT", 23);
      core_util_WEB_SEARCH_URL_DEFAULT = new string("core_util_WEB_SEARCH_URL_DEFAULT", 24);
      core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT", 25);
      core_util_WEB_SEARCH_URL_BING_DEFAULT = new string("core_util_WEB_SEARCH_URL_BING_DEFAULT", 26);
      core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT", 27);
      core_util_WEB_SEARCH_URL_YAHOO_DEFAULT = new string("core_util_WEB_SEARCH_URL_YAHOO_DEFAULT", 28);
      core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT", 29);
      core_util_WEB_SEARCH_URL_BAIDU_DEFAULT = new string("core_util_WEB_SEARCH_URL_BAIDU_DEFAULT", 30);
      core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US = new string("core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US", 31);
      core_util_WEB_SEARCH_URL_GOOGLE_DEFAULT = new string("core_util_WEB_SEARCH_URL_GOOGLE_DEFAULT", 32);
      core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT", 33);
      core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT", 34);
      core_util_WEB_SEARCH_URL_NAVER_DEFAULT = new string("core_util_WEB_SEARCH_URL_NAVER_DEFAULT", 35);
      core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT = new string("core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT", 36);
      core_util_WEB_SEARCH_URL_DAUM_DEFAULT = new string("core_util_WEB_SEARCH_URL_DAUM_DEFAULT", 37);
      core_car_tts_SCHEDULE_NO_EVENTS = new string("core_car_tts_SCHEDULE_NO_EVENTS", 38);
      core_schedule_all_day = new string("core_schedule_all_day", 39);
      core_schedule_to = new string("core_schedule_to", 40);
      core_say_command = new string("core_say_command", 41);
      core_car_event_saved = new string("core_car_event_saved", 42);
      core_car_task_save_cancel_update_prompt = new string("core_car_task_save_cancel_update_prompt", 43);
      core_car_tts_EVENT_SAY_TITLE = new string("core_car_tts_EVENT_SAY_TITLE", 44);
      core_who_would_you_like_to_call = new string("core_who_would_you_like_to_call", 45);
      core_car_tts_SCHEDULE_EVENTS = new string("core_car_tts_SCHEDULE_EVENTS", 46);
      core_car_tts_TASK_SAY_TITLE = new string("core_car_tts_TASK_SAY_TITLE", 47);
      core_car_tts_NO_APPMATCH_DEMAND = new string("core_car_tts_NO_APPMATCH_DEMAND", 48);
      core_car_tts_NO_SPOKEN_APPMATCH_DEMAND = new string("core_car_tts_NO_SPOKEN_APPMATCH_DEMAND", 49);
      core_car_tts_LAUNCHAPP_PROMPT_DEMAND = new string("core_car_tts_LAUNCHAPP_PROMPT_DEMAND", 50);
      core_car_event_save_confirm = new string("core_car_event_save_confirm", 51);
      core_social_api_twitter_err_login = new string("core_social_api_twitter_err_login", 52);
      core_social_api_twitter_update_error = new string("core_social_api_twitter_update_error", 53);
      core_social_api_err_auth1 = new string("core_social_api_err_auth1", 54);
      core_social_api_err_auth2 = new string("core_social_api_err_auth2", 55);
      core_social_api_error = new string("core_social_api_error", 56);
      core_social_api_twitter_error = new string("core_social_api_twitter_error", 57);
      core_wcis_social_twitter = new string("core_wcis_social_twitter", 58);
      core_car_util_loading = new string("core_car_util_loading", 59);
      core_car_tts_NO_PLAYLISTMATCH_DEMAND = new string("core_car_tts_NO_PLAYLISTMATCH_DEMAND", 60);
      core_car_tts_PLAYPLAYLIST_PROMPT_DEMAND = new string("core_car_tts_PLAYPLAYLIST_PROMPT_DEMAND", 61);
      core_social_logout_facebook_msg = new string("core_social_logout_facebook_msg", 62);
      core_social_logout_facebook = new string("core_social_logout_facebook", 63);
      core_wcis_social_facebook = new string("core_wcis_social_facebook", 64);
      core_social_logout_twitter_msg = new string("core_social_logout_twitter_msg", 65);
      core_social_logout_twitter = new string("core_social_logout_twitter", 66);
      core_car_safereader_hidden_message_body = new string("core_car_safereader_hidden_message_body", 67);
      core_car_tts_SAFEREADER_NEW_SMS_FROM = new string("core_car_tts_SAFEREADER_NEW_SMS_FROM", 68);
      core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO = new string("core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO", 69);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN", 70);
      core_car_tts_SAFEREADER_NEW_SMS_CMD_READ = new string("core_car_tts_SAFEREADER_NEW_SMS_CMD_READ", 71);
      core_car_tts_WHICH_CONTACT_DEMAND = new string("core_car_tts_WHICH_CONTACT_DEMAND", 72);
      core_social_status_updated = new string("core_social_status_updated", 73);
      core_social_no_network = new string("core_social_no_network", 74);
      core_social_no_status = new string("core_social_no_status", 75);
      core_car_social_service_prompt = new string("core_car_social_service_prompt", 76);
      core_car_social_status_prompt = new string("core_car_social_status_prompt", 77);
      core_social_too_long = new string("core_social_too_long", 78);
      core_social_login_to_facebook_msg = new string("core_social_login_to_facebook_msg", 79);
      core_social_login_to_network_msg = new string("core_social_login_to_network_msg", 80);
      core_social_login_to_twitter_msg = new string("core_social_login_to_twitter_msg", 81);
      core_car_social_final_prompt = new string("core_car_social_final_prompt", 82);
      core_car_social_prompt_ex1 = new string("core_car_social_prompt_ex1", 83);
      core_social_err_msg1 = new string("core_social_err_msg1", 84);
      core_social_err_msg2 = new string("core_social_err_msg2", 85);
      core_car_sms_message_empty_tts = new string("core_car_sms_message_empty_tts", 86);
      core_car_tts_SAFEREADER_NEW_MESSAGE_FROM_BODY_NO_PROMPT = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_FROM_BODY_NO_PROMPT", 87);
      core_car_tts_NAVIGATE_TO = new string("core_car_tts_NAVIGATE_TO", 88);
      core_car_tts_MAP_OF = new string("core_car_tts_MAP_OF", 89);
      core_car_tts_GOTO_URL = new string("core_car_tts_GOTO_URL", 90);
      core_voice_recognition_service_not_thru_iux_error = new string("core_voice_recognition_service_not_thru_iux_error", 91);
      core_memo_multiple_found = new string("core_memo_multiple_found", 92);
      core_memo_confirm_delete = new string("core_memo_confirm_delete", 93);
      core_memo_not_saved = new string("core_memo_not_saved", 94);
      core_social_api_weibo_err_login = new string("core_social_api_weibo_err_login", 95);
      core_social_api_weibo_error = new string("core_social_api_weibo_error", 96);
      core_social_api_weibo_update_error = new string("core_social_api_weibo_update_error", 97);
      core_social_login_to_weibo_msg = new string("core_social_login_to_weibo_msg", 98);
      core_social_logout_weibo = new string("core_social_logout_weibo", 99);
      core_social_logout_weibo_msg = new string("core_social_logout_weibo_msg", 100);
      core_social_api_qzone_err_login = new string("core_social_api_qzone_err_login", 101);
      core_social_api_qzone_error = new string("core_social_api_qzone_error", 102);
      core_social_api_qzone_update_error = new string("core_social_api_qzone_update_error", 103);
      core_social_api_qzone_update_error_publish = new string("core_social_api_qzone_update_error_publish", 104);
      core_social_login_to_qzone_msg = new string("core_social_login_to_qzone_msg", 105);
      core_social_logout_qzone = new string("core_social_logout_qzone", 106);
      core_social_logout_qzone_msg = new string("core_social_logout_qzone_msg", 107);
      core_wcis_social_qzone = new string("core_wcis_social_qzone", 108);
      core_wcis_social_weibo = new string("core_wcis_social_weibo", 109);
      core_car_tts_NO_ARTISTMATCH_DEMAND = new string("core_car_tts_NO_ARTISTMATCH_DEMAND", 110);
      core_car_tts_ARTIST_PROMPT_DEMAND = new string("core_car_tts_ARTIST_PROMPT_DEMAND", 111);
      core_car_tts_NO_ALBUMMATCH_DEMAND = new string("core_car_tts_NO_ALBUMMATCH_DEMAND", 112);
      core_car_tts_ALBUM_PROMPT_DEMAND = new string("core_car_tts_ALBUM_PROMPT_DEMAND", 113);
      core_car_tts_NO_TITLEMATCH_DEMAND = new string("core_car_tts_NO_TITLEMATCH_DEMAND", 114);
      core_car_tts_TITLE_PROMPT_DEMAND = new string("core_car_tts_TITLE_PROMPT_DEMAND", 115);
      core_car_tts_NO_MUSICMATCH_DEMAND = new string("core_car_tts_NO_MUSICMATCH_DEMAND", 116);
      core_car_tts_MUSIC_PROMPT_DEMAND = new string("core_car_tts_MUSIC_PROMPT_DEMAND", 117);
      core_car_tts_NO_ANYMATCH_DEMAND = new string("core_car_tts_NO_ANYMATCH_DEMAND", 118);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SPOKEN = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SPOKEN", 119);
      core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES = new string("core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES", 120);
      core_voicedial_call_name = new string("core_voicedial_call_name", 121);
      core_voicedial_call_name_type = new string("core_voicedial_call_name_type", 122);
      core_car_tts_NO_MATCH_DEMAND_CALL = new string("core_car_tts_NO_MATCH_DEMAND_CALL", 123);
      core_car_tts_NO_MATCH_DEMAND_MESSAGE = new string("core_car_tts_NO_MATCH_DEMAND_MESSAGE", 124);
      core_car_tts_SAFEREADER_NEW_MSG = new string("core_car_tts_SAFEREADER_NEW_MSG", 125);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN", 126);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN", 127);
      core_checkEventTitleTimeDetailBrief = new string("core_checkEventTitleTimeDetailBrief", 128);
      core_checkEventYouHave = new string("core_checkEventYouHave", 129);
      core_checkEventYouHaveToday = new string("core_checkEventYouHaveToday", 130);
      core_checkEventYouHaveOnDate = new string("core_checkEventYouHaveOnDate", 131);
      core_multiple_contacts = new string("core_multiple_contacts", 132);
      core_multiple_phone_numbers = new string("core_multiple_phone_numbers", 133);
      core_multiple_phone_numbers2 = new string("core_multiple_phone_numbers2", 134);
      core_multiple_applications = new string("core_multiple_applications", 135);
      core_weather_general = new string("core_weather_general", 136);
      core_weather_with_locatin = new string("core_weather_with_locatin", 137);
      core_weather_default_location = new string("core_weather_default_location", 138);
      core_weather_plus_seven = new string("core_weather_plus_seven", 139);
      core_qa_more = new string("core_qa_more", 140);
      core_qa_tts_NO_ANS_WEB_SEARCH = new string("core_qa_tts_NO_ANS_WEB_SEARCH", 141);
      core_tts_NO_ANS_WEB_SEARCH = new string("core_tts_NO_ANS_WEB_SEARCH", 142);
      core_tts_NO_ANS_WEB_SEARCH_1 = new string("core_tts_NO_ANS_WEB_SEARCH_1", 143);
      core_tts_NO_ANS_WEB_SEARCH_2 = new string("core_tts_NO_ANS_WEB_SEARCH_2", 144);
      core_tts_NO_ANS_WEB_SEARCH_3 = new string("core_tts_NO_ANS_WEB_SEARCH_3", 145);
      core_tts_NO_ANS_WEB_SEARCH_4 = new string("core_tts_NO_ANS_WEB_SEARCH_4", 146);
      core_tts_NO_ANS_GOOGLE_NOW_SEARCH = new string("core_tts_NO_ANS_GOOGLE_NOW_SEARCH", 147);
      core_car_tts_VD_CALLING_CONFIRM_DEMAND = new string("core_car_tts_VD_CALLING_CONFIRM_DEMAND", 148);
      core_default_alarm_title = new string("core_default_alarm_title", 149);
      core_redial = new string("core_redial", 150);
      core_no_call_log = new string("core_no_call_log", 151);
      core_alarm_set = new string("core_alarm_set", 152);
      core_single_contact = new string("core_single_contact", 153);
      core_weather_no_results = new string("core_weather_no_results", 154);
      core_social_update_failed = new string("core_social_update_failed", 155);
      core_tts_local_required_engine_name = new string("core_tts_local_required_engine_name", 156);
      core_weather_current = new string("core_weather_current", 157);
      core_weather_date_tts = new string("core_weather_date_tts", 158);
      core_weather_date_display = new string("core_weather_date_display", 159);
      core_tomorrow = new string("core_tomorrow", 160);
      core_today = new string("core_today", 161);
      core_localsearch = new string("core_localsearch", 162);
      core_localsearch_no_location = new string("core_localsearch_no_location", 163);
      core_localsearch_provider_dianping = new string("core_localsearch_provider_dianping", 164);
      core_search_web_label_button = new string("core_search_web_label_button", 165);
      core_contact_email_not_found = new string("core_contact_email_not_found", 166);
      core_contact_address_not_found = new string("core_contact_address_not_found", 167);
      core_contact_birthday_not_found = new string("core_contact_birthday_not_found", 168);
      core_voicevideodial_call_name = new string("core_voicevideodial_call_name", 169);
      core_voicevideodial_call_name_type = new string("core_voicevideodial_call_name_type", 170);
      core_no_memo_saved = new string("core_no_memo_saved", 171);
      core_phone_in_use = new string("core_phone_in_use", 172);
      core_mic_in_use = new string("core_mic_in_use", 173);
      core_what_is_the_weather_today_location_var = new string("core_what_is_the_weather_today_location_var", 174);
      core_what_is_the_weather_today = new string("core_what_is_the_weather_today", 175);
      core_what_is_the_weather_date_var_location_var = new string("core_what_is_the_weather_date_var_location_var", 176);
      core_what_is_the_weather_date_var = new string("core_what_is_the_weather_date_var", 177);
      core_no_memo_saved_about = new string("core_no_memo_saved_about", 178);
      core_local_search_blank_request_message = new string("core_local_search_blank_request_message", 179);
      core_address_book_is_empty = new string("core_address_book_is_empty", 180);
      core_tts_local_fallback_engine_name = new string("core_tts_local_fallback_engine_name", 181);
      core_checkEventOneYouHave = new string("core_checkEventOneYouHave", 182);
      core_checkEventRussianTwoThreeFourYouHave = new string("core_checkEventRussianTwoThreeFourYouHave", 183);
      core_checkEventJapaneseMoreTenYouHave = new string("core_checkEventJapaneseMoreTenYouHave", 184);
      core_dot = new string("core_dot", 185);
      core_comma = new string("core_comma", 186);
      core_space = new string("core_space", 187);
      core_colon = new string("core_colon", 188);
      core_semicolon = new string("core_semicolon", 189);
      core_ellipsis = new string("core_ellipsis", 190);
      core_network_error = new string("core_network_error", 191);
      core_opening_app = new string("core_opening_app", 192);
      core_message_sending = new string("core_message_sending", 193);
      core_message_sending_readout = new string("core_message_sending_readout", 194);
      core_message_reading_preface = new string("core_message_reading_preface", 195);
      core_message_reading_none = new string("core_message_reading_none", 196);
      core_message_reading_single_from_sender = new string("core_message_reading_single_from_sender", 197);
      core_message_reading_multi_from_sender = new string("core_message_reading_multi_from_sender", 198);
      core_current_location = new string("core_current_location", 199);
      core_safereader_notif_title_silent = new string("core_safereader_notif_title_silent", 200);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN_NOCALL = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN_NOCALL", 201);
      core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL = new string("core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL", 202);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL", 203);
      core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN_NOCALL = new string("core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN_NOCALL", 204);
      core_car_tts_SAFEREADER_NO_REPLY = new string("core_car_tts_SAFEREADER_NO_REPLY", 205);
      core_wifi_setting_change_on_error = new string("core_wifi_setting_change_on_error", 206);
      core_playing_music = new string("core_playing_music", 207);
      core_not_detected_current_location = new string("core_not_detected_current_location", 208);
      core_unknown = new string("core_unknown", 209);
      core_format_time_AM = new string("core_format_time_AM", 210);
      core_format_time_PM = new string("core_format_time_PM", 211);
      core_error = new string("core_error", 212);
      core_permission_internet_error = new string("core_permission_internet_error", 213);
      core_message_reading_none_spoken = new string("core_message_reading_none_spoken", 214);
      core_message_none_from_sender = new string("core_message_none_from_sender", 215);
      core_message_none_from_sender_spoken = new string("core_message_none_from_sender_spoken", 216);
      core_no_more_messages_verbose = new string("core_no_more_messages_verbose", 217);
      core_no_more_messages_regular = new string("core_no_more_messages_regular", 218);
      core_no_more_messages_spoken = new string("core_no_more_messages_spoken", 219);
      core_readout_no_match_found = new string("core_readout_no_match_found", 220);
      core_readout_single_message_initial_hidden = new string("core_readout_single_message_initial_hidden", 221);
      core_readout_single_message_initial_hidden_spoken = new string("core_readout_single_message_initial_hidden_spoken", 222);
      core_readout_single_message_initial_spoken = new string("core_readout_single_message_initial_spoken", 223);
      core_readout_single_message_next = new string("core_readout_single_message_next", 224);
      core_readout_single_message_next_spoken = new string("core_readout_single_message_next_spoken", 225);
      core_readout_single_message = new string("core_readout_single_message", 226);
      core_readout_single_message_spoken = new string("core_readout_single_message_spoken", 227);
      core_readout_multi_message = new string("core_readout_multi_message", 228);
      core_readout_multi_message_overflow = new string("core_readout_multi_message_overflow", 229);
      core_readout_multi_sender = new string("core_readout_multi_sender", 230);
      core_readout_multi_sender_info_verbose_spoken = new string("core_readout_multi_sender_info_verbose_spoken", 231);
      core_readout_multi_sender_info_verbose_overflow_spoken = new string("core_readout_multi_sender_info_verbose_overflow_spoken", 232);
      core_readout_multi_sender_command_spoken = new string("core_readout_multi_sender_command_spoken", 233);
      core_readout_multi_sender_dm_overflow = new string("core_readout_multi_sender_dm_overflow", 234);
      core_readout_multi_sender_overflow_spoken = new string("core_readout_multi_sender_overflow_spoken", 235);
      core_readout_multi_sender_overflow = new string("core_readout_multi_sender_overflow", 236);
      core_and = new string("core_and", 237);
      core_cradle_date = new string("core_cradle_date", 238);
      core_car_redial_confirm_number = new string("core_car_redial_confirm_number", 239);
      core_car_redial_confirm_contact = new string("core_car_redial_confirm_contact", 240);
      core_weibo_dialog_title = new string("core_weibo_dialog_title", 241);
      core_qzone_dialog_title = new string("core_qzone_dialog_title", 242);
      core_contact_phone_number = new string("core_contact_phone_number", 243);
      core_contact_email = new string("core_contact_email", 244);
      core_contact_address = new string("core_contact_address", 245);
      string[] arrayOfstring = new string['ö'];
      arrayOfstring[0] = core_car_sms_error_help_tts;
      arrayOfstring[1] = core_car_sms_error_sending;
      arrayOfstring[2] = core_car_sms_send_confirm;
      arrayOfstring[3] = core_car_sms_speak_msg_tts;
      arrayOfstring[4] = core_car_sms_text_who;
      arrayOfstring[5] = core_car_tts_SMS_SENT_CONFIRM_DEMAND;
      arrayOfstring[6] = core_car_tts_TASK_CANCELLED;
      arrayOfstring[7] = core_car_tts_VD_MULTIPLE_CONTACTS_DEMAND;
      arrayOfstring[8] = core_car_tts_VD_MULTIPLE_TYPES_DEMAND;
      arrayOfstring[9] = core_contacts_no_match_openquote;
      arrayOfstring[10] = core_nav_home_prompt;
      arrayOfstring[11] = core_navigate_home;
      arrayOfstring[12] = core_safe_reader_default_error;
      arrayOfstring[13] = core_safereader_enabled;
      arrayOfstring[14] = core_safereader_notif_reading_title;
      arrayOfstring[15] = core_safereader_notif_reading_ticker;
      arrayOfstring[16] = core_safereader_notif_title;
      arrayOfstring[17] = core_safereader_subject;
      arrayOfstring[18] = core_safereader_new_sms_from;
      arrayOfstring[19] = core_settings_default_support_url;
      arrayOfstring[20] = core_answer_prompt;
      arrayOfstring[21] = core_alert_message;
      arrayOfstring[22] = core_util_WEB_SEARCH_NAME_DEFAULT;
      arrayOfstring[23] = core_util_WEB_SEARCH_URL_HOME_DEFAULT;
      arrayOfstring[24] = core_util_WEB_SEARCH_URL_DEFAULT;
      arrayOfstring[25] = core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT;
      arrayOfstring[26] = core_util_WEB_SEARCH_URL_BING_DEFAULT;
      arrayOfstring[27] = core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT;
      arrayOfstring[28] = core_util_WEB_SEARCH_URL_YAHOO_DEFAULT;
      arrayOfstring[29] = core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT;
      arrayOfstring[30] = core_util_WEB_SEARCH_URL_BAIDU_DEFAULT;
      arrayOfstring[31] = core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US;
      arrayOfstring[32] = core_util_WEB_SEARCH_URL_GOOGLE_DEFAULT;
      arrayOfstring[33] = core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT;
      arrayOfstring[34] = core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT;
      arrayOfstring[35] = core_util_WEB_SEARCH_URL_NAVER_DEFAULT;
      arrayOfstring[36] = core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT;
      arrayOfstring[37] = core_util_WEB_SEARCH_URL_DAUM_DEFAULT;
      arrayOfstring[38] = core_car_tts_SCHEDULE_NO_EVENTS;
      arrayOfstring[39] = core_schedule_all_day;
      arrayOfstring[40] = core_schedule_to;
      arrayOfstring[41] = core_say_command;
      arrayOfstring[42] = core_car_event_saved;
      arrayOfstring[43] = core_car_task_save_cancel_update_prompt;
      arrayOfstring[44] = core_car_tts_EVENT_SAY_TITLE;
      arrayOfstring[45] = core_who_would_you_like_to_call;
      arrayOfstring[46] = core_car_tts_SCHEDULE_EVENTS;
      arrayOfstring[47] = core_car_tts_TASK_SAY_TITLE;
      arrayOfstring[48] = core_car_tts_NO_APPMATCH_DEMAND;
      arrayOfstring[49] = core_car_tts_NO_SPOKEN_APPMATCH_DEMAND;
      arrayOfstring[50] = core_car_tts_LAUNCHAPP_PROMPT_DEMAND;
      arrayOfstring[51] = core_car_event_save_confirm;
      arrayOfstring[52] = core_social_api_twitter_err_login;
      arrayOfstring[53] = core_social_api_twitter_update_error;
      arrayOfstring[54] = core_social_api_err_auth1;
      arrayOfstring[55] = core_social_api_err_auth2;
      arrayOfstring[56] = core_social_api_error;
      arrayOfstring[57] = core_social_api_twitter_error;
      arrayOfstring[58] = core_wcis_social_twitter;
      arrayOfstring[59] = core_car_util_loading;
      arrayOfstring[60] = core_car_tts_NO_PLAYLISTMATCH_DEMAND;
      arrayOfstring[61] = core_car_tts_PLAYPLAYLIST_PROMPT_DEMAND;
      arrayOfstring[62] = core_social_logout_facebook_msg;
      arrayOfstring[63] = core_social_logout_facebook;
      arrayOfstring[64] = core_wcis_social_facebook;
      arrayOfstring[65] = core_social_logout_twitter_msg;
      arrayOfstring[66] = core_social_logout_twitter;
      arrayOfstring[67] = core_car_safereader_hidden_message_body;
      arrayOfstring[68] = core_car_tts_SAFEREADER_NEW_SMS_FROM;
      arrayOfstring[69] = core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO;
      arrayOfstring[70] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN;
      arrayOfstring[71] = core_car_tts_SAFEREADER_NEW_SMS_CMD_READ;
      arrayOfstring[72] = core_car_tts_WHICH_CONTACT_DEMAND;
      arrayOfstring[73] = core_social_status_updated;
      arrayOfstring[74] = core_social_no_network;
      arrayOfstring[75] = core_social_no_status;
      arrayOfstring[76] = core_car_social_service_prompt;
      arrayOfstring[77] = core_car_social_status_prompt;
      arrayOfstring[78] = core_social_too_long;
      arrayOfstring[79] = core_social_login_to_facebook_msg;
      arrayOfstring[80] = core_social_login_to_network_msg;
      arrayOfstring[81] = core_social_login_to_twitter_msg;
      arrayOfstring[82] = core_car_social_final_prompt;
      arrayOfstring[83] = core_car_social_prompt_ex1;
      arrayOfstring[84] = core_social_err_msg1;
      arrayOfstring[85] = core_social_err_msg2;
      arrayOfstring[86] = core_car_sms_message_empty_tts;
      arrayOfstring[87] = core_car_tts_SAFEREADER_NEW_MESSAGE_FROM_BODY_NO_PROMPT;
      arrayOfstring[88] = core_car_tts_NAVIGATE_TO;
      arrayOfstring[89] = core_car_tts_MAP_OF;
      arrayOfstring[90] = core_car_tts_GOTO_URL;
      arrayOfstring[91] = core_voice_recognition_service_not_thru_iux_error;
      arrayOfstring[92] = core_memo_multiple_found;
      arrayOfstring[93] = core_memo_confirm_delete;
      arrayOfstring[94] = core_memo_not_saved;
      arrayOfstring[95] = core_social_api_weibo_err_login;
      arrayOfstring[96] = core_social_api_weibo_error;
      arrayOfstring[97] = core_social_api_weibo_update_error;
      arrayOfstring[98] = core_social_login_to_weibo_msg;
      arrayOfstring[99] = core_social_logout_weibo;
      arrayOfstring[100] = core_social_logout_weibo_msg;
      arrayOfstring[101] = core_social_api_qzone_err_login;
      arrayOfstring[102] = core_social_api_qzone_error;
      arrayOfstring[103] = core_social_api_qzone_update_error;
      arrayOfstring[104] = core_social_api_qzone_update_error_publish;
      arrayOfstring[105] = core_social_login_to_qzone_msg;
      arrayOfstring[106] = core_social_logout_qzone;
      arrayOfstring[107] = core_social_logout_qzone_msg;
      arrayOfstring[108] = core_wcis_social_qzone;
      arrayOfstring[109] = core_wcis_social_weibo;
      arrayOfstring[110] = core_car_tts_NO_ARTISTMATCH_DEMAND;
      arrayOfstring[111] = core_car_tts_ARTIST_PROMPT_DEMAND;
      arrayOfstring[112] = core_car_tts_NO_ALBUMMATCH_DEMAND;
      arrayOfstring[113] = core_car_tts_ALBUM_PROMPT_DEMAND;
      arrayOfstring[114] = core_car_tts_NO_TITLEMATCH_DEMAND;
      arrayOfstring[115] = core_car_tts_TITLE_PROMPT_DEMAND;
      arrayOfstring[116] = core_car_tts_NO_MUSICMATCH_DEMAND;
      arrayOfstring[117] = core_car_tts_MUSIC_PROMPT_DEMAND;
      arrayOfstring[118] = core_car_tts_NO_ANYMATCH_DEMAND;
      arrayOfstring[119] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SPOKEN;
      arrayOfstring[120] = core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES;
      arrayOfstring[121] = core_voicedial_call_name;
      arrayOfstring[122] = core_voicedial_call_name_type;
      arrayOfstring[123] = core_car_tts_NO_MATCH_DEMAND_CALL;
      arrayOfstring[124] = core_car_tts_NO_MATCH_DEMAND_MESSAGE;
      arrayOfstring[125] = core_car_tts_SAFEREADER_NEW_MSG;
      arrayOfstring[126] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN;
      arrayOfstring[127] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN;
      arrayOfstring[''] = core_checkEventTitleTimeDetailBrief;
      arrayOfstring[''] = core_checkEventYouHave;
      arrayOfstring[''] = core_checkEventYouHaveToday;
      arrayOfstring[''] = core_checkEventYouHaveOnDate;
      arrayOfstring[''] = core_multiple_contacts;
      arrayOfstring[''] = core_multiple_phone_numbers;
      arrayOfstring[''] = core_multiple_phone_numbers2;
      arrayOfstring[''] = core_multiple_applications;
      arrayOfstring[''] = core_weather_general;
      arrayOfstring[''] = core_weather_with_locatin;
      arrayOfstring[''] = core_weather_default_location;
      arrayOfstring[''] = core_weather_plus_seven;
      arrayOfstring[''] = core_qa_more;
      arrayOfstring[''] = core_qa_tts_NO_ANS_WEB_SEARCH;
      arrayOfstring[''] = core_tts_NO_ANS_WEB_SEARCH;
      arrayOfstring[''] = core_tts_NO_ANS_WEB_SEARCH_1;
      arrayOfstring[''] = core_tts_NO_ANS_WEB_SEARCH_2;
      arrayOfstring[''] = core_tts_NO_ANS_WEB_SEARCH_3;
      arrayOfstring[''] = core_tts_NO_ANS_WEB_SEARCH_4;
      arrayOfstring[''] = core_tts_NO_ANS_GOOGLE_NOW_SEARCH;
      arrayOfstring[''] = core_car_tts_VD_CALLING_CONFIRM_DEMAND;
      arrayOfstring[''] = core_default_alarm_title;
      arrayOfstring[''] = core_redial;
      arrayOfstring[''] = core_no_call_log;
      arrayOfstring[''] = core_alarm_set;
      arrayOfstring[''] = core_single_contact;
      arrayOfstring[''] = core_weather_no_results;
      arrayOfstring[''] = core_social_update_failed;
      arrayOfstring[''] = core_tts_local_required_engine_name;
      arrayOfstring[''] = core_weather_current;
      arrayOfstring[''] = core_weather_date_tts;
      arrayOfstring[''] = core_weather_date_display;
      arrayOfstring[' '] = core_tomorrow;
      arrayOfstring['¡'] = core_today;
      arrayOfstring['¢'] = core_localsearch;
      arrayOfstring['£'] = core_localsearch_no_location;
      arrayOfstring['¤'] = core_localsearch_provider_dianping;
      arrayOfstring['¥'] = core_search_web_label_button;
      arrayOfstring['¦'] = core_contact_email_not_found;
      arrayOfstring['§'] = core_contact_address_not_found;
      arrayOfstring['¨'] = core_contact_birthday_not_found;
      arrayOfstring['©'] = core_voicevideodial_call_name;
      arrayOfstring['ª'] = core_voicevideodial_call_name_type;
      arrayOfstring['«'] = core_no_memo_saved;
      arrayOfstring['¬'] = core_phone_in_use;
      arrayOfstring['­'] = core_mic_in_use;
      arrayOfstring['®'] = core_what_is_the_weather_today_location_var;
      arrayOfstring['¯'] = core_what_is_the_weather_today;
      arrayOfstring['°'] = core_what_is_the_weather_date_var_location_var;
      arrayOfstring['±'] = core_what_is_the_weather_date_var;
      arrayOfstring['²'] = core_no_memo_saved_about;
      arrayOfstring['³'] = core_local_search_blank_request_message;
      arrayOfstring['´'] = core_address_book_is_empty;
      arrayOfstring['µ'] = core_tts_local_fallback_engine_name;
      arrayOfstring['¶'] = core_checkEventOneYouHave;
      arrayOfstring['·'] = core_checkEventRussianTwoThreeFourYouHave;
      arrayOfstring['¸'] = core_checkEventJapaneseMoreTenYouHave;
      arrayOfstring['¹'] = core_dot;
      arrayOfstring['º'] = core_comma;
      arrayOfstring['»'] = core_space;
      arrayOfstring['¼'] = core_colon;
      arrayOfstring['½'] = core_semicolon;
      arrayOfstring['¾'] = core_ellipsis;
      arrayOfstring['¿'] = core_network_error;
      arrayOfstring['À'] = core_opening_app;
      arrayOfstring['Á'] = core_message_sending;
      arrayOfstring['Â'] = core_message_sending_readout;
      arrayOfstring['Ã'] = core_message_reading_preface;
      arrayOfstring['Ä'] = core_message_reading_none;
      arrayOfstring['Å'] = core_message_reading_single_from_sender;
      arrayOfstring['Æ'] = core_message_reading_multi_from_sender;
      arrayOfstring['Ç'] = core_current_location;
      arrayOfstring['È'] = core_safereader_notif_title_silent;
      arrayOfstring['É'] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN_NOCALL;
      arrayOfstring['Ê'] = core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL;
      arrayOfstring['Ë'] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL;
      arrayOfstring['Ì'] = core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN_NOCALL;
      arrayOfstring['Í'] = core_car_tts_SAFEREADER_NO_REPLY;
      arrayOfstring['Î'] = core_wifi_setting_change_on_error;
      arrayOfstring['Ï'] = core_playing_music;
      arrayOfstring['Ð'] = core_not_detected_current_location;
      arrayOfstring['Ñ'] = core_unknown;
      arrayOfstring['Ò'] = core_format_time_AM;
      arrayOfstring['Ó'] = core_format_time_PM;
      arrayOfstring['Ô'] = core_error;
      arrayOfstring['Õ'] = core_permission_internet_error;
      arrayOfstring['Ö'] = core_message_reading_none_spoken;
      arrayOfstring['×'] = core_message_none_from_sender;
      arrayOfstring['Ø'] = core_message_none_from_sender_spoken;
      arrayOfstring['Ù'] = core_no_more_messages_verbose;
      arrayOfstring['Ú'] = core_no_more_messages_regular;
      arrayOfstring['Û'] = core_no_more_messages_spoken;
      arrayOfstring['Ü'] = core_readout_no_match_found;
      arrayOfstring['Ý'] = core_readout_single_message_initial_hidden;
      arrayOfstring['Þ'] = core_readout_single_message_initial_hidden_spoken;
      arrayOfstring['ß'] = core_readout_single_message_initial_spoken;
      arrayOfstring['à'] = core_readout_single_message_next;
      arrayOfstring['á'] = core_readout_single_message_next_spoken;
      arrayOfstring['â'] = core_readout_single_message;
      arrayOfstring['ã'] = core_readout_single_message_spoken;
      arrayOfstring['ä'] = core_readout_multi_message;
      arrayOfstring['å'] = core_readout_multi_message_overflow;
      arrayOfstring['æ'] = core_readout_multi_sender;
      arrayOfstring['ç'] = core_readout_multi_sender_info_verbose_spoken;
      arrayOfstring['è'] = core_readout_multi_sender_info_verbose_overflow_spoken;
      arrayOfstring['é'] = core_readout_multi_sender_command_spoken;
      arrayOfstring['ê'] = core_readout_multi_sender_dm_overflow;
      arrayOfstring['ë'] = core_readout_multi_sender_overflow_spoken;
      arrayOfstring['ì'] = core_readout_multi_sender_overflow;
      arrayOfstring['í'] = core_and;
      arrayOfstring['î'] = core_cradle_date;
      arrayOfstring['ï'] = core_car_redial_confirm_number;
      arrayOfstring['ð'] = core_car_redial_confirm_contact;
      arrayOfstring['ñ'] = core_weibo_dialog_title;
      arrayOfstring['ò'] = core_qzone_dialog_title;
      arrayOfstring['ó'] = core_contact_phone_number;
      arrayOfstring['ô'] = core_contact_email;
      arrayOfstring['õ'] = core_contact_address;
      $VALUES = arrayOfstring;
    }
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.ResourceIdProvider
 * JD-Core Version:    0.6.0
 */