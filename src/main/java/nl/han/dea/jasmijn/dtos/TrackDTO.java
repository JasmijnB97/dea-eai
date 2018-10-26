package nl.han.dea.jasmijn.dtos;

public class TrackDTO {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int paycount;
    private String publicationDate;
    private String description;
    private boolean offlineAvailable;

    public int getId() {
        return id;
    }

    public TrackDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TrackDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public TrackDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public TrackDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public String getAlbum() {
        return album;
    }

    public TrackDTO setAlbum(String album) {
        this.album = album;
        return this;
    }

    public int getPaycount() {
        return paycount;
    }

    public TrackDTO setPaycount(int paycount) {
        this.paycount = paycount;
        return this;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public TrackDTO setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TrackDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public TrackDTO setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
        return this;
    }
}
