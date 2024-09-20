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


class Date(val year:Int, val month: Int = 1, val day: Int = 1):Any() {
    private val daysOfMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

    init {
        require(year in 1500..4000)
        require(month in 1..12)
        require(day in 1..lastDayOfMonth)
    }

    override operator fun equals(other: Any?) =
        other is Date
                && year == other.year && month == other.month && day == other.day

    override fun hashCode() = (year * 12 + month) * 31 + day

    override fun toString() = "$year-%02d-%02d".format(month, day)

    val lastDayOfMonth: Int
        get() = if (month==2 && isLeapYear()) 29 else daysOfMonth.get(month-1)
}

fun Date.isLeapYear() = year.isLeapYear()
fun Int.isLeapYear() = this%4==0 && this%100!=0 || this%400==0

operator fun Date.plus(daysToAdd: Int): Date = this.addDays(daysToAdd)
operator fun Int.plus( date: Date): Date = date.addDays(this)

private fun Date.addDays(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth ->Date(year, month, day + daysToAdd) // x() //Force an StackOverflowError
    month < 12 -> Date(year, month + 1, 1).addDays(daysToAdd - (lastDayOfMonth - day + 1))
    else -> Date(year + 1, 1, 1).addDays(daysToAdd - (lastDayOfMonth - day + 1))
}

tailrec private fun Date.addDaysTailRec(daysToAdd: Int): Date = when{
    day + daysToAdd <= lastDayOfMonth ->Date(year, month, day + daysToAdd) // x() //Force an StackOverflowError
    month < 12 -> Date(year, month + 1, 1).addDaysTailRec(daysToAdd - (lastDayOfMonth - day + 1))
    else -> Date(year + 1, 1, 1).addDaysTailRec(daysToAdd - (lastDayOfMonth - day + 1))
}

//fun x():Date = x() //Force an StackOverflowError

operator fun Date.compareTo(date: Date): Int = when {
    year != date.year -> year - date.year
    month != date.month -> month - date.month
    else -> day - date.day
}