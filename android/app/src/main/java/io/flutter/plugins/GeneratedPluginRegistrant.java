package io.flutter.plugins;

import io.flutter.plugin.common.PluginRegistry;
import com.naung9.flutter_exif_rw.FlutterExifRwPlugin;

/**
 * Generated file. Do not edit.
 */
public final class GeneratedPluginRegistrant {
  public static void registerWith(PluginRegistry registry) {
    if (alreadyRegisteredWith(registry)) {
      return;
    }
    FlutterExifRwPlugin.registerWith(registry.registrarFor("com.naung9.flutter_exif_rw.FlutterExifRwPlugin"));
  }

  private static boolean alreadyRegisteredWith(PluginRegistry registry) {
    final String key = GeneratedPluginRegistrant.class.getCanonicalName();
    if (registry.hasPlugin(key)) {
      return true;
    }
    registry.registrarFor(key);
    return false;
  }
}
