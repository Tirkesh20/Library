package entityCreation;

import entities.Author;
import scanners.Scanners;

public class AuthorCreation {

    public Author createAuthor(){
        System.out.println("enter name of author");
        Author author=new Author();
        author.setName(Scanners.StringScanner());
        return author;
    }
}
