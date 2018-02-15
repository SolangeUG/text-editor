package application;

import java.io.InputStream;
import java.util.Objects;
import java.util.Random;


public class LaunchClass {
	
	private InputStream dictStream =
			Objects.requireNonNull(LaunchClass.class.getClassLoader()
					.getResourceAsStream("data/dict.txt"));
	
	LaunchClass() {
		super();
	}
	
	public document.Document getDocument(String text) {
		// Change this to BasicDocument(text) for week 1 only
		return new document.EfficientDocument(text);
	}
	
	public textgen.MarkovTextGenerator getMTG() {
		return new textgen.MarkovTextGeneratorLoL(new Random());
	}
	
	public spelling.WordPath getWordPath() {
		return new spelling.WPTree();
	}
	
    public spelling.AutoComplete getAutoComplete() {
        spelling.AutoCompleteDictionaryTrie tr = new spelling.AutoCompleteDictionaryTrie();
		spelling.DictionaryLoader.loadDictionary(tr, dictStream);
        return tr;
    }
    
    public spelling.Dictionary getDictionary() {
        spelling.Dictionary d = new spelling.DictionaryBST();
		spelling.DictionaryLoader.loadDictionary(d, dictStream);
    	return d;
    }
    
    public spelling.SpellingSuggest getSpellingSuggest(spelling.Dictionary dic) {
    	//return new spelling.SpellingSuggestNW(new spelling.NearbyWords(dic));
    	return new spelling.NearbyWords(dic);
    
    }
}
