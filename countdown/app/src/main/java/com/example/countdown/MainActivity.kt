package com.example.countdown

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    val START_TIME_IN_MILLLIS: Long =25*60*1000
    var remainingTime:Long=START_TIME_IN_MILLLIS
    var isTimierRunnig =false
    var time:CountDownTimer?=null

    lateinit  var title : TextView
    lateinit  var time1 :TextView
    lateinit  var button : Button
    lateinit  var reset : TextView
    lateinit var progressBar :ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title =findViewById(R.id.title)
        time1=findViewById(R.id.time1)
        button=findViewById(R.id.button)
        reset=findViewById(R.id.reset)
        progressBar=findViewById(R.id.progressBar)

        button.setOnClickListener{
            if(!isTimierRunnig){
                startTime()
            }
        }

        reset.setOnClickListener {
            resetTime()
        }
    }

    private fun startTime(){
        val time=object: CountDownTimer(5*1000,1*1000){
            override fun onTick(timeLeft:Long){
                remainingTime=timeLeft
                updateTimerText()
                progressBar.progress=remainingTime.toDouble().div(START_TIME_IN_MILLLIS.toDouble()).times(100).toInt()
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity,"finish !!", Toast.LENGTH_SHORT).show()
                isTimierRunnig=false
            }
        }.start()
        isTimierRunnig=true
    }
    private fun resetTime(){
        time?.cancel()
        remainingTime=START_TIME_IN_MILLLIS
        updateTimerText()
        title.text="Take a Pomodoro ..."
        isTimierRunnig=false
        progressBar.progress=100


    }
    private fun updateTimerText(){
       val minute =remainingTime.div(1000).div(60)
       val second =remainingTime.div(1000)%60
       val formattedTime =String.format("%02d:%02d",minute ,second)
       time1.text=formattedTime
   }

}
