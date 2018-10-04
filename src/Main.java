import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean s = false;
        int x;
        String name1, name2, smt;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя первого игрока: ");
        name1 = in.next();
        System.out.println("Введите имя второго игрока: ");
        name2 = in.next();
        System.out.println("Введите размер поля: ");
        do {
            x = in.nextInt();
        } while (!checkIn(x));

        do {
            new TicTacToe(x, name1, name2);
            System.out.println("Чтобы сыграть еще раз введите хоть-что.\nЧтобы закончить введите 0: ");
            smt = in.next();
            if (smt.equals("0")) {
                s = true;
            }
        } while (!s);
    }

    public static boolean checkIn(int x) {
        if (x < 3) {
            System.out.println("Слишком маленькое поле. Введите размер снова: ");
            return false;
        }
        return true;
    }
}
