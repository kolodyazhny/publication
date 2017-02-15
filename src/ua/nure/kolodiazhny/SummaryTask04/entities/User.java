package ua.nure.kolodiazhny.SummaryTask04.entities;

/**
 * Класс, описывающий сущность Пользователь.
 */
public class User {
    /**
     * id пользователя из БД.
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Логин пользователя (адрес электронной почты).
     */
    private String login;
    /**
     * Пароль пользователя.
     */
    private String password;
    /**
     * Поле указывающее на роль пользователя:
     * true - администратор;
     * false - читатель.
     */
    private boolean isAdmin;

    public User(){}

    public User(int id, String name, String login, String password, boolean isAdmin) {
        this.setId(id);
        this.setLogin(login);
        this.setName(name);
        this.setPassword(password);
        this.setAdmin(isAdmin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("name = ").append(name);
        sb.append(", login = ").append(login);
        sb.append(", role = ");
        if (isAdmin){
            sb.append("admin");
        }else{
            sb.append("customer");
        }
        sb.append("}");
        return sb.toString();
    }
}