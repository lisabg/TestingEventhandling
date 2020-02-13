package com.example.lisabg_oblig1

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var input = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        converter_convert_button.setOnClickListener{

            if (converter_input_edittext.text.toString().trim().isBlank()) {

                converter_textview.text = getString(R.string.palindrome_no_input_error_msg)

            } else {
                val string = converter_input_edittext.text.toString().trim()

                converter_textview.text = getString(R.string.palindrome_response_text, string, checkPalindrome(string))
                input = string
                converter_convert_button.onEditorAction(EditorInfo.IME_ACTION_DONE)

                converter_input_edittext.setText("")

            }
        }



        converter_next_activity_button.setOnClickListener{

            val string = converter_textview.text.toString().trim()
            val error = getString(R.string.palindrome_no_input_error_msg)

            if (string.isBlank() || string == error) {
                converter_textview.text = error

            } else {
                val intent = Intent(this@MainActivity, ConverterActivity::class.java)
                intent.putExtra("textEdit", input)
                startActivity(intent)
            }
        }
    }

    // this function was originally a java function from: https://www.baeldung.com/java-palindrome
    private fun checkPalindrome(string: String) : String  {
        val clean = string.replace("\\s+".toRegex(), "").toLowerCase()
        val length = clean.length
        var forward = 0
        var backward = length - 1
        while (backward > forward) {
            val forwardChar = clean[forward++]
            val backwardChar = clean[backward--]
            if (forwardChar != backwardChar) return "not "
        }
        return ""
    }

}
