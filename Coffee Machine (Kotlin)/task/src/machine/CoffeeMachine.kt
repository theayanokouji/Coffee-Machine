package machine

class CoffeeMachine (
    _water: Int,
    _milk: Int,
    _coffeeBeans: Int,
    _cupsOfCoffee: Int,
    _money: Int
) {
    private var water: Int = _water
    private var milk: Int = _milk
    private var coffeeBeans: Int = _coffeeBeans
    private var cupsOfCoffee: Int = _cupsOfCoffee
    private var money: Int = _money

    // function to display
    private fun display() {
        // calculate the total quantity of ingredients needed to make n cups of coffee
        println("The coffee machine has:")
        val output = "%d ml of water\n%d ml of milk\n%d g of coffee beans".format(
            water, milk, coffeeBeans
        )
        println(output)
        println("$cupsOfCoffee disposable cups")
        println("$$money of money")
    }

    /**
     * @param amountOfWater - the amount of water needed to make the coffee
     * @param amountOfCoffeeBeans - the amount of coffee beans needed to make the coffee
     * @param amountOfMilk - the amount of milk needed to make the coffee
     * @return the integer representing the state of the coffee machine, any number other than 0 means there are little to no supplies
     */
    private fun hasEnoughSupplies(
        amountOfWater: Int,
        amountOfCoffeeBeans: Int,
        amountOfMilk: Int = 0
    ): Int {
        if (water < amountOfWater) {
            println("Sorry, not enough water!")
            return 1 // leave function
        }
        if (coffeeBeans < amountOfCoffeeBeans) {
            println("Sorry, not enough coffee beans!")
            return 2
        }
        if (amountOfMilk != 0) {
            if (milk < amountOfMilk) {
                println("Sorry, not enough milk!")
                return 3
            }
        }
        // if it reaches here then it means the resources are there
        println("I have enough resources, making you a coffee!")
        return 0 // if everything went well
    }

    /**
     * @param prompt - string to be displayed when asking user for input
     */
    private fun getUserInput(prompt: String): Int {
        println(prompt)
        var n = 0
        try {
            n = readln().toInt()
        } catch (e: NumberFormatException) {
            println("Please enter a valid integer number")
        }
        return n
    }

    private fun validatePurchase(typeOfCoffee: Int) {
        var response = -1
        when (typeOfCoffee) {
            1 -> {
                // espresso
                // check if there's enough resources
                response = hasEnoughSupplies(250, 16)
                if (response == 0) {
                    water -= 250
                    coffeeBeans -= 16
                    money += 4
                    cupsOfCoffee--
                }
            }
            2 -> {
                // latte
                response = hasEnoughSupplies(350, 20, 75)
                if (response == 0) {
                    water -= 350
                    milk -= 75
                    coffeeBeans -= 20
                    money += 7
                    cupsOfCoffee--
                }
            }
            3 -> {
                // cappuccino
                response = hasEnoughSupplies(200, 12, 100)
                if (response == 0) {
                    water -= 200
                    milk -= 100
                    coffeeBeans -= 12
                    money += 6 // costs $6
                    cupsOfCoffee--
                }
            }
        }
    }

    // takes orders from the user
    fun takeOrders() {
        while (true) {
            println("Write action (buy, fill, take, remaining, exit):")
            val action = readln()
            var typeOfCoffee = 0
            when (action.lowercase()) {
                "fill" -> {
                    water += getUserInput("Write how many ml of water you want to add:")
                    milk += getUserInput("Write how many ml of milk you want to add:")
                    coffeeBeans += getUserInput("Write how many grams of coffee beans you want to add:")
                    cupsOfCoffee += getUserInput("Write how many disposable cups you want to add:")
                }
                "buy" -> {
                    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                    var line = readln()
                    if (line.lowercase() == "back") continue
                    while (true) {
                        try {
                            typeOfCoffee = line.toInt()
                            break
                        } catch (e: NumberFormatException) {
                            println("please enter a valid number")
                        }
                        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                        line = readln()
                    }
                    // validate purchase of coffee
                    validatePurchase(typeOfCoffee)
                }
                "take" -> {
                    println("I gave you $$money")
                    money = 0
                }
                "remaining" -> display()
                "exit" -> break
            }
        }
    }
}