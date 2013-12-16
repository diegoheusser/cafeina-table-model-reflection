//Author.java
package model;

/**
 * @author Diego Heusser
 */
public class Author {
    private int code;
    private String name;

    @Getter(column = 0,name = "Code",editable = false)
    public int getCode() {
        return code;
    }

    @Setter(column = 0)
    public void setCode(int code) {
        this.code = code;
    }
    
    @Getter(column = 1, name = "Name", editable = true)
    public String getName() {
        return name;
    }

    @Setter(column = 1)
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
