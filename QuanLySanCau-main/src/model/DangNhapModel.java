package model;

public class DangNhapModel {
    private String userName;
    private String passWord;
    
    public DangNhapModel() { }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    
    public boolean kiemTra(String userName, String passWord) {
        return ("Khánh".equals(userName) && "1".equals(passWord)) ||
           ("Quỳnh".equals(userName) && "1".equals(passWord)) ||
           ("Hiệp".equals(userName) && "1".equals(passWord)) ||
           ("Khanh".equals(userName) && "1".equals(passWord));
    }
}
