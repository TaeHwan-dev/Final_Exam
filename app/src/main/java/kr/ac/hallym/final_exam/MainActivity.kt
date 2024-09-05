package kr.ac.hallym.final_exam


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.final_exam.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMonthView()

        binding.preBtn.setOnClickListener {
            CalendarUtil.selectedDate.add(Calendar.MONTH, -1)
            setMonthView()
        }


        binding.nextBtn.setOnClickListener {
            CalendarUtil.selectedDate.add(Calendar.MONTH, 1)
            setMonthView()
        }

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun setMonthView() {
        binding.monthYearText.text = monthYearFromDate(CalendarUtil.selectedDate)

        val dayList = dayInMonthArray()

        val adapter = CalendarAdapter(dayList)

        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        binding.recyclerView.layoutManager = manager

        binding.recyclerView.adapter = adapter
    }

    private fun monthYearFromDate(calendar: Calendar): String {

        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH) + 1

        return "$month 월 $year"
    }


    private fun dayInMonthArray(): ArrayList<Date>{

        var dayList = ArrayList<Date>()

        var monthCalendar = CalendarUtil.selectedDate.clone() as Calendar

        monthCalendar[Calendar.DAY_OF_MONTH] = 1

        val firstDayOfMonth = monthCalendar[Calendar.DAY_OF_WEEK]-1

        monthCalendar.add(Calendar.DAY_OF_MONTH, -firstDayOfMonth)

        while(dayList.size < 42){

            dayList.add(monthCalendar.time)

            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dayList
    }
}