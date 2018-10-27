package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by John Carter on 7/8/2017.
 */

public class MissionItems {

    String profession,level,time,distance;
    public MissionItems()
    {
        super();
    }

    public MissionItems(String profession, String level, String distance, String time)
    {
        this.profession = profession;
        this.level = level;
        this.time = time;
        this.distance = distance;

    }
}
