package com.example.mobil2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var tv: EditText
    private lateinit var tv2 : EditText
    private lateinit var textView : TextView
    private lateinit var bt: Button
    private lateinit var bt2: Button
    private val  REQUEST_CODE=1

    private var day =0
    private var month=0
    private var year = 0
    private var hour = 0
    private var minute = 0

    private var savedDay =0
    private var savedMonth=0
    private var savedYear = 0
    private var savedHour = 0
    private var savedMinute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv2=findViewById(R.id.editText)
        tv=findViewById(R.id.editText2)
        textView=findViewById(R.id.textView)
        bt=findViewById(R.id.button)
        bt2=findViewById(R.id.button2)




    }

    fun onClickHandler(view : View){
        val speechintent= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechintent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechintent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speach to text")
        startActivityForResult(speechintent,REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_CODE && resultCode== RESULT_OK){
            val matches =data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            tv.setText(matches!!.get(0).toString())
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day= cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year=cal.get(Calendar.YEAR)
        hour=cal.get(Calendar.HOUR)
        minute=cal.get(Calendar.MINUTE)
    }

    fun pickDate(view: View){
        getDateTimeCalendar()
        DatePickerDialog(this,this,year,month,day).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay=dayOfMonth
        savedMonth=month+1
        savedYear=year

        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,true).show()


    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour=hourOfDay
        savedMinute=minute
        val date="$savedYear-$savedMonth-$savedDay $savedHour:$savedMinute"
        tv2.setText(date)
    }


    fun buttonHandler(view: View){


            Toast.makeText(applicationContext, tv2.text.toString()+" "+ tv.text.toString()+ " hozzáadva",
                Toast.LENGTH_LONG).show()


    }


    fun listazPushed(view: View){
        val intent= Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }




}