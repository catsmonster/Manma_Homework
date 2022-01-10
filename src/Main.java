import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

enum terminationMSG {FILE_DOESNT_EXIST, SUCCESS, LIST_TYPE_NOT_SUPPORTED, ERROR_PROGRAM_TERMINATED}
enum inputMethod {CONSOLE, FILE}
enum listType {SORTED, UNSORTED, FOREIGN}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        terminationMSG msg = (args.length == 0) ? getInputFromUser() : getInputFromUser(args[0]);

        System.out.println("Program ended with message: " + msg);
    }

    private static terminationMSG getInputFromUser() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose your preferred list type:\n- Unsorted\n- Sorted\n- Unsorted Foreign");
        String chosenList = scan.nextLine().toLowerCase();
        return initializeSelectedList(chosenList, inputMethod.CONSOLE, scan);
    }

    private static terminationMSG getInputFromUser(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (file.exists()) {
            Scanner scan = new Scanner(file);
            String chosenList = scan.nextLine().toLowerCase();
            return initializeSelectedList(chosenList, inputMethod.FILE, scan);
        }
        else
            return terminationMSG.FILE_DOESNT_EXIST;
    }

    private static terminationMSG initializeSelectedList(String chosenList, inputMethod method, Scanner scan) {
        return switch (chosenList) {
            case "unsorted" -> mainInteractionLoop(method, scan, listType.UNSORTED);
            case "sorted" -> mainInteractionLoop(method, scan, listType.SORTED);
            case "unsorted foreign" -> mainInteractionLoop(method, scan, listType.FOREIGN);
            default -> terminationMSG.LIST_TYPE_NOT_SUPPORTED;
        };
    }


    private static terminationMSG mainInteractionLoop(inputMethod method, Scanner scan, listType type) {
        LinkedList head = null;
        LinkedList prevList = null;
        terminationMSG terminationCause = terminationMSG.SUCCESS;
        String command;
        do {
            command = getCommandInput(method, scan);
            if (command != null) {
                if (command.equals("makeheap")) {
                    if (head == null) {
                        head = initializeLinkedList(type);
                    } else {
                        prevList = head;
                        head = initializeLinkedList(type);
                    }
                } else if (command.contains("insert") && head != null) {
                    String[] str = command.split(" ");
                    head.insertToStart(new Node(Integer.parseInt(str[1])));
                } else if (command.equals("minimum") && head != null) {
                    System.out.println(head.getMin());
                } else if (command.equals("extractmin") && head != null) {
                    head.removeMin();
                } else if (command.equals("union") && head != null) {
                    head.uniteList(prevList);
                }
                else
                    terminationCause = terminationMSG.ERROR_PROGRAM_TERMINATED;
            }
        } while (command != null && !command.equals("quit"));
        return terminationCause;
    }

    private static LinkedList initializeLinkedList(listType type) {
        return switch (type) {
            case UNSORTED -> new LinkedList();
            case SORTED -> new SortedLinkedList();
            case FOREIGN -> new ForeignLinkedList();
        };
    }


    private static String getCommandInput(inputMethod method, Scanner scan) {
        if (method == inputMethod.CONSOLE) {
            showOptions();
        }
        if (method == inputMethod.FILE && !scan.hasNextLine())
            return null;
        return scan.nextLine().toLowerCase();
    }

    private static void showOptions() {
        System.out.println("""
                Please enter a command from the list:
                - MakeHeap
                - Insert #(integer instead of #)
                - Union
                - Minimum
                - ExtractMin
                - Quit
                \s""");
    }


}
