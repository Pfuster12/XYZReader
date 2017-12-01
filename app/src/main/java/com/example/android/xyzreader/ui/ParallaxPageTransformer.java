package com.example.android.xyzreader.ui;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.android.xyzreader.R;

/**
 * Created by pfust on 01/12/2017.
 */

/**
 * Custom Page Transformet to implement parallax animations to the background image.
 */
public class ParallaxPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        ImageView blurImage;
        // Grab the FAB which is in both sizes.
        FloatingActionButton mFAB = page.findViewById(R.id.share_fab);

        int pageWidth = page.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(1);
        } else if (position <= 1) { // [-1,1]
            if (page.getContext().getResources().getBoolean(R.bool.tablet)) {
                // Grab the image background
                blurImage = page.findViewById(R.id.blur_background);
                blurImage.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed
                mFAB.setRotation(-position * (pageWidth / 4));
            } else {
                // We are in a phone.
                ImageView mImage = page.findViewById(R.id.image);
                mImage.setTranslationX(-position * (pageWidth / 2)); //Half the normal speed
                mFAB.setRotation(-position * (pageWidth / 4));
            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(1);
        }
    }
}
