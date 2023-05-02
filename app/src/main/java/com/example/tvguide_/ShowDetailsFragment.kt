package com.example.tvguide_

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tvguide_.databinding.FragmentShowDetailsBinding
import com.example.tvguide_.model.ShowItem

class ShowDetailsFragment(private var showItem: ShowItem) : Fragment() {
    lateinit var binding: FragmentShowDetailsBinding
    private lateinit var viewModel: ShowDetailsViewModel
    private lateinit var castAdapter: CastAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_show_details, container, false);
        binding =FragmentShowDetailsBinding.bind(view)
        prepareRecyclerView()
        Glide.with(view).load(showItem.imageUrl).into(binding.ivItemImage)
        binding.tvItemName.text =  showItem.name
        binding.tvItemNetworkName.text = showItem.networkName
        binding.tvItemDate.text = if (showItem.rating == "null") "" else showItem.rating
        viewModel = ViewModelProvider(this)[ShowDetailsViewModel::class.java]
        viewModel.getShowDetails(showItem.href.toString())
        viewModel.getCast(showItem.href.toString())

        viewModel.observeShowDetailsLiveData().observe(viewLifecycleOwner, Observer { showDetails ->
            val sinopsis = getString(R.string.text_sinopsis, showDetails.summary)
            binding.tvSinopsis.text = sinopsis
            binding.tvGenero.text = showDetails.genres.toString()
            binding.tvHorario.text = showDetails.schedule.time + " | " + showDetails.schedule.days
        })

        binding.btnOpenLink.setOnClickListener {
            if(showItem.officialSite != null){
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(showItem.officialSite)
                if(showItem.officialSite != null)
                    startActivity(openURL)
            } else
                Toast.makeText(context, "Url sin información", Toast.LENGTH_LONG).show()
        }

        viewModel.observeCastLiveData().observe(viewLifecycleOwner, Observer { showDetails ->
            castAdapter.setItemList(showDetails)
        })

        binding.btnOpenLink.setOnClickListener {
            if(showItem.officialSite != null){
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(showItem.officialSite)
                if(showItem.officialSite != null)
                    startActivity(openURL)
            } else
                Toast.makeText(context, "Url sin información", Toast.LENGTH_LONG).show()
        }


        return view
    }

    private fun prepareRecyclerView() {
        castAdapter = CastAdapter()
        binding.rvSchedule.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            adapter = castAdapter
        }
    }

}