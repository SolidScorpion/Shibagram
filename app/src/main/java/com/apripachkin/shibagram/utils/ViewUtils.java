package com.apripachkin.shibagram.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Antony on 10.10.2016.
 */

public class ViewUtils {
    public static void setEmptyTypeFace(TextView textView) {
        Context context = textView.getContext();
        Typeface asset = Typeface.createFromAsset(context.getAssets(), "fonts/IndieFlower.ttf");
        setTypeFace(asset, textView);
    }

    public static void setPhotoTitleTypeFace(TextView textView) {
        AssetManager assets = textView.getContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/AmaticSC-Bold.ttf");
        setTypeFace(typeface, textView);
    }

    public static void setDescriptionTypeFace(TextView textView) {
        AssetManager assets = textView.getContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/GloriaHallelujah.ttf");
        setTypeFace(typeface, textView);
    }

    public static void setTitleTypeFace(TextView textView) {
        AssetManager assets = textView.getContext().getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "fonts/GloriaHallelujah.ttf");
        setTypeFace(fromAsset, textView);
    }

    private static void setTypeFace(Typeface typeFace, TextView textView) {
        textView.setTypeface(typeFace);
    }
}
