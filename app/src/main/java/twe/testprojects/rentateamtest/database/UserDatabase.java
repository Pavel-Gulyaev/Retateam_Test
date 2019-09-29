package twe.testprojects.rentateamtest.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import twe.testprojects.rentateamtest.model.User;

@Database(entities = User.class, version = 1,exportSchema = false)
abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    abstract UserDao userDao();

    static synchronized UserDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        PopulateDBAsyncTask(UserDatabase db) {
            this.userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... Void) {
            return null;
        }
    }

}
