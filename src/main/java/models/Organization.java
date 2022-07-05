package models;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Organization {
    private Long id;
    private String title;
    private String address;
    private LocalDate creationDate;

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1000);

    public Organization(String title, String address) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.title = title;
        this.address = address;
        this.creationDate = LocalDate.now();
    }

    public Organization(Long id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.creationDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
