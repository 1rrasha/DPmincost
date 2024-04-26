package application;

// Custom implementation of ArrayList data structure
public class ArrayList<T> {
	private static final int DEFAULT_CAPACITY = 10; // Default initial capacity for the array
	private Object[] elements; // Array to store elements
	private int size; // Current number of elements in the list

	// Default constructor
	public ArrayList() {
		this.elements = new Object[DEFAULT_CAPACITY];
		this.size = 0; // Initially, the list is empty
	}

	// Constructor with initial capacity specified
	public ArrayList(int initialCapacity) {
		if (initialCapacity <= 0) {
			throw new IllegalArgumentException("Initial capacity must be greater than zero");
		}
		this.elements = new Object[initialCapacity];
		this.size = 0; // Initially, the list is empty
	}

	// Returns the number of elements in the list
	public int size() {
		return size;
	}

	// Checks if the list is empty
	public boolean isEmpty() {
		return size == 0;
	}

	// Adds an element to the end of the list
	public void add(T element) {
		ensureCapacity(size + 1); // Ensure capacity for new element
		elements[size++] = element; // Add the element and increment size
	}

	// Retrieves the element at the specified index
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return (T) elements[index]; // Type casting and returning the element
	}

	// Removes the element at the specified index
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		// Shift elements to the left to fill the gap
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[--size] = null; // Dereference the last element and decrement size
	}

	// Ensures that the underlying array has sufficient capacity
	private void ensureCapacity(int minCapacity) {
		if (minCapacity > elements.length) {
			// Calculate new capacity, ensuring it's at least double the current capacity
			int newCapacity = Math.max(elements.length * 2, minCapacity);
			// Create a new array with the new capacity
			Object[] newElements = new Object[newCapacity];
			// Copy existing elements to the new array
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements; // Replace the old array with the new one
		}
	}

	// Returns a string representation of the list
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(elements[i]);
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// Static method to join elements of the list with a delimiter
	public static <T> String join(ArrayList<T> list, CharSequence delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}
}
