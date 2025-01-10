public class Book
{
    private String author;
    private String title;
    private String isbn;
    private String description;
    private String genre;
    private int numcopies;
    //private int availablecopies;





    public Book(String title, String author, String genre, String isbn, String description, int numcopies)
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.description = description;
        this.numcopies = 1;
        //this.availablecopies = 1;
        
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
        return numcopies > 0;
    }

    public int getNumCopies()
    {
        return numcopies;
    } 

    // public int getAvailableCopies()
    // {
    //     return availablecopies;
    // }

    
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
        if(numcopies > 0)
        {
            numcopies--;
        }
    }


    public void ReturnCopy()
    {
        numcopies++;
    }

    public void addCopy() {
        numcopies++;  // Increment available copies as well
        //availablecopies++;
    }



    public String toString()
    {
        return String.format("%s by %s  Genre: %s (ID: %s) - %d copies available", title, author, genre, isbn, numcopies);
    }

}