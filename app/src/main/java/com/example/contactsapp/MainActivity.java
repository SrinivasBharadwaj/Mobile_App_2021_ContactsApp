/*
* Srinivas Bharadwaj Chintalapati
* */

package com.example.contactsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  implements ContactsListFragment.IContactListFragmentTellsMain, ContactDetailsFragment.ITellMainBackIsClickedInDetailsFragment, NewContactFragment.InewContactFragmentTellMain, EditContactFragment.IEditContactFragmentTellsMain {

    private final OkHttpClient client = new OkHttpClient();
    ArrayList<Contacts> userContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts();

    }

    public void getContacts()
    {
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contacts/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                try {
                    JSONObject json = new JSONObject(response.body().string());
                    JSONArray contactsJson = json.getJSONArray("contacts");
                    for(int i=0; i<contactsJson.length(); i++)
                    {
                        JSONObject contactJsonObject = contactsJson.getJSONObject(i);
                        Contacts contacts = new Contacts();
                        contacts.setContactID(Integer.parseInt(contactJsonObject.getString("Cid")));
                        contacts.setContactName(contactJsonObject.getString("Name"));
                        contacts.setContactEmail(contactJsonObject.getString("Email"));
                        contacts.setContactPhoneNumber(contactJsonObject.getString("Phone"));
                        contacts.setContactPhoneType(contactJsonObject.getString("PhoneType"));
                        userContacts.add(contacts);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getSupportFragmentManager().beginTransaction()
                                    .add(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                                    .commit();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void deleteContacts(String contactName, String contactEmail, String contactPhone, String contactType, int contactID)
    {
        Log.d("PRINT", "deleteContacts: "+ contactID);
        FormBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(contactID))
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/delete")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Request request1 = new Request.Builder()
                        .url("https://www.theappsdr.com/contacts/json")
                        .build();

                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray contactsJson = json.getJSONArray("contacts");
                            userContacts.clear();
                            for(int i=0; i<contactsJson.length(); i++)
                            {
                                JSONObject contactJsonObject = contactsJson.getJSONObject(i);
                                Contacts contacts = new Contacts();
                                contacts.setContactID(Integer.parseInt(contactJsonObject.getString("Cid")));
                                contacts.setContactName(contactJsonObject.getString("Name"));
                                contacts.setContactEmail(contactJsonObject.getString("Email"));
                                contacts.setContactPhoneNumber(contactJsonObject.getString("Phone"));
                                contacts.setContactPhoneType(contactJsonObject.getString("PhoneType"));
                                Log.d("NEWSWW", "onResponse: "+ contacts.toString());
                                userContacts.add(contacts);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    FragmentManager fm = getSupportFragmentManager();
                                    for(int i =0; i< fm.getBackStackEntryCount(); ++i)
                                    {
                                        fm.popBackStack();
                                    }
                                    Log.d("SEE", "run: "+ userContacts.size());
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void createNewContact(String newName, String newEmail, String newPhone, String newType)
    {
        Log.d("AAA", "newContactFragmentTellMainSubmitButtonIsClickedInNewContact: "+ newName);
        FormBody formBody1 = new FormBody.Builder()
                .add("name", newName)
                .add("email", newEmail)
                .add("phone", newPhone)
                .add("type", newType)
                .build();

        Request request3 = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/create")
                .post(formBody1)
                .build();

        client.newCall(request3).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Request request2 = new Request.Builder()
                        .url("https://www.theappsdr.com/contacts/json")
                        .build();

                client.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray contactsJson = json.getJSONArray("contacts");
                            userContacts.clear();
                            for(int i=0; i<contactsJson.length(); i++)
                            {
                                JSONObject contactJsonObject = contactsJson.getJSONObject(i);
                                Contacts contacts = new Contacts();
                                contacts.setContactID(Integer.parseInt(contactJsonObject.getString("Cid")));
                                contacts.setContactName(contactJsonObject.getString("Name"));
                                contacts.setContactEmail(contactJsonObject.getString("Email"));
                                contacts.setContactPhoneNumber(contactJsonObject.getString("Phone"));
                                contacts.setContactPhoneType(contactJsonObject.getString("PhoneType"));
                                Log.d("NEWSWW", "onResponse: "+ contacts.toString());
                                userContacts.add(contacts);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void contactListFragmentTellsMain(String contactName, String contactEmail, String contactPhone, String contactType, int contactID) {
        setTitle("Contact Details");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, ContactDetailsFragment.newInstance(contactName, contactEmail, contactPhone, contactType, contactID))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void contactListFragmentTellsMainDeleteIsClickedInList(String contactName, String contactEmail, String contactPhone, String contactType, int contactID) {
        Log.d("DELETE", "contactListFragmentTellsMainDeleteIsClickedInList: "+ contactID);
        deleteContacts(contactName, contactEmail, contactPhone, contactType, contactID);
    }

    @Override
    public void contactListFragmentTellsMainAddContactIsClickedInFragment() {
        setTitle("New Contact");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new NewContactFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void contactListFragmentTellsMainToSetTitle() {
        setTitle("Contacts List");
    }

    @Override
    public void tellMainBackIsClickedInDetailsFragment() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                        .commit();
            }
        });
    }

    @Override
    public void tellMainDeleteButtonIsClickedInDetailsFragment(int contactID) {
        deleteButtonClickInDetailsFragment(contactID);
    }

    @Override
    public void tellMainUpdateButtonIsClickedInDetailsFragment(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int contactID) {
        setTitle("Edit Contacts");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, EditContactFragment.newInstance(editContactName, editContactEmail, editContactPhone, editContactType, contactID))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void tellMainToSetTitleForDetailsFragment() {
        setTitle("Contact Details");
    }

    public void deleteButtonClickInDetailsFragment(int contactID) {
        Log.d("PRINT", "deleteContacts: "+ contactID);
        FormBody formBodyDelete = new FormBody.Builder()
                .add("id", String.valueOf(contactID))
                .build();

        Request requestDeleteFromDetails = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/delete")
                .post(formBodyDelete)
                .build();

        client.newCall(requestDeleteFromDetails).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Request request1 = new Request.Builder()
                        .url("https://www.theappsdr.com/contacts/json")
                        .build();

                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray contactsJson = json.getJSONArray("contacts");
                            userContacts.clear();
                            for(int i=0; i<contactsJson.length(); i++)
                            {
                                JSONObject contactJsonObject = contactsJson.getJSONObject(i);
                                Contacts contacts = new Contacts();
                                contacts.setContactID(Integer.parseInt(contactJsonObject.getString("Cid")));
                                contacts.setContactName(contactJsonObject.getString("Name"));
                                contacts.setContactEmail(contactJsonObject.getString("Email"));
                                contacts.setContactPhoneNumber(contactJsonObject.getString("Phone"));
                                contacts.setContactPhoneType(contactJsonObject.getString("PhoneType"));
                                Log.d("NEWSWW", "onResponse: "+ contacts.toString());
                                userContacts.add(contacts);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    FragmentManager fm = getSupportFragmentManager();
                                    for(int i =0; i< fm.getBackStackEntryCount(); ++i)
                                    {
                                        fm.popBackStack();
                                    }
                                    Log.d("SEE", "run: "+ userContacts.size());
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }


    @Override
    public void newContactFragmentTellMainCancelButtonIsClicked() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                        .commit();
            }
        });
    }

    @Override
    public void newContactFragmentTellMainSubmitButtonIsClickedInNewContact(String newName, String newEmail, String newPhone, String newType) {
        createNewContact(newName, newEmail, newPhone, newType);
    }

    @Override
    public void newContactFragmentTellMainToSetTitle() {
        setTitle("New Contact");
    }

    @Override
    public void editContactFragmentTellsMainCancelIsClicked() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                        .commit();
            }
        });
    }

    @Override
    public void editContactFragmentTellsMainUpdateIsClicked(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int editContactID) {
        updateContacts(editContactName, editContactEmail, editContactPhone, editContactType, editContactID);
    }

    @Override
    public void editContactFragmentTellsMainToSetTitle() {
        setTitle("Edit Contact");
    }

    public void updateContacts(String editContactName, String editContactEmail, String editContactPhone, String editContactType, int editContactID) {
        Log.d("FINAL", "updateContacts: " + editContactName);

        FormBody formBodyUpdate = new FormBody.Builder()
                .add("name", editContactName)
                .add("email", editContactEmail)
                .add("phone", editContactPhone)
                .add("type", editContactType)
                .add("id", String.valueOf(editContactID))
                .build();

        Request requestUpdate = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/update")
                .post(formBodyUpdate)
                .build();

        client.newCall(requestUpdate).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Request request2 = new Request.Builder()
                        .url("https://www.theappsdr.com/contacts/json")
                        .build();

                client.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            JSONArray contactsJson = json.getJSONArray("contacts");
                            userContacts.clear();
                            for(int i=0; i<contactsJson.length(); i++)
                            {
                                JSONObject contactJsonObject = contactsJson.getJSONObject(i);
                                Contacts contacts = new Contacts();
                                contacts.setContactID(Integer.parseInt(contactJsonObject.getString("Cid")));
                                contacts.setContactName(contactJsonObject.getString("Name"));
                                contacts.setContactEmail(contactJsonObject.getString("Email"));
                                contacts.setContactPhoneNumber(contactJsonObject.getString("Phone"));
                                contacts.setContactPhoneType(contactJsonObject.getString("PhoneType"));
                                Log.d("NEWSWW", "onResponse: "+ contacts.toString());
                                userContacts.add(contacts);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.containerView, ContactsListFragment.newInstance(userContacts))
                                            .addToBackStack(null)
                                            .commit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}