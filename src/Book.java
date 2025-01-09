public class Book
{
    private String author;
    private String title;
    private String isbn;
    private String description;
    private String genre;
    private int numcopies;
    private int availablecopies;





    public Book(String title, String author, String genre, String isbn)
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.numcopies = 1;
        this.availablecopies = 1;
        
    }


    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public String getDescription()
    {
        return description;
    }

    public boolean isAvailable()
    {
        return availablecopies > 0;
    }

    public int getNumCopies()
    {
        return numcopies;
    } 

    public int getAvailableCopies()
    {
        return availablecopies;
    }

    
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setAuthor(String author)
    {
        this.author= author;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }


    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public void BorrowCopy()
    {
        if(availablecopies > 0)
        {
            availablecopies--;
        }
    }


    public void ReturnCopy()
    {
        if(availablecopies < numcopies)
        {
            availablecopies++;
        }
    }

    public void addCopy() {
        numcopies++;
        availablecopies++; // Increment available copies as well
    }



    public String toString()
    {
        return String.format("%s by %s  Genre: %s (ID: %s) - %d copies available", title, author, genre, isbn, numcopies);
    }

}