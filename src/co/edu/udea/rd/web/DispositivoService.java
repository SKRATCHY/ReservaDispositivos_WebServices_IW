package co.edu.udea.rd.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.rd.bl.DispositivoBL;
import co.edu.udea.rd.dto.Dispositivo;
import co.edu.udea.rd.exception.MyException;
import co.edu.udea.rd.web.dto.DispositivoWS;

@Component
@Path("Dispositivo")
public class DispositivoService {

	@Autowired
	DispositivoBL dispositivoBL;
	
	Logger log = Logger.getLogger(this.getClass());

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("registrar")
	public String registrarDispositivo(@QueryParam("idDispositivo") String idDispositivo,
			@QueryParam("idTipoDispositivo") int idTipoDispositivo, @QueryParam("stock") int stock,
			@QueryParam("disponibles") int disponibles) {
		try {
			dispositivoBL.registrarDispositivo(idDispositivo, idTipoDispositivo, stock, disponibles);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Dispositivo creado correctamente";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("modificar")
	public String modificarDispositivo(@QueryParam("idDispositivo") String idDispositivo,
			@QueryParam("idTipoDispositivo") int idTipoDispositivo, @QueryParam("stock") int stock,
			@QueryParam("disponibles") int disponibles) {
		try {
			dispositivoBL.modificarDispositivo(idDispositivo, idTipoDispositivo, stock, disponibles);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Dispositivo modificado correctamente";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<DispositivoWS> listarDispositivos() {

		List<DispositivoWS> lista = new ArrayList<>();
		try {
			for (Dispositivo dispositivo : dispositivoBL.listarDispositivos()) {
				DispositivoWS dispositivoWS = new DispositivoWS();
				dispositivoWS.setIdDispositivo(dispositivo.getIdDispositivo());
				dispositivoWS.setIdTipoDispositivo(dispositivo.getTipoDispositivo().getIdTipoDispositivo());
				dispositivoWS.setStock(dispositivo.getStock());
				dispositivoWS.setDisponibles(dispositivo.getDisponibles());
				
				lista.add(dispositivoWS);
			}
		} catch (MyException e) {
			log.error(e.getMessage());
			return null;
		}
		return lista;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("eliminar")
	public String eliminarDispositivo(@QueryParam("idDispositivo") String idDispositivo){
		try {
			dispositivoBL.eliminarDispositivo(idDispositivo);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Dispositivo eliminado correctamente";
		
	}
	

}
