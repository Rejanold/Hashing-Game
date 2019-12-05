public class TNode<E> {
        private E element;        // Value for this node
        private TNode<E> parent;     // reference to parent

        /** Constructor
         * @param item the element to be stored in Node
         * @param parent the next Node that this is pointing to
         */
        public TNode(E item, TNode<E> parent)
        {
            element = item;
            this.parent = parent;
        }
        /**
         *@return the Node that is next to this
         */
        public TNode<E> getParent() {
            return parent;
        }


        /**
         * returns the element in the Node
         *@return element in the Node
         */
        public E getElement() {
            return element;
        }





}
