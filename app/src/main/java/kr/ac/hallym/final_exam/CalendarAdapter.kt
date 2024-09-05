package kr.ac.hallym.final_exam


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(private val dayList: ArrayList<Date>) :
    RecyclerView.Adapter<CalendarAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dayText: TextView = itemView.findViewById(R.id.dayText)
    }

    //화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var monthDate = dayList[holder.adapterPosition]

        var dateCalendar = Calendar.getInstance()

        dateCalendar.time = monthDate

        var dayNo = dateCalendar.get(Calendar.DAY_OF_MONTH)

        holder.dayText.text = dayNo.toString()

        var iYear = dateCalendar.get(Calendar.YEAR)
        var iMonth = dateCalendar.get(Calendar.MONTH) + 1
        var iDay = dateCalendar.get(Calendar.DAY_OF_MONTH)


        //현재 날짜
        var selectYear = CalendarUtil.selectedDate.get(Calendar.YEAR)
        var selectMonth = CalendarUtil.selectedDate.get(Calendar.MONTH) + 1
        var selectDay = CalendarUtil.selectedDate.get(Calendar.DAY_OF_MONTH)


        if (iYear == selectYear && iMonth == selectMonth) {
            holder.dayText.setTextColor(Color.parseColor("#000000"))

            if (selectDay == dayNo) {

                holder.itemView.setBackgroundColor(Color.LTGRAY)
            }
            if ((position + 1) % 7 == 0) {
                holder.dayText.setTextColor(Color.BLUE)

            } else if (position == 0 || position % 7 == 0) {
                holder.dayText.setTextColor(Color.RED)
            }
        } else {

            holder.dayText.setTextColor(Color.parseColor("#B4B4B4"))

            if ((position + 1) % 7 == 0) {
                holder.dayText.setTextColor(Color.parseColor("#B4FFFF"))

            } else if (position == 0 || position % 7 == 0) {
                holder.dayText.setTextColor(Color.parseColor("#FFB4B4"))
            }
        }

        holder.itemView.setOnClickListener {

            var yearMonDay = "$iYear 년 $iMonth 월 $iDay 일"

            Toast.makeText(holder.itemView.context, yearMonDay, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}