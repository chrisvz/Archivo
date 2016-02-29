package model;

import java.util.UUID;

/**
 * Created by Chris on 2/29/2016.
 */
public class Goal {

    private UUID uuid;
    private String title;
    private String description;
    private boolean successful;

    public Goal(String title,String description,boolean successful) {
        this.title  = title;
        this.description = description;
        this.successful = successful;
        uuid = UUID.randomUUID();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", successful=" + successful +
                '}';
    }
    
}
