package entities;

public class Book {
    private int id;
    private String name;

    public Book(){

    }
    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int hashCode(){
        int result=14;
        result+=5*result+Integer.hashCode(id);
        result+=5*result+hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
