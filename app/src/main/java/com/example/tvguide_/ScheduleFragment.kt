package com.example.tvguide_

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tvguide_.databinding.FragmentScheduleBinding
import com.example.tvguide_.model.ShowItem


class ScheduleFragment(private var callBack: IEventReturnMainCallBack) : Fragment() {
    lateinit var binding: FragmentScheduleBinding
    private lateinit var viewModel: SheduleViewModel
    private lateinit var itemAdapter: ShowAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false);
        binding = FragmentScheduleBinding.bind(view)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[SheduleViewModel::class.java]
        viewModel.getSchedule()
        viewModel.observeScheduleLiveData().observe(viewLifecycleOwner, Observer { scheduleList ->
            val showList = ArrayList<ShowItem>()
            for (schedule in scheduleList) {
                showList.add(
                    ShowItem(
                        schedule.show.image?.medium,
                        schedule.show.name,
                        schedule.show.network?.name,
                        schedule.airdate + " | " + schedule.airtime,
                        schedule.show.rating?.average.toString(),
                        schedule.show._links.self.href,
                        schedule.show.officialSite
                    )
                )
            }
            itemAdapter.setItemList(showList, callBack)
        })

        viewModel.observeSearchLiveData().observe(viewLifecycleOwner, Observer { searchList ->
            val showList = ArrayList<ShowItem>()
            for (search in searchList) {
                showList.add(
                    ShowItem(
                        search.show.image?.medium,
                        search.show.name,
                        search.show.network?.name,
                        search.show.schedule.time + " | " + search.show.schedule.days,
                        search.show.rating.average.toString(),
                        search.show._links.self.href,
                        search.show.officialSite
                    )
                )
            }
            itemAdapter.setItemList(showList, callBack)
        })

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getSearch(binding.etSearch.text.toString())
            }
            true
        }
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.etSearch.setText("")
    }


    private fun prepareRecyclerView() {
        var spanCount = 1;
        itemAdapter = ShowAdapter()
        val configuration = resources.configuration
        val smallestScreenWidthDp = configuration.smallestScreenWidthDp
        if (smallestScreenWidthDp >= 600) {
            // El dispositivo es una tableta con una anchura mínima de 600dp
            spanCount = 2
        }
        binding.rvSchedule.apply {
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = itemAdapter
        }
    }
}