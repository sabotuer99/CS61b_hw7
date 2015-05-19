package hw7;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

	private BSTNode root;
	private int size;

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean containsKey(K key) {
		if (get(key) == null)
			return false;
		else
			return true;
	}

	@Override
	public V get(K key) {
		if(root == null){
			return null;
		}
		
		BSTNode node = root.find(key);
		if (node == null)
			return null;
		else
			return node.getValue();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void put(K key, V value) {
		if (root == null) {
			root = new BSTNode(key, value);
			size = 1;
		} else {
			root.insert(key, value);
			size += 1;
		}
	}

	@Override
	public V remove(K key) {
		V value = get(key);
		if (value == null) {
			return null;
		} else {
			root = root.remove(key);
			size -= 1;
			return value;
		}
	}

	@Override
	public V remove(K key, V value) {
		V testValue = get(key);
		if (testValue == null || testValue != value) {
			return null;
		} else {
			root = root.remove(key);
			size -= 1;
			return value;
		}
	}

	@Override
	public Set<K> keySet() {
		if (root == null)
			return null;
		else
			return new HashSet<K>(root.inOrder(new ArrayList<K>()));
	}

	public void printInOrder() {
		ArrayList<K> keys = getKeysInOrder();
		for (int i = 0; i < keys.size(); i += 1) {
			System.out.println(keys.get(i));
		}
	}
	
	public ArrayList<K> getKeysInOrder() {
		if (root == null)
			return null;

		return root.inOrder(new ArrayList<K>());
	}

	@SuppressWarnings("unused")
	private class BSTNode {
		private BSTNode _left;
		private BSTNode _right;
		private K _key;
		private V _val;

		public ArrayList<K> inOrder(ArrayList<K> list) {
			if (_left != null)
				_left.inOrder(list);
			list.add(_key);
			if (_right != null)
				_right.inOrder(list);
			return list;
		}

		/*
		 * This constructor is private to force all BST creation to be done by
		 * the insert method.
		 */
		private BSTNode(K key, V val, BSTNode left, BSTNode right) {
			_key = key;
			_val = val;
			_left = left;
			_right = right;
		}

		/** A leaf node with given key/value */
		public BSTNode(K key, V val) {
			this(key, val, null, null);
		}

		/** Fetch the value of this node. */
		public V getValue() {
			return _val;
		}

		/** Fetch the value of this node. */
		public K getKey() {
			return _key;
		}

		/** Fetch the left (right) child of this. */
		public BSTNode left() {
			return _left;
		}

		public BSTNode right() {
			return _right;
		}

		/**
		 * The highest node in T that contains the label L, or null if there is
		 * none.
		 */
		public BSTNode find(K key) {
			if (key == null)
				return null;

			if (key.equals(_key))
				return this;

			if (key.compareTo(_key) < 0) {
				if (_left != null)
					return _left.find(key);
				else
					return null;
			} else {
				if (_right != null)
					return _right.find(key);
				else
					return null;
			}

		}

		/** True iff label L is in T. */
		public boolean isIn(K key) {
			return find(key) != null;
		}

		/**
		 * Insert the key/value into T, returning the modified tree. The nodes
		 * of the original tree may be modified. If T is a subtree of a larger
		 * BST, T’, then insertion into T will render T’ invalid due to
		 * violation of the binary- search-tree property if L > T’.label() and T
		 * is in T’.left() or L < T’.label() and T is in T’.right().
		 */
		public BSTNode insert(K key, V val) {
			if (key == null)
				throw new InvalidParameterException("Cannot insert null key");

			if (key.equals(_key))
				throw new InvalidParameterException(
						"Cannot insert duplicate key");

			if (key.compareTo(_key) < 0) {
				if (_left == null) {
					_left = new BSTNode(key, val);
				} else {
					_left = _left.insert(key, val);
				}
			} else {
				if (_right == null) {
					_right = new BSTNode(key, val);
				} else {
					_right = _right.insert(key, val);
				}
			}
			return this;
		}

		/**
		 * Delete the instance of label L from T that is closest to to the root
		 * and return the modified tree. The nodes of the original tree may be
		 * modified.
		 */
		public BSTNode remove(K key) {
			// if key is null throw exception
			if (key == null)
				throw new InvalidParameterException("Cannot remove null key");

			if (key.equals(_key)) {

				// node is a leaf, can be nulled out
				if (_left == null && _right == null)
					return null;

				// if node has one child, return that child
				if (_left == null && _right != null)
					return _right;

				if (_left != null && _right == null)
					return _left;

				// node has two children
				// get minimum node from right child tree
				// set key and val to that of minimum node
				// remove that node from right tree
				BSTNode tracker = _right;
				while (tracker._left != null) {
					tracker = tracker._left;
				}
				_key = tracker.getKey();
				_val = tracker.getValue();
				_right = _right.remove(_key);
				return this;
			}

			if (key.compareTo(_key) < 0) {
				_left = _left.remove(key);
			} else {
				_right = _right.remove(key);
			}
			return this;

		}

	}

}
