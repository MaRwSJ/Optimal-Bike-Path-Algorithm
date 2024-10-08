public class DLStack<T> implements DLStackADT<T> {

    // Instance variables
    private DoubleLinkedNode<T> top;
    private int numItems;

    /**
     * Constructor for DLStack
     * Initializes the top to null and numItems to 0.
     * @author Marwa Jamali
     */
    public DLStack() {
        top = null;
        numItems = 0;
    }
    

    /**
     * Adds item to top of stack
     * Creates a new node by passing the dataItem to the constructor
     * @param 
     */
    @Override
    public void push(T dataItem) {
        if (top == null) {
            top = new DoubleLinkedNode<T>(dataItem);
        } else {
            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(dataItem);
            // Setting the pointer of new node to the top of stack
            newNode.setPrevious(top); 
            top.setNext(newNode);
            // Setting top of the stack to the new node
            top = newNode; 
            // Setting to null
            newNode.setNext(null); 
        }
        numItems++;
    }
        
        /**
         * Removes and returns the data item at the top of the stack. 
         * An EmptyStackException is thrown if the stack is empty.
         * @return the element removed from the stack
         * @throws EmptyStackException if stack has no elements
         */
        public T pop() throws EmptyStackException {
            if (isEmpty()) throw new EmptyStackException("The stack is empty");
            
            T result = top.getElement();
            
            top = top.getPrevious();
            
            numItems--;
            
            return result;
        }
        
        /**
         * Checking the element at the top of the stack without removing it
         * @return the element at the top of the stack
         */
        public T peek() {
            if (isEmpty()) throw new EmptyStackException("The stack is empty");
            return top.getElement();
        }
        /**
         * Removes and returns the k=th data item from the top of the stack.
         * So if k = 1 the method must remove the data item at the top of the stack
         * Returns the element at the specified index and removes it from the stack.
         * @param 
         * @return the element at the specified index
         * @throws InvalidItemException if the index is invalid 
         */
        @Override
        public T pop(int k) throws InvalidItemException {
            T itemPopped;
            if (k > numItems || k <= 0) {
                throw new InvalidItemException("Invalid item");
            } else if (k == 1) {
                itemPopped = pop(); 
            } else {
                DoubleLinkedNode<T> current = top; 
                // Looping through
                for (int i = 1; i < k - 1; i++) { 
                	// Setting the current node to the previous node of the current node
                    current = current.getPrevious(); 
                }
                // Storing element of node before node specified
                itemPopped = current.getPrevious().getElement(); 
                if (current.getPrevious().getPrevious() != null) { 
                    current.getPrevious().getPrevious().setNext(current);
                    current.setPrevious(current.getPrevious().getPrevious()); 
                } else {
                    current.setPrevious(null);
                }
            }
            numItems--;
            return itemPopped;
        }
        /**
         * Checking if stack is empty
         * @return Returns true if the stack is empty and it returns false otherwise.
         */
        public boolean isEmpty() {
            return (top == null);
        }

        /**
         * Checking the size of the stack
         * @return Returns the number of data items in the stack
         */
        public int size() {
            return numItems;
        }

        /**
         * Presenting the stack and all elements contained inside 
         * @return a string representation of the stack in specific form
         */
        public String toString() {
            if (!isEmpty()) {
                DoubleLinkedNode<T> curr = top;
                String result = "[" + curr.getElement();
                curr = curr.getPrevious();

                while (curr != null) {
                    result += " " + curr.getElement();
                    curr = curr.getPrevious();
                }
                result += "]";
                return result;
            } else
                return "";
        }

        /**
         * Returns the top 
         * @return the first element of the stack as pointed by the top pointer
         */
        public DoubleLinkedNode<T> getTop() {
            return top;
        }
    }



        
        

