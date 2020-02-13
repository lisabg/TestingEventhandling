package com.example.lisabg_oblig1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_converter.*
import java.math.RoundingMode
import java.text.DecimalFormat

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val myArray = resources.getStringArray(R.array.converter_options)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myArray)
        converter_spinner.adapter = adapter

        converter_convert_button.setOnClickListener {
            val inputNum = converter_input_edittext.text.toString()
            converter_convert_button.onEditorAction(EditorInfo.IME_ACTION_DONE)

            if (inputNum.isBlank()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show()

            } else if (converter_spinner.selectedItem.toString() == "select unit") {
                Toast.makeText(this, "Please select a unit type", Toast.LENGTH_SHORT).show()

            } else {
                val result = getConverterResult(inputNum)
                converter_input_edittext.setText("")
                converter_textview.text = getString(R.string.converter_response_text, inputNum, converter_spinner.selectedItem.toString(), result)
            }
        }

        converter_next_activity_button.setOnClickListener {
            val textViewValue = intent.extras?.getString("textEdit")

            val intent = Intent(this@ConverterActivity, QuizActivity::class.java)
            intent.putExtra("textEdit", textViewValue)
            startActivity(intent)
        }
    }


    private fun getConverterResult(inputNum : String) : String {

        val convertedNum = convertNum(inputNum)
        val df = DecimalFormat("######.00")
        df.roundingMode = RoundingMode.CEILING
        return df.format(convertedNum).toString()

    }

    private fun convertNum(number: String): Double {
        val num = number.toDoubleOrNull()
        if (num !== null) {
            return when (converter_spinner.selectedItem.toString()) {
                "fl oz" -> flozConvert(num)
                "cp" -> cpConvert(num)
                "gal" -> galConvert(num)
                "hogshead" -> hogsheadConvert(num)
                else -> 0.0
            }
        }
        return 0.0
    }

    private fun flozConvert (num: Double) : Double {
        return num * 0.02957
    }

    private fun cpConvert (num: Double) : Double {
        return num * 0.23659
    }

    private fun galConvert (num: Double) : Double {
        return num * 3.78541
    }

    private fun hogsheadConvert (num: Double) : Double {
        return num * 238.481
    }
}
