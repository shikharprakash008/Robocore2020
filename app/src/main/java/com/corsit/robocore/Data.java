package com.corsit.robocore;

import java.util.ArrayList;

public class Data {
    public static ArrayList<listitemsponser2> givedata()
    {
        ArrayList<listitemsponser2>  listitemsponser2= new ArrayList<>();

        String app_name[]={"CORSIT","ICE & SPICE","GREEN TRENDS","MERIDIEN","SAICO","SKY LODGE"};

        int app_logo[]={R.drawable.logo,R.drawable.ice,R.drawable.greentrnd,R.drawable.meridien,R.drawable.saico,R.drawable.sky};
        for(int i=0;i<app_name.length;i++){


            listitemsponser2 listitemsponser21=new listitemsponser2();
            listitemsponser21.app_name= app_name[i];
            listitemsponser21.app_logo= app_logo[i];
            listitemsponser2.add(listitemsponser21);
        }

return listitemsponser2;

    }


}
