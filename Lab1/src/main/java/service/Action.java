package service;

import java.util.List;
import java.util.Scanner;

public class Action {

    public void calculateWinner(List<Candidate> candidates, int[][] priority, int countOfPeople){
        String winner = null;
        boolean haveWinner = false;
        for(int i=0;i<countOfPeople;i++){
            System.out.print("\nИзбиратель "+(i+1)+" распределил голоса: ");
            for(int j=0;j<candidates.size();j++){
                System.out.print(priority[i][j]+" ");
            }
        }
        System.out.println("\n");
        for(int i=0;i<candidates.size();i++){
            int value = 0;
            for(int j=0;j<countOfPeople;j++) {
                if (priority[j][i] == 1) {
                    value += 1;
                }
            }
            candidates.get(i).setVotes(value);
        }
        for (Candidate item : candidates) {
            System.out.println("Количество проголосовавших = " + item.getVotes());
            item.setPercentVotes(((double) item.getVotes() / countOfPeople) * 100);
            System.out.println("Стартовая избирательная кампания представителя " + item.getName() + " составляет " + item.getPercentVotes() + "%.");
            if (item.getPercentVotes() > 50) {
                winner = item.getName();
                printWinnerName(winner);
            }
        }
        do{
            double minPercent=candidates.getFirst().getPercentVotes();
            int minId=0;
            int numberOfVotePercentageMatches=0;
            double value;
            for (Candidate item : candidates) {
                if (item.getPercentVotes() > 50) {
                    winner = item.getName();
                    printWinnerName(winner);
                }
            }
            for (Candidate candidate : candidates) {
                System.out.println("\nИзбирательная кампания представителя " + candidate.getName() + " составляет " + candidate.getPercentVotes() + "%.");
            }
            for(Candidate candidate: candidates){
                if(candidate.getPercentVotes()==50){
                    numberOfVotePercentageMatches++;
                }
            }
            if (numberOfVotePercentageMatches == 2) {
                winner = "Ничья";
                haveWinner=true;
                continue;
            }
            for(int i=0;i<candidates.size();i++){ //выявление минимального процента голосов
                if(candidates.get(i).getPercentVotes()<minPercent){
                    minPercent=candidates.get(i).getPercentVotes();
                    minId=i;
                }
            }
            System.out.println("Минимальный процент прогосовавших у "+candidates.get(minId).getName()+", процент прогосовавших = "+minPercent+"%");
            if(minId==candidates.size()-1){//прибавление процента выбывшего кандидата следующему по очереди кандидату
                value=minPercent+candidates.getFirst().getPercentVotes();
                candidates.getFirst().setPercentVotes(value);
            }else{
                value=minPercent+candidates.get(minId+1).getPercentVotes();
                candidates.get(minId+1).setPercentVotes(value);
            }
            candidates.remove(minId);
            System.out.println("Данный кандидат выбывает.\n");

        }while(!haveWinner);
        printWinnerName(winner);
    }

    public static void printWinnerName(String winner) {
        if(winner.equals("Ничья")){
            System.out.println("Победителя нет. Ничья!");
        }else{
            System.out.println("Победитель найден! Это "+ winner);
        }
        System.exit(0);
    }


    public static String fileSelection(){
        String pathFile1 = "/home/mathew/IdeaProjects/1.1/Lab1/src/main/java/input_params_1.txt";
        String pathFile2 = "/home/mathew/IdeaProjects/1.1/Lab1/src/main/java/input_params_2.txt";
        String pathFile3 = "/home/mathew/IdeaProjects/1.1/Lab1/src/main/java/input_params_4.txt";
        String pathFile;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер файла, с которого будем считывать данные: 1, 2, 3 ");
        int fileNumber=scanner.nextInt();
        pathFile = switch (fileNumber) {
            case 1 -> pathFile1;
            case 2 -> pathFile2;
            case 3 -> pathFile3;
            default -> throw new IllegalStateException("Unexpected value: " + fileNumber);
        };
        return pathFile;
    }
}