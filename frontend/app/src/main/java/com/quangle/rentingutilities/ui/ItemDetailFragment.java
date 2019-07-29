package com.quangle.rentingutilities.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.quangle.rentingutilities.R;
import com.quangle.rentingutilities.core.model.Auth;
import com.quangle.rentingutilities.core.model.Item;
import com.quangle.rentingutilities.networking.NetworkResource;
import com.quangle.rentingutilities.utils.Helper;
import com.quangle.rentingutilities.utils.MySharedPreferences;
import com.quangle.rentingutilities.utils.Validation;
import com.quangle.rentingutilities.viewmodel.ItemViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ItemDetailFragment extends BaseFragment {

    public static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1000;
    private static final String ARG_ITEM = "item";
    private static final String ARG_CAN_EDIT = "canEdit";
    private static final int READ_REQUEST_CODE = 42;
    private Item item;
    private boolean canEdit = false;
    private ImageView imageView;
    private String imageAbsolutePath = null;
    private Auth auth;
    private File file;
    private RatingBar ratingBar;
    private EditText etName, etDescription, etPrice, etCategory, etCondition, etTags;

    public static ItemDetailFragment newInstance(Item item, boolean canEdit) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, item);
        args.putSerializable(ARG_CAN_EDIT, canEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = (Item) getArguments().getSerializable(ARG_ITEM);
            canEdit = getArguments().getBoolean(ARG_CAN_EDIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        Validation validation = new Validation(getActivity());
        ratingBar = view.findViewById(R.id.ratingBar);
        imageView = view.findViewById(R.id.imageView);
        TextInputLayout tiName = view.findViewById(R.id.tiName);
        etName = view.findViewById(R.id.etName);
        TextInputLayout tiDescription = view.findViewById(R.id.tiDescription);
        etDescription = view.findViewById(R.id.etDescription);
        TextInputLayout tiPrice = view.findViewById(R.id.tiPrice);
        etPrice = view.findViewById(R.id.etPrice);
        TextInputLayout tiCategory = view.findViewById(R.id.tiCategory);
        etCategory = view.findViewById(R.id.etCategory);
        TextInputLayout tiCondition = view.findViewById(R.id.tiCondition);
        etCondition = view.findViewById(R.id.etCondition);
        TextInputLayout tiTags = view.findViewById(R.id.tiTags);
        etTags = view.findViewById(R.id.etTags);
        Button btnBook = view.findViewById(R.id.btnBook);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        Button btnSetImage = view.findViewById(R.id.btnSetImage);
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        auth = MySharedPreferences.getAuth(getActivity());

        setFields();

        if (!canEdit) {
            etName.setEnabled(false);
            etDescription.setEnabled(false);
            etPrice.setEnabled(false);
            etCategory.setEnabled(false);
            etCondition.setEnabled(false);
            etTags.setEnabled(false);
            btnBook.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            btnSetImage.setVisibility(View.GONE);
        } else {
            checkUserPermission();
            btnBook.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            btnSetImage.setVisibility(View.VISIBLE);
            // Is a new Item
            if (item.getId() == -1) {
                ratingBar.setVisibility(View.GONE);
            }
        }

        etCondition.setOnClickListener(v -> {
            Helper.showSelectDialog(getActivity(), etCondition,
                    getString(R.string.condition), Item.CONDITION.valuesList());
        });

        etCategory.setOnClickListener(v -> {
            Helper.showSelectDialog(getActivity(), etCategory,
                    getString(R.string.category), Item.CATEGORY.valuesList());
        });

        btnSetImage.setOnClickListener(v -> {
            //Create an Intent with action as ACTION_PICK
            Intent intent = new Intent(Intent.ACTION_PICK);
            // Sets the type as image/*. This ensures only components of type image are selected
            intent.setType("image/*");
            //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
            // Launching the Intent
            startActivityForResult(intent, READ_REQUEST_CODE);
        });

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BookingActivity.class);
            intent.putExtra("fragmentOptions", "processBooking");
            BookingActivity.setItem(intent, item);
            getActivity().startActivity(intent);
        });

        btnSubmit.setOnClickListener(v -> {
            Helper.hideKeyboard(getContext(), getView());
            boolean isNameValid = validation.required(tiName, etName.getText().toString());
            boolean isDescriptionValid = validation.required(tiDescription, etDescription.getText().toString());
            boolean isPriceValid = validation.required(tiPrice, etPrice.getText().toString());
            boolean isImageValid = true;
            if (item.getId() == -1 && imageAbsolutePath == null) {
                Toast.makeText(getContext(), "You need to upload an image", Toast.LENGTH_SHORT).show();
                isImageValid = false;
            }

            if (isNameValid && isDescriptionValid && isPriceValid && isImageValid) {
                btnSubmit.setEnabled(false);
                showProgressBar();

                setItem();
                MultipartBody.Part filePart = null;
                if (imageAbsolutePath != null) {
                    file = new File(imageAbsolutePath);
                    String extension = MimeTypeMap.getFileExtensionFromUrl(imageAbsolutePath);
                    String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    RequestBody requestBody = RequestBody.create(MediaType.parse(type), file);
                    filePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                }

                LiveData<NetworkResource<Item>> networkResourceLiveData;
                if (item.getId() == -1)
                    networkResourceLiveData = itemViewModel.createItem(filePart, item.toHashMap());
                else
                    networkResourceLiveData = itemViewModel.editItem(filePart, Integer.toString(item.getId()), item.toHashMap());
                //    networkResourceLiveData = userViewModel.edit(auth, user.toHashMap());
                networkResourceLiveData.observe(this, itemNetworkResource -> {
                   btnSubmit.setEnabled(true);
                   hideProgressBar();
                   if (itemNetworkResource.data != null) {
                       if (item.getId() == -1) {
                           ((ItemActivity) getActivity()).updateViews = true;
                           getActivity().onBackPressed();
                       }
                       else {
                          Toast.makeText(getActivity(), "Item successfully edited", Toast.LENGTH_SHORT).show();
                          item = itemNetworkResource.data;
                          setFields();
                          ((ItemActivity) getActivity()).updateViews = true;
                       }
                   }
                });
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case READ_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    imageAbsolutePath = cursor.getString(columnIndex);
                    cursor.close();
                    imageView.setImageURI(selectedImage);
                    break;
            }
        }
    }

    public void checkUserPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            return;
        }
    }

    public void setFields() {
        ratingBar.setRating((float) item.getAverageRating());
        if (!item.getImageURL().equals(""))
            Picasso.get().load(item.getImageURL()).resize(1000, 500).onlyScaleDown().placeholder(R.mipmap.ic_launcher).into(imageView);
        etName.setText(item.getName());
        etDescription.setText(item.getDescription());
        etPrice.setText(Double.toString(item.getPrice()));
        etCategory.setText(item.getCategory());
        etCondition.setText(item.getCondition());
        etTags.setText(item.getTags());
    }

    public void setItem() {
        item.setName(etName.getText().toString());
        item.setDescription(etDescription.getText().toString());
        item.setCondition(etCondition.getText().toString());
        item.setCategory(etCategory.getText().toString());
        item.setPrice(Double.parseDouble(etPrice.getText().toString()));
        item.setTags(etTags.getText().toString());
    }

}
