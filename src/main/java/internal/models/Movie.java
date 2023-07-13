package internal.models;

import java.time.LocalDate;

public class Movie {
    public int id;
    public String title;
    public LocalDate date;
    public String coverUrl;

    public Movie(String title, LocalDate date, String coverUrl, int id) {
        this.id       = id;
        this.title    = title;
        this.date     = date;
        this.coverUrl = coverUrl;
    }

    public Movie(String title, LocalDate date, String coverUrl) {
        this.title    = title;
        this.date     = date;
        this.coverUrl = coverUrl;
    }
}
