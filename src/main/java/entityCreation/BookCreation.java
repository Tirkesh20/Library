package entityCreation;

import entities.Book;
import entities.Genre;
import scanners.Scanners;

public class BookCreation {

    public Book createBook(){
        System.out.println("enter name of book");
        Book book=new Book();
        book.setName(Scanners.StringScanner());
        String string=Scanners.StringScanner();
        book.setGangre(Genre.valueOf(string));
        return book;
    }
}
