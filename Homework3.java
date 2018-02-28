// package homework3;

/**
 *
 * @author vitvalecka
 */

// public class Homework3 {}

/*
    interface CustomIterator {
    boolean hasNext();
    int next();
}
*/
 
class Node {
    final int contents;
    final Node left, right;
    Node parent;
 
    Node(int contents, Node left, Node right) {
        this.contents = contents;
        this.left = left;
        if (left != null) left.parent = this;
        this.right = right;
        if (right != null) right.parent = this;
    }
 
    CustomIterator preorderIterator() {
        return new PreorderIterator(this); 
    }

    CustomIterator inorderIterator() {
        return new InorderIterator(this);
    }

    CustomIterator postorderIterator() {
        return new PostorderIterator(this);
    }
}

class PreorderIterator implements CustomIterator {
    private Node currentNode;
    private Node parentNode;
    private Node previousNode;
    private boolean hasNextNode = false;
    private int nextNumber;

    PreorderIterator(Node current) {
        this.currentNode = current;
        this.parentNode = current.parent;
        this.previousNode = parentNode;
    }

    private void seekNext() {
        int foo = 0;
        boolean output = false;
        while (!output && (currentNode != parentNode)) {
            if (previousNode == currentNode.parent) {
                previousNode = currentNode;
                output = true;
                foo = currentNode.contents;
                if (currentNode.left != null) {
                    currentNode = currentNode.left;
                }
                else {
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    }
                    else {
                        currentNode = currentNode.parent;
                    }
                }
            }
            else if (previousNode == currentNode.left) {
                previousNode = currentNode;
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                }
                else {
                    currentNode = currentNode.parent;
                }
            }
            else if (previousNode == currentNode.right) {
                previousNode = currentNode;
                currentNode = currentNode.parent;
            }
        }
        hasNextNode = output;
        nextNumber = foo;
    }

    @Override
    public boolean hasNext() {
        if (!hasNextNode) {
            seekNext();
        }
        return hasNextNode;
    }

    @Override
    public int next() {
        hasNextNode = false;
        return nextNumber;
    }
}

class PostorderIterator implements CustomIterator {
    private Node currentNode;
    private Node parentNode;
    private Node previousNode = null;
    private boolean hasNext = false;
    private int nextNumber;

    PostorderIterator(Node current) {
        this.currentNode = current;
        this.parentNode = current.parent;
        this.previousNode = parentNode;
    }

    private void seekNext() {
        int foo = 0;
        boolean output = false;
        while (!output && currentNode != parentNode) {
            if (previousNode == currentNode.parent) {
                previousNode = currentNode;
                if (currentNode.left != null) {
                    currentNode = currentNode.left;
                }
                else {
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    }
                    else {
                        output = true;
                        foo = currentNode.contents;
                        currentNode = currentNode.parent;
                    }
                }
            }
            else if (previousNode == currentNode.left) {
                previousNode = currentNode;
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                }
                else {
                    output = true;
                    foo = currentNode.contents;
                    currentNode = currentNode.parent;
                }
            }
            else if (previousNode == currentNode.right) {
                previousNode = currentNode;
                output = true;
                foo = currentNode.contents;
                currentNode = currentNode.parent;
            }
        }
        hasNext = output;
        nextNumber = foo;
    }

    @Override
    public boolean hasNext() {
        if (!hasNext) {
            seekNext();
        }
        return hasNext;
    }

    @Override
    public int next() {
        hasNext = false;
        return nextNumber;
    }
}

class InorderIterator implements CustomIterator {
    private Node currentNode;    
    private Node parentNode;
    private Node previousNode = null;
    private boolean hasNext = false;
    private int nextNumber;

    InorderIterator(Node current) {
        this.currentNode = current;
        this.parentNode = current.parent;
        this.previousNode = parentNode;
    }

    private void seekNext() {
        int foo = 0;
        boolean output = false;
        while (!output && currentNode != parentNode) {
            if (previousNode == currentNode.parent) {
                previousNode = currentNode;
                if (currentNode.left != null) {
                    currentNode = currentNode.left;
                }
                else {
                    output = true;
                    foo = currentNode.contents;
                    if (currentNode.right != null) {
                        currentNode = currentNode.right;
                    }
                    else {
                        currentNode = currentNode.parent;
                    }
                }
            }
            else if (previousNode == currentNode.left) {
                previousNode = currentNode;
                output = true;
                foo = currentNode.contents;
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                }
                else {
                    currentNode = currentNode.parent;
                }
            }
            else if (previousNode == currentNode.right) {
                previousNode = currentNode;
                currentNode = currentNode.parent;
            }
        }
        hasNext = output;
        nextNumber = foo;
    }

    @Override
    public boolean hasNext() {
        if (!hasNext) {
            seekNext();
        }
        return hasNext;
    }

    @Override
    public int next() {
        hasNext = false;
        return nextNumber;
    }
}