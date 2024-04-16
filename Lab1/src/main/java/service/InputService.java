package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputService {

    final static String mes1 = "Введите количество кандидатов (не меньше 2 и не больше 19): ";
    final static String mes2 = "Введите имена кандидатов (не более 80 символов): ";
    final static String mes3 = "Введите описание для каждого кандидата в бюллетени: ";
    final static String mes4 = "Введите количество избирателей (не больше 11): ";
    private int countOfCandidate;
    private int countOfPeople;
    private int[][] priorities = new int[0][0];

    private final Scanner scanner;

    private final List<Candidate> candidateList = new ArrayList<>();

    public InputService(boolean isScanner, File fileName) throws FileNotFoundException {
        this.scanner = isScanner? new Scanner(System.in):new Scanner(new FileReader(fileName));
        System.out.println("Успешность обнаружения и загрузки файла:"+fileName.exists());
    }


    public void inputCandidateData() {
        do {
            System.out.print(mes1);
            countOfCandidate = Integer.parseInt(getNextLine());
            System.out.println();
            System.out.print(mes4);
            countOfPeople = Integer.parseInt(getNextLine());
        }while(isInputParamValid(countOfCandidate, countOfPeople));
        System.out.println(mes2);
        inputNameOfCandidates(countOfCandidate);
        System.out.println(mes3);
        inputDescriptionOfCandidates(countOfCandidate);
        printCandidates();
        inputPriorityOfEveryCandidate();
    }


    public int[][] getPriorities() {
        return priorities;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }


    public int getCountOfPeople() {
        return countOfPeople;
    }


    private synchronized String getNextLine() {
        String name = scanner.nextLine();////Версия для файла
        System.out.println(name);//Версия для файла
        //return scanner.nextLine(); ////Версия без считывания с файла
        return name;///Версия для файла
    }


    private void inputNameOfCandidates(int countOfCandidate){
        byte maxLength = 80;
        String name;
        for(int i = 1; i<countOfCandidate+1;i++) {
            do{
                System.out.println("Кандидат номер " + i + ": ");
                name = getNextLine();
                candidateList.add(new Candidate(name));
            }while(name.length()>maxLength);
        }
    }


    private void inputDescriptionOfCandidates(int countOfCandidate){

        for(int i = 1; i<countOfCandidate+1;i++){
            List<String> bullet = new ArrayList<>();
            System.out.print("Кандидат №"+i+" "+candidateList.get(i-1).getName()+": ");
            for(int j=0;j<1000;j++) {

                String inputStringForDescription=getNextLine();
                if(inputStringForDescription.isEmpty()){
                    break;
                }
                bullet.add(inputStringForDescription);
            }
            candidateList.get(i - 1).setBulleten(bullet);
        }
    }


    private void printCandidates(){
        System.out.println("Список кандидатов с их описанием: \n");
        candidateList.forEach(System.out::println);
    }


    private void inputPriorityOfEveryCandidate(){
        priorities = new int[countOfPeople][countOfCandidate];
        System.out.println("Дайте приоритет каждому кандидату, указав у нужного " + "кандидата число от 1 до "+countOfCandidate+". Пример: 1 5 4 3 2");
        System.out.println("Всего кандидатов: "+countOfCandidate+".");
        givingPriorityToPeopleOverCandidates();
    }


    private void givingPriorityToPeopleOverCandidates(){
        for(int j=0;j<countOfPeople;j++) {
            System.out.println("Выбирает человек под номером №"+(j+1));
            for (int i = 0; i < countOfCandidate; i++) {
                int[] results = new int[countOfCandidate];
                System.out.print("Введите приоритетность для кандидата "+candidateList.get(i).getName() + " под номером бюллетеня " + (i+1) + ": ");
                int value = Integer.parseInt(getNextLine());
                boolean isValidInput = validateInput(value, results, i);
                while (!isValidInput) {
                    System.out.println("Ошибка ввода. Пожалуйста, введите значение от 1 до " + countOfCandidate + " и убедитесь, что значение не повторяется.");
                    System.out.print("Введите приоритетность для кандидата  " + (i+1) + ": ");
                    value = Integer.parseInt(getNextLine());
                    isValidInput = validateInput(value, results, i);
                }
                priorities[j][i] = value;
            }
            demonstrationOfGivenOriority(j);
        }
    }


    private void demonstrationOfGivenOriority(int j){
        System.out.println("Ваши голоса отданы. Ваш выбор по приоритету: ");
        for (int i=0;i<countOfCandidate;i++) {
            System.out.print(priorities[j][i]+" ");
        }
    }


    private boolean validateInput(int value, int[] candidates, int currentIndex) {
        if (value < 1 || value > candidates.length) {
            return false;
        }
        for (int i = 0; i < currentIndex; i++) {
            if (candidates[i] == value) {
                return false;
            }
        }
        return true;
    }


    private static boolean isInputParamValid(int countOfCandidate, int countOfPeople) {
        return ((countOfCandidate <= 2) || (countOfCandidate >= 20)) && ((countOfPeople <= 0) || (countOfPeople >= 11));
    }
}
