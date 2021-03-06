package com.example.pinch_hitter_homelearn.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pinch_hitter_homelearn.R
import com.example.pinch_hitter_homelearn.`interface`.ApiService
import com.example.pinch_hitter_homelearn.`interface`.SignUpClass
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up2.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.pw_find_answer
import kotlinx.android.synthetic.main.activity_signup.pw_find_question_choice
import kotlinx.android.synthetic.main.activity_signup.user_id_input
import kotlinx.android.synthetic.main.activity_signup.user_pw_input
import kotlinx.android.synthetic.main.activity_signup.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class SignUpActivity : AppCompatActivity() {

    var id_check = true
    var find_question = ""
    var id_flag = false
    var pw_flag = false
    var pw_check_flag = false
    var answer_flag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val items = resources.getStringArray(R.array.pw_find_array)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)

        pw_find_question_choice.adapter = spinnerAdapter

        user_id_input.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()?.length == 0) {
                    id_flag = false
                } else if (p0.toString()?.length > 0 ) {
                    id_flag = true
                }

                if(id_flag && pw_flag && pw_check_flag && answer_flag) {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button2)
                } else {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {}
        })

        user_pw_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()?.length == 0) {
                    pw_flag = false
                } else if (p0.toString()?.length > 0 ) {
                    pw_flag = true
                }

                if(id_flag && pw_flag && pw_check_flag && answer_flag) {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button2)
                } else {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button)
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {}

        })

        user_pw_input_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()?.length == 0) {
                    pw_check_flag = false
                } else if (p0.toString()?.length > 0 ) {
                    pw_check_flag = true
                }

                if(id_flag && pw_flag && pw_check_flag && answer_flag) {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button2)
                } else {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {}

        })

        pw_find_answer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString()?.length == 0) {
                    answer_flag = false
                } else if (p0.toString()?.length > 0 ) {
                    answer_flag = true
                }

                if(id_flag && pw_flag && pw_check_flag && answer_flag) {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button2)
                } else {
                    next_btn.setBackgroundResource(R.drawable.sign_up_button)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, count: Int) {}
        })

        sign_back_btn.setOnClickListener {
            var back = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(back)
        }

        next_btn.setOnClickListener {
            if(id_flag && pw_flag && pw_check_flag && answer_flag) {
                var email = user_id_input.text.toString()
                var pw = user_pw_input.text.toString()
                var pw_check = user_pw_input_check.text.toString()
                find_question = pw_find_question_choice.selectedItemPosition.toString()
                find_question = find_question + 1
                var find_answer = pw_find_answer.text.toString()

                if( pw == pw_check) {
                    if( email != null && pw != null && pw_check != null && find_answer != null && id_check == true) {
                        val intent = Intent(this@SignUpActivity, SignUpActivity2::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("pw", pw)
                        intent.putExtra("question", find_question)
                        intent.putExtra("findAnswer", find_answer)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "비밀번호가 다릅니다.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}