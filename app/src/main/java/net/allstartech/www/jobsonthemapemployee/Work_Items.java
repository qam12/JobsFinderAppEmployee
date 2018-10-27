package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by John Carter on 7/7/2017.
 */

public class Work_Items {

    String profession,level,time;
    int img;
    public Work_Items()
    {
        super();
    }

    public Work_Items(int img, String profession, String level, String time)
    {
        this.profession = profession;
        this.level = level;
        this.time = time;
        this.img = img;

    }

}
