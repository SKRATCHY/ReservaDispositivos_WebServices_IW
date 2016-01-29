package co.edu.udea.rd.web;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.rd.bl.UsuarioBL;
import co.edu.udea.rd.dto.Dispositivo;
import co.edu.udea.rd.dto.Usuario;
import co.edu.udea.rd.exception.MyException;
import co.edu.udea.rd.web.dto.DispositivoWS;
import co.edu.udea.rd.web.dto.UsuarioWS;

@Component
@Path("Usuario")
public class UsuarioService {

	@Autowired
	UsuarioBL usuarioBL; // Se inyecta un objeto de la clase UsuarioBL para
							// acceder a los metodos de la logica de negocio

	Logger log = Logger.getLogger(this.getClass()); // Para lo del error

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String autenticar(@QueryParam("username") String username, @QueryParam("password") String password) {
		try {
			return usuarioBL.loginUsuario(username, password);

		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("crear")
	public String crearUsuario(@QueryParam("username") String username, @QueryParam("password") String password,
			@QueryParam("passwordConfirmacion") String passwordConfirmacion, @QueryParam("nombre") String nombre,
			@QueryParam("apellido") String apellido, @QueryParam("tipoDocumento") String tipoDocumento,
			@QueryParam("nroDocumento") String nroDocumento, @QueryParam("correo") String correo,
			@QueryParam("idRol") int idRol, @QueryParam("telefono") String telefono,
			@QueryParam("celular") String celular) {
		try {
			usuarioBL.registrarUsuario(username, password, passwordConfirmacion, nombre, apellido, tipoDocumento,
					nroDocumento, correo, idRol, telefono, celular);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Usuario creado correctamente";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("actualizar")
	public String actualizarUsuario(@QueryParam("username") String username, @QueryParam("password") String password,
			@QueryParam("passwordConfirmacion") String passwordConfirmacion, @QueryParam("nombre") String nombre,
			@QueryParam("apellido") String apellido, @QueryParam("tipoDocumento") String tipoDocumento,
			@QueryParam("nroDocumento") String nroDocumento, @QueryParam("correo") String correo,
			@QueryParam("telefono") String telefono, @QueryParam("celular") String celular) {
		try {
			usuarioBL.actualizarInformacionPersonalUsuario(username, password, passwordConfirmacion, nombre, apellido,
					correo, telefono, celular);
		} catch (MyException e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
		return "Usuario actualización correctamente";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<UsuarioWS> listarUsuarios() {

		List<UsuarioWS> lista = new ArrayList<>();
		try {
			for (Usuario usuario : usuarioBL.listarUsuarios()) {
				UsuarioWS usuarioWS = new UsuarioWS();
				usuarioWS.setApellido(usuario.getApellido());
				usuarioWS.setCelular(usuario.getCelular());
				usuarioWS.setCorreo(usuario.getCorreo());
				usuarioWS.setNombre(usuario.getNombre());
				usuarioWS.setNroDocumento(usuario.getNroDocumento());
				// usuarioWS.setRol(usuario.getRol());
				usuarioWS.setTipoRol(usuario.getRol().getTipoRol());
				usuarioWS.setTelefono(usuario.getTelefono());
				usuarioWS.setTipoDocumento(usuario.getTipoDocumento());
				usuarioWS.setUsername(usuario.getUsername());

				lista.add(usuarioWS);
			}
		} catch (MyException e) {
			log.error(e.getMessage());
			return null;
		}
		return lista;
	}

}
