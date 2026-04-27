package project20280.tree;

import project20280.interfaces.Position;
import project20280.interfaces.BinaryTree;
import project20280.interfaces.Tree;

import java.util.ArrayList;


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
        // TODO
        if (root != null) {
            throw new IllegalStateException("tree is not empty");
        }

        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        // TODO
        if (root == null) {
            addRoot(e);
        } else {
            addRecursive(root, e);
        }
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // TODO
        int cmp = ((Comparable<? super E>) e).compareTo(p.getElement());

        if (cmp < 0) {
            if (p.getLeft() == null) {
                Node<E> child = createNode(e, p, null, null);
                p.setLeft(child);
                size++;
                return child;
            }
            return addRecursive(p.getLeft(), e);
        } else {
            if (p.getRight() == null) {
                Node<E> child = createNode(e, p, null, null);
                p.setRight(child);
                size++;
                return child;
            }
            return addRecursive(p.getRight(), e);
        }
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
        // TODO
        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("left child already exists");
        }

        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
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
        // TODO
        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException("right child already exists");
        }

        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
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
        // TODO
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
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
        Node<E> node = validate(p);

        if (node.getLeft() != null || node.getRight() != null) {
            throw new IllegalArgumentException("Position must be a leaf");
        }

        if (!t1.isEmpty()) {
            node.setLeft(t1.root);
            t1.root.setParent(node);
            size += t1.size;
            t1.root = null;
            t1.size = 0;
        }

        if (!t2.isEmpty()) {
            node.setRight(t2.root);
            t2.root.setParent(node);
            size += t2.size;
            t2.root = null;
            t2.size = 0;
        }
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
        // TODO
        Node<E> node = validate(p);

        if (node.getLeft() != null && node.getRight() != null) {
            throw new IllegalArgumentException("cannot remove node with two children");
        }

        Node<E> child;

        if (node.getLeft() != null) {
            child = node.getLeft();
        } else {
            child = node.getRight();
        }

        if (node == root) {
            root = child;
            if (child != null) {
                child.setParent(null);
            }
        } else {
            Node<E> parent = node.getParent();

            if (parent.getLeft() == node) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }

            if (child != null) {
                child.setParent(parent);
            }
        }

        size--;
        E removed = node.getElement();
        node.setParent(node);   // mark as defunct
        return removed;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        // TODO
        size = 0;
        root = createLevelOrderHelper(l, root, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        // TODO
        if (i >= l.size() || l.get(i) == null) return null;

        Node<E> node = createNode(l.get(i), p, null, null);

        Node<E> leftChild = createLevelOrderHelper(l, node, 2 * i + 1);
        node.setLeft(leftChild);

        Node<E> rightChild = createLevelOrderHelper(l, node, 2 * i + 2);
        node.setRight(rightChild);

        size++;
        return node;
    }

    public void createLevelOrder(E[] arr) {
        size = 0;
        root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        // TODO
        if (i >= arr.length || arr[i] == null) return null;

        Node<E> node = createNode(arr[i], p, null, null);

        Node<E> leftChild = createLevelOrderHelper(arr, node, 2 * i + 1);
        node.setLeft(leftChild);

        Node<E> rightChild = createLevelOrderHelper(arr, node, 2 * i + 2);
        node.setRight(rightChild);

        size++;
        return node;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }


    // lab 2 question 3

    public void construct(E[] inorder, E[] preorder) {
        this.root = constructHelper(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
        this.size = inorder.length;
    }

    // Recursive helper to build the nodes
    private Node<E> constructHelper(E[] inorder, int inStart, int inEnd, E[] preorder, int preStart, int preEnd) {
        if (inStart > inEnd || preStart > preEnd) return null;

        // the first element in preorder is always the root
        E rootValue = preorder[preStart];
        Node<E> node = new Node<>(rootValue, null, null, null);

        // find the index of this root in the inorder array
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i].equals(rootValue)) {
                rootIndex = i;
                break;
            }
        }

        // calculate the size of the left subtree
        int leftSize = rootIndex - inStart;

        // recursively build the left and right subtrees
        node.setLeft(constructHelper(inorder, inStart, rootIndex - 1, preorder, preStart + 1, preStart + leftSize));
        node.setRight(constructHelper(inorder, rootIndex + 1, inEnd, preorder, preStart + leftSize + 1, preEnd));

        // set parent links
        if (node.getLeft() != null) node.getLeft().setParent(node);
        if (node.getRight() != null) node.getRight().setParent(node);

        return node;
    }

//lab 2 question 4

    public java.util.List<java.util.List<E>> rootToLeafPaths() {
        java.util.List<java.util.List<E>> allPaths = new ArrayList<>();
        if (!isEmpty()) {
            rootToLeafPathsHelper(root(), new ArrayList<>(), allPaths);
        }
        return allPaths;
    }

    private void rootToLeafPathsHelper(Position<E> p, java.util.List<E> path, java.util.List<java.util.List<E>> allPaths) {
        if (p == null) return;

        // add this node to the current path
        path.add(p.getElement());

        if (isExternal(p)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            if (left(p) != null) rootToLeafPathsHelper(left(p), path, allPaths);
            if (right(p) != null) rootToLeafPathsHelper(right(p), path, allPaths);
        }

        //before going back to the parent, remove the last node we added
        path.remove(path.size() - 1);
    }


    // diameter
    public int diameter() {
        int[] best = new int[]{0};
        diameterHeight(root, best);
        return best[0];
    }

    private int diameterHeight(Node<E> node, int[] best) {
        if (node == null) return 0;

        int leftH = diameterHeight(node.getLeft(), best);
        int rightH = diameterHeight(node.getRight(), best);

        int throughHere = leftH + rightH + 1;   // nodes on path through this node
        if (throughHere > best[0]) best[0] = throughHere;

        return Math.max(leftH, rightH) + 1;
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


    public static void main(String[] args) {


        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();

        String[] arr = {
                "A", "B", "C", "D", "E", null, "F",
                null, null, "G", "H", null, null, null, null
        };

        bt.createLevelOrder(arr);

        System.out.println(bt.toBinaryTreeString());

        System.out.print("Leaf nodes: ");
        bt.printLeavesLeftToRight();
    }



    // prints all leaf nodes from left to right
    public void printLeavesLeftToRight() {
        printLeaves(root());
        System.out.println();
    }
    // helper
    private void printLeaves(Position<E> p) {

        if (p == null)
            return;

        // check if leaf node
        if (left(p) == null && right(p) == null) {
            System.out.print(p.getElement() + " ");
            return;
        }

        // visit left subtree
        printLeaves(left(p));

        // visit right subtree
        printLeaves(right(p));
    }
}




