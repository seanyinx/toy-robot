package toy.robot

trait Interpreter {
  val displayable: Displayable

  def parse(command: String): Command = {
    val arguments: Array[String] = command.split(' ')

    arguments.head.trim match {
      case "MOVE" => Move
      case "PLACE" => arguments.tail.flatMap(_.split(',')).toList match {
        case x :: y :: orientation :: Nil => place(x.toInt, y.toInt, orientation)
        case _ => throw new IllegalArgumentException
      }
      case "LEFT" => Left
      case "RIGHT" => Right
      case "REPORT" => Report(displayable)
      case _ => throw new IllegalArgumentException
    }
  }

  private def place(x: Int, y: Int, orientation: String): Place = {
    Place(x, y, toOrientation(orientation))
  }

  private def toOrientation(orientation: String): Orientation = {
    orientation match {
      case "NORTH" => North
      case "SOUTH" => South
      case "WEST" => West
      case "EAST" => East
      case _ => throw new scala.IllegalArgumentException
    }
  }
}
