package com.example.weatherapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.weatherapplication.view_model.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.weatherapplication.model.Sys
import com.example.weatherapplication.retrofit.ResponseWrapper


class MainActivity : AppCompatActivity() {
    private val viewModel:WeatherViewModel by viewModel()
     var selectedcountryName = ""
    var selectedcityName = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getCountryDetail("In").observe(this, Observer {

            val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, it)

            countryName.apply {
                threshold = 1
                setAdapter(adapter)
                setTextColor(Color.RED)
            }
        })

        countryName.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            selectedcountryName = parent.getItemAtPosition(position).toString()

            viewModel.getCityDetail(selectedcountryName).observe(this, Observer {

                val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, it)

                cityName.apply {
                    threshold = 1
                    setAdapter(adapter)
                    setTextColor(Color.RED)
                }

            })

        }


        cityName.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            selectedcityName = parent.getItemAtPosition(position).toString()

        }

        viewModel.cityData.observe(this, Observer {
            when(it){
                is ResponseWrapper.Loading->{showLoader(true)}
                is ResponseWrapper.Success->{
                    showLoader(false)
                    val humidity = it.data.clouds
                    val temp = it.data.main.temp_max+"\u2103"
                    val weatherdescription = it.data.weather[0].description


                    tv_info.text ="Humdity:= $humidity \n Temp: = $temp \n WeatherDescription: = $weatherdescription"

                }
                is ResponseWrapper.Error->{
                    showLoader(false)
                    Toast.makeText(this,it.msg,Toast.LENGTH_SHORT).show()
                }
            }
        })

        bt_getweatherInfo.setOnClickListener{
            viewModel.getWeatherInformation(selectedcityName,selectedcountryName,blankField = {
                showToast("Please fill the City and Country")
            })
        }
    }


    private fun showLoader(show:Boolean){
        if(show){
            bt_getweatherInfo.visibility = View.GONE
            progress_circular.visibility = View.VISIBLE
        }else{
            bt_getweatherInfo.visibility = View.VISIBLE
            progress_circular.visibility = View.GONE
        }
    }


}
