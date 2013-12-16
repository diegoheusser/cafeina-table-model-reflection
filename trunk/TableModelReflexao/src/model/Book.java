//Book.java
package model;

/**
 * @author Diego Heusser
 */
public class Book {
    private int code;
    private String title;
    private Author author;

    @Getter(column = 0, name = "Code", editable = false)
    public int getCode() {
        return code;
    }

    @Setter(column = 0)
    public void setCode(int code) {
        this.code = code;
    }

    @Getter(column = 1, name = "Title", editable = true)
    public String getTitle() {
        return title;
    }

    @Setter(column = 1)
    public void setTitle(String title) {
        this.title = title;
    }

    @Getter(column = 2, name = "Author", editable = true)
    public Author getAuthor() {
        return author;
    }

    @Setter(column = 2)
    public void setAuthor(Author author) {
        this.author = author;
    }
    
}
