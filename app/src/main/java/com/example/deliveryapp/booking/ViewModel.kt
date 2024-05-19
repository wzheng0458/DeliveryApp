package com.example.deliveryapp.booking
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


open class ViewModel : ViewModel() {
    var BookingID: MutableState<String> = mutableStateOf("")
    fun BookingIDgenerate(TableNum:String,Stime:String,Etime:String,Date:String){
        var ID1 =TableNum.substring(1)
        var ID2 =Stime.replace(Regex("[:\\sAPM]"), "").trim()
        var ID3 =Etime.replace(Regex("[:\\sAPM]"), "").trim()
        var ID4=Date.replace("/","")
        BookingID= mutableStateOf("B$ID1$ID2$ID3$ID4")

    }

    var Start:String=""
    var End:String=""
    var Date:String=""
    var TableNum:String=""

    fun SetBookingInfo(Stime: String,Etime: String,DateUsed: String,TableNumUsed: String){
        Start=Stime
        End=Etime
        Date=DateUsed
        TableNum=TableNumUsed
    }








}