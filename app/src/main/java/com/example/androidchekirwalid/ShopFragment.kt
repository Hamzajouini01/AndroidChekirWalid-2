package com.example.androidchekirwalid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchekirwalid.adapter.ShopAdapter
import com.example.androidchekirwalid.data.Book

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopFragment : Fragment() {

    lateinit var carRecyclerView: RecyclerView
    lateinit var carAdapter: ShopAdapter

    lateinit var carList : MutableList<Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_shop, container, false)

        carRecyclerView = rootView.findViewById(R.id.recyclerBookShop)

        carList = mutableListOf(
            Book("Programming Android with Kotlin","Pierre-Olivier Laurence","85 DT", R.drawable.ic_book1),
            Book("Kotlin Programming","David Greenhalgh","30 DT", R.drawable.ic_book2),
            Book("Java to Kotlin","Duncan McGregor","25 DT", R.drawable.ic_book3),
            Book("Reactive Programming with Kotlin","Ray Wenderlich","50 DT", R.drawable.ic_book4),
            Book("Dagger by Tutorials","Ray Wenderlich","45 DT", R.drawable.ic_book5),

            Book("Kotlin and Android Development featuring Jetpack","Michael Fazio","70 DT", R.drawable.ic_book6),
            Book("Real-World Android by Tutorials","Ray Wenderlich","60 DT", R.drawable.ic_book7),

            Book("Android Studio 4.2 Development Essentials","Neil Smyth","40 DT", R.drawable.ic_book8),
            Book("Learn to Program with Kotlin","Tim Lavers","80 DT", R.drawable.ic_book9),
            Book("Kotlin Golang Rust","Mourahi Studio","55 DT", R.drawable.ic_book10),
        )

        carAdapter = ShopAdapter(carList)

        carRecyclerView.adapter = carAdapter

        carRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ShopFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}