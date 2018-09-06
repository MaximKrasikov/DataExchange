package sql.demo;

import com.sun.rmi.rmid.ExecOptionPermission;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class StockExchangeDB {
    public static void main(String[] args) throws Exception {

        Class.forName("org.h2.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:h2:/c:/JavaPrj/SQLDemo/db/stockExchange");

        Statement st = conn.createStatement();
        st.execute("DROP TABLE STUDENTS IF EXISTS");//удалить таблицу если есть
        st.execute("CREATE TABLE STUDENTS(ID INT PRIMARY KEY, NAME VARCHAR(255),  HOBBY VARCHAR(255));");// создать таблицу со столбцами
        st.execute("INSERT INTO STUDENTS VALUES(1, 'Ivanov', '');");// помещаем значения
        st.execute("INSERT INTO STUDENTS VALUES(2, 'Petrov', 'Swim');");
        st.execute("INSERT INTO STUDENTS VALUES(3, 'Sidorov', '');");
        st.execute("INSERT INTO STUDENTS VALUES(4, 'Andreev', 'Wrestling');");

        ResultSet result = st.executeQuery("SELECT * FROM STUDENTS");// формируем запрос

        String id = "", name = "", hobby = "";

        while (result.next()) {
            // формирование переменных
            id = result.getString("ID");
            name = result.getString("NAME");
            hobby = result.getString("HOBBY");


            if (hobby.equals("")) {
                hobby = "Нет хобби";
            }
            System.out.printf("%3s | %-10s | %-10s \n", id, name, hobby);
        }
    }
}
