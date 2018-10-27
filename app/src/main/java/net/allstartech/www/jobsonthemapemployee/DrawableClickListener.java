package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by John Carter on 8/24/2017.
 */
public interface DrawableClickListener {

    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target);
}