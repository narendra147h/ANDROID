package myclass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adpr270759 on 19-06-2018.
 */

public class Student implements Parcelable {

    private String name;
    private int roll_no;
    private String college;

    public Student(String name, int roll_no, String college) {
        this.name = name;
        this.roll_no = roll_no;
        this.college = college;
    }

    public Student() {
    }

    protected Student(Parcel in) {
        name = in.readString();
        roll_no = in.readInt();
        college = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(roll_no);
        dest.writeString(college);
    }
}

