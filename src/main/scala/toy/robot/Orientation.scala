package toy.robot

trait Orientation {
  def move(position: Position): Position
}

object North extends Orientation {
  override def move(position: Position): Position = {
    Position(position.x, position.y + 1)
  }
}

object South extends Orientation {
  override def move(position: Position): Position = {
    Position(position.x, position.y - 1)
  }
}

object West extends Orientation {
  override def move(position: Position): Position = {
    Position(position.x - 1, position.y)
  }
}

object East extends Orientation {
  override def move(position: Position): Position = {
    Position(position.x + 1, position.y)
  }
}
