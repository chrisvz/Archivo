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

    public Goal() {
        this(UUID.randomUUID());
    }
    public Goal(UUID uuid) {
        this.uuid = uuid;
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
