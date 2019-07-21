package com.quangle.rentingutilities.utils;

import android.content.Context;
import com.google.android.material.textfield.TextInputLayout;
import android.util.Patterns;

import com.quangle.rentingutilities.R;

import java.util.regex.Pattern;

public class Validation {

    private Context c;

    public Validation(Context c) {
        this.c = c;
    }

    public static void setError(TextInputLayout field, String error) {
        field.setError(error);
    }

    public boolean required(TextInputLayout field, String value) {
        boolean result = false;
        if (value.length() == 0)
            setError(field, c.getString(R.string.required));
        else {
            setError(field, null);
            result = true;
        }
        return result;
    }

    public boolean isValidEmail(TextInputLayout field, String value) {
        boolean result = false;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        if (!emailPattern.matcher(value).matches())
            setError(field, c.getString(R.string.invalidEmail));
        else {
            setError(field, null);
            result = true;
        }
        return result;
    }

}
