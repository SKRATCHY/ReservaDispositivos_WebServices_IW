package co.edu.udea.rd.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.rd.bl.PrestamoBL;
import co.edu.udea.rd.exception.MyException;

@Component
@Path("Prestamo")
public class PrestamoService {

	@Autowired
	PrestamoBL prestamoBL;

	Logger log = Logger.getLogger(this.getClass());

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("actualizarEstado")
	public String actualizarEstadoPrestamo(@QueryParam("username") String username,
			@QueryParam("idDispositivo") String idDispositivo, @QueryParam("estado") String estado) {
		try {
			prestamoBL.actualizarEstadoPrestamo(username, idDispositivo, estado);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Estado del prestamo modificado correctamente";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("solicitar")
	public String solicitarPrestamo(@QueryParam("idDispositivo") String idDipositivo,
			@QueryParam("username") String username, @QueryParam("fechaInicialPrestamo") String fechaInicialPrestamo,
			@QueryParam("fechaFinalPrestamo") String fechaFinalPrestamo) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.US);

			prestamoBL.solicitarPrestamo(idDipositivo, username, formatoFecha.parse(fechaInicialPrestamo),
					formatoFecha.parse(fechaFinalPrestamo));

		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error en la fecha";
		}
		return "Prestamo solicitado correctamente";
	}
}
