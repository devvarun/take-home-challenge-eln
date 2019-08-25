package com.labforward.eln.util.endpoint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/eln/api/analyseKeyword")
	@ResponseBody
	public DataUtilResModel analyseKeyword(@RequestBody String jsonReq) throws JSONException{
		
		//Extract data from the json object received 
		JSONObject jsonObj = new JSONObject(jsonReq);
		String keyword = (String)jsonObj.get("keyword");
		String entryTxt = (String)jsonObj.get("entry_text");
		
		//Get the list of words
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
