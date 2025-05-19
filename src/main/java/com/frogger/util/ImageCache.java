package com.frogger.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageCache {
    private static final Map<String, Image> cache = new HashMap<>();

    public static Image get(String path) {
        return cache.get(path);
    }

    public static void preload(Class<?> clazz) {
        load(clazz, "/images/grasstile.png");
        load(clazz, "/images/roadtile.png");
        load(clazz, "/images/bluecar.png");
        load(clazz, "/images/redcar.png");
        load(clazz, "/images/tree.png");
        load(clazz, "/images/frog.png");
        load(clazz, "/images/turtle.png");
        load(clazz, "/images/Background.png", 1920, 1080, true, true);
    }

    private static void load(Class<?> clazz, String path) {
        cache.put(path, new Image(Objects.requireNonNull(clazz.getResourceAsStream(path))));
    }

    private static void load(Class<?> clazz, String path, double w, double h, boolean preserve, boolean smooth) {
        cache.put(path, new Image(Objects.requireNonNull(clazz.getResourceAsStream(path)), w, h, preserve, smooth));
    }
}