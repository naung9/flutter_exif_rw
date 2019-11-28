import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';

class FlutterExifRw {
  static const MethodChannel _channel =
      const MethodChannel('flutter_exif_rw');

  static Future<Map<dynamic,dynamic>> readExifData(Uint8List fileData, List<String> exifKeys) async {
    return await _channel.invokeMethod('readExifData', [fileData, exifKeys]);
  }

  static Future<Uint8List> writeExifData(Uint8List fileData, Map<String,dynamic> exifData) async{
    return await _channel.invokeMethod('writeExifData', [fileData, exifData]);
  }

  static const String GPS_LATLON = "GPS_LATLON";
  static const String TAG_F_NUMBER = "FNumber";
  static const String TAG_APERTURE_VALUE = "ApertureValue";
  static const String TAG_ARTIST = "Artist";
  static const String TAG_BITS_PER_SAMPLE = "BitsPerSample";
  static const String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
  static const String TAG_CFA_PATTERN = "CFAPattern";
  static const String TAG_COLOR_SPACE = "ColorSpace";
  static const String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
  static const String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
  static const String TAG_COMPRESSION = "Compression";
  static const String TAG_CONTRAST = "Contrast";
  static const String TAG_COPYRIGHT = "Copyright";
  static const String TAG_CUSTOM_RENDERED = "CustomRendered";
  static const String TAG_DATETIME = "DateTime";
  static const String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
  static const String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
  static const String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
  static const String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
  static const String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
  static const String TAG_DNG_VERSION = "DNGVersion";
  static const String TAG_EXIF_VERSION = "ExifVersion";
  static const String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
  static const String TAG_EXPOSURE_INDEX = "ExposureIndex";
  static const String TAG_EXPOSURE_MODE = "ExposureMode";
  static const String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
  static const String TAG_EXPOSURE_TIME = "ExposureTime";
  static const String TAG_FILE_SOURCE = "FileSource";
  static const String TAG_FLASH = "Flash";
  static const String TAG_FLASHPIX_VERSION = "FlashpixVersion";
  static const String TAG_FLASH_ENERGY = "FlashEnergy";
  static const String TAG_FOCAL_LENGTH = "FocalLength";
  static const String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
  static const String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
  static const String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
  static const String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
  static const String TAG_GAIN_CONTROL = "GainControl";
  static const String TAG_GPS_ALTITUDE = "GPSAltitude";
  static const String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
  static const String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
  static const String TAG_GPS_DATESTAMP = "GPSDateStamp";
  static const String TAG_GPS_DEST_BEARING = "GPSDestBearing";
  static const String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
  static const String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
  static const String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
  static const String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
  static const String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
  static const String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
  static const String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
  static const String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
  static const String TAG_GPS_DOP = "GPSDOP";
  static const String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
  static const String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
  static const String TAG_GPS_LATITUDE = "GPSLatitude";
  static const String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
  static const String TAG_GPS_LONGITUDE = "GPSLongitude";
  static const String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
  static const String TAG_GPS_MAP_DATUM = "GPSMapDatum";
  static const String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
  static const String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
  static const String TAG_GPS_SATELLITES = "GPSSatellites";
  static const String TAG_GPS_SPEED = "GPSSpeed";
  static const String TAG_GPS_SPEED_REF = "GPSSpeedRef";
  static const String TAG_GPS_STATUS = "GPSStatus";
  static const String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
  static const String TAG_GPS_TRACK = "GPSTrack";
  static const String TAG_GPS_TRACK_REF = "GPSTrackRef";
  static const String TAG_GPS_VERSION_ID = "GPSVersionID";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_LENGTH = "ImageLength";
  static const String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";
  static const String TAG_IMAGE_DESCRIPTION = "ImageDescription";

}
