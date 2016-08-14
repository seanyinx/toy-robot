package toy.robot

trait RobotApp extends Interpreter {
  val width: Int
  val height: Int
  val displayable: Displayable

  private var robot: Robot = _
  private var runCmd: String => Unit = runUninitialized

  def run(input: String): Unit = {
    runCmd(input)
  }

  private def runInitialized(input: String): Unit = {
    update(parse(input))
  }

  private def runUninitialized(input: String): Unit = {
    val cmd: Command = parse(input)
    cmd match {
      case p: Place if update(cmd) => runCmd = runInitialized
      case _ => // ignore till robot is properly placed
    }
  }

  private def update(cmd: Command): Boolean = {
    val next: Robot = cmd.execute(robot)
    val onTable: Boolean = !isOffTable(next.x, next.y)
    if (onTable)
      robot = next
    onTable
  }

  private def isOffTable(x: Int, y: Int): Boolean = {
    x >= width || x < 0 || y >= height || y < 0
  }
}
