package demo

class DemoController {

    def demo(MyCommand co) {
        [command: co]
    }
}

class MyCommand {
    List<Map> data
}
