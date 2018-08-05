package com.rijsoft.ethermine.etherminestats;

public class Constants {

    public static final class DATABASE {
        public static final String DB_NAME = "dbeth";
        public static final int DB_VERSION = 9;

        public static final String TABLE_NAME_CURRENT_STATS = "currentstats";
        public static final String TABLE_NAME_PAYOUTS = "payouts";
        public static final String TABLE_NAME_WORKERS = "workers";
        public static final String TABLE_NAME_SETTINGS = "settings";

        //Current stats------------------------------------------------------------------------START
        public static final String CURRENT_STATS_ID = "_id";
        public static final String CURRENT_STATS_JSON_BODY = "json_body";
        public static final String CURRENT_STATS_TIME_LIFE = "date_time_life";

        public static final String CREATE_TABLE_CURRENT_STATS = "CREATE TABLE "
                + TABLE_NAME_CURRENT_STATS + "" +
                "(" + CURRENT_STATS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CURRENT_STATS_JSON_BODY + " TEXT," +
                CURRENT_STATS_TIME_LIFE + " DATETIME)";

        public static final String DROP_CURRENT_STATS = "DROP TABLE IF EXISTS "
                + TABLE_NAME_CURRENT_STATS;

        public static final String GET_CURRENT_STATS = "SELECT * FROM " + TABLE_NAME_CURRENT_STATS;

        public static final String DELETE_CURRENT_STATS = "DELETE FROM "+TABLE_NAME_CURRENT_STATS;
        //Current stats--------------------------------------------------------------------------END

        //Payouts------------------------------------------------------------------------------START
        public static final String PAYOUTS_ID = "_id";
        public static final String PAYOUTS_JSON_BODY = "json_body";
        public static final String PAYOUTS_TIME_LIFE = "date_time_life";

        public static final String CREATE_TABLE_PAYOUTS = "CREATE TABLE "
                + TABLE_NAME_PAYOUTS + "" +
                "("+PAYOUTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PAYOUTS_JSON_BODY + " TEXT," +
                PAYOUTS_TIME_LIFE + " DATETIME)";

        public static final String DROP_PAYOUTS ="DROP TABLE IF EXISTS "
                + TABLE_NAME_PAYOUTS;

        public static final String GET_PAYOUTS = "SELECT * FROM " +TABLE_NAME_PAYOUTS;

        public static final String DELETE_PAYOUTS = "DELETE FROM "+TABLE_NAME_PAYOUTS;
        //Payouts--------------------------------------------------------------------------------END

        //Workers------------------------------------------------------------------------------START
        public static final String WORKERS_ID = "_id";
        public static final String WORKERS_JSON_BODY = "json_body";
        public static final String WORKERS_TIME_LIFE = "date_time_life";

        public static final String CREATE_TABLE_WORKERS = "CREATE TABLE "
                + TABLE_NAME_WORKERS + "" +
                "("+WORKERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WORKERS_JSON_BODY + " TEXT," +
                WORKERS_TIME_LIFE + " DATETIME)";

        public static final String DROP_WORKERS ="DROP TABLE IF EXISTS "
                + TABLE_NAME_WORKERS;

        public static final String GET_WORKERS = "SELECT * FROM " +TABLE_NAME_WORKERS;

        public static final String DELETE_WORKERS = "DELETE FROM "+TABLE_NAME_WORKERS;
        //Workers--------------------------------------------------------------------------------END

        //Settings-----------------------------------------------------------------------------START
        public static final String SETTINGS_ID = "_id";
        public static final String SETTINGS_JSON_BODY = "json_body";
        public static final String SETTINGS_TIME_LIFE = "date_time_life";

        public static final String CREATE_TABLE_SETTINGS = "CREATE TABLE "
                + TABLE_NAME_SETTINGS + "" +
                "("+SETTINGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SETTINGS_JSON_BODY + " TEXT," +
                SETTINGS_TIME_LIFE + " DATETIME)";

        public static final String DROP_SETTINGS ="DROP TABLE IF EXISTS "
                + TABLE_NAME_SETTINGS;

        public static final String GET_SETTINGS = "SELECT * FROM " +TABLE_NAME_SETTINGS;

        public static final String DELETE_SETTINGS = "DELETE FROM "+TABLE_NAME_SETTINGS;
        //Settings-------------------------------------------------------------------------------END


    }


}
