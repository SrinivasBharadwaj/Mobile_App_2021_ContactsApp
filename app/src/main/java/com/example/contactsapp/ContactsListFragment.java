package com.example.contactsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsListFragment extends Fragment implements ContactsListRecyclerViewAdapter.ContactsListRecyclerViewTellsContactsListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CONTACTS = "contacts";

    // TODO: Rename and change types of parameters
    private ArrayList<Contacts> contacts;

    public ContactsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ContactsListFragment.
     * @param contacts
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsListFragment newInstance(ArrayList<Contacts> contacts) {
        ContactsListFragment fragment = new ContactsListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACTS, contacts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contacts = (ArrayList<Contacts>) getArguments().getSerializable(ARG_CONTACTS);
        }
    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ContactsListRecyclerViewAdapter adapter;
    Button addContactButton;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        mContactListFragmentTellsMain.contactListFragmentTellsMainToSetTitle();

        recyclerView = view.findViewById(R.id.contactsListRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // form recyclerview adapter
        adapter = new ContactsListRecyclerViewAdapter(contacts, this);
        // set recyclerview adapter
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addContactButton = view.findViewById(R.id.buttonAddNewContact);
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContactListFragmentTellsMain.contactListFragmentTellsMainAddContactIsClickedInFragment();
            }
        });

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IContactListFragmentTellsMain)
        {
            mContactListFragmentTellsMain = (IContactListFragmentTellsMain) context;
        }
    }

    IContactListFragmentTellsMain mContactListFragmentTellsMain;

    @Override
    public void tellContactsListFragmentRowItemIsClicked(String contactName, String contactEmail, String contactPhone, String contactType, int contactID) {
        mContactListFragmentTellsMain.contactListFragmentTellsMain(contactName, contactEmail, contactPhone, contactType, contactID);
    }

    @Override
    public void tellContactsListFragmentDeleteButtonIsClicked(String contactName, String contactEmail, String contactPhone, String contactType, int contactID) {
        mContactListFragmentTellsMain.contactListFragmentTellsMainDeleteIsClickedInList(contactName, contactEmail, contactPhone, contactType, contactID);

    }


    public interface IContactListFragmentTellsMain{
        void contactListFragmentTellsMain(String contactName, String contactEmail, String contactPhone, String contactType, int contactID);
        void contactListFragmentTellsMainDeleteIsClickedInList(String contactName, String contactEmail, String contactPhone, String contactType, int contactID);
        void contactListFragmentTellsMainAddContactIsClickedInFragment();
        void contactListFragmentTellsMainToSetTitle();
    }

}