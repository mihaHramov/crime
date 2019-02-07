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
import rx.Single;

public class CommentsApiFirebase implements ICommentsApi {
    private static final String TABLE = "comments";
    private DatabaseReference databaseReference;

    public CommentsApiFirebase(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable<List<CommentOnServer>> getComments(String key) {
        return Observable.create(subscriber ->
                databaseReference.child(TABLE).child(key)
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
                                        subscriber.onCompleted();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        subscriber.onError(getError(databaseError.getMessage()));
                                    }
                                }));

    }

    @Override
    public Observable<Boolean> sendComment(CommentOnServer commentOnServer) {
        return Single.create(
                (Single.OnSubscribe<Boolean>) singleSubscriber -> databaseReference
                        .child(TABLE)
                        .child(commentOnServer.crimeId)
                        .setValue(commentOnServer)
                        .addOnCompleteListener(task -> singleSubscriber.onSuccess(true))
                        .addOnFailureListener(e -> singleSubscriber.onError(getError(e.getMessage()))))
                .toObservable();
    }

    private Throwable getError(String e) {
        return new Throwable(e);
    }
}
