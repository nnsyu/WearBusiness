package com.ewoo.wearbusiness;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {

    public static DatabaseReference companyRef = FirebaseDatabase.getInstance().getReference("company");
    public static DatabaseReference radioRef = companyRef.child("token").child("radio");
}
