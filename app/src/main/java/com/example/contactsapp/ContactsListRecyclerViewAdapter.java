package com.example.contactsapp;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsListRecyclerViewAdapter extends RecyclerView.Adapter<ContactsListRecyclerViewAdapter.ContactsListViewHolder>{


    ArrayList<Contacts> userContacts;
    ContactsListRecyclerViewTellsContactsListFragment mRecyclerViewTellsContactsListFragment;

    public ContactsListRecyclerViewAdapter(ArrayList<Contacts> data, ContactsListRecyclerViewTellsContactsListFragment mRecyclerViewTellsContactsListFragment){
        this.mRecyclerViewTellsContactsListFragment = mRecyclerViewTellsContactsListFragment;
        this.userContacts = data;
    }

    @NonNull
    @Override
    public ContactsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_screen, parent, false);
        ContactsListViewHolder userSortViewHolder = new ContactsListViewHolder(view, mRecyclerViewTellsContactsListFragment);
        return userSortViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsListViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Contacts contacts = userContacts.get(position);
        holder.textViewContactName.setText(contacts.getContactName());
        holder.textViewContactEmail.setText(contacts.getContactEmail());
        holder.textViewContactPhone.setText(contacts.getContactPhoneNumber());
        holder.textViewContactType.setText(contacts.getContactPhoneType());
        holder.position = position;
        holder.contactID = contacts.getContactID();
    }

    @Override
    public int getItemCount() {
        return this.userContacts.size();
    }

    public static class ContactsListViewHolder extends RecyclerView.ViewHolder {

        TextView textViewContactName;
        TextView textViewContactEmail;
        TextView textViewContactPhone;
        TextView textViewContactType;
        Button contactListDeleteButton;
        int position;
        int contactID;
        ContactsListRecyclerViewTellsContactsListFragment mRecyclerViewTellsContactsListFragment;
        public ContactsListViewHolder(@NonNull View itemView, ContactsListRecyclerViewTellsContactsListFragment mRecyclerViewTellsContactsListFragment) {
            super(itemView);
            this.mRecyclerViewTellsContactsListFragment = mRecyclerViewTellsContactsListFragment;
            textViewContactName = itemView.findViewById(R.id.textViewContactNames);
            contactListDeleteButton = itemView.findViewById(R.id.buttonDeleteInContactsList);
            textViewContactEmail = itemView.findViewById(R.id.textViewContactEmail);
            textViewContactPhone = itemView.findViewById(R.id.textViewContactPhone);
            textViewContactType = itemView.findViewById(R.id.textViewContactType);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mRecyclerViewTellsContactsListFragment.tellContactsListFragmentRowItemIsClicked(textViewContactName.getText().toString(), textViewContactEmail.getText().toString(), textViewContactPhone.getText().toString(), textViewContactType.getText().toString(), contactID);
                }
            });
            contactListDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("ICLICK", "onClick: " + position);
                    mRecyclerViewTellsContactsListFragment.tellContactsListFragmentDeleteButtonIsClicked(textViewContactName.getText().toString(), textViewContactEmail.getText().toString(), textViewContactPhone.getText().toString(), textViewContactType.getText().toString(), contactID);

                }
            });

        }
    }

    public interface ContactsListRecyclerViewTellsContactsListFragment{
        void tellContactsListFragmentRowItemIsClicked( String contactName, String contactEmail, String contactPhone, String contactType, int contactID);
        void tellContactsListFragmentDeleteButtonIsClicked(String contactName, String contactEmail, String contactPhone, String contactType, int contactID);
    }
}
