package toy.robot

import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

class CommandTest extends WordSpec with Matchers with BeforeAndAfter {
  var displayed: List[String] = Nil

  object ConsoleDisplay extends Displayable {
    override def display(text: String): Unit = displayed = text :: displayed
  }

  val displayable: Displayable = ConsoleDisplay
  var robot = Robot(0, 0, North)

  before {
    displayed = Nil
    robot = Robot(0, 0, North)
  }

  "Command" should {
    "place robot to provided position on table" in {
      Place(0,0,North).execute(robot) shouldBe Robot(0, 0, North)
      Place(2,4,North).execute(robot) shouldBe Robot(2, 4, North)
    }

    "move robot forward to north" in {
      robot = Move.execute(robot)
      robot shouldBe Robot(0, 1, North)
    }

    "move forward to south" in {
      robot = Place(0, 1, South).execute(robot)
      robot shouldBe Robot(0, 1, South)
      Move.execute(robot) shouldBe Robot(0, 0, South)
    }

    "turn left" in {
      robot = Left.execute(robot)
      robot shouldBe Robot(0, 0, West)
      robot = Left.execute(robot)
      robot shouldBe Robot(0, 0, South)
      robot = Left.execute(robot)
      robot shouldBe Robot(0, 0, East)
      robot = Left.execute(robot)
      robot shouldBe Robot(0, 0, North)
    }

    "turn right" in {
      robot = Right.execute(robot)
      robot shouldBe Robot(0, 0, East)
      robot = Right.execute(robot)
      robot shouldBe Robot(0, 0, South)
      robot = Right.execute(robot)
      robot shouldBe Robot(0, 0, West)
      robot = Right.execute(robot)
      robot shouldBe Robot(0, 0, North)
    }

    "report position" in {
      Report(displayable).execute(robot) shouldBe robot
      displayed shouldBe List("0,0,NORTH")
    }
  }
}
