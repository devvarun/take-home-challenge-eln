package com.labforward.eln.util.endpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labforward.eln.model.DataUtilResModel;
import com.labforward.eln.util.StringUtils;

/**
 * REST controller for performing various operations on the data
 * @author Dev
 *
 */
@Controller
public class DataUtilsControllerEndpoint {
	
	@GetMapping("/test")
	@ResponseBody
	public DataUtilResModel test(){
		return new DataUtilResModel();
	}

	@GetMapping("/eln/api/analyseKeyword")
	@ResponseBody
	public DataUtilResModel analyseKeyword(@RequestParam(name="entry_text", required=true, defaultValue="") String entryTxt, 
			@RequestParam(name="keyword", required=true, defaultValue="") String keyword){
		
		List<String> words = StringUtils.getWords(entryTxt);
		
		int frequency = 0;
		Set<String> similarWords = new HashSet<>();
		for(String word : words){
			if(keyword.equals(word))
				frequency = frequency + 1;
			else { //Calculate distance 
				int distance = StringUtils.calculateLevenshteinDistance(keyword, word);
				if(distance == 1)
					similarWords.add(word);
			}
		}
		
		return new DataUtilResModel(keyword, new ArrayList<String>(similarWords), frequency);
	}
}
