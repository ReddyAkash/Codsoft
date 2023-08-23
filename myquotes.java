package com.example.codsoft;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myquotes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyQuotesAdapter adapter;

    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myquotes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> savedQuotes = new ArrayList<>();

        // Create the adapter with an empty list initially
        adapter = new MyQuotesAdapter(savedQuotes);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("users").child("codsoft").child("myquotes");

        // Set up ValueEventListener to retrieve saved quotes
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> savedQuotes = new ArrayList<>();
                for (DataSnapshot quoteSnapshot : snapshot.getChildren()) {
                    String quote = quoteSnapshot.getValue(String.class);
                    savedQuotes.add(quote);
                }

                // Update the adapter with the retrieved quotes
                adapter.setQuotes(savedQuotes);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
            }


        });
    }

}
