package com.example.contactsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CONTACT_NAME = "contactName";
    private static final String ARG_CONTACT_EMAIL = "contactEmail";
    private static final String ARG_CONTACT_PHONE = "contactPhone";
    private static final String ARG_CONTACT_TYPE = "contactType";
    private static final String ARG_CONTACT_ID = "contactID";

    // TODO: Rename and change types of parameters
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String contactType;
    private String contactID;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ContactDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactDetailsFragment newInstance(String contactName, String contactEmail, String contactPhone, String contactType, int contactID) {

        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CONTACT_NAME, contactName);
        args.putString(ARG_CONTACT_EMAIL, contactEmail);
        args.putString(ARG_CONTACT_PHONE, contactPhone);
        args.putString(ARG_CONTACT_TYPE, contactType);
        args.putString(ARG_CONTACT_ID, String.valueOf(contactID));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contactName = getArguments().getString(ARG_CONTACT_NAME);
            contactEmail = getArguments().getString(ARG_CONTACT_EMAIL);
            contactPhone = getArguments().getString(ARG_CONTACT_PHONE);
            contactType = getArguments().getString(ARG_CONTACT_TYPE);
            contactID = getArguments().getString(ARG_CONTACT_ID);
        }
    }

    TextView textViewContactName;
    TextView textViewContactEmail;
    TextView textViewContactPhone;
    TextView textViewContactType;
    Button backButton;
    Button deleteButton;
    Button updateButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        mTellMainBackIsClickedInDetailsFragment.tellMainToSetTitleForDetailsFragment();
        Log.d("KKKKK", "newInstance: " + contactName);

        textViewContactName = view.findViewById(R.id.textViewContactNameResult);
        textViewContactEmail = view.findViewById(R.id.textViewContactEmailResult);
        textViewContactPhone = view.findViewById(R.id.textViewContactPhoneResult);
        textViewContactType = view.findViewById(R.id.textViewContactTypeResult);
        backButton = view.findViewById(R.id.buttonBackInDetailsFragment);
        deleteButton = view.findViewById(R.id.buttonDeleteInContactDetailsFragment);
        updateButton = view.findViewById(R.id.buttonUpdateInContactDetailsFragment);

        textViewContactName.setText(contactName);
        textViewContactEmail.setText(contactEmail);
        textViewContactPhone.setText(contactPhone);
        textViewContactType.setText(contactType);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTellMainBackIsClickedInDetailsFragment.tellMainBackIsClickedInDetailsFragment();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTellMainBackIsClickedInDetailsFragment.tellMainDeleteButtonIsClickedInDetailsFragment(Integer.parseInt(contactID));
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTellMainBackIsClickedInDetailsFragment.tellMainUpdateButtonIsClickedInDetailsFragment(contactName, contactEmail, contactPhone, contactType, Integer.parseInt(contactID));
            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ITellMainBackIsClickedInDetailsFragment)
        {
            mTellMainBackIsClickedInDetailsFragment = (ITellMainBackIsClickedInDetailsFragment) context;
        }
    }

    ITellMainBackIsClickedInDetailsFragment mTellMainBackIsClickedInDetailsFragment;

    public interface ITellMainBackIsClickedInDetailsFragment{
        void tellMainBackIsClickedInDetailsFragment();
        void tellMainDeleteButtonIsClickedInDetailsFragment(int contactID);
        void tellMainUpdateButtonIsClickedInDetailsFragment(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int contactID);
        void tellMainToSetTitleForDetailsFragment();
    }
}