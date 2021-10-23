/**
 * MowItNow
 * Chenchen FENG, IMSD 2021
 */

package main

object RunMower extends App {

  def lawnMowing(instruction : Array[String], verbose : Boolean = false): Unit ={
    // Controlling function
    try{

      val ordersArray : Array[String]= instruction
      val limit = ordersArray(0).split(" ").map(_.toInt) // Define terrain's size
      assert(limit.forall(_ >= 0)) // To be sure there is no negative value in the coordinates.
      println(s"Running... ")

      for (i <- 1 until ordersArray.length if i%2 == 1){  // Take 2 lines - 1 mower at a time
        println(s"\nMower no. ${(i + 1)/2}")

        // Initial position of the mower from first instruction line
        val initialStand = ordersArray(i).toUpperCase.split(" ")
        val initialPosition = initialStand.take(2).map(_.toInt)
        assert(initialPosition.forall(_ >= 0))

        val myMower = new Mower(limit ,
          initialPosition,
          initialStand(2)
        )

        // Handling each character of second instruction line as an action
        var orders = ordersArray(i+1).toUpperCase
        for (order <- orders){
          order match{
            case 'A' => myMower.move(verbose)
            case 'D' | 'G' => myMower.rotate(order , verbose)
            case _ => if (verbose) println(s"$order is an invalid order.")
          }
        }
        println(s"$myMower")
      }

    } catch {
      // Display errors in the input file.
      case nfe: NumberFormatException => println("File structure is invalid. Please follow instructions and modify the file.")
      case ind: ArrayIndexOutOfBoundsException => println("Starting orientation is not valid.")
      case asrt: AssertionError => println("Some input numbers are invalid.")
      case ex: Exception => ex.printStackTrace()
    }
  }


  def getFile(): Array[String] = {
    // Handling input file
    println("Please enter filepath: ")
    val filepath = scala.io.StdIn.readLine()
    val fileSource = scala.io.Source.fromFile(filepath)
    val fileArray = fileSource.getLines.toArray
    fileSource.close
    return fileArray
  }

  val MowIt = getFile()
  lawnMowing(MowIt)
}
