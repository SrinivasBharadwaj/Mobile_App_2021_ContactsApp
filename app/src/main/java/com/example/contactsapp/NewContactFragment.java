package com.example.contactsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewContactFragment newInstance(String param1, String param2) {
        NewContactFragment fragment = new NewContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button backButton;
    Button submitButton;
    EditText newContactName;
    EditText newContactEmail;
    EditText newContactPhone;
    EditText newContactType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_contact, container, false);

        mNewContactFragmentTellMain.newContactFragmentTellMainToSetTitle();

        newContactName = view.findViewById(R.id.editTextNewContactName);
        newContactEmail = view.findViewById(R.id.editTextNewContactEmail);
        newContactPhone = view.findViewById(R.id.editTextNewContactPhone);
        newContactType = view.findViewById(R.id.editTextNewContactType);

        backButton = view.findViewById(R.id.buttonCancelInNewContactFragment);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewContactFragmentTellMain.newContactFragmentTellMainCancelButtonIsClicked();
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

        submitButton = view.findViewById(R.id.buttonSubmitInNewContactFragment);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(newContactName.getText().toString()))
                {
                    builder1.create().show();
                }else if(TextUtils.isEmpty(newContactEmail.getText().toString()))
                {
                    builder2.create().show();
                }else if(TextUtils.isEmpty(newContactPhone.getText().toString()))
                {
                    builder3.create().show();
                } else if(TextUtils.isEmpty(newContactType.getText().toString()))
                {
                    builder4.create().show();
                } else {
                    Log.d("CHECK", "onClick: "+ newContactName.getText().toString());
                    mNewContactFragmentTellMain.newContactFragmentTellMainSubmitButtonIsClickedInNewContact(newContactName.getText().toString(), newContactEmail.getText().toString(), newContactPhone.getText().toString(), newContactType.getText().toString());
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof InewContactFragmentTellMain)
        {
            mNewContactFragmentTellMain = (InewContactFragmentTellMain) context;
        }
    }

    InewContactFragmentTellMain mNewContactFragmentTellMain;

    public interface InewContactFragmentTellMain {
        void newContactFragmentTellMainCancelButtonIsClicked();
        void newContactFragmentTellMainSubmitButtonIsClickedInNewContact(String newName, String newEmail, String newPhone, String newType);
        void newContactFragmentTellMainToSetTitle();

    }
}