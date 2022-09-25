package pe.isil.seguridad;

import com.sun.jdi.IntegerType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);


        //Cargar driver
        Class.forName("com.mysql.jdbc.Driver");

        //Crear conexion

        Connection conexion =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/EP1",
                        "root", "");

        //Crear Statement
        Statement stCreate = conexion.createStatement();
        int filasAfectas =
                stCreate.executeUpdate("insert  into Clientes(name,lastname,username,pass,tipDoc,nroDoc,enable) values('Piero','Rosales','DNI77584930','123456','DNI','77584930',1)");

        System.out.println("Filas Afectas: " + filasAfectas);

        Statement stmt = conexion.createStatement();
        ResultSet resultSet = stmt.executeQuery("Select * from Clientes");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + "\n" +
                    resultSet.getString("lastname") + "\n" +
                    resultSet.getString("nroDoc"));
        }

        //PreparedStatement
        PreparedStatement preparedStatement = conexion.prepareStatement("Delete  from Clientes where nroDoc=?");
        preparedStatement.setString(1, "56384920");

        int delnum = preparedStatement.executeUpdate();

        System.out.println("Filas Afectas: " + delnum);

        Statement stmt2 = conexion.createStatement();
        ResultSet resultSet2 = stmt2.executeQuery("Select * from Clientes");

    //---CallableStatement
            CallableStatement callsp = conexion.prepareCall("{call listarAllClients()}");


            callsp.executeQuery();

            ResultSet resultado = callsp.executeQuery();


            while (resultado.next()) {
                System.out.println(resultado.getString("name") + "\n" +
                        resultado.getString("lastname") + "\n" +
                        resultado.getString("nroDoc"));
            }
        }
    }




