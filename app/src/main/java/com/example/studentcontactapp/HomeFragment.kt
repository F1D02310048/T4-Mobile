package com.example.studentcontactapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentcontactapp.database.AppDatabase
import com.example.studentcontactapp.database.entity.StudentEntity
import com.example.studentcontactapp.utils.PrefManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var adapter: StudentAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        database = AppDatabase.getDatabase(requireContext())

        val rvStudents = view.findViewById<RecyclerView>(R.id.rvStudents)
        val fabAdd = view.findViewById<FloatingActionButton>(R.id.fabAdd)
        val tvWelcomeHome = view.findViewById<TextView>(R.id.tvWelcomeHome)

        val prefManager = PrefManager(requireContext())
        tvWelcomeHome.text = "Welcome, ${prefManager.getUsername()}!"

        adapter = StudentAdapter(
            emptyList(),
            { student ->
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("STUDENT_NIM", student.nim)
                    putExtra("STUDENT_NAME", student.name)
                    putExtra("STUDENT_PRODI", student.prodi)
                }
                startActivity(intent)
            },
            { student ->
                val intent = Intent(requireContext(), AddStudentActivity::class.java)
                intent.putExtra("STUDENT_ID", student.id)
                startActivity(intent)
            },
            { student ->
                showDeleteDialog(student)
            }
        )

        rvStudents.layoutManager = LinearLayoutManager(context)
        rvStudents.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val student = adapter.getStudentAt(position)
                showDeleteDialog(student)
            }
        })
        itemTouchHelper.attachToRecyclerView(rvStudents)

        fabAdd.setOnClickListener {
            startActivity(Intent(requireContext(), AddStudentActivity::class.java))
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        loadStudentData()
    }

    private fun loadStudentData() {
        viewLifecycleOwner.lifecycleScope.launch {
            val list = database.studentDao().getAllStudents()

            if (list.isEmpty()) {
                val sampleData = listOf(
                    StudentEntity(name = "M. Bayu Aji", nim = "F1B021001", prodi = "Teknik Informatika", email = "bayu@unram.ac.id", semester = "6"),
                    StudentEntity(name = "Andi Wijaya", nim = "F1B021002", prodi = "Teknik Sipil", email = "andi@unram.ac.id", semester = "4")
                )
                database.studentDao().insertAll(sampleData)
                val updatedList = database.studentDao().getAllStudents()
                adapter.updateData(updatedList)
            } else {
                adapter.updateData(list)
            }
        }
    }

    private fun showDeleteDialog(student: StudentEntity) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Hapus Data?")
        builder.setMessage("Hapus \"${student.name}\"? Tindakan ini tidak dapat dibatalkan.")

        builder.setPositiveButton("Hapus") { _, _ ->
            viewLifecycleOwner.lifecycleScope.launch {
                database.studentDao().deleteById(student.id)
                loadStudentData()
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Batal") { dialog, _ ->
            loadStudentData()
            dialog.dismiss()
        }

        builder.setOnCancelListener {
            loadStudentData()
        }

        val alert = builder.create()
        alert.show()

        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY)
    }
}