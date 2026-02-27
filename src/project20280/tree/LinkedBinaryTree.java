package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Math.max;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    private int diameter = 0;

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
//        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
//        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
//        bt.createLevelOrder(arr);
//        System.out.println(bt.toBinaryTreeString());

        // Q1(h) Wk4
//        Integer [] arr2 = new Integer [] {1,
//                2,3,
//                4,5,6,7,
//                8,9,10,11,12, 13, 14, 15,
//                16,17 ,18,19,20,21,22,23 ,24,25,26,27,28,29,30,31,
//                null ,null ,null ,35};
//        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
//        bt2.createLevelOrder(arr2);
//        System.out.println(bt2.toBinaryTreeString());
//        System.out.println(bt2.height());

        // Q1(i) Wk4
//        Integer[] arr3 = new Integer [] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
//                23, 24, 25, 26, 27, 28, 29, 30, 31, null, null, null, 35};
//        LinkedBinaryTree <Integer> bt3 = new LinkedBinaryTree <>();
//        bt3.createLevelOrder(arr3);
//        System.out.println(bt3.toBinaryTreeString());
//        System.out.println("Diameter: " + bt3.getDiameter());

        // Q2 Wk5
        LinkedBinaryTree <String > bt4 = new LinkedBinaryTree <>();
        String [] arr4 = { "A", "B", "C", "D", "E", null , "F", null , null , "G", "H", null , null , null , null};
        bt4.createLevelOrder(arr4);
        System.out.println(bt4.toBinaryTreeString());

        // Q3 Wk5
//        Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
//        Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16, 17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
//        LinkedBinaryTree <Integer > bt5 = new LinkedBinaryTree <>();
//        bt5.construct(inorder, preorder);
//        System.out.println(bt5.toBinaryTreeString());

        // Q6 Wk5
//        getAverageTreeHeight(50, 5000, 50);

        // Wk6 Q9
        bt4.preorderLeafs();
    }

    public static void getAverageTreeHeight(int start, int end, int step){
        int heightSum;
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        for(int i=start; i<=end; i+=step){
            heightSum = 0;
            for(int j=0; j<100; ++j) {
                bt = makeRandom(i);
                heightSum += bt.getHeight(bt.root);

            }
            System.out.println(heightSum/100.0);
        }
    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(size != 0){
            throw new IllegalStateException();
        }
        root = new Node<E>(e, null, null, null);
        ++size;
        return root();
    }

    public void insert(E e) {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        if(validate((Node<E>) p).getLeft() != null){
            throw new IllegalArgumentException();
        }
        ((Node<E>) p).setLeft(new Node<E>(e, (Node<E>) p, null, null));
        ++size;
        return ((Node<E>) p).getLeft();
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        if(validate((Node<E>) p).getRight() != null){
            throw new IllegalArgumentException();
        }
        ((Node<E>) p).setRight(new Node<E>(e, (Node<E>) p, null, null));
        ++size;
        return ((Node<E>) p).getRight();
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        E replaced = p.getElement();
        validate(p).setElement(e);
        return replaced;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        validate(p);
        --size;
        Node<E> child;
        if(((Node<E>)p).getLeft() != null){
            if(((Node<E>)p).getRight() != null){
                ++size;
                throw new IllegalArgumentException();
            }
            if(p == root){
                root = ((Node<E>) p).getLeft();
                root.setParent(null);
                return p.getElement();
            }
            child = ((Node<E>) p).getLeft();
        }
        else{
            if(p == root){
                root = ((Node<E>) p).getRight();
                root.setParent(null);
                return p.getElement();
            }
            child = ((Node<E>) p).getRight();
        }
        Node<E> parent = ((Node<E>) p).getParent();
        if(parent.getLeft() == (Node<E>) p){
            parent.setLeft(child);
        }
        else{
            parent.setRight(child);
        }
        if(child != null) {
            child.setParent(parent);
        }
        return p.getElement();
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        if(l.isEmpty()){
            return;
        }
        root = new Node<E>(l.getFirst(), null, null, null);
        ArrayList<Node<E>> prev = new ArrayList<Node<E>>();
        ArrayList<Node<E>> next = new ArrayList<Node<E>>();
        prev.add(root);
        int i = 1;
        E e1, e2;
        while(i < l.size()) {
            for (Node<E> eNode : prev) {
                if (i >= l.size()) {
                    break;
                }
                e1 = l.get(i);
                if (i + 1 < l.size()) {
                    e2 = l.get(i + 1);
                } else {
                    e2 = null;
                }
                i += 2;
                addChildren(e1, e2, eNode);
                next.add(eNode.getLeft());
                next.add(eNode.getRight());
            }
            prev = (ArrayList<Node<E>>) next.clone();
            next.clear();
        }
        size = l.size();
    }

    private void addChildren(E e1, E e2, Node<E> p){
        p.setLeft(new Node<E>(e1, p, null, null));
        p.setRight(new Node<E>(e2, p, null, null));
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        return null;
    }

    public void createLevelOrder(E[] arr) {
        ArrayList<E> al = new ArrayList<E>();
        for(E el: arr){
            al.addLast(el);
        }
        createLevelOrder(al);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        return null;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }

    private boolean isLater(E[] inorder, E a, E b){
        for(E e: inorder){
            if(e == a){
                return true;
            }
            if(e == b){
                return false;
            }
        }
        throw new NoSuchElementException();
    }

    private int createInorderPreorderSubtree(E[] inorder, E[] preorder, int cur, Node<E> parent, E grandparent){
        if(cur >= inorder.length){
            return -1;
        }
        int next = cur;
        if(isLater(inorder, preorder[cur], parent.element)){
            parent.setLeft(new Node<E>(preorder[cur], parent, null, null));
            next = createInorderPreorderSubtree(inorder, preorder, cur+1, parent.getLeft(), parent.element);
        }
        if(isLater(inorder, grandparent, preorder[next])){
            return next;
        }
        parent.setRight(new Node<E>(preorder[next], parent, null, null));
        return createInorderPreorderSubtree(inorder, preorder, next+1, parent.getRight(), grandparent);
    }

    public void construct(E[] inorder, E[] preorder){
        root = new Node<E>(preorder[0], null, null, null);
        createInorderPreorderSubtree(inorder, preorder, 1, root, null);
    }

    public ArrayList<ArrayList<E>> nodeToLeafPaths(Node<E> cur){
        ArrayList<ArrayList<E>> result = new ArrayList<ArrayList<E>>();
        if(cur == null){
            return result;
        }
        if(cur.getLeft() == null && cur.getRight() == null){
            ArrayList<E> temp = new ArrayList<>();
            temp.addLast(cur.getElement());
            result.addLast(temp);
            return result;
        }
        ArrayList<ArrayList<E>> left = nodeToLeafPaths(cur.getLeft());
        ArrayList<ArrayList<E>> right = nodeToLeafPaths(cur.getRight());
        for(ArrayList<E> al: left){
            al.addFirst(cur.getElement());
            result.addLast(al);
        }
        for(ArrayList<E> al: right){
            al.addFirst(cur.getElement());
            result.addLast(al);
        }
        return result;
    }

    public ArrayList<ArrayList<E>> rootToLeafPaths(){
        return nodeToLeafPaths(root);
    }

    public int getHeight(Node<E> node){
        if(node == null){
            return 0;
        }

        return max(getHeight(node.getRight()), getHeight(node.getLeft())) + 1;
    }

    private int getDiameterReturnHeight(Node<E> node){
        if(node == null){
            return 0;
        }

        int heightL = getDiameterReturnHeight(node.getLeft());
        int heightR = getDiameterReturnHeight(node.getRight());

        diameter = max(heightL + heightR, diameter);
        return max(heightL, heightR) + 1;
    }

    public int getDiameter(){
        getDiameterReturnHeight(root);
        return diameter;
    }

    private void preorderLeafsSubtree(Position<E> p) {
        if(p == null){
            return;
        }
        Iterable<Position<E>> children = children(p);
        if(!children.iterator().hasNext()){
            System.out.println(p.getElement());
            return;
        }
        for(Position<E> c: children) {
            preorderLeafsSubtree(c);
        }
    }

    public void preorderLeafs() {
        preorderLeafsSubtree(root());
    }
}