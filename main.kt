interface Transport {
    val avgSpeed: Int
    val fuelUsage: Double
    val fuelType: String
    val fuelUnits: String
    val engineType: String
    val engineCapacity: Int
    val transportType: String

    var fuel: Double
    var isStarted: Boolean

    fun info(): String

    fun start() {
        if (!checkFuel()) {
            isStarted = false
            println("Can't start engine. No fuel.")
            return
        }
        if (isStarted) {
            println("Can't start engine. It's started.")
            return
        }
        isStarted = true
        println("Engine has started...")
    }

    fun stop() {
        if (!isStarted) {
            println("Can't stop engine. It's stopped.")
            return
        }
        isStarted = false
        println("Engine has stopped...")
    }

    fun checkFuel(): Boolean {
        if (this.fuel == 0.0) {
            this.stop()
            return false
        }
        return fuel > 0
    }

    fun goTo(destination: String, distance: Int) {
        if (!isStarted) {
            println("Can't get to $destination. Engine is stopped.")
            return
        }
        if (!checkFuel()) {
            println("Can't get to $destination. No fuel. :^(")
            return
        }
        val fuelNeeded = distance / this.avgSpeed / this.fuelUsage
        if (fuelNeeded > this.fuel) {
            println("Can't get to $destination. Need more fuel. (Fuel: ${this.fuel}, needed: $fuelNeeded) :^(")
            return
        }
        this.fuel -= fuelNeeded
        println("You successfully got to $destination. Fuel left: ${this.fuel}.")
    }

    fun sound() {
        println("Bee-beep!")
    }

    fun fuelFill(amount: Double) {
        this.fuel += amount
    }
}

class Car(
    private val carType: String = "Sedan",
    private val amountOfSeats: Int = 4,
    override val engineCapacity: Int = 4,
    override val fuelUsage: Double = 1.0,
    override val fuelType: String = "Gasoline",
    override val fuelUnits: String = "l",
    override val avgSpeed: Int = 80,
    override val engineType: String = "ICE",
    override var fuel: Double = 40.0,
    private val amountOfWheels: Int = 4
) : Transport {
    override val transportType = "car"
    override var isStarted: Boolean = false

    override fun info(): String = """
        Transport type: ${this.carType} ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Amount of seats: ${this.amountOfSeats}
        Amount of wheels: ${this.amountOfWheels}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h
    """.trimIndent()
}
class Bus(
    private val amountOfSeats: Int = 24,
    override val engineCapacity: Int = 10,
    override val fuelUsage: Double = 1.0,
    override val fuelType: String = "Gasoline",
    override val fuelUnits: String = "l",
    override val avgSpeed: Int = 60,
    override val engineType: String = "ICE",
    override var fuel: Double = 100.0,
    private val amountOfWheels: Int = 6
) : Transport {
    override val transportType = "bus"
    override var isStarted: Boolean = false

    override fun sound() {
        println("Bo-o-o-op")
    }

    override fun info(): String = """
        Transport type: ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Amount of seats: ${this.amountOfSeats}
        Amount of wheels: ${this.amountOfWheels}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h
    """.trimIndent()
}

open class Train(
    override val engineCapacity: Int = 1000,
    override val fuelUsage: Double = 0.5,
    override val fuelType: String = "Electricity",
    override val fuelUnits: String = "W",
    override val avgSpeed: Int = 100,
    override val engineType: String = "TEM",
    override var fuel: Double = 4000.0,
    private val amountOfCars: Int = 10,
) : Transport {
    override val transportType = "train"
    override var isStarted: Boolean = false

    override fun sound() {
        println("Too-too!")
    }

    override fun info(): String = """
        Transport type: ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Amount of cars: ${this.amountOfCars}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}

class PassengerTrain(
    private val typeOfTrain: String = "Passenger",
    private val amountOfSeats: Int = 512,
    engineCapacity: Int = 1000,
    fuelUsage: Double = 0.5,
    fuelType: String = "Electricity",
    fuelUnits: String = "W",
    avgSpeed: Int = 100,
    engineType: String = "TEM",
    fuel: Double = 4000.0,
    private val amountOfCars: Int = 10,
) : Train(
    engineCapacity,
    fuelUsage,
    fuelType,
    fuelUnits,
    avgSpeed,
    engineType,
    fuel,
    amountOfCars
) {
    override fun info(): String = """
        Transport type: ${this.typeOfTrain} ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Amount of seats: ${this.amountOfSeats}
        Amount of cars: ${this.amountOfCars}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}

class CargoTrain(
    private val typeOfTrain: String = "Cargo",
    private val loadCapabilityTons: Int = 50,
    engineCapacity: Int = 1000,
    fuelUsage: Double = 0.5,
    fuelType: String = "Electricity",
    fuelUnits: String = "W",
    avgSpeed: Int = 100,
    engineType: String = "TEM",
    fuel: Double = 4000.0,
    private val amountOfCars: Int = 50,
) : Train(
    engineCapacity,
    fuelUsage,
    fuelType,
    fuelUnits,
    avgSpeed,
    engineType,
    fuel,
    amountOfCars
) {
    override fun info(): String = """
        Transport type: ${this.typeOfTrain} ${this.transportType}
        Load capability: ${this.loadCapabilityTons} tons
        Average speed: ${this.avgSpeed} km/h
        Amount of cars: ${this.amountOfCars}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}

open class Plane(
    override val engineCapacity: Int = 10000,
    override val fuelUsage: Double = 3.0,
    override val fuelType: String = "Aviation fuel",
    override val fuelUnits: String = "L",
    override val avgSpeed: Int = 800,
    override val engineType: String = "Aircraft engine",
    override var fuel: Double = 10000.0,
) : Transport {
    override val transportType = "plane"
    override var isStarted: Boolean = false

    override fun sound() {
        println("We-e-e-e!")
    }

    override fun info(): String = """
        Transport type: ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}


class PassengerPlane(
    private val typeOfPlane: String = "Passenger",
    private val amountOfSeats: Int = 512,
    engineCapacity: Int = 10000,
    fuelUsage: Double = 3.0,
    fuelType: String = "Aviation fuel",
    fuelUnits: String = "L",
    avgSpeed: Int = 800,
    engineType: String = "Aircraft engine",
    fuel: Double = 10000.0
) : Plane(
    engineCapacity,
    fuelUsage,
    fuelType,
    fuelUnits,
    avgSpeed,
    engineType,
    fuel
) {
    override fun info(): String = """
        Transport type: ${this.typeOfPlane} ${this.transportType}
        Average speed: ${this.avgSpeed} km/h
        Amount of seats: ${this.amountOfSeats}
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}

class CargoPlane(
    private val typeOfPlane: String = "Cargo",
    private val loadCapabilityTons: Int = 100,
    engineCapacity: Int = 20000,
    fuelUsage: Double = 5.0,
    fuelType: String = "Aviation fuel",
    fuelUnits: String = "L",
    avgSpeed: Int = 700,
    engineType: String = "Aircraft engine",
    fuel: Double = 15000.0
) : Plane(
    engineCapacity,
    fuelUsage,
    fuelType,
    fuelUnits,
    avgSpeed,
    engineType,
    fuel
) {
    override fun info(): String = """
        Transport type: ${this.typeOfPlane} ${this.transportType}
        Load capability: ${this.loadCapabilityTons} tons
        Average speed: ${this.avgSpeed} km/h
        Engine: ${this.engineType}, ${this.engineCapacity}L
        Fuel: ${this.fuelType}, ${this.fuel}${this.fuelUnits}, ${this.fuelUsage} ${this.fuelUnits}/h usage
    """.trimIndent()
}


fun createCar(choice: Int = 1): Car {

    var carType: String
    var amountOfSeats: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double
    var amountOfWheels: Int

    if (choice == 1) {
        return Car()
    }

    while (true) {
        println("Введите тип легковой машины:")
        carType = readLine() ?: ""
        if (carType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество мест в легковой машине:")
        amountOfSeats = readLine()?.toIntOrNull() ?: 0
        if (amountOfSeats in 1..9) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество колес легковой машины:")
        amountOfWheels = readLine()?.toIntOrNull() ?: 0
        if (amountOfWheels in 1..9) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите объем двигателя легковой машины (в литрах):")
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива легковой машины:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива легковой машины:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива легковой машины (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите среднюю скорость легковой машины (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя легковой машины:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива легковой машины:")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return Car(
        carType,
        amountOfSeats,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel,
        amountOfWheels
    )
}

fun createBus(choice: Int = 1): Bus {

    var amountOfSeats: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double
    var amountOfWheels: Int

    if (choice == 1) {
        return Bus()
    }

    while (true) {
        println("Введите количество мест в автобусе:")
        amountOfSeats = readLine()?.toIntOrNull() ?: 0
        if (amountOfSeats in 10..100) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество колес автобуса:")
        amountOfWheels = readLine()?.toIntOrNull() ?: 0
        if (amountOfWheels in 4..20) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите объем двигателя автобуса (в литрах):")
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива автобуса:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива автобуса:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива автобуса (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите среднюю скорость автобуса (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя автобуса:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива автобуса:")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return Bus(
        amountOfSeats,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel,
        amountOfWheels
    )
}

fun createPassengerTrain(choice: Int = 1): PassengerTrain {

    var amountOfSeats: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double
    var amountOfCars: Int

    if (choice == 1) {
        return PassengerTrain()
    }

    while (true) {
        println("Введите количество мест в пассажирском поезде:")
        amountOfSeats = readLine()?.toIntOrNull() ?: 0
        if (amountOfSeats in 100..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество вагонов пассажирского поезда:")
        amountOfCars = readLine()?.toIntOrNull() ?: 0
        if (amountOfCars in 10..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите объем двигателя пассажирского поезда (в литрах):")
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива пассажирского поезда:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива пассажирского поезда:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива пассажирского поезда (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }


    while (true) {
        println("Введите среднюю скорость пассажирского поезда (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя пассажирского поезда:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива пассажирского поезда:")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return PassengerTrain(
        "Passenger",
        amountOfSeats,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel,
        amountOfCars
    )
}

fun createCargoTrain(choice: Int = 1): CargoTrain {

    var loadCapabilityTons: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double
    var amountOfCars: Int

    if (choice == 1) {
        return CargoTrain()
    }

    while (true) {
        println("Введите грузоподъемность грузового поезда (в тоннах):")
        loadCapabilityTons = readLine()?.toIntOrNull() ?: 0
        if (loadCapabilityTons in 100..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество вагонов грузового поезда:")
        amountOfCars = readLine()?.toIntOrNull() ?: 0
        if (amountOfCars in 5..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите объем двигателя грузового поезда (в литрах):")
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива грузового поезда:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива грузового поезда:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива грузового поезда (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите среднюю скорость грузового поезда (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя грузового поезда:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива грузового поезда (в литрах):")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return CargoTrain(
        "Cargo",
        loadCapabilityTons,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel,
        amountOfCars
    )
}

fun createPassengerPlane(choice: Int = 1): PassengerPlane {

    var amountOfSeats: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double

    if (choice == 1) {
        return PassengerPlane()
    }

    while (true) {
        println("Введите количество мест в пассажирском самолете:")
        amountOfSeats = readLine()?.toIntOrNull() ?: 0
        if (amountOfSeats in 50..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println(
            "Введите объем двигателя пассажирского самолета (в литрах):"
        )
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива пассажирского самолета:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива пассажирского самолета:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива пассажирского самолета (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите среднюю скорость пассажирского самолета (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя пассажирского самолета:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива пассажирского самолета:")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return PassengerPlane(
        "Passenger",
        amountOfSeats,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel
    )
}

fun createCargoPlane(choice: Int = 1): CargoPlane {

    var loadCapabilityTons: Int
    var engineCapacity: Int
    var fuelUsage: Double
    var fuelType: String
    var fuelUnits: String
    var avgSpeed: Int
    var engineType: String
    var fuel: Double

    if (choice == 1) {
        return CargoPlane()
    }

    while (true) {
        println("Введите грузоподъемность грузового самолета (в тоннах):")
        loadCapabilityTons = readLine()?.toIntOrNull() ?: 0
        if (loadCapabilityTons in 50..1000) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println(
            "Введите объем двигателя грузового самолета (в литрах):"
        )
        engineCapacity = readLine()?.toIntOrNull() ?: 0
        if (engineCapacity > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип топлива грузового самолета:")
        fuelType = readLine() ?: ""
        if (fuelType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите единицы измерения топлива грузового самолета:")
        fuelUnits = readLine() ?: ""
        if (fuelUnits.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите расход топлива грузового самолета (в $fuelUnits/h):")
        fuelUsage = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuelUsage > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите среднюю скорость грузового самолета (в км/ч):")
        avgSpeed = readLine()?.toIntOrNull() ?: 0
        if (avgSpeed > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите тип двигателя грузового самолета:")
        engineType = readLine() ?: ""
        if (engineType.isNotEmpty()) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    while (true) {
        println("Введите количество топлива грузового самолета:")
        fuel = readLine()?.toDoubleOrNull() ?: 0.0
        if (fuel > 0) break
        println("Некорректный ввод. Попробуйте снова.")
    }

    return CargoPlane(
        "Cargo",
        loadCapabilityTons,
        engineCapacity,
        fuelUsage,
        fuelType,
        fuelUnits,
        avgSpeed,
        engineType,
        fuel
    )
}

fun main() {
    var transport: Transport? = null

    while (true) {
        println("Выберите действие:")
        println("1. Создать транспортное средство")
        println("2. Запустить двигатель")
        println("3. Остановить двигатель")
        println("4. Поехать куда-то")
        println("5. Посмотреть информацию о транспортном средстве")
        println("6. Сигнал")
        println("7. Выход")
        val choice = readLine()?.toIntOrNull() ?: 0

        when (choice) {
            1 -> {
                println("Выберите тип транспортного средства:")
                println("1. Легковая машина")
                println("2. Автобус")
                println("3. Пассажирский поезд")
                println("4. Грузовой поезд")
                println("5. Пассажирский самолет")
                println("6. Грузовой самолет")
                val typeChoice = readLine()?.toIntOrNull() ?: 0

                // Хотите сами ввести данные или дефолтные?
                while (true) {
                    println("Хотите сами ввести данные или дефолтные?")
                    println("1. Самостоятельно")
                    println("2. Предустановленные")
                    val choice = readLine()?.toIntOrNull() ?: 0
                    if (choice in 1..2) break
                    println("Некорректный ввод. Попробуйте снова.")
                }

                transport = when (typeChoice) {
                    1 -> createCar(choice)
                    2 -> createBus(choice)
                    3 -> createPassengerTrain(choice)
                    4 -> createCargoTrain(choice)
                    5 -> createPassengerPlane(choice)
                    6 -> createCargoPlane(choice)
                    else -> null
                }

                println("Транспортное средство создано.")
            }
            2 -> {
                transport?.start()
            }
            3 -> {
                transport?.stop()
            }
            4 -> {
                println("Введите пункт назначения:")
                val destination = readLine() ?: ""
                println("Введите расстояние до пункта назначения (в км):")
                val distance = readLine()?.toIntOrNull() ?: 0
                transport?.goTo(destination, distance)
            }
            5 -> {
                println(transport?.info() ?: "Транспортное средство не создано.")
            }
            6 -> {
                transport?.sound()
            }
            7 -> {
                println("Программа завершена.")
                break
            }
            else -> {
                println("Некорректный ввод. Попробуйте снова.")
            }
        }
    }
}

