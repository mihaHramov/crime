package com.example.miha.criminalintent.data.network.comments;

import android.support.annotation.NonNull;

import com.example.miha.criminalintent.domain.model.CommentOnServer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class CommentsApiFirebase implements ICommentsApi {
    private static final String TABLE = "comments";
    private DatabaseReference databaseReference;

    public CommentsApiFirebase(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable<List<CommentOnServer>> getComments(String key) {
        return Observable.unsafeCreate(subscriber -> databaseReference
                .child(TABLE)
                .child(key)
                .addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                List<CommentOnServer> commentOnServers = new ArrayList<>();
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    CommentOnServer commentOnServer = data.getValue(CommentOnServer.class);
                                    if (commentOnServer != null) {
                                        commentOnServers.add(commentOnServer);
                                    }
                                }
                                subscriber.onNext(commentOnServers);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                subscriber.onError(new Throwable(databaseError.getMessage()));
                            }
                        })
        );

    }
}
