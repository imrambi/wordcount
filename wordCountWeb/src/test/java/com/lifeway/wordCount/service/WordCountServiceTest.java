package com.lifeway.wordCount.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.lifeway.wordCount.controller.WordCountController;
import com.lifeway.wordCount.dto.WordCountRequestDTO;
import com.lifeway.wordCount.dto.WordCountResponseDTO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ContextConfiguration(classes= {WordCountService.class, WordCountController.class})
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class WordCountServiceTest
{
	
	@Autowired
	private WordCountService service;

	@Test
	@Order(1)
	public void testCountPotentialtWords_BlankString()
	{
		WordCountRequestDTO request = new WordCountRequestDTO("1","");
		
		ResponseEntity<WordCountResponseDTO> response = service.countWords(request);
		
		WordCountResponseDTO wordCount = response.getBody();
		assertNotNull(wordCount);
		assertEquals(0, wordCount.getCount());
	}
	
	@Test
	@Order(2)
	public void testCountPotentialtWords_DupId()
	{
		WordCountRequestDTO request = new WordCountRequestDTO("1","Hello World!");
		
		ResponseEntity<WordCountResponseDTO> response = service.countWords(request);
		
		WordCountResponseDTO wordCount = response.getBody();
		
		assertNull(wordCount);
	}
	
	@Test
	@Order(3)
	public void testCountPotentialtWords_30Word()
	{
		String verse = "You, God, are my God,"
				+ "    earnestly I seek you; "
				+ " I thirst for you,"
				+ "    my whole being longs for you,"
				+ " in a dry and parched land"
				+ "    where there is no water.";
		WordCountRequestDTO request = new WordCountRequestDTO("2",verse);
		
		ResponseEntity<WordCountResponseDTO> response = service.countWords(request);
		
		WordCountResponseDTO wordCount = response.getBody();
		assertNotNull(wordCount);
		assertEquals(30, wordCount.getCount());
	}
	
	@Test
	@Order(3)
	public void testCountPotentialtWords_73Word_plus_30()
	{
		//The below text was copied and pasted and has some non-printable characters.
		String verse = "On the twenty-third day of the third month—that is,"
				+ " the month Sivan—the royal scribes were summoned."
				+ " Everything was written exactly as Mordecai commanded for the Jews,"
				+ " to the satraps, the governors, and the officials of the 127 provinces from India to Cush."
				+ " The edict was written for each province in its own script,"
				+ " for each ethnic group in its own language, and to the Jews in their own script and language.";
		WordCountRequestDTO request = new WordCountRequestDTO("3",verse);
		
		ResponseEntity<WordCountResponseDTO> response = service.countWords(request);
		
		WordCountResponseDTO wordCount = response.getBody();
		assertNotNull(wordCount);
		assertEquals(103, wordCount.getCount());
	}

}
