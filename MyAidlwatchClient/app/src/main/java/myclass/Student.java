package myclass;

import android.os.Parcel;
import android.os.Parcelable;



public class Student implements Parcelable  {

    private String mName;
    private int mRoll_no;
    private String mCollege;

    public Student(String name, int roll_no, String college) {
        this.mName = name;
        this.mRoll_no = roll_no;
        this.mCollege = college;
    }

    public Student() {
    }

    protected Student(Parcel in) {
        mName = in.readString();
        mRoll_no = in.readInt();
        mCollege = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
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
        dest.writeString(mName);
        dest.writeInt(mRoll_no);
        dest.writeString(mCollege);
    }

}
