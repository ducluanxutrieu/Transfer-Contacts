package com.example.tranducluan.contacts;

public class CheckNhaMang {
    public String KiemTraNhaMang(String number){
        String nhaMang = null;
        String firtNumber2 = number.substring(0, 2);
        String firtNumber3 = number.substring(0, 3);
        if(firtNumber2.equals("03")){
            nhaMang = "Viettel";
        }else if(firtNumber2.equals("08")){
            nhaMang = "Vinaphone";
        }else if(firtNumber2.equals("07")){
            nhaMang = "Mobifone";
        }else if(firtNumber2.equals("05")){
            if(firtNumber3.equals("056")||firtNumber3.equals("058")){
                nhaMang = "Vietnamobile";
            }
            else if(firtNumber3.equals("059")){
                nhaMang = "G-Mobile";
            }
        }else if(firtNumber3.equals("086") || firtNumber3.equals("096") || firtNumber3.equals("097") || firtNumber3.equals("098")){
            nhaMang = "Viettel";
        }else if(firtNumber3.equals("088") || firtNumber3.equals("091") || firtNumber3.equals("094")){
            nhaMang = "Vinaphone";
        }else if(firtNumber3.equals("089") || firtNumber3.equals("090") || firtNumber3.equals("093")){
            nhaMang = "Mobifone";
        }else if(firtNumber3.equals("092")){
            nhaMang = "Vietnamobile";
        }else if (firtNumber3.equals("099")){
            nhaMang = "G-Mobile";
        }

        return nhaMang;
    }
}
