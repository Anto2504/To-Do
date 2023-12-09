package com.example.todo.data

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.data.Tarea

class DetalleTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_tarea)

        val tarea = intent.getParcelableExtra<Tarea>("tarea")

        if (tarea != null) {
            mostrarDetalles(tarea)
        } else {
            // Handle the case when no task is passed
            finish()
        }
    }

    private fun mostrarDetalles(tarea: Tarea) {
        val textViewTitulo: TextView = findViewById(R.id.textViewTitulo)
        val textViewPrioridad: TextView = findViewById(R.id.textViewPrioridad)
        val textViewFecha: TextView = findViewById(R.id.textViewFecha)
        val textViewDescripcion: TextView = findViewById(R.id.textViewDescripcion)
        val textViewEstado: TextView = findViewById(R.id.textViewEstado)

        textViewTitulo.text = tarea.titulo
        textViewPrioridad.text = tarea.prioridad
        textViewFecha.text = tarea.fecha
        textViewDescripcion.text = tarea.descripcion
        textViewEstado.text = tarea.estado
    }
}
