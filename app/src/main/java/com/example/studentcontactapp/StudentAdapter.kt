package com.example.StudentContactApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.StudentContactApp.database.entity.StudentEntity
import com.example.crudmahasiswa.R

class StudentAdapter(
    private var students: List<StudentEntity>,
    private val onClick: (StudentEntity) -> Unit,
    private val onEdit: (StudentEntity) -> Unit,
    private val onDelete: (StudentEntity) -> Unit
) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvStudentName)
        val tvNim: TextView = view.findViewById(R.id.tvStudentNim)
        val tvAvatar: TextView = view.findViewById(R.id.tvAvatarCircle)
        val btnEdit: TextView = view.findViewById(R.id.btnEdit)
        val btnDelete: TextView = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = students[position]

        holder.tvName.text = student.name
        holder.tvNim.text = student.nim

        val initials = student.name.split(" ")
            .mapNotNull { it.firstOrNull() }
            .take(2)
            .joinToString("")
            .uppercase()

        holder.tvAvatar.text = initials

        holder.itemView.setOnClickListener { onClick(student) }
        holder.btnEdit.setOnClickListener { onEdit(student) }
        holder.btnDelete.setOnClickListener { onDelete(student) }
    }

    override fun getItemCount() = students.size

    fun updateData(newStudents: List<StudentEntity>) {
        students = newStudents
        notifyDataSetChanged()
    }

    fun getStudentAt(position: Int): StudentEntity {
        return students[position]
    }
}