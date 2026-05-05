package com.example.StudentContactApp.database.dao

import androidx.room.*
import com.example.StudentContactApp.database.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: List<StudentEntity>)

    @Query("SELECT * FROM students ORDER BY name ASC")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE id = :id")
    suspend fun getStudentById(id: Int): StudentEntity?

    @Update
    suspend fun update(student: StudentEntity)

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteById(id: Int)
}