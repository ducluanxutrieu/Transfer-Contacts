package com.example.tranducluan.contacts;

import android.util.Log;

public class TransferNumber extends MainActivity {
        public String ChangeNumber(String phone){
        phone = phone.trim();
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("\\.", "");
        phone = phone.replaceAll("-", "");
        String temp84 = "+84";
        if(phone.length()>10) {
            String firstStringNumber = phone.substring(0, 4);
            String temp = phone.substring(0, 3);
            if(temp.equals(temp84)){
                phone = phone.replaceFirst("\\+84", "0");
                temp = phone.substring(0, 3);
            }
            if (temp.equals("012")||temp.equals("016") || temp.equals("018") || temp.equals("019")) {
                //Do something
                phone = ChuyenDoi(firstStringNumber, phone);
            }
        }

        return phone;
    }

    String ChuyenDoi(String firstStringNumber, String phone){
        switch (firstStringNumber){
            //Mobifone
            case "0120": phone = phone.replaceFirst("0120", "070");
                break;
            case "0121": phone = phone.replaceFirst("0121", "079");
                break;
            case "0122": phone = phone.replaceFirst("0122", "077");
                break;
            case "0126": phone = phone.replaceFirst("0126", "076");
                break;
            case "0128": phone = phone.replaceFirst("0128", "078");
                break;

            //Vinaphone
            case "0123": phone = phone.replaceFirst("0123", "083");
                break;
            case "0124": phone = phone.replaceFirst("0124", "084");
                break;
            case "0125": phone = phone.replaceFirst("0125", "085");
                break;
            case "0127": phone = phone.replaceFirst("0127", "081");
                break;
            case "0129": phone = phone.replaceFirst("0129", "082");
                break;

            //Viettel
            case "0162": phone = phone.replaceFirst("0162", "032");
                break;
            case "0163": phone = phone.replaceFirst("0163", "033");
                break;
            case "0164": phone = phone.replaceFirst("0164", "034");
                break;
            case "0165": phone = phone.replaceFirst("0165", "035");
                break;
            case "0166": phone = phone.replaceFirst("0166", "036");
                break;
            case "0167": phone = phone.replaceFirst("0167", "037");
                break;
            case "0168": phone = phone.replaceFirst("0168", "038");
                break;
            case "0169": phone = phone.replaceFirst("0169", "039");
                break;

            //Vietnamobile
            case "0186": phone = phone.replaceFirst("0186", "056");
                break;
            case "0188": phone = phone.replaceFirst("1688", "058");
                break;

            //Gmobile
            case "0199": phone = phone.replaceFirst("0199", "059");
                break;

        }

        return phone;
    }
}
