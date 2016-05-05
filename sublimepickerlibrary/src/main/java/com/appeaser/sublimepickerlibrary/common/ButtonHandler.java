package com.appeaser.sublimepickerlibrary.common;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.appeaser.sublimepickerlibrary.R;
import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
/**
 * Created by Admin on 15/02/2016.
 */
public class ButtonHandler implements View.OnClickListener {

    private ButtonLayout mPortraitButtonHandler;

    // Can be 'android.widget.Button' or 'android.widget.ImageView'
    View mPositiveButtonDP, mPositiveButtonTP, mNegativeButtonDP, mNegativeButtonTP;
    // 'Button' used for switching between 'SublimeDatePicker'
    // and 'SublimeTimePicker'. Also displays the currently
    // selected date/time depending on the visible picker
    Button mSwitcherButtonDP, mSwitcherButtonTP;

    Callback mCallback;

    public ButtonHandler(@NonNull SublimePicker sublimePicker) {
        mPortraitButtonHandler = (ButtonLayout) sublimePicker.findViewById(R.id.button_layout);
    }

    /**
     * Initializes state for this layout
     *
     * @param switcherRequired Whether the switcher button needs
     *                         to be shown.
     * @param callback         Callback to 'SublimePicker'
     */
    public void applyOptions(boolean switcherRequired, @NonNull Callback callback) {
        mCallback = callback;
        mPortraitButtonHandler.applyOptions(switcherRequired, callback);
    }

    // Returns whether switcher button is being used in this layout
    public boolean isSwitcherButtonEnabled() {
        return false;
    }

    // Used when the pickers are switched
    public void updateSwitcherText(@NonNull SublimeOptions.Picker displayedPicker, CharSequence text) {
        mPortraitButtonHandler.updateSwitcherText(text);
    }

    // Disables the positive button as and when the user selected options
    // become invalid.
    public void updateValidity(boolean valid) {
        mPortraitButtonHandler.updateValidity(valid);
    }

    @Override
    public void onClick(View v) {
        if (v == mPositiveButtonDP || v == mPositiveButtonTP) {
            mCallback.onOkay();
        } else if (v == mNegativeButtonDP || v == mNegativeButtonTP) {
            mCallback.onCancel(false);
        } else if (v == mSwitcherButtonDP || v == mSwitcherButtonTP) {
            mCallback.onSwitch();
        }
    }

    public interface Callback {
        void onOkay();
        void onCancel(boolean removeReminder);
        void onSwitch();
    }
}
