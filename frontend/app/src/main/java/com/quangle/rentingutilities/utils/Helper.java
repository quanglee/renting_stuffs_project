package com.quangle.rentingutilities.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.quangle.rentingutilities.R;

import java.util.List;

import androidx.appcompat.app.AlertDialog;

public class Helper {

    public static void errorsInForm(Context c) {
        Toast.makeText(c, c.getString(R.string.someErrors), Toast.LENGTH_SHORT).show();
    }

    //Hide keyboard for fragment
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //Hide keyboard for activity
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSelectDialog(Activity activity, EditText editText, String title, List<String> optionList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppTheme_DialogTheme);
        builder.setTitle(title);

        //list of items
        int checkedItem = 0;
        if (!editText.getText().toString().equals("")) {
            int indexOf = optionList.indexOf(editText.getText().toString());
            checkedItem = indexOf != -1 ? indexOf : 0;
        }
        builder.setSingleChoiceItems(optionList.toArray(new String[0]), checkedItem, null);

        builder.setPositiveButton(activity.getString(android.R.string.ok), (dialog, which) -> {
            int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
            editText.setText(optionList.get(position));
        });

        builder.setNegativeButton(activity.getString(android.R.string.cancel), null);

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public static CircleOptions configureMapCircle(LatLng location, Resources resources) {
        return new CircleOptions()
                .center(location)
                .strokeWidth(0)
                .fillColor(resources.getColor(R.color.colorAccentA35))
                .radius(300); // In meters
    }

}
