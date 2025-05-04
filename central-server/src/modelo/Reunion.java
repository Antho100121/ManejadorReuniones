package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
//import java.util.Objects;

public class Reunion implements Serializable {
    private String tema;
    private String organizador;
    private List<String> invitados;
    private String lugar;
    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Reunion(String tema, String organizador, List<String> invitados, String lugar, LocalDateTime inicio, LocalDateTime fin) {
        this.tema = tema;
        this.organizador = organizador;
        this.invitados = invitados;
        this.lugar = lugar;
        this.inicio = inicio;
        this.fin = fin;
    }

    public String getOrganizador() {
        return organizador;
    }

    public List<String> getInvitados() {
        return invitados;
    }

    public String getTema() {
        return tema;
    }

    public String getLugar() {
        return lugar;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return tema + " en " + lugar + " desde " + inicio + " hasta " + fin;
    }
}