package com.company;


public class Main {

    public static void main(String[] args) {
        int []array={10,2,3,4,6,7,8,1,7,3,45,234,41,42};
        Tree tree = new Tree();
        tree.insert(0,array[0]);
        for(int i=1; i<array.length;i++) {
            tree.insert(array[i-1],array[i]);
        }
        Node toPrint = tree.root;
        tree.printTree(toPrint);
    }

    public static class Node {

        public int key; //ключ узла
        public int data; //некоторые данные в узле
        public Node leftChild; //левый потомок
        public Node rightChild; //правый потомок

        /**
         * Метод который выводит на экран содержимое узла
         */
        public void printNode(){
            System.out.println("K("+key+")=" +data);
            System.out.println();
        }
    }
    public static class Tree {

        Node root;

        /**
         * Поиск элемента в дереве по ключу, где key-ключ , пока текущий ключ != искомому идет поиск по ветвям
         * деревьев, в зависимости от того больше или меньше искомый ключ, чем данный, поиск уходит влево
         * если искомый ключ больше) или влево(если искомый ключ меньше)
         * @param key
         */
        public Node find(int key){
            Node current = root;
            while(current.key!=key){
                if(current.key<key){
                    current = current.leftChild;
                }else{
                    current = current.rightChild;
                }
                if(current==null){
                    return null;
                }
            }
            return current;
        }

        /**
         * Вставка в дерево. Суть таже что и поиск
         * Только вместо вывода элемента к нему левым или правым потомком
         * добавляем новый элемент
         * @param key
         * @param data
         */
        public void insert(int key, int data){
            Node node = new Node();
            node.key = key;
            node.data = data;
            if(root==null){
                root = node;
            }else{
                Node current = root;
                Node prev = null;
                while (true){
                    prev = current;
                    if(key<prev.key){
                        current = current.leftChild;
                        if(current==null){
                            prev.leftChild = node;
                            return;
                        }
                    }else{
                        current = current.rightChild;
                        if(current==null){
                            prev.rightChild = node;
                            return;
                        }
                    }
                }
            }
        }

        /**
         * Вывод всех элементов дерева методом асиметричного обхода
         * @param startNode
         */
        public void printTree(Node startNode){
            if(startNode != null){//условие сработает, когда мы достигним конца дерева и потомков не останется
                printTree(startNode.leftChild);                         //рекурсивно вызываем левых потомков
                startNode.printNode();                                  //вызов метода принт
                printTree(startNode.rightChild);                        //вызов правых
            }
        }
        ublic void byLevel(Node root){
            Queue<Node> level  = new LinkedList<>();
            level.add(root);
            while(!level.isEmpty()){
                Node node = level.poll();
                System.out.print(node.item + " ");
                if(node.leftChild!= null)
                    level.add(node.leftChild);
                if(node.rightChild!= null)
                    level.add(node.rightChild);
            }
        }

    }
}
