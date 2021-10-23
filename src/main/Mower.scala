/**
 * MowItNow
 * Chenchen FENG, IMSD 2021
 */

package main

class Mower(startPosition:Array[Int], limit:Array[Int], startDirection:String) {
  // Create variables
  private val cardinalAbbrev = List("E" , "N" , "W" , "S")
  private val CardinalPoints = Array("East" , "North" , "West" , "South")

  private val maxSize = limit
  private var position = startPosition
  private var orientation : Int = this.cardinalAbbrev.indexOf(startDirection)

  // Print the position output
  override def toString: String = s"${this.position.mkString(" ")} ${cardinalAbbrev(orientation)} \n The mower is currently at (${this.position.mkString(", ")}), facing ${CardinalPoints(orientation)}."

  // Handling rotation orders (D & G)
  def rotate(turn:Char , verbose: Boolean = false): Unit ={
    val newCardinal : Int = this.orientation + {if (turn == 'D') -1 else 1}
    this.orientation = (newCardinal + 4) % 4
    if (verbose) println(s"${this.CardinalPoints(this.orientation)}.")
  }

  // Process moving orders (A)
  def move(verbose: Boolean =false): Unit ={
    val nextSquare = this.position
    val movement = {if (this.orientation < 2) 1 else -1}
    val axis = this.orientation % 2
    nextSquare(axis) += movement

    // Before assigning the new position, verify that the position is valid.
    if (0 <= nextSquare(axis) & nextSquare(axis) <= this.maxSize(axis)){
      this.position = nextSquare
      if (verbose) println(s"The mower has moved to ${this.CardinalPoints(this.orientation)}.")
    } else if (verbose) println("The mower cannot move there.")
  }
}

