package com.example.androidchekirwalid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var txtName: TextView
    private lateinit var txtEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView : View = inflater.inflate(R.layout.fragment_profile, container, false)

        txtName = rootView.findViewById(R.id.txtFullName)

        txtName.isEnabled = false

        txtName.text = requireArguments().getString(FULLNAME,"NULL")

        txtEmail = rootView.findViewById(R.id.txtEmail)

        txtEmail.isEnabled = false

        txtEmail.text = requireArguments().getString(EMAIL,"NULL")

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, email: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(FULLNAME, name)
                    putString(EMAIL, email)
                }
            }
    }
}