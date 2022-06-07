package com.example.loginactivity.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginactivity.myObjects.Contact;

import java.util.List;

//public class ContactViewModel extends ViewModel {
//
//    // interaction between contact and DB
//    private ContactsRepository repository;
//
//         // its not mutable because the mRepository change it and it live just for observe
//         private LiveData<List<Contact>> contacts;
//
//         public ContactViewModel () {
//         // the repository- ask for all contacts
//         repository = new ContactsRepository();
//             contacts = repository.getAll();
//         }
//
//         // basics operation
//         public LiveData<List<Contact>> get() { return contacts; }
//
//         public void add(Contact contact) { repository.add(contact); }
//
//         public void delete(Contact contact) { repository.delete(contact); }
//
//         public void reload() { repository.reload(); }
//
//}
