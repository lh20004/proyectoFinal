package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Servicio;
import org.postgresql.Driver;

/**
 *
 * @author guill
 */
public class ServicioImagenDao {

    private String dbURL = "jdbc:postgresql://localhost:5432/peluqueriabarberia";
    private String dbUser = "postgres";
    private String dbPass = "root";

    public ServicioImagenDao() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String insertarImagen(Servicio servicio) {
        String message = null;
        Connection coon = null;

        try {
            coon = DriverManager.getConnection(dbURL, dbUser, dbPass);
            coon.setAutoCommit(false);

            String sql = "INSERT INTO servicio(servicio, descripcion, precio, estado, imagen) VALUES (?,?,?,?,?)";
            PreparedStatement statement = coon.prepareStatement(sql);
            statement.setString(1, servicio.getServicio());
            statement.setString(2, servicio.getDescripcion());
            statement.setDouble(3, servicio.getPrecio());
            statement.setString(4, servicio.getEstado());

            if (servicio.getImagen() != null) {
                statement.setBytes(5, servicio.getImagen());
            } else {
                System.out.println("Input stream es null");
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Imagen cargada y guardada con éxito.";
            }

            coon.commit();
        } catch (SQLException ex) {
            message = "ERROR" + ex.getMessage();
            ex.printStackTrace();

            if (coon != null) {
                try {
                    coon.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (coon != null) {
                try {
                    coon.setAutoCommit(true);
                    coon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return message;
    }

    public List<Servicio> getAllServicios() {
        List<Servicio> servi = new ArrayList<>();
        String sql = "SELECT idservicio, servicio, descripcion, precio, estado FROM servicio";

        try ( Connection con = DriverManager.getConnection(dbURL, dbUser, dbPass);  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Servicio ser = new Servicio();
                ser.setIdServicio(rs.getInt("idservicio"));
                ser.setServicio(rs.getString("servicio"));
                ser.setDescripcion(rs.getString("descripcion"));
                ser.setPrecio(rs.getDouble("precio"));
                ser.setEstado(rs.getString("estado"));
                servi.add(ser);

            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return servi;
    }
    
    public Servicio getServiciosById(int id){
        Servicio deServ = null;
        String sql = "SELECT idservicio, servicio, descripcion, precio, estado, imagen FROM servicio WHERE idservicio = ?";
    
        try (Connection con = DriverManager.getConnection(dbURL, dbUser, dbPass);
             PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    int idServicio = rs.getInt("idservicio");
                    String servicio = rs.getString("servicio");
                    String descripcion = rs.getString("descripcion");
                    Double precio = rs.getDouble("precio");
                    String estado = rs.getString("estado");
                    byte[] imgData = rs.getBytes("imagen");
                    
                    deServ = new Servicio(idServicio, servicio, descripcion, precio, estado, imgData);
                }
            } catch (SQLException e) {
            e.printStackTrace();
            }
        } catch (Exception e) {
        }
        return deServ;
    }
}
