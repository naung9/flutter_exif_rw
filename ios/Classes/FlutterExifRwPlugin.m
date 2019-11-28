#import "FlutterExifRwPlugin.h"
#import <flutter_exif_rw/flutter_exif_rw-Swift.h>

@implementation FlutterExifRwPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterExifRwPlugin registerWithRegistrar:registrar];
}
@end
