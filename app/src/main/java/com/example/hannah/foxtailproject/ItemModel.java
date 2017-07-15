package com.example.hannah.foxtailproject;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Item implements Serializable{
    String id;
    String name;
    String location;
    Integer year;
    Integer month;
    Integer day;
    Float score;
    String review;

    public Item() {

    }

    public Item(String id, String name, String location, int year, int month, int day, float score, String review) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.year = year;
        this.month = month;
        this.day = day;
        this.score = score;
        this.review = review;
    }

    public String getName() {
        return this.name;
    }
    public String getLocation() {
        return this.location;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }

}

interface OnItemChangedListener {
    void onDataChanged(List<Item> items);
}

public class ItemModel {
    private DatabaseReference itemRef;
    private List<Item> items = new ArrayList<>();
    private OnItemChangedListener onItemChangedListener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseUser user;
    private String userKey;

    public void setOnItemChangedListener(final OnItemChangedListener listener) {
        this.onItemChangedListener = listener;
    }

    public ItemModel() {

        itemRef = database.getReference("items");

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items = new ArrayList<>();

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot e : children) {
                    Item item = e.getValue(Item.class);
                    items.add(item);
                }
                if (onItemChangedListener != null) {
                    onItemChangedListener.onDataChanged(items);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveItem(String name, String location, int year, int month, int day, float score, String review) {
        DatabaseReference childRef = itemRef.push();
        childRef.setValue(new Item(childRef.getKey(), name, location, year, month, day, score, review));
    }

    public void deleteItem(String id) {
        itemRef.child(id).removeValue();
    }

    public void updateItem(String id, String name, String location, int year, int month, int day, float score, String review) {
        DatabaseReference childRef = itemRef.child(id);
        childRef.setValue(new Item(childRef.getKey(), name, location, year, month, day, score, review));
    }

    public List<Item> getAllItem() {
        return items;
    }



}
