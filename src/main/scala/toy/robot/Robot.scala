package toy.robot

case class Position(x: Int, y: Int)

case class Robot(x: Int, y: Int, orientation: Orientation) {
  def this(position: Position, orientation: Orientation) = {
    this(position.x, position.y, orientation)
  }

  def position: Position = Position(x, y)
}
