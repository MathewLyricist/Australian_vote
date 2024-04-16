import service.Action;
import service.InputService;

import java.io.*;

class TestInputParams {
    public static void main(String[] args) throws FileNotFoundException {
        testMainMethodWhenInputParamsFromFileResultOK();
    }

    static void testMainMethodWhenInputParamsFromFileResultOK() throws FileNotFoundException {

        Action action = new Action();
        String pathFile=Action.fileSelection();
        InputService inputService = new InputService(false, new File(pathFile));

        inputService.inputCandidateData();
        action.calculateWinner(inputService.getCandidateList(), inputService.getPriorities(), inputService.getCountOfPeople());
    }
}