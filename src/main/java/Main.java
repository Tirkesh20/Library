import entities.Author;
import entities.Book;
import entities.Genre;
import entityCreation.AuthorCreation;
import entityCreation.BookCreation;
import exceptions.ServiceException;
import scanners.Scanners;
import services.AuthorBookConnectionService;
import services.AuthorService;
import services.BookService;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static AuthorBookConnectionService authorBookConnectionService=new AuthorBookConnectionService();
    private static AuthorService authorService=new AuthorService();
    private static BookService bookService=new BookService();

    public static void main(String[] args) throws ServiceException {
        System.out.println("1 add book");
        System.out.println("2 create author");
        System.out.println("3 add author to book");
        System.out.println("4 select books from genre");
        System.out.println("5 select book of author");
        System.out.println("6 delete book");
        System.out.println("7 delete author");
        System.out.println("8 author of book");
        Scanner scanner=new Scanner(System.in);
        boolean flag=false;
        int choice=scanner.nextInt();
        do {
            switch (choice){
                case 1:{
                    BookCreation bookCreation=new BookCreation();
                   bookService.insertBook(bookCreation.createBook());
                   break;
                }
                case 2:{
                    AuthorCreation authorCreation=new AuthorCreation();
                   authorService.insertAuthor(authorCreation.createAuthor());
                   break;
                }
                case 3:{
                    System.out.println("enter name of author");
                   Author author=authorService.findByName(Scanners.StringScanner());
                    System.out.println("enter name of book");
                    Book book=bookService.findBooks(Scanners.StringScanner());
                    authorBookConnectionService.authorsBook(author,book);
                    break;
                }
                case 4:{
                    System.out.println("select genre");
                    System.out.println(Arrays.toString(Genre.values()));
                    System.out.println(bookService.findByGenre(Scanners.StringScanner()));
                    break;
                }
                case 5:{
                    System.out.println(bookService.findAuthorsBook(Scanners.StringScanner()));
                }
                case 6:{
                    System.out.println("enter name of book");
                    Book book=bookService.findBooks(Scanners.StringScanner());
                    if (book!=null){
                    bookService.deleteBook(book.getId());
                    }else {
                        System.out.println("no such book found");
                    }
                    break;
                }
                case 7:{
                    System.out.println("enter name of author");
                    Author author=authorService.findByName(Scanners.StringScanner());
                    authorService.deleteAuthor(author.getId());
                }
                case 8:{
                    flag=true;
                }
            }
        }while (!flag);
    }
}
