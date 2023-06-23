package ObjectMapping;

public class UserParams {
    public String email;
    public String password;

    public void setPass(String pas)
    {
        this.password = pas;
    }
    public void setEmail(String mail)
    {
        this.email = mail;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPass()
    {
        return this.password;
    }
}
