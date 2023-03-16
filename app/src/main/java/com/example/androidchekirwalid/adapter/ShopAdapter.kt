package com.example.androidchekirwalid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchekirwalid.R
import com.example.androidchekirwalid.data.Book
import com.example.androidchekirwalid.utils.AppDataBase
import com.google.android.material.snackbar.Snackbar


class ShopAdapter(var carList: MutableList<Book>) : RecyclerView.Adapter<ShopAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_single_row, parent, false)
        return CarViewHolder(view)
    }

    @SuppressLint("StringFormatInvalid")
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        holder.bookPic.setImageResource(carList[position].bookLogo)
        holder.bookTitle.text = carList[position].bookTitle
        holder.bookAuthor.text = carList[position].bookAuthor
        holder.bookPrice.text = carList[position].bookPrice

        holder.btnBuy.setOnClickListener {
            val book = Book(
                carList[position].bookTitle,
                carList[position].bookAuthor,
                carList[position].bookPrice,
                carList[position].bookLogo
            )
            if(AppDataBase.getDatabase(holder.itemView.context).bookDao().findCar(book.bookTitle) == null){
                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Buy "+book.bookTitle)
                    .setMessage(holder.itemView.context.getString(R.string.BuyBookMsg, book.bookTitle))
                    .setPositiveButton("Yes"){ dialogInterface, which ->
                        AppDataBase.getDatabase(holder.itemView.context).bookDao().insert(book)
                        println("must save")
                    }.setNegativeButton("No"){ dialogInterface, which ->
                        dialogInterface.dismiss()
                    }.create().show()
            }else{
                Snackbar.make(holder.itemView, "Book already added in Favorite!",Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(holder.itemView.context ,R.color.colorSecondaryLight))
                    .show();
            }
        }

    }

    override fun getItemCount() = carList.size


    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bookPic : ImageView = itemView.findViewById<ImageView>(R.id.bookLogo)
        val bookTitle : TextView = itemView.findViewById<TextView>(R.id.bookTitle)
        val bookAuthor : TextView = itemView.findViewById<TextView>(R.id.bookAuthor)
        val bookPrice : TextView = itemView.findViewById<TextView>(R.id.bookPrice)
        val btnBuy : ImageView = itemView.findViewById(R.id.btnBuy)
    }
}