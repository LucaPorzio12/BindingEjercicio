package luca.porzio.bindingejercicio.modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private String direccion;
    private int numero;
    private String cp;
    private String ciudad;
    private String provincia;
    private float valoracion;

    public Inmueble() {
    }

    public Inmueble(String direccion, int numero, String cp, String ciudad, String provincia, float valoracion) {
        this.direccion = direccion;
        this.numero = numero;
        this.cp = cp;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.valoracion = valoracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "direccion='" + direccion + '\'' +
                ", numero=" + numero +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", valoracion=" + valoracion +
                '}';
    }
}
