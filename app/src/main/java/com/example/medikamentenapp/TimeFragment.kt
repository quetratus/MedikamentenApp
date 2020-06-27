package com.example.medikamentenapp

import androidx.fragment.app.Fragment


class TimeFragment : Fragment()
/*
    private lateinit var medViewModel: MedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTimeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false)
       // binding.buttonTimeFinish.setOnClickListener {
       //     Navigation.createNavigateOnClickListener(R.id.action_time_to_overview)
        val application = requireNotNull(this.activity).application
        val dao = UserDatabase.getDatabase(application)!!.daoAccess
        val repository = MedicamentRepository(dao)
        val factory = MedViewModelFactory(repository)
        medViewModel = ViewModelProvider(this, factory).get(MedViewModel::class.java)
        binding.medViewModel = medViewModel
        binding.lifecycleOwner = this
        displayMedList()

        medViewModel.message.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(getActivity(), it, Toast.LENGTH_LONG).show()
            }
        })
        return binding.root
    }

    private fun displayMedList() {
        medViewModel.meds.observe(viewLifecycleOwner, Observer { Log.i("MYTAG", it.toString()) })

    }
}

*/
