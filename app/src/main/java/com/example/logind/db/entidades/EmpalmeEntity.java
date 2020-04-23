package com.example.logind.db.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "empalmes")
public class EmpalmeEntity {
    @PrimaryKey(autoGenerate = true)
    public int id_e;
    public String nombre_e;
    public String tramo;
    public double distancia;
    public String numero_poste;
    public String observacion;

    public EmpalmeEntity(String nombre_e, String tramo, double distancia, String numero_poste, String observacion) {
        this.nombre_e = nombre_e;
        this.tramo = tramo;
        this.distancia = distancia;
        this.numero_poste = numero_poste;
        this.observacion = observacion;
    }

    public int getId_e() {
        return id_e;
    }

    public void setId_e(int id_e) {
        this.id_e = id_e;
    }

    public String getNombre_e() {
        return nombre_e;
    }

    public void setNombre_e(String nombre_e) {
        this.nombre_e = nombre_e;
    }

    public String getTramo() {
        return tramo;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getNumero_poste() {
        return numero_poste;
    }

    public void setNumero_poste(String numero_poste) {
        this.numero_poste = numero_poste;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
