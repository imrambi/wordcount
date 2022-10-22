package com.lifeway.wordCount.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;

import com.lifeway.wordCount.dto.WordCountDTO;
import com.lifeway.wordCount.exceptions.IDAlreadyUsedException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WordCountController
{

	private Set<String> idsInUse = new HashSet<>();
	private AtomicInteger currentWordCount = new AtomicInteger(0);
	
	public Integer countWords(WordCountDTO wordCountDTO) throws IDAlreadyUsedException
	{
		if (idsInUse.contains(wordCountDTO.getId()))
		{
			throw new IDAlreadyUsedException(String.format("ID %s has already been used.", wordCountDTO.getId()));
		}
		
		Integer wordCount = countPotentialWords(wordCountDTO.getMessage());
		
		idsInUse.add(wordCountDTO.getId());
		
		return currentWordCount.addAndGet(wordCount);
	}

	protected Integer countPotentialWords(String message)
	{
		if (StringUtils.isBlank(message))
		{
			return 0;
		}
		
		log.info("Counting words in message: {}", message);
		
		//Swap whitespace characters with a pipe to make it easy to split
		String replacedWhiteSpacedString = message.replaceAll("\\s", "|");
		
		
		String[] wordArray = replacedWhiteSpacedString.split("\\|",-1);
		
		List<String> validWords = new ArrayList<>();
		
		for (String potentialWord : wordArray)
		{
			if (StringUtils.isBlank(potentialWord))
			{
				continue;
			}
			
			//We only want words that have letters a-z and A-Z
			if (potentialWord.matches("[A-Za-z]+[!-/:-;\\?]?"))
			{
				log.debug("Found what seems to be a valid word: {}", potentialWord);
				validWords.add(potentialWord);
			}
			else
			{
				log.info("Invalid word: {}", potentialWord);
			}
		}
		
		return validWords.size();
	}

}
