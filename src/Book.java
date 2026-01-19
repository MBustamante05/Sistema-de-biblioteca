public class Book {
    private int id;
    private String title;
    private String author;
    private String description;
    private boolean available;

    public Book (int id, String title, String author, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Información del libro:\nID: " + id + " - Título: " + title + " - Author: " + author + "\nDescripción: " + description;
    }
}
