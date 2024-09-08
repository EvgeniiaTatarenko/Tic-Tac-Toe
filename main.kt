fun main() {

    val input = "_________"
    makeList(input)
    printInput(firstRaw.joinToString(" "), secondRaw.joinToString(" "), thirdRaw.joinToString(" "))

    var currentPlayer = "X"

    while (true) {
        println("Enter the coordinates (1-3 for both rows and columns):")
        val userInput = readln().split(" ")

        // Check if input is valid numbers
        if (userInput.size != 2 || !userInput[0].all { it.isDigit() } || !userInput[1].all { it.isDigit() }) {
            println("You should enter numbers!")
            continue
        }

        val row = userInput[0].toInt()
        val col = userInput[1].toInt()

        // Check if coordinates are within the 1-3 range
        if (row !in 1..3 || col !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        val index = (row - 1) * 3 + (col - 1)

        // Check if the selected cell is already occupied
        if (gameList[index] != "_") {
            println("This cell is occupied! Choose another one!")
            continue
        }

        // Place the current player's move
        gameList[index] = currentPlayer

        // Update the grid and display it
        updateGridFromGameList()
        printInput(firstRaw.joinToString(" "), secondRaw.joinToString(" "), thirdRaw.joinToString(" "))

        // Check for a win or draw
        when (checkGameState()) {
            "X wins" -> {
                println("X wins")
                break
            }
            "O wins" -> {
                println("O wins")
                break
            }
            "Draw" -> {
                println("Draw")
                break
            }
            else -> {
                // Continue the game and switch the player
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }
}

fun checkGameState(): String {
    val lines = listOf(
        listOf(gameList[0], gameList[1], gameList[2]), // Row 1
        listOf(gameList[3], gameList[4], gameList[5]), // Row 2
        listOf(gameList[6], gameList[7], gameList[8]), // Row 3
        listOf(gameList[0], gameList[3], gameList[6]), // Column 1
        listOf(gameList[1], gameList[4], gameList[7]), // Column 2
        listOf(gameList[2], gameList[5], gameList[8]), // Column 3
        listOf(gameList[0], gameList[4], gameList[8]), // Diagonal 1
        listOf(gameList[2], gameList[4], gameList[6])  // Diagonal 2
    )

    // Check for any winning condition
    for (line in lines) {
        if (line.all { it == "X" }) {
            return "X wins"
        }
        if (line.all { it == "O" }) {
            return "O wins"
        }
    }

    // Check for draw or unfinished game
    return if ("_" in gameList) "Game not finished" else "Draw"
}

// Update the grid after every move
fun updateGridFromGameList() {
    firstRaw.clear()
    secondRaw.clear()
    thirdRaw.clear()

    for (i in 0..2) {
        firstRaw.add(gameList[i])
    }
    for (i in 3..5) {
        secondRaw.add(gameList[i])
    }
    for (i in 6..8) {
        thirdRaw.add(gameList[i])
    }
}

val firstRaw: MutableList<String> = mutableListOf()
val secondRaw: MutableList<String> = mutableListOf()
val thirdRaw: MutableList<String> = mutableListOf()
val gameList: MutableList<String> = mutableListOf()

fun printInput(fRaw: String, sRaw: String, tRaw: String) {
    println("---------")
    println("| $fRaw |")
    println("| $sRaw |")
    println("| $tRaw |")
    println("---------")
}

fun makeList(input: String) {
    for (i in 0..2) {
        firstRaw.add(input[i].toString())
    }
    for (i in 3..5) {
        secondRaw.add(input[i].toString())
    }
    for (i in 6..8) {
        thirdRaw.add(input[i].toString())
    }
    for (i in 0 .. 8) {
        gameList.add(input[i].toString())
    }
}
