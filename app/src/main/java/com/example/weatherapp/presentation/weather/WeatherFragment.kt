package com.example.weatherapp.presentation.weather

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.databinding.SelectDialogBinding
import org.koin.android.ext.android.inject
import java.lang.String

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by inject()

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        viewModel.getWeatherFor("London")
        viewModel.weather.observe(viewLifecycleOwner) {
            showWeather(it)
        }

        binding.floatingActionButton.setOnClickListener {
            showSelectCityDialog()
        }

        return binding.root
    }

    private fun showWeather(weather: Weather) {
        with(binding) {
            tvCityTitle.text = weather.name
            tvWeatherType.text = weather.weather?.get(0)?.description

            tvDegrees.text = String.valueOf(weather.main?.temp)
            tvWind.text = String.valueOf(weather.wind?.speed)
            tvPressure.text = String.valueOf(weather.main?.pressure)

            val drawable = weather.weather?.get(0)?.getDescriptionDrawable()

            imageView.setImageDrawable(
                drawable?.let { ContextCompat.getDrawable(requireContext(), it) }
            )
        }
    }

    private fun showSelectCityDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: SelectDialogBinding = SelectDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.btnDialogOk.setOnClickListener { v: View? ->
            val cityName = binding.etCity.text.toString()
            if (cityName.isNotEmpty()) {
                viewModel.getWeatherFor(cityName.trim())
                dialog.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.empty_name),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        dialog.show()
    }

}