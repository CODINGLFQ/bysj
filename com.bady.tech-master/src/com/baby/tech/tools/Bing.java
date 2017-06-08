package com.baby.tech.tools;



/**
 * @author dux(duxionggis@126.com)
 */
public strictfp class Bing {
 public static int TileSize = 256;
 private static double MinLat = -85.0511287798;
 private static double MaxLat = 85.0511287798;
 private static double MinLong = -180;
 private static double MaxLong = 180;
 public static double Radius = 6378137;

 private static double clip(double n, double minValue, double maxValue) {
  return Math.min(Math.max(n, minValue), maxValue);
 }

 public static int mapSize(int zoom) {
  return TileSize << zoom;
 }

 public static double groundResolution(double latitude, int zoom) {
  latitude = clip(latitude, MinLat, MaxLat);
  return Math.cos(latitude * Math.PI / 180) * 2 * Math.PI * Radius / mapSize(zoom);
 }

 public static double mapScale(double latitude, int levelOfDetail, int screenDpi) {
  return groundResolution(latitude, levelOfDetail) * screenDpi / 0.0254;
 }

 public static EarthPos degreeToPixel(double lat, double lon, int zoom) {
  lat = clip(lat, MinLat, MaxLat);
  lon = clip(lon, MinLong, MaxLong);

  double x = (lon + 180) / 360;
  double sinLatitude = Math.sin(lat * Math.PI / 180);
  double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);
  EarthPos mEarthPos = new EarthPos();
  int mapSize = mapSize(zoom);
  double pixelX = clip(x * mapSize, 0, mapSize - 1);
  double pixelY = clip(y * mapSize, 0, mapSize - 1);
  mEarthPos.setX(pixelX);
  mEarthPos.setY(pixelY);
  return mEarthPos;
 }

 public static EarthPos pixelToDegree(double xPixel, double yPixel, int zoom) {
  double mapSize = mapSize(zoom);
  double x = (clip(xPixel, 0, mapSize - 1) / mapSize) - 0.5;
  double y = 0.5 - (clip(yPixel, 0, mapSize - 1) / mapSize);
  double latitude = 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
  double longitude = 360 * x;
  return new EarthPos(longitude, latitude);
 }

 public static EarthPos pixelToTile(double xPixel, double yPixel) {
  EarthPos mEarthPos = new EarthPos();
  mEarthPos.setX((int) Math.floor(xPixel * 1.0 / TileSize));
  mEarthPos.setY((int) Math.floor(yPixel * 1.0 / TileSize));
  return mEarthPos;
 }

 public static EarthPos tileToPixel(int xTile, int yTile) {
  return new EarthPos(xTile * TileSize * 1.0, yTile * TileSize * 1.0);
 }

 public static String tileToQuadKey(int xTile, int yTile, int zoom) {
  StringBuffer quadKey = new StringBuffer();
  for (int i = zoom; i > 0; i--) {
   long mask = 1 << (i - 1);
   int cell = 0;
   if ((xTile & mask) != 0) {
    cell++;
   }
   if ((yTile & mask) != 0) {
    cell += 2;
   }
   quadKey.append(cell);
  }
  return quadKey.toString();
 }

 public static EarthPos quadKeyToTile(String quadKey) {
  int tileX = 0, tileY = 0;
  int levelOfDetail = quadKey.length();
  for (int i = levelOfDetail; i > 0; i--) {
   int mask = 1 << (i - 1);
   switch (quadKey.charAt(levelOfDetail - i)) {
   case '0':
    break;
   case '1':
    tileX |= mask;
    break;
   case '2':
    tileY |= mask;
    break;
   case '3':
    tileX |= mask;
    tileY |= mask;
    break;
   default:
    break;
   }
  }
  return new EarthPos(tileX, tileY);
 }

 public static EarthPos degreeToMercator(double lat, double lon) {
  lat = clip(lat, MinLat, MaxLat) * Math.PI / 180;
  lon = clip(lon, MinLong, MaxLong) * Math.PI / 180;
  double sinLatitude = Math.sin(lat);
  double xMeters = Radius * lon;
  double yMeters = Radius / 2 * Math.log((1 + sinLatitude) / (1 - sinLatitude));
  return new EarthPos(xMeters, yMeters);
 }

 public static EarthPos mercatorToDegree(double y, double x) {
  double lon = (x * 180) / (Radius * Math.PI);
  double lat = Math.exp(y * 2 / Radius);
  lat = Math.asin((lat - 1) / (lat + 1)) * 180 / Math.PI;
  return new EarthPos(lon, lat);
 }

}
