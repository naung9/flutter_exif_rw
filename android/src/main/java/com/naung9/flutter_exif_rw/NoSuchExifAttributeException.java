package com.naung9.flutter_exif_rw;

public class NoSuchExifAttributeException extends Exception {
    public NoSuchExifAttributeException() {

    }

    public NoSuchExifAttributeException(String message) {
        super(message);
    }

    public NoSuchExifAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchExifAttributeException(Throwable cause) {
        super(cause);
    }
}
