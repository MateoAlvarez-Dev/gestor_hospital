package database;

public enum ConfigDB {
    USERNAME("sql10696243"),
    PASSWORD("XuAv1YhmBq"),
    URL("jdbc:mysql://sql10.freemysqlhosting.net/sql10696243");

    private String value;

    ConfigDB(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
