package machine

fun main() {
    // take orders from the user
    val coffeeMachine = CoffeeMachine(_water = 400, _milk = 540, _coffeeBeans = 120, _cupsOfCoffee = 9, _money = 550)
    coffeeMachine.takeOrders()
}
