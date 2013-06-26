package com.vlingo.core.internal.weather;

import android.sax.RootElement;
import android.util.Xml;
import android.util.Xml.Encoding;
import com.vlingo.core.internal.util.ServerErrorUtil;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherResponseParser
  implements WeatherElementNames
{
  private static final String kWeatherXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><WeatherResponse Location=\"?????, ?????\" Provider=\"KweatherContentProvider\"><CurrentCondition><CurrentCondition BadFeel=\"0.0\" DewPt=\"0.0\" FineDustConcentration=\"0.0\" Humidity=\"52.0\" ObservationTime=\"2012-09-14 17:54:00\" Pressure=\"1020.7\" RealFeel=\"27.3\" TempC=\"0.0\" TempF=\"0.0\" Temperature=\"26.7\" Vis=\"0.0\" Visibility=\"-1\" WeatherCode=\"2\" WeatherText=\"NA\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></CurrentCondition><Forecasts><Forecast ForecastDate=\"2012-09-14\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"25.5\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.4\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"25.5\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.4\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-15\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.1\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"5\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.1\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"5\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-16\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast  IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"18.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"18.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-17\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-18\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"15.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"15.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-19\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.3\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"17.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.3\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"17.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-20\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"21.6\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"13.9\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"21.6\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"13.9\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast></Forecasts><MoonPhases/><WeatherLocation><Location City=\"?????\" Country=\"?????\"/></WeatherLocation></WeatherResponse>";
  private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><WeatherResponse><CurrentCondition><CurrentCondition CloudCover=\"0%\" DewPt=\"6.0\" Humidity=\"11.0\" ObservationTime=\"2012-01-26 07:53:14\" Precip=\"0.0\" Pressure=\"102.0\" RealFeel=\"13.0\" Sunrise=\"6:54 AM\" Sunset=\"5:17 PM\" TempC=\"0.0\" TempF=\"0.0\" Temperature=\"11.0\" UVIndex=\"Low\" Visibility=\"16\" WeatherCode=\"1\" WeatherText=\"Sunny\" WindDir=\"N\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></CurrentCondition><Forecasts><Forecast ForecastDate=\"2012-01-26\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Very warm with sunshine\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"29.0\" RealFeelLow=\"13.0\" ShortText=\"Very warm with sunshine\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"28.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindDir=\"N\" WindGusts=\"4.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mainly clear\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"29.0\" RealFeelLow=\"13.0\" ShortText=\"Mainly clear\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"28.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"34\" WindDir=\"N\" WindGusts=\"3.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-01-27\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mostly sunny\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"27.0\" RealFeelLow=\"13.0\" ShortText=\"Mostly sunny\" SnowAmount=\"0.0\" TStormProb=\"3.0\" TempMax=\"24.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindDir=\"N\" WindGusts=\"6.0\" WindSpeed=\"3.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Clear\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"27.0\" RealFeelLow=\"13.0\" ShortText=\"Clear\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"24.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"33\" WindDir=\"N\" WindGusts=\"3.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-01-28\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mostly sunny and very warm\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"28.0\" RealFeelLow=\"12.0\" ShortText=\"Mostly sunny and very warm\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"28.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"10.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindDir=\"NNW\" WindGusts=\"4.0\" WindSpeed=\"3.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mostly clear\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"28.0\" RealFeelLow=\"12.0\" ShortText=\"Mostly clear\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"28.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"10.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"34\" WindDir=\"NNE\" WindGusts=\"1.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-01-29\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Plenty of sunshine\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"26.0\" RealFeelLow=\"12.0\" ShortText=\"Plenty of sunshine\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"25.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindDir=\"N\" WindGusts=\"1.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Partly cloudy\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"26.0\" RealFeelLow=\"12.0\" ShortText=\"Partly cloudy\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"25.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"35\" WindDir=\"SW\" WindGusts=\"3.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-01-30\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Sunny to partly cloudy and nice\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"23.0\" RealFeelLow=\"9.0\" ShortText=\"Mostly sunny and nice\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"22.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"9.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindDir=\"N\" WindGusts=\"1.0\" WindSpeed=\"1.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Partly cloudy\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"23.0\" RealFeelLow=\"9.0\" ShortText=\"Partly cloudy\" SnowAmount=\"0.0\" TStormProb=\"1.0\" TempMax=\"22.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"9.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"35\" WindDir=\"NE\" WindGusts=\"3.0\" WindSpeed=\"3.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-01-31\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Sunny to partly cloudy and pleasant\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"22.0\" RealFeelLow=\"11.0\" ShortText=\"Mostly sunny and pleasant\" SnowAmount=\"0.0\" TStormProb=\"2.0\" TempMax=\"22.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"10.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindDir=\"NE\" WindGusts=\"3.0\" WindSpeed=\"3.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mainly clear\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"22.0\" RealFeelLow=\"11.0\" ShortText=\"Mainly clear\" SnowAmount=\"0.0\" TStormProb=\"2.0\" TempMax=\"22.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"10.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"34\" WindDir=\"NNE\" WindGusts=\"6.0\" WindSpeed=\"4.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-02-01\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Mostly sunny\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"25.0\" RealFeelLow=\"11.0\" ShortText=\"Mostly sunny\" SnowAmount=\"0.0\" TStormProb=\"2.0\" TempMax=\"25.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindDir=\"NNE\" WindGusts=\"6.0\" WindSpeed=\"4.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" LongText=\"Clear to partly cloudy\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"25.0\" RealFeelLow=\"11.0\" ShortText=\"Clear to partly cloudy\" SnowAmount=\"0.0\" TStormProb=\"2.0\" TempMax=\"25.0\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"11.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"34\" WindDir=\"NNE\" WindGusts=\"12.0\" WindSpeed=\"6.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast></Forecasts><Images><Images MapSpace=\"http://vortex.accuweather.com/adc2004/mapspace/usradar/480x480/CAS.png\" Radar=\"&#10;&#9;&#9;&#9;&#9;&#9;&#9;http://sirocco.accuweather.com/nx_mosaic_108x81c/re/inmreCAS.gif&#10;&#9;&#9;&#9;&#9;&#9;\" Url=\"http://www.accuweather.com/m/en-us/US/CA/Los Angeles/radar.aspx?p=samsungmobile&amp;cityId=347625\"/></Images><MoonPhases><MoonPhase Date=\"2012-01-26\" Day=\"2\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-01-27\" Day=\"3\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-01-28\" Day=\"4\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-01-29\" Day=\"5\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-01-30\" Day=\"6\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-01-31\" Day=\"7\" Text=\"First\"/><MoonPhase Date=\"2012-02-01\" Day=\"8\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-02\" Day=\"9\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-03\" Day=\"10\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-04\" Day=\"11\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-05\" Day=\"12\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-06\" Day=\"13\" Text=\"Waxing Gibbous\"/><MoonPhase Date=\"2012-02-07\" Day=\"14\" Text=\"Full\"/><MoonPhase Date=\"2012-02-08\" Day=\"15\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-09\" Day=\"16\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-10\" Day=\"17\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-11\" Day=\"18\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-12\" Day=\"19\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-13\" Day=\"20\" Text=\"Waning Gibbous\"/><MoonPhase Date=\"2012-02-14\" Day=\"21\" Text=\"Last\"/><MoonPhase Date=\"2012-02-15\" Day=\"22\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-16\" Day=\"23\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-17\" Day=\"24\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-18\" Day=\"25\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-19\" Day=\"26\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-20\" Day=\"27\" Text=\"Waning Crescent\"/><MoonPhase Date=\"2012-02-21\" Day=\"1\" Text=\"New\"/><MoonPhase Date=\"2012-02-22\" Day=\"1\" Text=\"New\"/><MoonPhase Date=\"2012-02-23\" Day=\"1\" Text=\"New\"/><MoonPhase Date=\"2012-02-24\" Day=\"1\" Text=\"New\"/><MoonPhase Date=\"2012-02-25\" Day=\"2\" Text=\"Waxing Crescent\"/><MoonPhase Date=\"2012-02-26\" Day=\"3\" Text=\"Waxing Crescent\"/></MoonPhases></WeatherResponse>";

  private static String getErrorMessage(InputStream paramInputStream)
  {
    String str1 = "Unexpected Server Error";
    try
    {
      org.w3c.dom.Element localElement = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramInputStream).getDocumentElement();
      NodeList localNodeList1 = localElement.getElementsByTagName("Error");
      if (localNodeList1 != null)
      {
        if (localNodeList1.getLength() > 0)
        {
          NodeList localNodeList2 = localElement.getElementsByTagName("Message");
          for (int i = 0; i < localNodeList2.getLength(); i++)
            str1 = str1 + " " + localNodeList2.item(i).getTextContent();
        }
        ServerErrorUtil localServerErrorUtil = new ServerErrorUtil(localElement);
        if (localServerErrorUtil.hasServerError())
        {
          str1 = str1 + " Code=" + localServerErrorUtil.getCode();
          localServerErrorUtil.logIt();
          str2 = null;
          return str2;
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        String str2 = str1;
    }
  }

  public static WeatherElement getSample()
  {
    WeatherElement localWeatherElement = new WeatherElement("WeatherResponse");
    parse("<?xml version=\"1.0\" encoding=\"UTF-8\"?><WeatherResponse Location=\"?????, ?????\" Provider=\"KweatherContentProvider\"><CurrentCondition><CurrentCondition BadFeel=\"0.0\" DewPt=\"0.0\" FineDustConcentration=\"0.0\" Humidity=\"52.0\" ObservationTime=\"2012-09-14 17:54:00\" Pressure=\"1020.7\" RealFeel=\"27.3\" TempC=\"0.0\" TempF=\"0.0\" Temperature=\"26.7\" Vis=\"0.0\" Visibility=\"-1\" WeatherCode=\"2\" WeatherText=\"NA\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></CurrentCondition><Forecasts><Forecast ForecastDate=\"2012-09-14\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"25.5\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.4\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"25.5\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.4\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"1\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-15\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.1\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"5\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"16.1\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"5\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-16\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast  IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"18.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"18.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-17\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"22.8\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"12.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-18\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"15.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.9\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"15.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-19\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.3\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"17.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"23.3\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"17.2\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"10\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast><Forecast ForecastDate=\"2012-09-20\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"-1\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"><DaytimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"21.6\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"13.9\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></DaytimeForecast><NighttimeForecast><DayNighttimeForecast IceAmount=\"0.0\" PrecipAmount=\"0.0\" RainAmount=\"0.0\" RealFeelHigh=\"0.0\" RealFeelLow=\"0.0\" SnowAmount=\"0.0\" TStormProb=\"0.0\" TempMax=\"21.6\" TempMaxC=\"0.0\" TempMaxF=\"0.0\" TempMin=\"13.9\" TempMinC=\"0.0\" TempMinF=\"0.0\" WeatherCode=\"2\" WindGusts=\"0.0\" WindSpeed=\"0.0\" WindSpeedKmph=\"0.0\" WindSpeedMiles=\"0.0\"/></NighttimeForecast></Forecast></Forecasts><MoonPhases/><WeatherLocation><Location City=\"?????\" Country=\"?????\"/></WeatherLocation></WeatherResponse>", setWeatherParser(localWeatherElement));
    return localWeatherElement;
  }

  public static WeatherElement parse(InputStream paramInputStream)
  {
    WeatherElement localWeatherElement = new WeatherElement("WeatherResponse");
    parse(paramInputStream, setWeatherParser(localWeatherElement), localWeatherElement);
    return localWeatherElement;
  }

  private static void parse(InputStream paramInputStream, RootElement paramRootElement, WeatherElement paramWeatherElement)
  {
    try
    {
      Xml.parse(paramInputStream, Xml.Encoding.UTF_8, paramRootElement.getContentHandler());
      return;
    }
    catch (Exception localException1)
    {
      while (true)
        try
        {
          paramWeatherElement.setErrorMessage(getErrorMessage(paramInputStream));
        }
        catch (Exception localException2)
        {
        }
    }
  }

  private static void parse(String paramString, RootElement paramRootElement)
  {
    try
    {
      Xml.parse(paramString, paramRootElement.getContentHandler());
      label8: return;
    }
    catch (Exception localException)
    {
      break label8;
    }
  }

  private static void setAttribute(WeatherElement paramWeatherElement, android.sax.Element paramElement)
  {
    paramElement.setStartElementListener(new WeatherAttributeListener(paramWeatherElement));
  }

  private static RootElement setWeatherParser(WeatherElement paramWeatherElement)
  {
    RootElement localRootElement = new RootElement("WeatherResponse");
    setAttribute(paramWeatherElement, localRootElement);
    WeatherElement localWeatherElement1 = new WeatherElement("CurrentCondition");
    android.sax.Element localElement1 = localRootElement.getChild("CurrentCondition");
    setAttribute(localWeatherElement1, localElement1);
    WeatherResponseParser.1 local1 = new WeatherResponseParser.1(paramWeatherElement, localWeatherElement1);
    localElement1.setEndElementListener(local1);
    android.sax.Element localElement2 = localElement1.getChild("CurrentCondition");
    WeatherElement localWeatherElement2 = new WeatherElement("CurrentCondition");
    setAttribute(localWeatherElement2, localElement2);
    WeatherResponseParser.2 local2 = new WeatherResponseParser.2(localWeatherElement1, localWeatherElement2);
    localElement2.setEndElementListener(local2);
    WeatherElement localWeatherElement3 = new WeatherElement("Forecasts");
    android.sax.Element localElement3 = localRootElement.getChild("Forecasts");
    setAttribute(localWeatherElement3, localElement3);
    WeatherResponseParser.3 local3 = new WeatherResponseParser.3(paramWeatherElement, localWeatherElement3);
    localElement3.setEndElementListener(local3);
    WeatherElement localWeatherElement4 = new WeatherElement("Forecast");
    android.sax.Element localElement4 = localElement3.getChild("Forecast");
    setAttribute(localWeatherElement4, localElement4);
    WeatherResponseParser.4 local4 = new WeatherResponseParser.4(localWeatherElement3, localWeatherElement4);
    localElement4.setEndElementListener(local4);
    WeatherElement localWeatherElement5 = new WeatherElement("DaytimeForecast");
    android.sax.Element localElement5 = localElement4.getChild("DaytimeForecast");
    setAttribute(localWeatherElement5, localElement5);
    WeatherResponseParser.5 local5 = new WeatherResponseParser.5(localWeatherElement4, localWeatherElement5);
    localElement5.setEndElementListener(local5);
    WeatherElement localWeatherElement6 = new WeatherElement("DayNighttimeForecast");
    android.sax.Element localElement6 = localElement5.getChild("DayNighttimeForecast");
    setAttribute(localWeatherElement6, localElement6);
    WeatherResponseParser.6 local6 = new WeatherResponseParser.6(localWeatherElement5, localWeatherElement6);
    localElement6.setEndElementListener(local6);
    WeatherElement localWeatherElement7 = new WeatherElement("NighttimeForecast");
    android.sax.Element localElement7 = localElement4.getChild("NighttimeForecast");
    setAttribute(localWeatherElement7, localElement7);
    WeatherResponseParser.7 local7 = new WeatherResponseParser.7(localWeatherElement4, localWeatherElement7);
    localElement7.setEndElementListener(local7);
    WeatherElement localWeatherElement8 = new WeatherElement("DayNighttimeForecast");
    android.sax.Element localElement8 = localElement7.getChild("DayNighttimeForecast");
    setAttribute(localWeatherElement8, localElement8);
    WeatherResponseParser.8 local8 = new WeatherResponseParser.8(localWeatherElement7, localWeatherElement8);
    localElement8.setEndElementListener(local8);
    WeatherElement localWeatherElement9 = new WeatherElement("Images");
    android.sax.Element localElement9 = localRootElement.getChild("Images");
    setAttribute(localWeatherElement9, localElement9);
    WeatherResponseParser.9 local9 = new WeatherResponseParser.9(paramWeatherElement, localWeatherElement9);
    localElement9.setEndElementListener(local9);
    WeatherElement localWeatherElement10 = new WeatherElement("Images");
    android.sax.Element localElement10 = localElement9.getChild("Images");
    setAttribute(localWeatherElement10, localElement10);
    WeatherResponseParser.10 local10 = new WeatherResponseParser.10(localWeatherElement9, localWeatherElement10);
    localElement10.setEndElementListener(local10);
    WeatherElement localWeatherElement11 = new WeatherElement("MoonPhases");
    android.sax.Element localElement11 = localRootElement.getChild("MoonPhases");
    setAttribute(localWeatherElement11, localElement11);
    WeatherResponseParser.11 local11 = new WeatherResponseParser.11(paramWeatherElement, localWeatherElement11);
    localElement11.setEndElementListener(local11);
    WeatherElement localWeatherElement12 = new WeatherElement("MoonPhase");
    android.sax.Element localElement12 = localElement11.getChild("MoonPhase");
    setAttribute(localWeatherElement12, localElement12);
    WeatherResponseParser.12 local12 = new WeatherResponseParser.12(localWeatherElement11, localWeatherElement12);
    localElement12.setEndElementListener(local12);
    WeatherElement localWeatherElement13 = new WeatherElement("Units");
    android.sax.Element localElement13 = localRootElement.getChild("Units");
    setAttribute(localWeatherElement13, localElement13);
    WeatherResponseParser.13 local13 = new WeatherResponseParser.13(paramWeatherElement, localWeatherElement13);
    localElement13.setEndElementListener(local13);
    WeatherElement localWeatherElement14 = new WeatherElement("Units");
    android.sax.Element localElement14 = localElement13.getChild("Units");
    setAttribute(localWeatherElement14, localElement14);
    WeatherResponseParser.14 local14 = new WeatherResponseParser.14(localWeatherElement13, localWeatherElement14);
    localElement14.setEndElementListener(local14);
    WeatherElement localWeatherElement15 = new WeatherElement("WeatherLocation");
    android.sax.Element localElement15 = localRootElement.getChild("WeatherLocation");
    setAttribute(localWeatherElement15, localElement15);
    WeatherResponseParser.15 local15 = new WeatherResponseParser.15(paramWeatherElement, localWeatherElement15);
    localElement15.setEndElementListener(local15);
    WeatherElement localWeatherElement16 = new WeatherElement("Location");
    android.sax.Element localElement16 = localElement15.getChild("Location");
    setAttribute(localWeatherElement16, localElement16);
    WeatherResponseParser.16 local16 = new WeatherResponseParser.16(localWeatherElement15, localWeatherElement16);
    localElement16.setEndElementListener(local16);
    return localRootElement;
  }
}

/* Location:           C:\Users\nigeluno\Desktop\APK2Java\tools\classes-dex2jar.jar
 * Qualified Name:     com.vlingo.core.internal.weather.WeatherResponseParser
 * JD-Core Version:    0.6.0
 */