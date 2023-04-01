package com.example.authservice.authservice.util;



public final class AppConstants {

   public static final String SECRET_KEY = System.getenv("SECRET_KEY");

   public static final int TOKEN_EXPIRATION = 120000;

   public static final String STATESTORE_NAME = "tokenstore";





}
