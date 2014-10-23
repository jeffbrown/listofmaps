package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
class DemoControllerSpec extends Specification {

    void "test binding a List of List of Person to a command object"() {
        when:
        request.method = 'POST'
        request.json = '''
            {"listOfListOfPeople": [
                    [
                      {"firstName":"Jeff", "lastName":"Brown"},
                      {"firstName":"Betsy", "lastName":"Brown"}
                    ],
                    [
                      {"firstName":"Jeff", "lastName":"Brown"},
                      {"firstName":"Lari", "lastName":"Hotari"},
                      {"firstName":"Graeme", "lastName":"Rocher"}
                    ]
                ]
            }
        '''
        def model = controller.demo2()
        def command = model.command

        then:
        command instanceof MyOtherCommand
        command.listOfListOfPeople instanceof List
        command.listOfListOfPeople.size() == 2

        command.listOfListOfPeople[0] instanceof List
        command.listOfListOfPeople[0].size() == 2

        command.listOfListOfPeople[0][0].firstName == 'Jeff'
        command.listOfListOfPeople[0][0].lastName == 'Brown'

        command.listOfListOfPeople[0][1].firstName == 'Betsy'
        command.listOfListOfPeople[0][1].lastName == 'Brown'

        command.listOfListOfPeople[1] instanceof List
        command.listOfListOfPeople[1].size() == 3

        command.listOfListOfPeople[1][0].firstName == 'Jeff'
        command.listOfListOfPeople[1][0].lastName == 'Brown'

        command.listOfListOfPeople[1][1].firstName == 'Lari'
        command.listOfListOfPeople[1][1].lastName == 'Hotari'

        command.listOfListOfPeople[1][2].firstName == 'Graeme'
        command.listOfListOfPeople[1][2].lastName == 'Rocher'
    }

    void "test binding a List of Map to a command object"() {
        when:
        request.method = 'POST'
        request.json = '''
            {"data": [
                      {"prop1":"a", "prop2":"b", "prop3":"c"},
                      {"prop1":"d", "prop2":"e", "prop3":"f"},
                      {"prop1":"g", "prop2":"h", "prop3":"i"}
                     ]
            }'''
        def model = controller.demo()
        def command = model.command

        then:
        command instanceof MyCommand
        command.data instanceof List
        command.data.size() == 3

        command.data[0] instanceof Map
        command.data[0].prop1 == 'a'
        command.data[0].prop2 == 'b'
        command.data[0].prop3 == 'c'

        command.data[1] instanceof Map
        command.data[1].prop1 == 'd'
        command.data[1].prop2 == 'e'
        command.data[1].prop3 == 'f'

        command.data[2] instanceof Map
        command.data[2].prop1 == 'g'
        command.data[2].prop2 == 'h'
        command.data[2].prop3 == 'i'
    }
}
