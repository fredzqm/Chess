package viewServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Helper class for providing sample title for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class Job {
    public static final String PATH = "job";
    public static final String RECRUITER_KEY = "recruiterKey";

    @Exclude
    private String key;

    public Job() {
        // required constructor for Jackson
    }

    public static DatabaseReference getReference() {
        return FirebaseDatabase.getInstance().getReference().child(PATH);
    }

    public static Job newInstance() {
        return null;
    }

    // ---------------------- static methods to load data

    public Job(String id, String title, String city, String company, String details) {
    }

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Job> ITEMS = new ArrayList<Job>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Job> ITEM_MAP = new HashMap<String, Job>();

    private static final int COUNT = 25;

}
