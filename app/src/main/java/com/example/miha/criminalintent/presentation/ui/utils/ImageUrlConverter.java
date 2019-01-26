package com.example.miha.criminalintent.presentation.ui.utils;

public class ImageUrlConverter {
    private static final String url = "http://";
    private static final String url_secret = "https://";

    public static String getRealAddressFile(String filename) {
        if (filename.startsWith(url) || filename.startsWith(url_secret)) {
            return filename;
        } else {
            return "file://" + filename;
        }
    }
}
