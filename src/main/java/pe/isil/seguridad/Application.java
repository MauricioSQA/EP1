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

        Connection conexion=
                DriverManager.getConnection("jdbc:mysql://localhost:3306/moduloseg",
                                            "root","");

        //Crear Statement
        //Statement st =conexion.createStatement();
        //PreparedStatement pt = conexion.prepareStatement("Select * from users where id =?");
        //pt.setInt(1,3);

        //CallableStatement
        CallableStatement callsp  = conexion.prepareCall("{call validarLogin(?,?,?)}");
        callsp.setString(1,"DNI46299021");
        callsp.setString(2,"123456");
        callsp.registerOutParameter(3, Types.INTEGER);


        //Ejecutar Sentencia
        callsp.executeQuery();
        int resultado = callsp.getInt(3);

        if(resultado == 1){
            System.out.println("Login Exitoso");
        }else
        {
            System.out.println("Usuario o clave invalido");
        }

        CallableStatement callSp2 = conexion.prepareCall("{Call listarAllUsers()}");
        ResultSet resultadoSp2 = callSp2.executeQuery();

        //Recorrer el resultado
        while(resultadoSp2.next())
            System.out.println(resultadoSp2.getString("username"));


        CallableStatement callSp3 = conexion.prepareCall("{Call listarUserPerId(?)}");
        callSp3.setInt(1,2);
        ResultSet resultadoSP3 = callSp3.executeQuery();

        while(resultadoSP3.next()){
            System.out.println(resultadoSP3.getString("name"));
        }
        }
    }


