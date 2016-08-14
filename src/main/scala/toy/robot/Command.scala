package toy.robot

trait Command {
  val orientations: Seq[Orientation] = Vector(North, West, South, East)
  def execute(robot: Robot): Robot
}


object Move extends Command {
  override def execute(robot: Robot): Robot =
    new Robot(robot.orientation.move(robot.position), robot.orientation)
}

case class Place(x: Int, y: Int, orientation: Orientation) extends Command {
  override def execute(position: Robot): Robot = {
    Robot(x, y, orientation)
  }
}

object Left extends Command {
  override def execute(position: Robot): Robot = {
    val nextIndex = (orientations.indexOf(position.orientation) + 1) % orientations.size
    Robot(position.x, position.y, orientations(nextIndex))
  }
}

object Right extends Command {
  override def execute(position: Robot): Robot = {
    val nextIndex = (orientations.size + orientations.indexOf(position.orientation) - 1) % orientations.size
    Robot(position.x, position.y, orientations(nextIndex))
  }
}

trait Displayable {
  def display(text: String): Unit
}

case class Report(displayable: Displayable) extends Command {
  override def execute(position: Robot): Robot = {
    displayable.display(s"${position.x},${position.y},${textOf(position.orientation)}")
    position
  }

  private def textOf(orientation: Orientation): String = {
    orientation match {
      case North => "NORTH"
      case South => "SOUTH"
      case West => "WEST"
      case East => "EAST"
    }
  }
}
