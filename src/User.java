public class User {
    private String username;
    private String password;
    private String role;
    //private boolean admin;




    public User(String username, String password, String role)
    {
        this.username = username;
        this.password = password;
        this.role = role.toLowerCase();
    }


    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole(){
        return role;
    }

    public boolean isManager()
    {
        return role.equals("manager");
    }

    public boolean isAdmin()
    {
        return role.equals("admin");
    }


    

    public boolean isUser()
    {
        return role.equals("user");
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    // public void setAdmin(boolean admin)
    // {
    //     this.admin = admin;
    // }

    public String toString()
    {
        return username + "(" + role + ")";
    }


}
