package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by John Carter on 7/7/2017.
 */

public class Items {

   /* public int distance;
    public String name, icon, categories;
    double latitude, longitude;
    static String check;*/
    String date,time;
    int imgId;

    public Items()
    {
        super();
    }

    public Items(String date, String time, int imgId)
    {
        this.date = date;
        this.time = time;
        this.imgId = imgId;
    }



}
