public class User {
    private String username;
    private String password;
    private boolean admin;




    public User(String username, String password, boolean admin)
    {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }


    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }



}
