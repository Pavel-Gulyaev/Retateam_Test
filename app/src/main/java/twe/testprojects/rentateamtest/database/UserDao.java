package twe.testprojects.rentateamtest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import twe.testprojects.rentateamtest.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM Users")
    void deleteAll();

    @Query("SELECT * FROM Users")
    LiveData<List<User>> getAllUsers();
}
