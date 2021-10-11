package com.example.contactsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_EDIT_CONTACT_NAME = "editContactName";
    private static final String ARG_EDIT_CONTACT_EMAIL = "editContactEmail";
    private static final String ARG_EDIT_CONTACT_PHONE = "editContactPhone";
    private static final String ARG_EDIT_CONTACT_TYPE = "editContactType";
    private static final String ARG_EDIT_CONTACT_ID = "editContactID";

    // TODO: Rename and change types of parameters
    private String editContactName;
    private String editContactEmail;
    private String editContactPhone;
    private String editContactType;
    private String editContactID;

    public EditContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment EditContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditContactFragment newInstance(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int editContactID) {
        EditContactFragment fragment = new EditContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EDIT_CONTACT_NAME, editContactName);
        args.putString(ARG_EDIT_CONTACT_EMAIL, editContactEmail);
        args.putString(ARG_EDIT_CONTACT_PHONE, editContactPhone);
        args.putString(ARG_EDIT_CONTACT_TYPE, editContactType);
        args.putString(ARG_EDIT_CONTACT_ID, String.valueOf(editContactID));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            editContactName = getArguments().getString(ARG_EDIT_CONTACT_NAME);
            editContactEmail = getArguments().getString(ARG_EDIT_CONTACT_EMAIL);
            editContactPhone = getArguments().getString(ARG_EDIT_CONTACT_PHONE);
            editContactType = getArguments().getString(ARG_EDIT_CONTACT_TYPE);
            editContactID = getArguments().getString(ARG_EDIT_CONTACT_ID);
        }
    }

    Button cancelButton;
    Button updateButton;
    EditText editTextEditContactName;
    EditText editTextEditContactEmail;
    EditText editTextEditContactPhone;
    EditText editTextEditContactType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        mEditContactFragmentTellsMain.editContactFragmentTellsMainToSetTitle();

        cancelButton = view.findViewById(R.id.buttonCancelInEditContactFragment);
        updateButton = view.findViewById(R.id.buttonUpdateInEditContactFragment);
        editTextEditContactName = view.findViewById(R.id.editTextEditContactName);
        editTextEditContactEmail = view.findViewById(R.id.editTextEditContactEmail);
        editTextEditContactPhone = view.findViewById(R.id.editTextEditContactPhone);
        editTextEditContactType = view.findViewById(R.id.editTextEditContactType);

        editTextEditContactName.setText(editContactName);
        editTextEditContactEmail.setText(editContactEmail);
        editTextEditContactPhone.setText(editContactPhone);
        editTextEditContactType.setText(editContactType);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditContactFragmentTellsMain.editContactFragmentTellsMainCancelIsClicked();
            }
        });

        AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());

        builder1.setTitle("Error")
                .setMessage("Please enter the contact name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getContext());

        builder2.setTitle("Error")
                .setMessage("Please enter the contact email")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog.Builder builder3 = new AlertDialog.Builder(view.getContext());

        builder3.setTitle("Error")
                .setMessage("Please enter the contact phone")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog.Builder builder4 = new AlertDialog.Builder(view.getContext());

        builder4.setTitle("Error")
                .setMessage("Please enter the contact type")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(editTextEditContactName.getText().toString()))
                {
                    builder1.create().show();
                }else if(TextUtils.isEmpty(editTextEditContactEmail.getText().toString()))
                {
                    builder2.create().show();
                }else if(TextUtils.isEmpty(editTextEditContactPhone.getText().toString()))
                {
                    builder3.create().show();
                } else if(TextUtils.isEmpty(editTextEditContactType.getText().toString()))
                {
                    builder4.create().show();
                } else {
                    Toast.makeText(view.getContext(), "Contact is successfully updated", Toast.LENGTH_SHORT).show();
                    mEditContactFragmentTellsMain.editContactFragmentTellsMainUpdateIsClicked(editTextEditContactName.getText().toString(), editTextEditContactEmail.getText().toString(), editTextEditContactPhone.getText().toString(), editTextEditContactType.getText().toString(), Integer.parseInt(editContactID));
                }

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IEditContactFragmentTellsMain)
        {
            mEditContactFragmentTellsMain = (IEditContactFragmentTellsMain) context;
        }
    }

    IEditContactFragmentTellsMain mEditContactFragmentTellsMain;
    public interface IEditContactFragmentTellsMain{
        void editContactFragmentTellsMainCancelIsClicked();
        void editContactFragmentTellsMainUpdateIsClicked(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int editContactID);
        void editContactFragmentTellsMainToSetTitle();
    }

}