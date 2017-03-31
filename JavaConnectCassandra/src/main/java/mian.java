
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Damian Stępniak
 */
public class mian {

    public static void main(String[] args) {

        Cluster c = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = c.connect();
        session.execute("USE test");
        session.execute("insert into user (id, imie) values(1, 'Seba')");
        ResultSet rs = session.execute("select * from user");
        Row row = rs.one();
        System.out.println(row);

    }
}
