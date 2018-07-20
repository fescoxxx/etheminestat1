package com.rijsoft.ethermine.etherminestats;

public class Constants {

    public static final class DATABASE {
        public static final String DB_NAME = "dbeth";
        public static final int DB_VERSION = 4;
        public static final String TABLE_NAME_CURRENT_STATS = "currentstats";


        public static final String CURRENT_STATS_ID = "_id";
        public static final String CURRENT_STATS_JSON_BODY = "json_body";
        public static final String CURRENT_STATS_TIME_LIFE = "date_time_life";

        public static final String CREATE_TABLE_CURRENT_STATS = "CREATE TABLE " + TABLE_NAME_CURRENT_STATS + "" +
                "(" + CURRENT_STATS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CURRENT_STATS_JSON_BODY + " TEXT," +
                CURRENT_STATS_TIME_LIFE + " DATETIME)";

        public static final String DROP_CURRENT_STATS = "DROP TABLE IF EXISTS " + TABLE_NAME_CURRENT_STATS;

        public static final String GET_CURRENT_STATS = "SELECT * FROM " + TABLE_NAME_CURRENT_STATS;

        public static final String DELETE_CURRENT_STATS = "DELETE FROM "+TABLE_NAME_CURRENT_STATS;

    }


}
