package isel.tds.galo.view

import isel.tds.galo.model.Board

object ConsoleApplication {
    fun start(){
            val commands: Map<String, Command> = getCommands()
            var board: Board? = null
            while(true){
                val (cmdName, argList)= readCommand()

                val command = commands[cmdName]
                if(command == null){
                    println("Invalid command $cmdName")
                    continue
                }
                if(command.isToFinish) break
                board = command.execute(argList, board)

                board.show()
            }
    }
}