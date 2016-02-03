package co.edu.udea.rd.web.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DispositivoWS {
	
	private String idDispositivo;
	private int idTipoDispositivo;
	private int stock;
	private int disponibles;
	
	public String getIdDispositivo() {
		return idDispositivo;
	}
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	public int getIdTipoDispositivo() {
		return idTipoDispositivo;
	}
	public void setIdTipoDispositivo(int idTipoDispositivo) {
		this.idTipoDispositivo = idTipoDispositivo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDisponibles() {
		return disponibles;
	}
	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}
	
}
