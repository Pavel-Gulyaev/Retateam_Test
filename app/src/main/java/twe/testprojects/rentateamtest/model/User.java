package twe.testprojects.rentateamtest.model;

import com.google.gson.annotations.SerializedName;
import androidx.room.Ignore;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @SerializedName("id")
    @PrimaryKey
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String name;

    @SerializedName("last_name")
    private String surname;

    @SerializedName("avatar")
    private String avatar;

    public User(int id, String email, String name, String surname, String avatar) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Ignore
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
