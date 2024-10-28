package com.example.budgetplanningapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.budgetplanningapp.R
import com.example.budgetplanningapp.domain.models.DayItem
import java.time.LocalDate
import java.util.Calendar

class DayItemDialog(private var listener:Listener):DialogFragment() {

    private lateinit var btnOk:Button
    private lateinit var btnClear:Button
    private lateinit var edInc:EditText
    private lateinit var edCon:EditText
    private lateinit var tvDate:TextView
    private var calendar: Calendar = Calendar.getInstance()
    lateinit var calendarDate:String
    lateinit var formattedDate:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        val viewDialog=view
        if(viewDialog!=null){
           onFormattedDate()
           btnOk= view?.findViewById(R.id.btnOk)!!
           btnClear = view?.findViewById(R.id.btnClear)!!
           edInc = view?.findViewById(R.id.edInc)!!
           edCon = view?.findViewById(R.id.edCon)!!
           tvDate = view?.findViewById(R.id.tvDate)!!
           tvDate.text = calendarDate
           btnOk.setOnClickListener{

               if(edInc.text.toString()!=""&&edCon.text.toString()!=""){
                   val inc = edInc.text.toString().toDouble()
                   val con = edCon.text.toString().toDouble()
                   val profit = inc-con
                   //Здесь передаем отформатированную дату
                   val item = DayItem(formattedDate,inc,con,profit)
//                   Log.d("MyLog","formattedDate: $formattedDate")
                   listener.onClick(item)
                   dismiss()
               }else {
                   if(edInc.text.toString()=="")edInc.setHint(R.string.inc_value)
                   if(edCon.text.toString()=="")edCon.setHint(R.string.con_value)
               }

           }
           btnClear.setOnClickListener{
               edInc.setText("")
               edCon.setText("")
           }

        }
    }
    //Получаем значение дня, месяца и года из календаря на текущий день
//и преобразуем ее в формат для отображения в заголовке диалогового окна
//и для отправки в БД соответственно
    private fun onFormattedDate(){
        val day = calendar.get(Calendar.DATE).toString()
        val month = (calendar.get(Calendar.MONTH)+1).toString()
        val year = (calendar.get(Calendar.YEAR)).toString()
        calendarDate = "$day.$month.$year"
        formattedDate = "$year-$month-$day"
        Log.d("MyLog","date: $day")
        Log.d("MyLog","month: $month")
        Log.d("MyLog","year: $year")
    }

    interface Listener{
        fun onClick(item: DayItem)
    }

}
