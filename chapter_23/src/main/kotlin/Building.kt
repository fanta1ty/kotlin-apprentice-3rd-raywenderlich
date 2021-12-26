import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Building(
  val name: String,
  var floors: Int = 0,
  private val scope: CoroutineScope
) {

  suspend fun makeFoundation() = scope.launch {
    delay(300)
    speakThroughBullhorn("[${Thread.currentThread()}] The foundation is ready")
  }

  suspend fun buildFloor(floor: Int) = scope.launch {
    delay(100)
    speakThroughBullhorn("[${Thread.currentThread()}] The $floor'th floor is raised Floor number $floor floor is built")
    ++floors
  }

  suspend fun placeWindows(floor: Int) = scope.launch {
    delay(100)
    speakThroughBullhorn("[${Thread.currentThread()}] Windows are placed on the $floor'th floor")
  }

  suspend fun installDoors(floor: Int) = scope.launch {
    delay(100)
    speakThroughBullhorn("[${Thread.currentThread()}] Doors are installed on the $floor'th floor")
  }

  suspend fun provideElectricity(floor: Int) = scope.launch {
    delay(100)
    speakThroughBullhorn("[${Thread.currentThread()}] Electricity is provided on the $floor'th floor")
  }

  suspend fun buildRoof() = scope.launch {
    delay(200)
    speakThroughBullhorn("[${Thread.currentThread()}] The roof is ready")
  }

  suspend fun fitOut(floor: Int) = scope.launch {
    delay(200)
    speakThroughBullhorn("[${Thread.currentThread()}] The $floor'th floor is furnished")
  }

  fun speakThroughBullhorn(message: String) = println(message)

}
