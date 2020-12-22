package APITests;


import java.util.List;

class Authority{
    public String authority;
}

class Friend{
    public int id;
    public String role;
    public String email;
    public String password;
    public String name;
    public Object friends;
    public List<String> badges;
    public boolean enabled;
    public String username;
    public List<Authority> authorities;
    public boolean accountNonExpired;
    public boolean credentialsNonExpired;
    public boolean accountNonLocked;
}

class UserProfile{
    public int id;
    public String role;
    public String email;
    public String password;
    public String name;
    public List<Friend> friends;
    public List<String> badges;
    public boolean enabled;
    public String username;
    public List<Authority> authorities;
    public boolean accountNonExpired;
    public boolean credentialsNonExpired;
    public boolean accountNonLocked;
}