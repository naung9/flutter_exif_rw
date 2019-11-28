package com.naung9.flutter_exif_rw;

import android.content.Context;

import androidx.exifinterface.media.ExifInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterExifRwPlugin */
public class FlutterExifRwPlugin implements MethodCallHandler {

    private Context context;
    private MethodChannel channel;
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
      FlutterExifRwPlugin exifRwPlugin = new FlutterExifRwPlugin();
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_exif_rw");
    exifRwPlugin.context = registrar.context();
    exifRwPlugin.channel = channel;
    exifRwPlugin.channel.setMethodCallHandler(exifRwPlugin);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("readExifData")) {
      try{
        //Retrieving Method Arguments
        ArrayList<Object> args = (ArrayList<Object>) call.arguments;
        //Asserting Number of Arguments given
        if(args.size() != 2){
          result.error("readExifData method Requires two positional arguments. Given "+args.size(), null, null);
        }else {
          result.success(readExifData((byte[])args.get(0), (ArrayList<String>)args.get(1)));
        }
        //Asserting The Type of Arguments Given
      }catch (ClassCastException e){
        result.error("This Method Requires (Uint8List, List<String>) as Arguments", null,null);
      }catch (NoSuchExifAttributeException | IOException e){
        result.error(e.getMessage(), null, null);
      }

    }
    else if(call.method.equals("writeExifData")){
      try{
        //Retrieving Method Arguments
        ArrayList<Object> args = (ArrayList<Object>) call.arguments;
        //Asserting Number of Arguments given
        if(args.size() != 2){
          result.error("writeExifData method requires two positional arguments. Given "+args.size(), null, null);
        }else {
          result.success(writeExifData((byte[])args.get(0), (Map<String,Object>) args.get(1)));
        }
        //Asserting The Type of Arguments Given
      }catch (ClassCastException e){
        result.error("This Method Requires (Uint8List, Map<String,dynamic>) as Arguments.", null,null);
      }catch (Exception e){
        result.error(e.getMessage(), null, null);
      }
    }
    else {
      result.notImplemented();
    }
  }


  private Map<String, Object> readExifData(byte[] data, List<String> exifKeys) throws NoSuchExifAttributeException, IOException{
    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    Map<String, Object> exifDataMap = new HashMap<>();
    try {
      ExifInterface exifInterface = new ExifInterface(bais);
      for(String key : exifKeys){

        switch (key){
          case ExifInterface.TAG_GPS_ALTITUDE : exifDataMap.put(key, exifInterface.getAltitude(0));break;
          case "GPS_LATLON": exifDataMap.put(key, exifInterface.getLatLong()==null ? new double[]{0,0}:exifInterface.getLatLong());break;
          default :
            if(!exifInterface.hasAttribute(key))throw new NoSuchExifAttributeException("There is no such exif attribute \""+key+"\"");
            String attribute = exifInterface.getAttribute(key);
            exifDataMap.put(key, attribute == null ? "":attribute);
            break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }
    return exifDataMap;
  }

  @SuppressWarnings("unchecked")
  private byte[] writeExifData(byte[] data, Map<String, Object> exifData) throws Exception{
    ByteArrayInputStream bais = new ByteArrayInputStream(data);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    File tempFile = null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
        File tempDir = context.getCacheDir();
        Log.i("DIRECTORY", tempDir.getAbsolutePath());
        tempFile = File.createTempFile("temp", "jpg", tempDir);
    }
    copyInputStreamToFile(bais, tempFile);
    try {
      ExifInterface exifInterface = new ExifInterface(tempFile);
      for(Map.Entry<String,Object> entry : exifData.entrySet()){
        switch (entry.getKey()){
          case "GPS_LATLON" :
            try{
              List<Double> latLong = (ArrayList<Double>)entry.getValue();
              if(latLong.size() == 2){
                  exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE, dec2DMS(latLong.get(0)));
                  exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, dec2DMS(latLong.get(1)));
//                exifInterface.setLatLong(latLong.get(0), latLong.get(1));
              }
              else {
                tempFile.delete();
                throw new InvalidDataException("Invalid Latitude And Longitude Data. Requires Double Array");
              }
            }catch (ClassCastException e){
              e.printStackTrace();
              tempFile.delete();
              throw new InvalidDataException("Invalid Latitude And Longitude Data. Requires Double Array");
            }
            break;
          case ExifInterface.TAG_GPS_ALTITUDE:
            try{
              exifInterface.setAltitude((double)entry.getValue());
            }catch (ClassCastException e){
              e.printStackTrace();
              tempFile.delete();
              throw new InvalidDataException("Invalid Altitude Data. Requires Double");
            }
            break;
          default:
            if(!exifInterface.hasAttribute(entry.getKey())){
              tempFile.delete();
              throw new NoSuchExifAttributeException("There is no such exif attribute \""+entry.getKey()+"\"");
            }
            try{
              exifInterface.setAttribute(entry.getKey(), (String) entry.getValue());
            }catch (ClassCastException e){
              e.printStackTrace();
              tempFile.delete();
              throw new InvalidDataException(String.format("Invalid Attribute Data. Attribute \"%s\" Requires String",entry.getKey()));
            }
        }
      }
      exifInterface.saveAttributes();
      if(exifInterface.getLatLong()==null){
          tempFile.delete();
          throw new ExifWriteException("Failed To Write Lat Long Data");
      }
      FileInputStream fis = new FileInputStream(tempFile);
      byte[] buf = new byte[2048];
      int n;
      while ((n = fis.read(buf)) > 0) {
        baos.write(buf, 0, n);
      }
      fis.close();
      tempFile.delete();
      bais.close();
      byte[] writtenBytes = baos.toByteArray();
      baos.flush();
      baos.close();
      tempFile.delete();
      return writtenBytes;
    } catch (IOException e) {
      tempFile.delete();
      e.printStackTrace();
      throw e;
    }
  }

  private void copyInputStreamToFile(InputStream inputStream, File file)
          throws IOException {

    try (FileOutputStream outputStream = new FileOutputStream(file)) {

      int read;
      byte[] bytes = new byte[1024];

      while ((read = inputStream.read(bytes)) != -1) {
        outputStream.write(bytes, 0, read);
      }
      outputStream.flush();
      outputStream.close();
      inputStream.close();
    }
  }

  private String dec2DMS(double coord) {
    coord = coord > 0 ? coord : -coord;
    String sOut = Integer.toString((int)coord) + "/1,";
    coord = (coord % 1) * 60;
    sOut = sOut + Integer.toString((int)coord) + "/1,";
    coord = (coord % 1) * 60000;
    sOut = sOut + Integer.toString((int)coord) + "/1000";
    return sOut;
  }

//    @Override
//    public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
//        channel =
//                new MethodChannel(
//                        flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_exif_rw");
//        context = flutterPluginBinding.getApplicationContext();
//        channel.setMethodCallHandler(this);
//    }
//
//    @Override
//    public void onDetachedFromEngine(FlutterPluginBinding flutterPluginBinding) {
//        channel.setMethodCallHandler(null);
//        channel = null;
//    }
}
