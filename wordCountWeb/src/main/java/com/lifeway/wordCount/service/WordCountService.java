package com.lifeway.wordCount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeway.wordCount.controller.WordCountController;
import com.lifeway.wordCount.dto.WordCountDTO;
import com.lifeway.wordCount.dto.WordCountRequestDTO;
import com.lifeway.wordCount.dto.WordCountResponseDTO;
import com.lifeway.wordCount.exceptions.IDAlreadyUsedException;

import lombok.extern.slf4j.Slf4j;

import static com.lifeway.wordCount.util.SanitizerUtil.sanitizeString;

@Service
@RequestMapping("/api/words")
@Slf4j
public class WordCountService
{
	@Autowired
	private WordCountController wordCountController;

	@PostMapping("/count")
	public ResponseEntity<WordCountResponseDTO> countWords(@RequestBody WordCountRequestDTO request)
	{
		WordCountDTO wordCountDTO = new WordCountDTO();
		wordCountDTO.setId(sanitizeString(request.getId()));
		wordCountDTO.setMessage(sanitizeString(request.getMessage()));
		
		WordCountResponseDTO response = new WordCountResponseDTO();
		
		log.info("countWords: Args {}", wordCountDTO.toString());
		
		try
		{
			Integer numberOfWordsCounted = wordCountController.countWords(wordCountDTO);
			
			response.setCount(numberOfWordsCounted);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch (IDAlreadyUsedException idaue)
		{
			log.info("Found id already in use, ignoring message: {}", wordCountDTO.toString());
			//We need to respond, but we are ignoring the request and returning nothing
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e)
		{
			log.info("Caught unexpected error", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
