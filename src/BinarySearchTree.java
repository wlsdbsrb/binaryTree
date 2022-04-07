import java.util.Scanner;

public class BinarySearchTree {
    public Node root;
    public Node insert(Node node, int num){
        if(node==null){
            return new Node(num);
        }
        if(num<node.data){
            node.left=insert(node.left , num);
        }
        else if(num>node.data){
            node.right=insert(node.right, num);
        }

        return node;
    }
    public Node delete(Node root,int num){
        if(root == null) {
            return root;
        }

        if(num < root.data) { // 키가 루트보다 작다면, 왼쪽 서브 트리에 있는 것
            root.left = delete(root.left, num);
        }
        else if(num > root.data) { // 키가 루트보다 크다면, 오른쪽 서브 트리에 있는 것
            root.right = delete(root.right, num);
        }
        else { // 키가 루트와 같다면 이 노드가 바로 삭제할 노드

            // 1번, 2번의 경우

            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }

            // 3번의 경우
            Node temp = minValueNode(root.right); // 후계 노드 찾기
            root.data = temp.data; // 후계 노드 값 복사
            root.right = delete(root.right, temp.data); // 후계 노드 삭제
        }

        return root;
    }

    public Node minValueNode(Node node) {
        Node currentNode = node;

        while(currentNode.left != null) {
            currentNode = currentNode.left; // 맨 왼쪽 단말 노드를 찾으러 내려감
        }
        return currentNode;
    }

    public static Node CSearch(Node node, int num) {
        if(node == null) {
            return null;
        }

        if(num == node.data) {
            return node;
        } else if(num < node.data) {
            return CSearch(node.left, num);
        } else {
            return CSearch(node.right, num);
        }
    }
    public static Node RSearch(Node node, int key) {
        while(node != null) {
            if(key == node.data) {
                return node;
            } else if(key < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null; // 탐색 실패했을 경우
    }
    public void Order(Node node) {
        if(node != null) {
            System.out.print(node.data + " ");
            if(node.left != null) Order(node.left);
            if(node.right != null)Order(node.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        while(true){
            System.out.println("0 : 종료");
            System.out.println("1 : 검색");
            System.out.println("2 : 삽입");
            System.out.println("3 : 삭제");
            System.out.println("4 : 출력");
            System.out.print("메뉴를 선택하세요 : ");
            Scanner scan = new Scanner(System.in);
            int x= scan.nextInt();

            if(x==0){
                break;
            }
            else if(x==1){
                System.out.print("검색할 값을 입력하세요: ");
                Scanner sc =new Scanner(System.in);
                int num = sc.nextInt();
                if(RSearch(tree.root,num) != null) {
                    System.out.println("찾는 값 [" +num+ "]는 트리에 있습니다");
                } else {
                    System.out.println("찾는 값 [" +num+ "]는 트리에 없습니다");
                }
            }
            else if(x==2){
                System.out.print("삽입할 값을 입력하세요: ");
                Scanner sc =new Scanner(System.in);
                int num = sc.nextInt();
                if(CSearch(tree.root, num) == null) {
                    tree.root = tree.insert(tree.root, num);
                }
            }

            else if(x==3){
                System.out.print("삭제할 값을 입력하세요: ");
                Scanner sc =new Scanner(System.in);
                int num = sc.nextInt();
                if(RSearch(tree.root,num) == null){
                    System.out.println("삭제할 값 [" +num+ "]는 트리에 없습니다");
                }
                tree.root = tree.delete(tree.root,num);
            }

            else {
                System.out.print("출력결과>>>");
                tree.Order(tree.root);
            }
        }
    }
}

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
    }
}