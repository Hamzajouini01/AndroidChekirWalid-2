package com.example.androidchekirwalid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchekirwalid.R
import com.example.androidchekirwalid.data.Book
import com.example.androidchekirwalid.utils.AppDataBase


class HistoryAdapter (val educationList: MutableList<Book>) : RecyclerView.Adapter<HistoryAdapter.EducationViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_single_row, parent, false)
        return  EducationViewHolder(view)
    }

    @SuppressLint("StringFormatInvalid")
    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {

        holder.bookPic.setImageResource(educationList[position].bookLogo)
        holder.bookTitle.text = educationList[position].bookTitle
        holder.bookAuthor.text = educationList[position].bookAuthor
        holder.bookPrice.text = educationList[position].bookPrice

        holder.btnDelete.setOnClickListener {
            val book = Book(
                educationList[position].bookTitle,
                educationList[position].bookAuthor,
                educationList[position].bookPrice,
                educationList[position].bookLogo
            )
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete History for " +educationList[position].bookTitle)
                .setMessage(holder.itemView.context.getString(R.string.DeleteBookMsg, educationList[position].bookTitle))
                .setPositiveButton("Yes"){ dialogInterface, which ->
                    AppDataBase.getDatabase(holder.itemView.context).bookDao().delete(book)
                    educationList.removeAt(position)
                    notifyDataSetChanged()
                }.setNegativeButton("No"){ dialogInterface, which ->
                    dialogInterface.dismiss()
                }.create().show()
        }
    }

    override fun getItemCount(): Int = educationList.size

    class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookPic : ImageView = itemView.findViewById<ImageView>(R.id.bookLogo)
        val bookTitle : TextView = itemView.findViewById<TextView>(R.id.bookTitle)
        val bookAuthor : TextView = itemView.findViewById<TextView>(R.id.bookAuthor)
        val bookPrice : TextView = itemView.findViewById<TextView>(R.id.bookPrice)
        val btnDelete : ImageView = itemView.findViewById(R.id.btnDelete)
    }
}