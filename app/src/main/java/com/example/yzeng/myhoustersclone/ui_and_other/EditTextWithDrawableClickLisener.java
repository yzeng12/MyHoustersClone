package com.example.yzeng.myhoustersclone.ui_and_other;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.yzeng.myhoustersclone.R;

public class EditTextWithDrawableClickLisener extends AppCompatEditText {

    Drawable mlocationImage;

    public EditTextWithDrawableClickLisener(Context context) {
        super(context);
        init();
    }

    public EditTextWithDrawableClickLisener(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithDrawableClickLisener(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mlocationImage = ResourcesCompat.getDrawable(getResources(),
                R.drawable.ic_location, null);
        // TODO: If the clear (X) button is tapped, clear the text.

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {

                    float clearButtonEnd;  // Used for RTL languages
                    boolean isClearButtonClicked = false;

                        // If RTL, get the end of the button on the left side.
                        clearButtonEnd = mlocationImage
                                .getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }


                    // TODO: Check for actions if the button is tapped.
                    if (isClearButtonClicked) {
                        // Check for ACTION_DOWN (always occurs before ACTION_UP).
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            // Switch to the black version of clear button.
                            mlocationImage =
                                    ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.ic_cleartext, null);
                            showClearButton();
                        }
                        // Check for ACTION_UP.
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            // Switch to the opaque version of clear button.
                            mlocationImage =
                                    ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.ic_cleartext, null);
                            // Clear the text and hide the clear button.
                            getText().clear();
                            hideClearButton();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearButton();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showClearButton();
            }
        });
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null,                      // Start of text.
                        null,               // Above text.
                        mlocationImage,  // End of text.
                        null);              // Below text.
    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null,             // Start of text.
                        null,      // Above text.
                        null,      // End of text.
                        null);     // Below text.
    }

}
