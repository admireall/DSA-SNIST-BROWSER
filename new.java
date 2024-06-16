import java.util.*;

class node {
    int id; // website id
    String name; // website name
    node next;

    node(String name, int id) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}

class browser {
    static HashMap<Integer, List<String>> map = new HashMap<>(); // for tabs
    static HashMap<String, Integer> cmap = new HashMap<>(); // for website count
    static ArrayList<String> his = new ArrayList<>(); // for history
    static ArrayList<String> lclose = new ArrayList<>(); // for last closed tabs

    static int index = 0;

    public static void add(String str, node root, int id1) {
        node temp = new node(str, id1); // creation of node
        root.next = temp; // addition of node to linkedlist
        his.add(str); // addition of website to history list
        List<String> list = new ArrayList<>();
        list.add(str);
        map.put(id1, list);
        cmap.put(str, cmap.getOrDefault(str, 0) + 1); // increment website count
        index = id1; // updating current tab index
        System.out.println("<< " + temp.name + " >>");
    }

    public static void addweb(int id1, String str) {
        List<String> list = map.get(id1);
        if (list == null) {
            list = new ArrayList<>();
            map.put(id1, list);
        }
        list.add(str);
        his.add(str);
        cmap.put(str, cmap.getOrDefault(str, 0) + 1); // increment website count
        index = id1; // updating current tab index
        System.out.println("Opened website " + str + " in tab " + id1);
    }

    public static void forward(int tabn) {
        List<String> temp = map.get(tabn);
        if (temp == null || temp.size() < 2) {
            System.out.println("No previously opened website in the current tab");
        } else {
            System.out.println("Previously opened website in tab " + tabn + " is " + temp.get(temp.size() - 1));
        }
    }

    public static void backward(int tabn) {
        List<String> temp = map.get(tabn);
        if (temp == null || temp.size() < 2) {
            System.out.println("No previous website in the current tab");
        } else {
            System.out.println("Previous website in tab " + tabn + " is " + temp.get(temp.size() - 2));
        }
    }

    public static void display(node root) {
        node temp = root.next;
        if (temp == null) {
            System.out.println("Empty");
        } else {
            while (temp != null) {
                System.out.print(temp.name + " ");
                temp = temp.next;
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        node dummy = new node("", 0); // creating a linked list to order the tabs
        node head = dummy;
        int p = 1;

        while (true) {
            System.out.println("Menu");
            System.out.println("1: Addition of tabs");
            System.out.println("2: Opening of website");
            System.out.println("3: Forward button");
            System.out.println("4: Backward button");
            System.out.println("5: Display tabs");
            System.out.println("6: Close browser");
            int n = s.nextInt();
            switch (n) {
                case 1:
                    System.out.println("Enter the website name in a new tab");
                    String str1 = s.next();
                    add(str1, dummy, p++);
                    dummy = dummy.next;
                    break;
                case 2:
                    System.out.println("Enter the website name");
                    String str2 = s.next();
                    System.out.println("Enter the tab number");
                    int n1 = s.nextInt();
                    addweb(n1, str2);
                    break;
                case 3:
                    System.out.println("Enter the tab number");
                    int forwardTab = s.nextInt();
                    forward(forwardTab);
                    break;
                case 4:
                    System.out.println("Enter the tab number");
                    int backwardTab = s.nextInt();
                    backward(backwardTab);
                    break;
                case 5:
                    System.out.println("Tabs:");
                    display(head);
                    break;
                case 6:
                    System.out.println("Closing browser...");
                    s.close();
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
