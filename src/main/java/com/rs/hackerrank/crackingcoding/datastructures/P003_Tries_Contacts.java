package com.rs.hackerrank.crackingcoding.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by rsharma on 6/2/2017.
 */
public class P003_Tries_Contacts {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream("input/P003_Tries_Contacts-Input.txt"));
		int num = scanner.nextInt();
		Trie trie = new Trie();
		for (int i = 0; i < num; i++) {
			String operation = scanner.next();
			String contact   = scanner.next();
			if (operation.equals("add")) {
				trie.add(contact);
				//System.out.println(trie.root);
			} else if (operation.equals("find")) {
				System.out.println(trie.find(contact));
			}
		}
		scanner.close();
	}
}


class Trie{

	TrieNode root = new TrieNode();

	Trie() {}

	Trie(String[] words){
		for(String word: words){
			add(word);
		}
	}

	public void add(String word){
		TrieNode current = root;
		for(int i = 0 ; i < word.length() ; i++){
			Character ch = word.charAt(i);
			current.putChildifAbsent(ch);
			current = current.getChild(ch);
			current.size++;
		}
	}

	public int find(String prefix) {
		TrieNode curr = root;
		for (int i = 0; i < prefix.length(); i++) {
			Character ch = prefix.charAt(i);
			curr = curr.getChild(ch);
			if (curr == null) {
				return 0;
			}
		}
		return curr.size;
	}
}

class TrieNode {
	Map<Character , TrieNode> children = new HashMap<>();
	int size;

	public void putChildifAbsent(char ch){
		children.putIfAbsent(ch , new TrieNode());
	}

	public TrieNode getChild(char ch){
		return children.get(ch);
	}

	@Override
	public String toString(){
		return children + " , " + size;
	}
}

