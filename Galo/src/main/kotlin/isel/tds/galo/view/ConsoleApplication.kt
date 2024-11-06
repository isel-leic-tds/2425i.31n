package isel.tds.galo.view

import isel.tds.galo.model.Clash
import isel.tds.galo.storage.GameSerializer
import isel.tds.galo.storage.TextFileStorage

object ConsoleApplication {
    fun start(){
            val commands: Map<String, Command> = getCommands()
//            var game = Game()
            var clash = Clash(TextFileStorage("games", GameSerializer))
            while(true){
                val (cmdName, argList)= readCommand()

                val command = commands[cmdName]
                if(command == null){
                    println("Invalid command $cmdName")
                    continue
                }else try {
                    if (command.isToFinish) break
                    clash = command.execute(argList, clash)

                    clash.show()
                }catch (e: IllegalStateException) {
                    println(e.message)
                }catch (e: IllegalArgumentException) {
                    println("${e.message}\nUse: $cmdName")
                }catch (e: Exception) {
                    println("Error: ${e.message}")
                }
            }
    }
}