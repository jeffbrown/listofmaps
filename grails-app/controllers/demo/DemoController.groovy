package demo

class DemoController {

    def demo(MyCommand co) {
        [command: co]
    }

    def demo2(MyOtherCommand co) {
        [command: co]
    }
}

class MyCommand {
    List<Map> data
}

class MyOtherCommand {
    List<List> listOfListOfPeople
}
