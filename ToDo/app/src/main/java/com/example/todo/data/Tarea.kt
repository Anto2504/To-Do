package com.example.todo.data

import android.os.Parcel
import android.os.Parcelable

data class Tarea(
    val prioridad: String,
    val estado: String,
    val fecha: String,
    val titulo: String,
    val descripcion: String,
    val estadoDetalle: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(prioridad)
        parcel.writeString(estado)
        parcel.writeString(fecha)
        parcel.writeString(titulo)
        parcel.writeString(descripcion)
        parcel.writeString(estadoDetalle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarea> {
        override fun createFromParcel(parcel: Parcel): Tarea {
            return Tarea(parcel)
        }

        override fun newArray(size: Int): Array<Tarea?> {
            return arrayOfNulls(size)
        }
    }
}

