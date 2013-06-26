package com.vlingo.midas;

import com.vlingo.core.internal.BaseResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider;
import com.vlingo.core.internal.ResourceIdProvider.array;
import com.vlingo.core.internal.ResourceIdProvider.drawable;
import com.vlingo.core.internal.ResourceIdProvider.id;
import com.vlingo.core.internal.ResourceIdProvider.raw;
import com.vlingo.core.internal.ResourceIdProvider.string;

class CoreResourceProviderImpl extends BaseResourceIdProvider
  implements ResourceIdProvider
{
  CoreResourceProviderImpl()
  {
    initUrls();
  }

  private void initUrls()
  {
    if (ClientConfiguration.isChineseBuild())
    {
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_NAME_DEFAULT, Integer.valueOf(2131362040));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DEFAULT, Integer.valueOf(2131362049));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_HOME_DEFAULT, Integer.valueOf(2131362054));
    }
    while (true)
    {
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BING_HOME_DEFAULT, Integer.valueOf(2131362047));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BING_DEFAULT, Integer.valueOf(2131362046));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_YAHOO_HOME_DEFAULT, Integer.valueOf(2131362056));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_YAHOO_DEFAULT, Integer.valueOf(2131362055));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BAIDU_HOME_DEFAULT, Integer.valueOf(2131362045));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_BAIDU_DEFAULT, Integer.valueOf(2131362044));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT_US, Integer.valueOf(2131362050));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_DEFAULT, Integer.valueOf(2131362051));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_GOOGLE_HOME_DEFAULT, Integer.valueOf(2131362052));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_NAVER_HOME_DEFAULT, Integer.valueOf(2131362058));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_NAVER_DEFAULT, Integer.valueOf(2131362057));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DAUM_HOME_DEFAULT, Integer.valueOf(2131362060));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DAUM_DEFAULT, Integer.valueOf(2131362059));
      return;
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_NAME_DEFAULT, Integer.valueOf(2131362039));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_DEFAULT, Integer.valueOf(2131362048));
      putString(ResourceIdProvider.string.core_util_WEB_SEARCH_URL_HOME_DEFAULT, Integer.valueOf(2131362053));
    }
  }

  protected void initArrayMap()
  {
    if ((ClientConfiguration.isChineseBuild()) || (ClientConfiguration.isChineseTestBuild()))
    {
      putArray(ResourceIdProvider.array.core_languages_iso, Integer.valueOf(2131165194));
      putArray(ResourceIdProvider.array.core_monthMatchers, Integer.valueOf(2131165225));
      putArray(ResourceIdProvider.array.core_dayMatchers, Integer.valueOf(2131165226));
      putArray(ResourceIdProvider.array.core_weather_phenomenon, Integer.valueOf(2131165270));
      putArray(ResourceIdProvider.array.core_weather_wind_force, Integer.valueOf(2131165272));
      putArray(ResourceIdProvider.array.core_weather_wind_direction, Integer.valueOf(2131165271));
      if ((!ClientConfiguration.isChineseBuild()) && (!ClientConfiguration.isChineseTestBuild()))
        break label136;
      putArray(ResourceIdProvider.array.core_languages_names, Integer.valueOf(2131165192));
    }
    while (true)
    {
      putArray(ResourceIdProvider.array.core_russian_weekdays, Integer.valueOf(2131165227));
      return;
      putArray(ResourceIdProvider.array.core_languages_iso, Integer.valueOf(2131165193));
      break;
      label136: putArray(ResourceIdProvider.array.core_languages_names, Integer.valueOf(2131165191));
    }
  }

  protected void initDrawableMap()
  {
    putDrawable(ResourceIdProvider.drawable.core_sr_notification_skip, Integer.valueOf(2130837965));
    putDrawable(ResourceIdProvider.drawable.core_stat_safereader_off, Integer.valueOf(2130837971));
    putDrawable(ResourceIdProvider.drawable.core_stat_safereader_on, Integer.valueOf(2130837972));
    putDrawable(ResourceIdProvider.drawable.core_stat_safereader_sms, Integer.valueOf(2130837973));
    putDrawable(ResourceIdProvider.drawable.core_stat_safereader_silent, Integer.valueOf(2130837972));
    putDrawable(ResourceIdProvider.drawable.core_twitter_icon, Integer.valueOf(2130838074));
    putDrawable(ResourceIdProvider.drawable.core_facebook_icon, Integer.valueOf(2130837629));
    putDrawable(ResourceIdProvider.drawable.core_weibo_icon, Integer.valueOf(2130838321));
    putDrawable(ResourceIdProvider.drawable.core_qzone_icon, Integer.valueOf(2130837947));
  }

  protected void initIdMap()
  {
    putId(ResourceIdProvider.id.core_text, Integer.valueOf(2131558608));
  }

  protected void initLayoutMap()
  {
  }

  protected void initRawMap()
  {
    putRaw(ResourceIdProvider.raw.core_start_tone, Integer.valueOf(2131099659));
    putRaw(ResourceIdProvider.raw.core_start_tone_drv, Integer.valueOf(2131099659));
    putRaw(ResourceIdProvider.raw.core_stop_tone, Integer.valueOf(2131099661));
    putRaw(ResourceIdProvider.raw.core_stop_tone_drv, Integer.valueOf(2131099661));
    putRaw(ResourceIdProvider.raw.core_start_tone_bt, Integer.valueOf(2131099660));
    putRaw(ResourceIdProvider.raw.core_stop_tone_bt, Integer.valueOf(2131099662));
    putRaw(ResourceIdProvider.raw.core_processing_tone, Integer.valueOf(2131099667));
  }

  protected void initStringMap()
  {
    putString(ResourceIdProvider.string.core_car_tts_SMS_SENT_CONFIRM_DEMAND, Integer.valueOf(2131361839));
    putString(ResourceIdProvider.string.core_safereader_subject, Integer.valueOf(2131361889));
    putString(ResourceIdProvider.string.core_safereader_new_sms_from, Integer.valueOf(2131361885));
    putString(ResourceIdProvider.string.core_alert_message, Integer.valueOf(2131361793));
    putString(ResourceIdProvider.string.core_car_tts_SCHEDULE_NO_EVENTS, Integer.valueOf(2131361838));
    putString(ResourceIdProvider.string.core_schedule_all_day, Integer.valueOf(2131361891));
    putString(ResourceIdProvider.string.core_schedule_to, Integer.valueOf(2131361892));
    putString(ResourceIdProvider.string.core_car_event_saved, Integer.valueOf(2131361796));
    putString(ResourceIdProvider.string.core_car_tts_EVENT_SAY_TITLE, Integer.valueOf(2131361811));
    putString(ResourceIdProvider.string.core_car_tts_NO_ARTISTMATCH_DEMAND, Integer.valueOf(2131361820));
    putString(ResourceIdProvider.string.core_car_tts_ARTIST_PROMPT_DEMAND, Integer.valueOf(2131361810));
    putString(ResourceIdProvider.string.core_car_tts_NO_ALBUMMATCH_DEMAND, Integer.valueOf(2131361817));
    putString(ResourceIdProvider.string.core_car_tts_ALBUM_PROMPT_DEMAND, Integer.valueOf(2131361809));
    putString(ResourceIdProvider.string.core_car_tts_NO_TITLEMATCH_DEMAND, Integer.valueOf(2131361825));
    putString(ResourceIdProvider.string.core_car_tts_TITLE_PROMPT_DEMAND, Integer.valueOf(2131361842));
    putString(ResourceIdProvider.string.core_car_tts_NO_MUSICMATCH_DEMAND, Integer.valueOf(2131361823));
    putString(ResourceIdProvider.string.core_car_tts_MUSIC_PROMPT_DEMAND, Integer.valueOf(2131361815));
    putString(ResourceIdProvider.string.core_car_tts_NO_ANYMATCH_DEMAND, Integer.valueOf(2131362013));
    putString(ResourceIdProvider.string.core_social_api_error, Integer.valueOf(2131361897));
    putString(ResourceIdProvider.string.core_social_api_twitter_error, Integer.valueOf(2131361914));
    putString(ResourceIdProvider.string.core_car_util_loading, Integer.valueOf(2131361847));
    putString(ResourceIdProvider.string.core_wcis_social_twitter, Integer.valueOf(2131361936));
    putString(ResourceIdProvider.string.core_social_logout_facebook_msg, Integer.valueOf(2131361922));
    putString(ResourceIdProvider.string.core_social_logout_facebook, Integer.valueOf(2131361921));
    putString(ResourceIdProvider.string.core_wcis_social_facebook, Integer.valueOf(2131361935));
    putString(ResourceIdProvider.string.core_social_logout_twitter_msg, Integer.valueOf(2131361924));
    putString(ResourceIdProvider.string.core_social_logout_twitter, Integer.valueOf(2131361923));
    putString(ResourceIdProvider.string.core_car_safereader_hidden_message_body, Integer.valueOf(2131361797));
    putString(ResourceIdProvider.string.core_wcis_social_qzone, Integer.valueOf(2131361911));
    putString(ResourceIdProvider.string.core_wcis_social_weibo, Integer.valueOf(2131361910));
    putString(ResourceIdProvider.string.core_social_api_weibo_err_login, Integer.valueOf(2131361899));
    putString(ResourceIdProvider.string.core_social_api_weibo_error, Integer.valueOf(2131361898));
    putString(ResourceIdProvider.string.core_social_api_weibo_update_error, Integer.valueOf(2131361900));
    putString(ResourceIdProvider.string.core_social_login_to_weibo_msg, Integer.valueOf(2131361901));
    putString(ResourceIdProvider.string.core_social_logout_weibo, Integer.valueOf(2131361902));
    putString(ResourceIdProvider.string.core_social_logout_weibo_msg, Integer.valueOf(2131361903));
    putString(ResourceIdProvider.string.core_social_api_qzone_err_login, Integer.valueOf(2131361904));
    putString(ResourceIdProvider.string.core_social_api_qzone_error, Integer.valueOf(2131361905));
    putString(ResourceIdProvider.string.core_social_api_qzone_update_error, Integer.valueOf(2131361906));
    putString(ResourceIdProvider.string.core_social_api_qzone_update_error_publish, Integer.valueOf(2131361907));
    putString(ResourceIdProvider.string.core_social_login_to_qzone_msg, Integer.valueOf(2131361908));
    putString(ResourceIdProvider.string.core_social_logout_qzone, Integer.valueOf(2131361909));
    putString(ResourceIdProvider.string.core_social_logout_qzone_msg, Integer.valueOf(2131361912));
    putString(ResourceIdProvider.string.core_social_status_updated, Integer.valueOf(2131361927));
    putString(ResourceIdProvider.string.core_social_no_network, Integer.valueOf(2131361925));
    putString(ResourceIdProvider.string.core_social_no_status, Integer.valueOf(2131361926));
    putString(ResourceIdProvider.string.core_car_social_status_prompt, Integer.valueOf(2131361807));
    putString(ResourceIdProvider.string.core_car_sms_message_empty_tts, Integer.valueOf(2131361800));
    putString(ResourceIdProvider.string.core_car_tts_NAVIGATE_TO, Integer.valueOf(2131361816));
    putString(ResourceIdProvider.string.core_car_tts_MAP_OF, Integer.valueOf(2131361814));
    putString(ResourceIdProvider.string.core_car_tts_GOTO_URL, Integer.valueOf(2131361812));
    putString(ResourceIdProvider.string.core_car_tts_NO_MATCH_DEMAND_MESSAGE, Integer.valueOf(2131361822));
    putString(ResourceIdProvider.string.core_checkEventTitleTimeDetailBrief, Integer.valueOf(2131361848));
    putString(ResourceIdProvider.string.core_checkEventYouHave, Integer.valueOf(2131361852));
    putString(ResourceIdProvider.string.core_multiple_phone_numbers, Integer.valueOf(2131361868));
    putString(ResourceIdProvider.string.core_multiple_phone_numbers2, Integer.valueOf(2131361869));
    putString(ResourceIdProvider.string.core_multiple_applications, Integer.valueOf(2131361866));
    putString(ResourceIdProvider.string.core_opening_app, Integer.valueOf(2131361963));
    putString(ResourceIdProvider.string.core_weather_general, Integer.valueOf(2131361942));
    putString(ResourceIdProvider.string.core_weather_with_locatin, Integer.valueOf(2131361945));
    putString(ResourceIdProvider.string.core_weather_default_location, Integer.valueOf(2131362014));
    putString(ResourceIdProvider.string.core_weather_plus_seven, Integer.valueOf(2131361944));
    putString(ResourceIdProvider.string.core_weather_no_results, Integer.valueOf(2131361943));
    putString(ResourceIdProvider.string.core_weather_current, Integer.valueOf(2131361937));
    putString(ResourceIdProvider.string.core_weather_date_tts, Integer.valueOf(2131361939));
    putString(ResourceIdProvider.string.core_weather_date_display, Integer.valueOf(2131361938));
    putString(ResourceIdProvider.string.core_tomorrow, Integer.valueOf(2131361941));
    putString(ResourceIdProvider.string.core_today, Integer.valueOf(2131361940));
    putString(ResourceIdProvider.string.core_car_tts_VD_CALLING_CONFIRM_DEMAND, Integer.valueOf(2131361843));
    putString(ResourceIdProvider.string.core_default_alarm_title, Integer.valueOf(2131361859));
    putString(ResourceIdProvider.string.core_redial, Integer.valueOf(2131361882));
    putString(ResourceIdProvider.string.core_single_contact, Integer.valueOf(2131361894));
    putString(ResourceIdProvider.string.core_tts_local_required_engine_name, Integer.valueOf(2131362041));
    putString(ResourceIdProvider.string.core_tts_local_fallback_engine_name, Integer.valueOf(2131362042));
    putString(ResourceIdProvider.string.core_social_update_failed, Integer.valueOf(2131361929));
    putString(ResourceIdProvider.string.core_multiple_contacts, Integer.valueOf(2131361867));
    putString(ResourceIdProvider.string.core_contact_email_not_found, Integer.valueOf(2131361855));
    putString(ResourceIdProvider.string.core_contact_address_not_found, Integer.valueOf(2131361857));
    putString(ResourceIdProvider.string.core_contact_birthday_not_found, Integer.valueOf(2131361856));
    putString(ResourceIdProvider.string.core_no_memo_saved, Integer.valueOf(2131361873));
    putString(ResourceIdProvider.string.core_no_memo_saved_about, Integer.valueOf(2131361874));
    putString(ResourceIdProvider.string.core_car_social_service_prompt, Integer.valueOf(2131361806));
    putString(ResourceIdProvider.string.core_safereader_enabled, Integer.valueOf(2131361884));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_FROM_BODY_NO_PROMPT, Integer.valueOf(2131361832));
    putString(ResourceIdProvider.string.core_memo_multiple_found, Integer.valueOf(2131361864));
    putString(ResourceIdProvider.string.core_car_tts_NO_MATCH_DEMAND_CALL, Integer.valueOf(2131361821));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MSG, Integer.valueOf(2131361833));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN, Integer.valueOf(2131361828));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SPOKEN, Integer.valueOf(2131361829));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN, Integer.valueOf(2131361830));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN, Integer.valueOf(2131361831));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SHOWN_NOCALL, Integer.valueOf(2131361972));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ_NOCALL, Integer.valueOf(2131361974));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_SHOWN_NOCALL, Integer.valueOf(2131361971));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_MESSAGE_BODY_READOUT_SPOKEN_NOCALL, Integer.valueOf(2131361973));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NO_REPLY, Integer.valueOf(2131361976));
    putString(ResourceIdProvider.string.core_checkEventYouHaveToday, Integer.valueOf(2131361854));
    putString(ResourceIdProvider.string.core_checkEventYouHaveOnDate, Integer.valueOf(2131361853));
    putString(ResourceIdProvider.string.core_car_sms_error_help_tts, Integer.valueOf(2131361798));
    putString(ResourceIdProvider.string.core_car_sms_error_sending, Integer.valueOf(2131361799));
    putString(ResourceIdProvider.string.core_car_sms_speak_msg_tts, Integer.valueOf(2131361802));
    putString(ResourceIdProvider.string.core_car_tts_VD_MULTIPLE_CONTACTS_DEMAND, Integer.valueOf(2131361844));
    putString(ResourceIdProvider.string.core_car_tts_VD_MULTIPLE_TYPES_DEMAND, Integer.valueOf(2131361845));
    putString(ResourceIdProvider.string.core_contacts_no_match_openquote, Integer.valueOf(2131361858));
    putString(ResourceIdProvider.string.core_nav_home_prompt, Integer.valueOf(2131361870));
    putString(ResourceIdProvider.string.core_navigate_home, Integer.valueOf(2131361871));
    putString(ResourceIdProvider.string.core_safereader_notif_reading_title, Integer.valueOf(2131361887));
    putString(ResourceIdProvider.string.core_safereader_notif_reading_ticker, Integer.valueOf(2131361886));
    putString(ResourceIdProvider.string.core_safereader_notif_title, Integer.valueOf(2131361888));
    putString(ResourceIdProvider.string.core_safereader_notif_title_silent, Integer.valueOf(2131361888));
    putString(ResourceIdProvider.string.core_answer_prompt, Integer.valueOf(2131361794));
    putString(ResourceIdProvider.string.core_social_api_err_auth1, Integer.valueOf(2131361895));
    putString(ResourceIdProvider.string.core_social_api_err_auth2, Integer.valueOf(2131361896));
    putString(ResourceIdProvider.string.core_social_api_twitter_err_login, Integer.valueOf(2131361913));
    putString(ResourceIdProvider.string.core_social_api_twitter_update_error, Integer.valueOf(2131361915));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM, Integer.valueOf(2131361835));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_FROM_INTRO, Integer.valueOf(2131361836));
    putString(ResourceIdProvider.string.core_social_too_long, Integer.valueOf(2131361928));
    putString(ResourceIdProvider.string.core_social_login_to_facebook_msg, Integer.valueOf(2131361918));
    putString(ResourceIdProvider.string.core_social_login_to_network_msg, Integer.valueOf(2131361919));
    putString(ResourceIdProvider.string.core_social_login_to_twitter_msg, Integer.valueOf(2131361920));
    putString(ResourceIdProvider.string.core_social_err_msg1, Integer.valueOf(2131361916));
    putString(ResourceIdProvider.string.core_social_err_msg2, Integer.valueOf(2131361917));
    putString(ResourceIdProvider.string.core_safe_reader_default_error, Integer.valueOf(2131361883));
    putString(ResourceIdProvider.string.core_car_task_save_cancel_update_prompt, Integer.valueOf(2131361808));
    putString(ResourceIdProvider.string.core_car_tts_SCHEDULE_EVENTS, Integer.valueOf(2131361837));
    putString(ResourceIdProvider.string.core_car_tts_TASK_SAY_TITLE, Integer.valueOf(2131361841));
    putString(ResourceIdProvider.string.core_car_tts_LAUNCHAPP_PROMPT_DEMAND, Integer.valueOf(2131361813));
    putString(ResourceIdProvider.string.core_car_tts_NO_APPMATCH_DEMAND, Integer.valueOf(2131361818));
    putString(ResourceIdProvider.string.core_car_tts_NO_SPOKEN_APPMATCH_DEMAND, Integer.valueOf(2131361819));
    putString(ResourceIdProvider.string.core_car_event_save_confirm, Integer.valueOf(2131361795));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_NEW_SMS_CMD_READ, Integer.valueOf(2131361834));
    putString(ResourceIdProvider.string.core_car_tts_WHICH_CONTACT_DEMAND, Integer.valueOf(2131361846));
    putString(ResourceIdProvider.string.core_car_social_final_prompt, Integer.valueOf(2131361804));
    putString(ResourceIdProvider.string.core_car_sms_text_who, Integer.valueOf(2131361803));
    putString(ResourceIdProvider.string.core_car_tts_TASK_CANCELLED, Integer.valueOf(2131361840));
    putString(ResourceIdProvider.string.core_car_tts_NO_PLAYLISTMATCH_DEMAND, Integer.valueOf(2131361824));
    putString(ResourceIdProvider.string.core_car_sms_send_confirm, Integer.valueOf(2131361801));
    putString(ResourceIdProvider.string.core_who_would_you_like_to_call, Integer.valueOf(2131361946));
    putString(ResourceIdProvider.string.core_car_tts_PLAYPLAYLIST_PROMPT_DEMAND, Integer.valueOf(2131361826));
    putString(ResourceIdProvider.string.core_voice_recognition_service_not_thru_iux_error, Integer.valueOf(2131361930));
    putString(ResourceIdProvider.string.core_voicevideodial_call_name, Integer.valueOf(2131361933));
    putString(ResourceIdProvider.string.core_voicevideodial_call_name_type, Integer.valueOf(2131361934));
    putString(ResourceIdProvider.string.core_voicedial_call_name, Integer.valueOf(2131361931));
    putString(ResourceIdProvider.string.core_voicedial_call_name_type, Integer.valueOf(2131361932));
    putString(ResourceIdProvider.string.core_multiple_phone_numbers2, Integer.valueOf(2131361869));
    putString(ResourceIdProvider.string.core_say_command, Integer.valueOf(2131361890));
    putString(ResourceIdProvider.string.core_memo_confirm_delete, Integer.valueOf(2131361863));
    putString(ResourceIdProvider.string.core_memo_not_saved, Integer.valueOf(2131361865));
    putString(ResourceIdProvider.string.core_car_tts_SAFEREADER_MULTIPLE_NEW_MESSAGES, Integer.valueOf(2131361827));
    putString(ResourceIdProvider.string.core_qa_more, Integer.valueOf(2131361875));
    putString(ResourceIdProvider.string.core_qa_tts_NO_ANS_WEB_SEARCH, Integer.valueOf(2131361876));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH, Integer.valueOf(2131361877));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_1, Integer.valueOf(2131361878));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_2, Integer.valueOf(2131361879));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_3, Integer.valueOf(2131361880));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_WEB_SEARCH_4, Integer.valueOf(2131361881));
    putString(ResourceIdProvider.string.core_tts_NO_ANS_GOOGLE_NOW_SEARCH, Integer.valueOf(2131362015));
    putString(ResourceIdProvider.string.core_no_call_log, Integer.valueOf(2131361872));
    putString(ResourceIdProvider.string.core_alarm_set, Integer.valueOf(2131361792));
    putString(ResourceIdProvider.string.core_localsearch_no_location, Integer.valueOf(2131361861));
    putString(ResourceIdProvider.string.core_localsearch, Integer.valueOf(2131361860));
    putString(ResourceIdProvider.string.core_localsearch_provider_dianping, Integer.valueOf(2131361862));
    putString(ResourceIdProvider.string.core_search_web_label_button, Integer.valueOf(2131361893));
    putString(ResourceIdProvider.string.core_car_social_prompt_ex1, Integer.valueOf(2131361805));
    putString(ResourceIdProvider.string.core_phone_in_use, Integer.valueOf(2131361947));
    putString(ResourceIdProvider.string.core_mic_in_use, Integer.valueOf(2131361948));
    putString(ResourceIdProvider.string.core_what_is_the_weather_date_var, Integer.valueOf(2131361953));
    putString(ResourceIdProvider.string.core_what_is_the_weather_today, Integer.valueOf(2131361951));
    putString(ResourceIdProvider.string.core_what_is_the_weather_today_location_var, Integer.valueOf(2131361952));
    putString(ResourceIdProvider.string.core_what_is_the_weather_date_var_location_var, Integer.valueOf(2131361950));
    putString(ResourceIdProvider.string.core_local_search_blank_request_message, Integer.valueOf(2131361954));
    putString(ResourceIdProvider.string.core_address_book_is_empty, Integer.valueOf(2131361955));
    putString(ResourceIdProvider.string.core_checkEventOneYouHave, Integer.valueOf(2131361850));
    putString(ResourceIdProvider.string.core_checkEventRussianTwoThreeFourYouHave, Integer.valueOf(2131361849));
    putString(ResourceIdProvider.string.core_checkEventJapaneseMoreTenYouHave, Integer.valueOf(2131361851));
    putString(ResourceIdProvider.string.core_network_error, Integer.valueOf(2131361956));
    putString(ResourceIdProvider.string.core_dot, Integer.valueOf(2131361957));
    putString(ResourceIdProvider.string.core_comma, Integer.valueOf(2131361958));
    putString(ResourceIdProvider.string.core_space, Integer.valueOf(2131362061));
    putString(ResourceIdProvider.string.core_colon, Integer.valueOf(2131361960));
    putString(ResourceIdProvider.string.core_semicolon, Integer.valueOf(2131361961));
    putString(ResourceIdProvider.string.core_ellipsis, Integer.valueOf(2131361962));
    putString(ResourceIdProvider.string.core_message_sending, Integer.valueOf(2131361964));
    putString(ResourceIdProvider.string.core_message_sending_readout, Integer.valueOf(2131361965));
    putString(ResourceIdProvider.string.core_message_reading_preface, Integer.valueOf(2131361966));
    putString(ResourceIdProvider.string.core_message_reading_none, Integer.valueOf(2131361967));
    putString(ResourceIdProvider.string.core_message_reading_single_from_sender, Integer.valueOf(2131361968));
    putString(ResourceIdProvider.string.core_message_reading_multi_from_sender, Integer.valueOf(2131361969));
    putString(ResourceIdProvider.string.core_current_location, Integer.valueOf(2131361970));
    putString(ResourceIdProvider.string.core_wifi_setting_change_on_error, Integer.valueOf(2131361975));
    putString(ResourceIdProvider.string.core_playing_music, Integer.valueOf(2131361977));
    putString(ResourceIdProvider.string.core_not_detected_current_location, Integer.valueOf(2131361978));
    putString(ResourceIdProvider.string.core_unknown, Integer.valueOf(2131361979));
    putString(ResourceIdProvider.string.core_format_time_AM, Integer.valueOf(2131361980));
    putString(ResourceIdProvider.string.core_format_time_PM, Integer.valueOf(2131361981));
    putString(ResourceIdProvider.string.core_error, Integer.valueOf(2131361982));
    putString(ResourceIdProvider.string.core_permission_internet_error, Integer.valueOf(2131361983));
    putString(ResourceIdProvider.string.core_message_reading_none_spoken, Integer.valueOf(2131361967));
    putString(ResourceIdProvider.string.core_message_none_from_sender, Integer.valueOf(2131361985));
    putString(ResourceIdProvider.string.core_message_none_from_sender_spoken, Integer.valueOf(2131361986));
    putString(ResourceIdProvider.string.core_no_more_messages_verbose, Integer.valueOf(2131361987));
    putString(ResourceIdProvider.string.core_no_more_messages_regular, Integer.valueOf(2131361988));
    putString(ResourceIdProvider.string.core_no_more_messages_spoken, Integer.valueOf(2131361989));
    putString(ResourceIdProvider.string.core_readout_no_match_found, Integer.valueOf(2131361990));
    putString(ResourceIdProvider.string.core_readout_single_message_initial_hidden, Integer.valueOf(2131361991));
    putString(ResourceIdProvider.string.core_readout_single_message_initial_hidden_spoken, Integer.valueOf(2131361992));
    putString(ResourceIdProvider.string.core_readout_single_message_initial_spoken, Integer.valueOf(2131361993));
    putString(ResourceIdProvider.string.core_readout_single_message_next, Integer.valueOf(2131361994));
    putString(ResourceIdProvider.string.core_readout_single_message_next_spoken, Integer.valueOf(2131361995));
    putString(ResourceIdProvider.string.core_readout_single_message, Integer.valueOf(2131361996));
    putString(ResourceIdProvider.string.core_readout_single_message_spoken, Integer.valueOf(2131361997));
    putString(ResourceIdProvider.string.core_readout_multi_message, Integer.valueOf(2131361998));
    putString(ResourceIdProvider.string.core_readout_multi_message_overflow, Integer.valueOf(2131361999));
    putString(ResourceIdProvider.string.core_readout_multi_sender, Integer.valueOf(2131362000));
    putString(ResourceIdProvider.string.core_readout_multi_sender_info_verbose_spoken, Integer.valueOf(2131362001));
    putString(ResourceIdProvider.string.core_readout_multi_sender_info_verbose_overflow_spoken, Integer.valueOf(2131362002));
    putString(ResourceIdProvider.string.core_readout_multi_sender_command_spoken, Integer.valueOf(2131362003));
    putString(ResourceIdProvider.string.core_readout_multi_sender_dm_overflow, Integer.valueOf(2131362004));
    putString(ResourceIdProvider.string.core_readout_multi_sender_overflow_spoken, Integer.valueOf(2131362005));
    putString(ResourceIdProvider.string.core_readout_multi_sender_overflow, Integer.valueOf(2131362006));
    putString(ResourceIdProvider.string.core_and, Integer.valueOf(2131362007));
    putString(ResourceIdProvider.string.core_cradle_date, Integer.valueOf(2131362010));
    putString(ResourceIdProvider.string.core_car_redial_confirm_number, Integer.valueOf(2131362009));
    putString(ResourceIdProvider.string.core_car_redial_confirm_contact, Integer.valueOf(2131362008));
    putString(ResourceIdProvider.string.core_weibo_dialog_title, Integer.valueOf(2131362011));
    putString(ResourceIdProvider.string.core_qzone_dialog_title, Integer.valueOf(2131362012));
    putString(ResourceIdProvider.string.core_contact_phone_number, Integer.valueOf(2131362016));
    putString(ResourceIdProvider.string.core_contact_email, Integer.valueOf(2131362017));
    putString(ResourceIdProvider.string.core_contact_address, Integer.valueOf(2131362018));
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.midas.CoreResourceProviderImpl
 * JD-Core Version:    0.6.0
 */