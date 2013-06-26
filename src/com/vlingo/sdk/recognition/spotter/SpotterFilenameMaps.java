package com.vlingo.sdk.recognition.spotter;

import java.util.HashMap;

public class SpotterFilenameMaps
{
  public static HashMap<String, String> SPOTTER_ACOUSTIC_MODEL_FILENAME = new HashMap(11);
  public static HashMap<String, String> SPOTTER_PRONUN_MODEL_FILENAME;
  public static HashMap<String, String> SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME;
  public static HashMap<String, String> SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME;
  public static HashMap<String, String> SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME;
  public static HashMap<String, String> SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME;

  static
  {
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("en-US", "nn_en_us_mfcc_16k_15_big_250_v4_5.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("en-GB", "nn_en_uk_mfcc_16k_15_big_250_v2_0.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("fr-FR", "nn_fr_mfcc_16k_15_big_250_v2_2.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("it-IT", "nn_it_mfcc_16k_15_big_250_v3_2.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("de-DE", "nn_de_mfcc_16k_15_big_250_v3_0.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("es-ES", "nn_es_mfcc_16k_15_big_250_v1_1.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("v-es-LA", "nn_es_mfcc_16k_15_big_250_v1_1.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("v-es-NA", "nn_es_mfcc_16k_15_big_250_v1_1.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("zh-CN", "nn_zh_mfcc_16k_15_big_250_v2_2.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("ja-JP", "nn_ja_mfcc_16k_15_big_250_v4_2.raw");
    SPOTTER_ACOUSTIC_MODEL_FILENAME.put("ko-KR", "nn_ko_mfcc_16k_15_big_250_v2_1.raw");
    SPOTTER_PRONUN_MODEL_FILENAME = new HashMap(11);
    SPOTTER_PRONUN_MODEL_FILENAME.put("en-US", "lts_en_us_9.2.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("en-GB", "lts_en_2.8.1.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("fr-FR", "lts_fr_3.12.1.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("it-IT", "lts_it_1.6.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("de-DE", "lts_de_3.3.5.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("es-ES", "lts_es_3.14.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("v-es-LA", "lts_es_3.14.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("v-es-NA", "lts_es_3.14.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("zh-CN", "lts_zh_3.2.5.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("ja-JP", "lts_jp_5.17.raw");
    SPOTTER_PRONUN_MODEL_FILENAME.put("ko-KR", "lts_ko_1.0.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME = new HashMap(13);
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("en-US", "samsung_wakeup_am_quiet_en_us_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("en-GB", "samsung_wakeup_am_quiet_en_gb_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("fr-FR", "samsung_wakeup_am_quiet_fr_fr_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("it-IT", "samsung_wakeup_am_quiet_it_it_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("de-DE", "samsung_wakeup_am_quiet_de_de_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("es-ES", "samsung_wakeup_am_quiet_es_es_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("v-es-LA", "samsung_wakeup_am_quiet_es_la_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("v-es-NA", "samsung_wakeup_am_quiet_es_es_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("zh-CN", "samsung_wakeup_am_quiet_zh_cn_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("ja-JP", "samsung_wakeup_am_quiet_ja_jp_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("ko-KR", "samsung_wakeup_am_quiet_ko_kr_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("ru-RU", "samsung_wakeup_am_quiet_ru_ru_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_QUIET_FILENAME.put("pt-BR", "samsung_wakeup_am_quiet_pt_br_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME = new HashMap(13);
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("en-US", "samsung_wakeup_am_media_en_us_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("en-GB", "samsung_wakeup_am_media_en_gb_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("fr-FR", "samsung_wakeup_am_media_fr_fr_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("it-IT", "samsung_wakeup_am_media_it_it_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("de-DE", "samsung_wakeup_am_media_de_de_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("es-ES", "samsung_wakeup_am_media_es_es_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("v-es-LA", "samsung_wakeup_am_media_es_la_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("v-es-NA", "samsung_wakeup_am_media_es_es_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("zh-CN", "samsung_wakeup_am_media_zh_cn_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("ja-JP", "samsung_wakeup_am_media_ja_jp_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("ko-KR", "samsung_wakeup_am_media_ko_kr_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("ru-RU", "samsung_wakeup_am_media_ru_ru_v2.raw");
    SPOTTER_S2_ACOUSTIC_MODEL_MEDIA_FILENAME.put("pt-BR", "samsung_wakeup_am_media_pt_br_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME = new HashMap(13);
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("en-US", "samsung_wakeup_grammar_quiet_en_us_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("en-GB", "samsung_wakeup_grammar_quiet_en_gb_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("fr-FR", "samsung_wakeup_grammar_quiet_fr_fr_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("it-IT", "samsung_wakeup_grammar_quiet_it_it_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("de-DE", "samsung_wakeup_grammar_quiet_de_de_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("es-ES", "samsung_wakeup_grammar_quiet_es_es_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("v-es-LA", "samsung_wakeup_grammar_quiet_es_la_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("v-es-NA", "samsung_wakeup_grammar_quiet_es_es_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("zh-CN", "samsung_wakeup_grammar_quiet_zh_cn_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("ja-JP", "samsung_wakeup_grammar_quiet_ja_jp_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("ko-KR", "samsung_wakeup_grammar_quiet_ko_kr_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("ru-RU", "samsung_wakeup_grammar_quiet_ru_ru_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_QUIET_FILENAME.put("pt-BR", "samsung_wakeup_grammar_quiet_pt_br_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME = new HashMap(13);
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("en-US", "samsung_wakeup_grammar_media_en_us_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("en-GB", "samsung_wakeup_grammar_media_en_gb_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("fr-FR", "samsung_wakeup_grammar_media_fr_fr_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("it-IT", "samsung_wakeup_grammar_media_it_it_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("de-DE", "samsung_wakeup_grammar_media_de_de_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("es-ES", "samsung_wakeup_grammar_media_es_es_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("v-es-LA", "samsung_wakeup_grammar_media_es_la_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("v-es-NA", "samsung_wakeup_grammar_media_es_es_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("zh-CN", "samsung_wakeup_grammar_media_zh_cn_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("ja-JP", "samsung_wakeup_grammar_media_ja_jp_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("ko-KR", "samsung_wakeup_grammar_media_ko_kr_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("ru-RU", "samsung_wakeup_grammar_media_ru_ru_v2.raw");
    SPOTTER_S2_GRAMMAR_MODEL_MEDIA_FILENAME.put("pt-BR", "samsung_wakeup_grammar_media_pt_br_v2.raw");
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.sdk.recognition.spotter.SpotterFilenameMaps
 * JD-Core Version:    0.6.0
 */