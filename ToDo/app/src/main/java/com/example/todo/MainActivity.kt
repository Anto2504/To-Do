package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.adapters.TareaAdapter
import com.example.todo.data.DetalleTareaActivity
import com.example.todo.data.Tarea
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val listaTareas = mutableListOf(
        Tarea("baja", "por hacer", "01/01/2023", "Tarea 1", "Descripción 1", "pendiente"),
        // Agrega más tareas aquí
    )

    private lateinit var spinnerPrioridad: Spinner
    private lateinit var spinnerEstado: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextTitulo: EditText
    private lateinit var spinnerPrioridadFormulario: Spinner
    private lateinit var botonAgregarTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerPrioridad = findViewById(R.id.spinnerPrioridad)
        spinnerEstado = findViewById(R.id.spinnerEstado)
        recyclerView = findViewById(R.id.recyclerView)
        editTextTitulo = findViewById(R.id.editTextTitulo)
        spinnerPrioridadFormulario = findViewById(R.id.spinnerPrioridadFormulario)
        botonAgregarTarea = findViewById(R.id.botonAgregarTarea)

        setupRecyclerView()

        setupSpinners()

        setupFormulario()

        botonAgregarTarea.setOnClickListener {
            agregarTarea()
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val adapter = TareaAdapter(listaTareas) { position -> onTareaClick(position) }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun setupSpinners() {
        val prioridadAdapter = ArrayAdapter.createFromResource(
            this, R.array.prioridades_array, android.R.layout.simple_spinner_item
        )
        val estadoAdapter = ArrayAdapter.createFromResource(
            this, R.array.estados_array, android.R.layout.simple_spinner_item
        )

        prioridadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerPrioridad.adapter = prioridadAdapter
        spinnerEstado.adapter = estadoAdapter

        setupSpinnerListener(spinnerPrioridad)
        setupSpinnerListener(spinnerEstado)
    }

    private fun setupSpinnerListener(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterTareas()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se requiere acción aquí
            }
        }
    }

    private fun setupFormulario() {
        val prioridadFormularioAdapter = ArrayAdapter.createFromResource(
            this, R.array.prioridades_array, android.R.layout.simple_spinner_item
        )
        prioridadFormularioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPrioridadFormulario.adapter = prioridadFormularioAdapter
    }

    private fun agregarTarea() {
        val titulo = editTextTitulo.text.toString()
        val prioridad = spinnerPrioridadFormulario.selectedItem.toString()
        val nuevaTarea = Tarea(prioridad, "por hacer", obtenerFechaActual(), titulo, "", "pendiente")
        listaTareas.add(nuevaTarea)
        filterTareas()  // Aplicar el filtro actual después de agregar una nueva tarea
        limpiarFormulario()
    }

    private fun limpiarFormulario() {
        editTextTitulo.text.clear()
        spinnerPrioridadFormulario.setSelection(0)
    }

    private fun filterTareas() {
        val prioridadSeleccionada = spinnerPrioridad.selectedItem.toString()
        val estadoSeleccionado = spinnerEstado.selectedItem.toString()

        val tareasFiltradas = listaTareas.filter {
            it.prioridad == prioridadSeleccionada && it.estado == estadoSeleccionado
        }

        (recyclerView.adapter as TareaAdapter).updateTareas(tareasFiltradas)
    }

    private fun onTareaClick(position: Int) {
        val tareaSeleccionada = listaTareas[position]
        val intent = Intent(this, DetalleTareaActivity::class.java)
        intent.putExtra("tarea", tareaSeleccionada)
        startActivity(intent)
    }

    private fun obtenerFechaActual(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        return formatter.format(date)
    }
}
