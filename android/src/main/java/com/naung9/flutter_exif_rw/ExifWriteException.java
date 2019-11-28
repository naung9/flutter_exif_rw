package com.naung9.flutter_exif_rw;

public class ExifWriteException extends Exception {
    public ExifWriteException() {
    }

    public ExifWriteException(String message) {
        super(message);
    }

    public ExifWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExifWriteException(Throwable cause) {
        super(cause);
    }
}
