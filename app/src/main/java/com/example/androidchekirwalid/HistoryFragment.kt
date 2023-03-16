package com.example.androidchekirwalid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidchekirwalid.adapter.HistoryAdapter
import com.example.androidchekirwalid.data.Book
import com.example.androidchekirwalid.utils.AppDataBase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    lateinit var carRecyclerView: RecyclerView
    lateinit var carAdapter: HistoryAdapter

    lateinit var carList : MutableList<Book>

    lateinit var dataBase: AppDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_history, container, false)

        println("start")
        dataBase = AppDataBase.getDatabase(requireActivity())

        carRecyclerView = rootView.findViewById(R.id.recyclerBookHistory)

        carList = dataBase.bookDao().getAllCars()


        carAdapter = HistoryAdapter(carList)

        carRecyclerView.adapter = carAdapter

        carRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}