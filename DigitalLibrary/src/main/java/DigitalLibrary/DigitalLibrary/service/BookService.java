package DigitalLibrary.DigitalLibrary.service;

import DigitalLibrary.DigitalLibrary.model.Book;
import DigitalLibrary.DigitalLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        book.setCreatedDate(LocalDateTime.now()); // Set creation time
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book newBookData) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBookData.getTitle());
            book.setAuthor(newBookData.getAuthor());
            book.setGenre(newBookData.getGenre());
            book.setAvailable(newBookData.isAvailable());
            book.setUpdatedDate(LocalDateTime.now());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
