import java.util.Date;

public class User {
    /**
     * Класс пользователя, в ктором содержится вся информация,  в т.ч и его настройки
     */
    private String login;
    private String password;
    private String sex;
    private String name;
    private String surname;
    private String filePath;
    private Date dateLogin;
    private Date dateExit;
    private boolean result;
    private int resultNumeric;

    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public Date getDateExit() {
        return dateExit;
    }

    public void setDateExit(Date dateExit) {
        this.dateExit = dateExit;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getResultNumeric() {
        return resultNumeric;
    }

    public void setResultNumeric(int resultNumeric) {
        this.resultNumeric = resultNumeric;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    private int userid;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
