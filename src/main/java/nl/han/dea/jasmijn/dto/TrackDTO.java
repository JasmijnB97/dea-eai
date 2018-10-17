package nl.han.dea.jasmijn.dto;

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

    public TrackDTO(){

    }
    public TrackDTO(int id,String title, String performer, int duration, String album,
                    int paycount, String publicationDate, String description, boolean offlineAvailable){
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.paycount = paycount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getPaycount() {
        return paycount;
    }

    public void setPaycount(int paycount) {
        this.paycount = paycount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
