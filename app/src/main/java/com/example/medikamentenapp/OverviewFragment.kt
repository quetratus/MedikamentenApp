package com.example.medikamentenapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.medikamentenapp.Repository.MedicamentRepository
import com.example.medikamentenapp.databinding.FragmentOverviewBinding
import com.example.medikamentenapp.db.UserDatabase
import com.example.medikamentenapp.viewmodel.LoggedInUserView
import com.example.medikamentenapp.viewmodel.MedViewModel
import com.example.medikamentenapp.viewmodel.MedViewModelFactory

class OverviewFragment : Fragment() {

    private lateinit var medViewModel: MedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentOverviewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_overview, container, false)
        binding.buttonAddMed.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_overview_to_medikament)
        )
        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = MedicamentRepository(dao)
        val model = LoggedInUserView(displayName = "Sebastian")
        val factory = MedViewModelFactory(repository, application, model)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        binding.medViewModel = medViewModel
        binding.lifecycleOwner = this

        medViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(), it, Toast.LENGTH_LONG).show()
            }
        })

        return binding.root
    }
}


