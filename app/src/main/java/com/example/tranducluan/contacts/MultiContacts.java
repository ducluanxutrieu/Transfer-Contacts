package com.example.tranducluan.contacts;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiContacts implements Parcelable{
    String Id;
    String NumberPhone;
    String Name;
    String PhoneAfterChange;
    String NhaMang;

    public MultiContacts(String id, String numberPhone, String name, String phoneAfterChange, String nhaMang) {
        Id = id;
        NumberPhone = numberPhone;
        Name = name;
        PhoneAfterChange = phoneAfterChange;
        NhaMang = nhaMang;
    }

    public MultiContacts(Parcel in){
        Id = ((String) in.readValue((String.class.getClassLoader())));
        NumberPhone = ((String) in.readValue((String.class.getClassLoader())));
        Name = ((String) in.readValue((String.class.getClassLoader())));
        PhoneAfterChange = ((String) in.readValue((String.class.getClassLoader())));
        NhaMang = ((String) in.readValue((String.class.getClassLoader())));
    }
    public static final Creator<MultiContacts> CREATOR = new Creator<MultiContacts>() {
        @Override
        public MultiContacts createFromParcel(Parcel parcel) {
            return new MultiContacts(parcel);
        }

        @Override
        public MultiContacts[] newArray(int i) {
            return new MultiContacts[i];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.Id);
        parcel.writeValue(this.NumberPhone);
        parcel.writeValue(this.Name);
        parcel.writeValue(this.PhoneAfterChange);
        parcel.writeValue(this.NhaMang);
        }

    public String getId() {
        return (Id==null ? "" : Id);
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNumberPhone() {
        return (NumberPhone==null ? "" : NumberPhone);
    }

    public void setNumberPhone(String numberPhone) {
        NumberPhone = numberPhone;
    }

    public String getName() {
        return (Name==null ? "" : Name);
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneAfterChange() {
        return  (PhoneAfterChange==null ? "" : PhoneAfterChange);
    }

    public void setPhoneAfterChange(String phoneAfterChange) {
        PhoneAfterChange = phoneAfterChange;
    }

    public String getNhaMang() {
       return (NhaMang==null ? "" : NhaMang);
    }

    public void setNhaMang(String nhaMang) {
        NhaMang = nhaMang;
    }

}
