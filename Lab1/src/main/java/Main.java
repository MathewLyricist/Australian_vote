import service.InputService;
import service.Action;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        InputService inputService = new InputService(true, null);
        inputService.inputCandidateData();
        Action action = new Action();
        action.calculateWinner(inputService.getCandidateList(), inputService.getPriorities(), inputService.getCountOfPeople());
    }
}
