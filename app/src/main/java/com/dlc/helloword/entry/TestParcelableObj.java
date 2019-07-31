package com.dlc.helloword.entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述：
 * 日期：2019/7/31
 * 作者：水寒
 * 邮箱：lxq_xsyu@163.com
 */
public class TestParcelableObj implements Parcelable {

    public String title;
    public String subtitle;

    public TestParcelableObj() {
    }

    public static final Creator<TestParcelableObj> CREATOR = new Creator<TestParcelableObj>() {
        @Override
        public TestParcelableObj createFromParcel(Parcel in) {
            TestParcelableObj tpo = new TestParcelableObj();
            tpo.title = in.readString();
            tpo.subtitle = in.readString();
            return tpo;
        }

        @Override
        public TestParcelableObj[] newArray(int size) {
            return new TestParcelableObj[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(subtitle);
    }
}
