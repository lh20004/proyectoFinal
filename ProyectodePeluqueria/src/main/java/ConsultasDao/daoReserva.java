/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConsultasDao;

import entidades.Empleado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author javier
 */
public class daoReserva {
    public static String persistenceName = "peluqueriaPersistence";
    
    public static Empleado getEmpleadoPorIdCargo(Integer id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceName);/**Aqui se instancia la persistencia la 'persistenceName' dependera de la persistencia que tengan en su maquina*/
        EntityManager em = emf.createEntityManager();/**Crea una persistencia administradora*/
        TypedQuery consulta = em.createQuery("select e from Cargo as c inner join Empleado as e on c.empleadoList = e where c.idcargo = "+id, Empleado.class);/**La consulta es la misma que se crea en posque pero los nombre tienen que ser iguales a las entidades que estan en el paquete entidades*/
        List<Empleado> lista = consulta.getResultList();/**Se ejecuta y extrae la consulta*/
        Empleado empleado  = null;/**En este caso como solo se quiere obtener un unico registro creamos un objeto de ese tipo*/
        for(Empleado e: lista){
            empleado = e;
        }
        em.close();
        emf.close();
        return empleado;/**Retornamos el objeto obtenido*/
    }
}
