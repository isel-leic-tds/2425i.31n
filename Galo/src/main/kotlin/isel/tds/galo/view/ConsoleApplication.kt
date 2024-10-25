package isel.tds.galo.view

import isel.tds.galo.model.Game
import isel.tds.galo.storage.GameSerializer
import isel.tds.galo.storage.TextFileStorage

object ConsoleApplication {
    fun start(){
            val commands: Map<String, Command> =
                getCommands(TextFileStorage("games", GameSerializer))
            var game = Game()
            while(true){
                val (cmdName, argList)= readCommand()

                val command = commands[cmdName]
                if(command == null){
                    println("Invalid command $cmdName")
                    continue
                }else try {
                    if (command.isToFinish) break
                    game = command.execute(argList, game)

                    game.show()
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