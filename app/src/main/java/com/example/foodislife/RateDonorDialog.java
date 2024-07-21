package com.example.foodislife;

import static androidx.core.content.ContextCompat.startActivity;

import static java.lang.System.exit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.lang.Cloneable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class RateDonorDialog extends Dialog {

    private float userRate = 0;

    public RateDonorDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rate_donor_dialog_layout);

        final AppCompatButton rateNowBtn = findViewById(R.id.rateMeNowBtn);
        final AppCompatButton laterBtn = findViewById(R.id.LaterBtn);
        final RatingBar ratingBar = findViewById(R.id.RateBar);
        final ImageView IconImage = findViewById(R.id.ratingIcon);

        rateNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put code here
                Toast.makeText(getContext(), "Ratings was sent to Donors", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide rating dialog
                dismiss();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating <= 1) {
                    IconImage.setImageResource(R.drawable.one);
                } else if (rating <= 2) {
                    IconImage.setImageResource(R.drawable.two);
                } else if (rating <= 3) {
                    IconImage.setImageResource(R.drawable.three);
                } else if (rating <= 4) {
                    IconImage.setImageResource(R.drawable.four);
                } else if (rating <= 5) {
                    IconImage.setImageResource(R.drawable.five);
                }
                // animate emoji images
                animateImage(IconImage);
                // select rating by user
                userRate = rating;
            }
        });
    }
    private void animateImage(ImageView ratingImage){

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }
}
