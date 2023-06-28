package Features;

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
    private String announcementMessage;

    public static ArrayList<Announcement> announcementList = new ArrayList<>();
    private static File announcementListFile = new File("File/announcementList.json");

    public Announcement(String date, String announcementMessage) {
        this.date = date;
        this.announcementMessage = announcementMessage;
    }

    public String getDate() {
        return date;
    }

    public String getAnnouncementMessage() {
        return announcementMessage;
    }

    public static ArrayList<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAnnouncement(String announcementMessage) {
        this.announcementMessage = announcementMessage;
    }

    public static void setAnnounceList(ArrayList<Announcement> announceList) {
        Announcement.announcementList = announceList;
    }

    public static void addAnnouncement(Announcement announcement) {
        announcementList.add(announcement);
    }

    public static void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(announcementListFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Announcement announcement : announcementList) {
                oos.writeObject(announcement);
        }
        oos.close();
    }

    public static void readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(announcementListFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        while (fis.available() > 0) {
            Announcement announcement = (Announcement) ois.readObject();
            announcementList.add(announcement);
        }
        ois.close();
    }
}
