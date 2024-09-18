package org.example.isel.tds

//class Date {
//    val day: Int
//    val month: Int
//    val year: Int
//
//    constructor(y:Int, m:Int, d:Int){
//        year = y
//        month = m
//        day = d
//    }
//}

//class Date(val year:Int, val month: Int, val day: Int) {
//
//    constructor(year:Int,month: Int): this(year, month, 1)
//    constructor(year:Int): this(year, 1)
//
//}

//class Date(val year:Int, val month: Int = 1, val day: Int = 1) {
//
//    val isLeapYear: Boolean
//        get() = year%4==0 && year%100!=0 || year%400==0
//}


class Date(val year:Int, val month: Int = 1, val day: Int = 1) {

    init {
        require(month in 1..12)
        //TODO validate year and day
    }

    private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val lastDayOfMonth: Int
        get() = if (month==2 && isLeapYear()) 29 else daysOfMonth.get(month-1)
}

fun Date.isLeapYear() = year.isLeapYear()
fun Int.isLeapYear() = this%4==0 && this%100!=0 || this%400==0



