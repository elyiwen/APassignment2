package Controller.Announcements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Announcement {

    private String date;
    private String announcement;

    public static ArrayList<Announcement> announcementList = new ArrayList<>();
    private static File announcementListFile = new File("announcementList.txt");

    public Announcement(String date, String announcement) {
        this.date = date;
        this.announcement = announcement;
    }

    public String getDate() {
        return date;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public static ArrayList<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
    
    public static void addAnnouncement(Announcement announcement) {
        announcementList.add(announcement);
    }

    public static void setAnnounceList(ArrayList<Announcement> announceList) {
        Announcement.announcementList = announceList;
    }

    public static void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(announcementListFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Announcement announcement : announcementList) {
                oos.writeObject(announcement);
        }
        oos.close();
    }

    public void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(announcementListFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0) {
            Announcement announcement = (Announcement) ois.readObject();
            announcementList.add(announcement);
        }
        ois.close();
    }
}
