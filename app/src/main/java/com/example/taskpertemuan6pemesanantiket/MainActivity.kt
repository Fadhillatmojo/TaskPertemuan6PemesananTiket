package com.example.taskpertemuan6pemesanantiket

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.taskpertemuan6pemesanantiket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val types = arrayOf(
        "Regular",
        "Bussiness",
        "Executive",
        "VIP"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var selectedType = ""
        var selectedDate = ""
        var selectedTime = ""
        with(binding){
            // menginisiasikan array types ke dalam spinnertypes
            val adapterTypes = ArrayAdapter(this@MainActivity, R.layout.simple_spinner_item, types)

            adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTypes.adapter = adapterTypes

            // get data from spinnertypes
            spinnerTypes.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                    selectedType = types[position]
                }

                // Handle jika tidak ada item yang dipilih (opsional)
                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    selectedType = types[0]
                }
            })
            // end spinner section

            // date picker
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ){_, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            }
            // end datepicker

            // timepicker
            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            }
            // end timepicker

            // button listener
            btnPesan.setOnClickListener(){
                Toast.makeText(this@MainActivity, "Tiket Kapal $selectedType, tanggal $selectedDate, pukul $selectedTime telah dipesan!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}