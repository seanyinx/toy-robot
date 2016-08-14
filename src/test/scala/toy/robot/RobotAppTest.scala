package toy.robot

import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

class RobotAppTest extends WordSpec with Matchers with BeforeAndAfter {
  var displayed: List[String] = Nil

  object ConsoleDisplay extends Displayable {
    override def display(text: String): Unit = displayed = text :: displayed
  }

  case class ConcreteRobot() extends RobotApp {

    val width: Int = 5
    val height: Int = 5
    val displayable: Displayable = ConsoleDisplay
  }

  val robot = ConcreteRobot()

  before {
    displayed = Nil
  }

  "Robot App" should {
    "not entertain placing robot outside table" in {
      robot.run("PLACE 5,0,NORTH")
      robot.run("PLACE 0,5,NORTH")
      robot.run("PLACE 0,-1,NORTH")
      robot.run("PLACE -1,0,NORTH")

      displayed shouldBe Nil
    }

    "ignore moving request if it results in falling off table" in {
      robot.run("PLACE 0,4,NORTH")
      robot.run("MOVE")
      robot.run("PLACE 0,0,SOUTH")
      robot.run("MOVE")
      robot.run("PLACE 0,0,WEST")
      robot.run("MOVE")
      robot.run("PLACE 4,0,EAST")
      robot.run("MOVE")

      displayed shouldBe Nil
    }

    "ignore other commands till placed on table" in {
      val robot = ConcreteRobot()

      robot.run("MOVE")
      robot.run("PLACE -1,0,NORTH")
      robot.run("LEFT")
      robot.run("RIGHT")
      robot.run("REPORT")

      displayed shouldBe Nil

      robot.run("PLACE 0,0,NORTH")
      robot.run("REPORT")
      displayed shouldBe List("0,0,NORTH")
    }

    "run commands and produce expected output" in {
      robot.run("PLACE 1,2,EAST")
      robot.run("MOVE")
      robot.run("MOVE")
      robot.run("LEFT")
      robot.run("MOVE")
      robot.run("REPORT")

      displayed shouldBe List("3,3,NORTH")
    }
  }
}
