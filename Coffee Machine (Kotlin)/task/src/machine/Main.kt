package machine

fun main() {
    // take orders from the user
    val coffeeMachine = CoffeeMachine(water = 400, milk = 540, coffeeBeans = 120, cupsOfCoffee = 9, money = 550)
    coffeeMachine.takeOrders()
}
