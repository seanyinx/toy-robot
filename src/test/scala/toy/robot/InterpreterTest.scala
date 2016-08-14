package toy.robot

import org.scalatest.{Matchers, WordSpec}

import scala.util.Random

class InterpreterTest extends WordSpec with Matchers {

  object DoNothingDisplay extends Displayable {
    override def display(text: String): Unit = {
    }
  }

  case class ConcreteInterpreter() extends Interpreter {
    override val displayable: Displayable = DoNothingDisplay
  }

  val interpreter = ConcreteInterpreter()

  "Interpreter" should {
    "parse valid place command" in {
      interpreter.parse("PLACE 0,0,NORTH") shouldBe Place(0, 0, North)
    }

    "blow up when parsing invalid place command" in {
      intercept[IllegalArgumentException] {
        interpreter.parse("PLACE 0,NORTH")
      }

      intercept[IllegalArgumentException] {
        interpreter.parse("PLACE 0,0,NORTH,1")
      }
    }

    "parse valid move command" in {
      interpreter.parse("MOVE") shouldBe Move
    }

    "parse valid left command" in {
      interpreter.parse("LEFT") shouldBe Left
    }

    "parse valid right command" in {
      interpreter.parse("RIGHT") shouldBe Right
    }

    "parse valid REPORT command" in {
      interpreter.parse("REPORT") shouldBe Report(DoNothingDisplay)
    }

    "blow up on unrecognized command" in {
      intercept[IllegalArgumentException] {
        interpreter.parse(new Random().alphanumeric.take(5).toList.mkString(""))
      }
    }
  }
}
