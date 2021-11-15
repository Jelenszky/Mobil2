package com.example.mobil2

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    lateinit var sharedPreference: SharedPreferences
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val actionBar=supportActionBar
        actionBar!!.title="Teendők listája"
        actionBar.setDisplayHomeAsUpEnabled(true)

        sharedPreference = getSharedPreferences(getString(R.string.preference), Context.MODE_PRIVATE)

        addtable()
    }

    fun addtable(){
        val stk: TableLayout
        stk = findViewById(R.id.table)
        val tbrow0= TableRow(this)
        var tv0= TextView(this)
        tv0.setText("Határidő")
        tv0.setTextColor(Color.BLACK)
        tbrow0.addView(tv0)
        var tv1= TextView(this)
        tv1.setText("Teendő")
        tv1.setTextColor(Color.BLACK)
        tbrow0.addView(tv1)
        var tv2= TextView(this)
        tv2.setText("")
        tv2.setTextColor(Color.BLACK)
        tbrow0.addView(tv2)
        stk.addView(tbrow0)
        for (e in sharedPreference.all.entries.sortedBy { it.key }){
            val tbrow0= TableRow(this)
            var tv0= TextView(this)
            tv0.setText(e.key.toString())
            tv0.setTextColor(Color.BLACK)
            tbrow0.addView(tv0)
            var tv1= TextView(this)
            tv1.setText(e.value.toString())
            tv1.setTextColor(Color.BLACK)
            tbrow0.addView(tv1)
            var btn= Button(this)
            btn.setText("kész")
            tbrow0.addView(btn)
            btn.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    val row = view!!.getParent() as TableRow
                    val tvpassed=row.getChildAt(0) as TextView
                    val tvpassed2=row.getChildAt(1) as TextView
                    stk.removeAllViews()
                    refreshtable(tvpassed.text.toString())

                }

            })
            stk.addView(tbrow0)
        }
    }

    fun refreshtable(textpassed:String){
        println(textpassed)
        sharedPreference.edit().remove(textpassed).commit()

        addtable()
    }
}