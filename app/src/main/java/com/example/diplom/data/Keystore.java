package com.example.diplom.data;
import androidx.annotation.Nullable;

public interface Keystore {
    boolean userExists(String userName);
    @Nullable
    String isAdmin(String userName, String pin);
    void newUser(String userName, String pin);
}