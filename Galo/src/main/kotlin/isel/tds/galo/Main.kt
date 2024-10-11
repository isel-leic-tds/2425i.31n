package org.example.isel.tds.galo

import isel.tds.galo.view.ConsoleApplication

//fun main() {
//    println("Hello World!")
//}
//
//fun main(){
//    var board: Board? = null
//    while(true){
//        print("> ")
//        val cmd = readln().uppercase().split(' ')
//        when(cmd[0]){
//            "NEW" -> board = Board(turn = 'A')
//            "PLAY" -> {
//                val pos = cmd[1].toInt()
//                if( board!=null && board.freeCell(pos)){
//                    board = board.play(pos)
//                }
////                board?.let { if(it.canPlay(pos)) board = it.play(pos) }
//            }
//            "EXIT" -> break
//            else -> println("Invalid command ${cmd[0]}")
//        }
//        board?.show()
//    }
//
////    val b1 = BB()
////    b1.a()
////
////    val c1 = CC()
////    c1.a()
//}

// Example of inheritance with abstract class and function
//abstract class AA{
//    abstract fun a()
//    open val x =1
//
//}
//
//open class BB: AA(){
//    override fun a() {
//        println(x)
//    }
//    override val x =2
//}
//
//
//class CC: BB(){
//
//    override val x =3
//}

fun main(){
    ConsoleApplication.start()
}